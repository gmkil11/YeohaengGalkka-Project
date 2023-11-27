package gazua.models.file;

import gazua.entities.FileInfo;
import gazua.repositories.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileInfoRepository repository;
    private final FileInfoService infoService;

    // 썸네일 생성 사이즈
    private int width = 300;
    private int height = 250;

    /**
     * 파일을 업로드하고 관련 정보를 저장
     * @param files 업로드할 파일 배열
     * @param gid 파일 그룹 식별자
     * @param location 파일 저장 위치
     * @return 업로드된 파일 정보 리스트
     */
    public List<FileInfo> upload(MultipartFile[] files, String gid, String location) {
        gid = gid == null || gid.isBlank() ? UUID.randomUUID().toString() : gid;

        List<FileInfo> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            // 파일 정보 저장 처리 시작
            FileInfo item = FileInfo.builder()
                    .fileName(fileName)
                    .fileType(fileType)
                    .extension(extension)
                    .gid(gid)
                    .location(location)
                    .createdDt(LocalDateTime.now())
                    .build();

            repository.saveAndFlush(item);

            infoService.addFileInfo(item); // 파일 경로, 파일 URL 등의 추가 정보
            // 파일 정보 저장 처리 종료

            // 파일 업로드 처리 시작
            try {
                File _file = new File(item.getFilePath());
                file.transferTo(_file);

                // 썸네일 생성 처리 시작
                if (fileType.indexOf("image") != -1) { // 이미지 형식 파일
                    String thumbPath = infoService.getThumbPath(item.getId(), item.getExtension(), width, height);
                    String thumbUrl = infoService.getThumbUrl(item.getId(), item.getExtension(), width, height);

                    item.setThumbsPath(new String[] { thumbPath });
                    item.setThumbsUrl(new String[] { thumbUrl });

                    File _thumb = new File(thumbPath);
                    Thumbnails.of(_file)
                            .size(width, height)
                            .toFile(_thumb);
                }
                // 썸네일 생성 처리 종료

                uploadedFiles.add(item);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // 파일 업로드 처리 종료
        }

        return uploadedFiles;
    }

    /**
     * 파일을 업로드하고 관련 정보를 저장
     * @param files 업로드할 파일 배열
     * @param gid 파일 그룹 식별자
     * @return 업로드된 파일 정보 리스트
     */
    public List<FileInfo> upload(MultipartFile[] files, String gid) {
        return upload(files, gid, null);
    }

    /**
     * 파일을 업로드하고 관련 정보를 저장
     * @param files 업로드할 파일 배열
     * @return 업로드된 파일 정보 리스트
     */
    public List<FileInfo> upload(MultipartFile[] files) {
        return upload(files, null);
    }
}