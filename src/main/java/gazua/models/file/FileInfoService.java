package gazua.models.file;

import gazua.entities.FileInfo;
import gazua.repositories.FileInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 파일 정보 관리 서비스 클래스.
 * 파일 정보 조회, 리스트 반환, 썸네일 경로 및 URL 생성 등을 제공.
 */
@Service
@RequiredArgsConstructor
public class FileInfoService {
    @Value("${file.upload.path}")
    private String uploadPath; // 파일 업로드 경로

    @Value("${file.upload.url}")
    private String uploadUrl; // 파일 업로드 url


    private final HttpServletRequest request; // HTTP 요청 객체
    private final FileInfoRepository repository; // 파일 정보 레포지토리

    /**
     * 파일 등록 번호로 개별 조회
     * 파일이 없을 경우 FileNotFoundException을 던짐.
     * @param id 파일 등록 번호
     * @return 파일 정보
     */
    public FileInfo get(Long id) {

        FileInfo item = repository.findById(id).orElseThrow(FileNotFoundException::new);

        addFileInfo(item);

        return item;
    }

    /**
     * 주어진 옵션에 맞게 파일 정보 리스트를 검색.
     * @param opts 파일 정보 검색 옵션 객체
     * @return 파일 정보 리스트
     */
    public List<FileInfo> getList(Options opts) {
        // 파일 정보 레포지토리에서 옵션에 맞는 파일 정보들을 가져옴.
        List<FileInfo> items = repository.getFiles(opts.getGid(), opts.getLocation(), opts.getMode().name());

        // 각 파일 정보에 추가 정보를 설정.
        items.stream().forEach(this::addFileInfo);

        return items;
    }

    /**
     * 모든 그룹과 위치에 대한 파일 정보를 검색.
     * @param gid 그룹 ID
     * @param location 위치
     * @return 파일 정보 리스트
     */
    public List<FileInfo> getListAll(String gid, String location) {
        // 모든 그룹과 위치에 대한 파일 정보를 검색하기 위한 옵션을 생성.
        Options opts = Options.builder()
                .gid(gid)
                .location(location)
                .mode(SearchMode.ALL)
                .build();
        // getList 메서드를 사용하여 파일 정보 리스트를 검색하고 반환.
        return getList(opts);
    }

    /**
     * 특정 그룹에 속한 모든 파일 정보를 검색.
     * @param gid 그룹 ID
     * @return 파일 정보 리스트
     */
    public List<FileInfo> getListAll(String gid) {
        // 특정 그룹에 속한 모든 파일 정보를 검색하기 위한 옵션을 생성.
        return getListAll(gid, null);
    }

    /**
     * 완료된 파일 정보를 검색합니다.
     * @param gid 그룹 ID
     * @param location 위치
     * @return 파일 정보 리스트
     */
    public List<FileInfo> getListDone(String gid, String location) {
        // 완료된 파일 정보를 검색하기 위한 옵션을 생성.
        Options opts = Options.builder()
                .gid(gid)
                .location(location)
                .mode(SearchMode.DONE)
                .build();
        // getList 메서드를 사용하여 완료된 파일 정보 리스트를 검색하고 반환.
        return getList(opts);
    }

    /**
     * 특정 그룹에 속한 완료된 파일 정보를 검색.
     * @param gid 그룹 ID
     * @return 파일 정보 리스트
     */
    public List<FileInfo> getListDone(String gid) {
        // 특정 그룹에 속한 완료된 파일 정보를 검색하기 위한 옵션을 생성.
        return getListDone(gid, null);
    }

    /**
     * - 파일 업로드 서버 경로(filePath)
     * - 파일 서버 접속 URL (fileUrl)
     * - 썸네일 경로(thumbsPath), 썸네일 URL(thumbsUrl)
     * 주어진 FileInfo 객체에 추가 정보를 설정
     */
    public void addFileInfo(FileInfo item) {
        long id = item.getId();
        String extension = item.getExtension();
        String fileName = getFileName(id, extension);

        long folder = id % 10L;

        // 파일 업로드 서버 경로
        String fileDir = uploadPath + folder;
        String filePath = fileDir + "/" + fileName;

        File _fileDir = new File(fileDir);
        if (!_fileDir.exists()) {
            _fileDir.mkdir();
        }

        // 파일 서버 접속 URL (fileUrl)
        String fileUrl = request.getContextPath() + uploadUrl + folder + "/" + fileName;

        // 썸네일 경로(thumbsPath)
        String thumbPath = getUploadThumbPath() + folder;
        File thumbDir = new File(thumbPath);
        if (!thumbDir.exists()) {
            thumbDir.mkdirs();
        }

        String[] thumbsPath = Arrays.stream(thumbDir.list((dir, name) -> name.indexOf("_" + fileName) != -1))
                .map(n -> thumbPath + "/" + n).toArray(String[]::new);


        // 썸네일 URL(thumbsUrl)
        String[] thumbsUrl = Arrays.stream(thumbsPath)
                .map(s -> s.replace(uploadPath, request.getContextPath() + uploadUrl)).toArray(String[]::new);

        item.setFilePath(filePath);
        item.setFileUrl(fileUrl);
        item.setThumbsPath(thumbsPath);
        item.setThumbsUrl(thumbsUrl);
    }

    /**
     * 파일 업로드 썸네일 경로를 반환
     */
    private String getUploadThumbPath() {
        return uploadPath + "thumbs/";
    }

    /**
     * 파일 업로드 썸네일 URL을 반환
     */
    private String getUploadThumbUrl() {
        return uploadUrl + "thumbs/";
    }

    /**
     * 지정한 크기의 썸네일 URL을 생성하여 반환합니다.
     * @param id        파일 등록 번호
     * @param extension 파일 확장자
     * @param width     썸네일 너비
     * @param height    썸네일 높이
     * @return 생성된 썸네일 URL
     */
    public String getThumbUrl(long id, String extension, int width, int height) {
        long folder = id % 10L;
        return String.format(getUploadThumbUrl() + folder + "/%d_%d_%s", width, height, getFileName(id, extension));
    }

    /**
     * 지정한 크기의 썸네일 경로를 생성하여 반환합니다.
     * @param id        파일 등록 번호
     * @param extension 파일 확장자
     * @param width     썸네일 너비
     * @param height    썸네일 높이
     * @return 생성된 썸네일 경로
     */
    public String getThumbPath(long id, String extension, int width, int height) {
        long folder = id % 10L;
        return String.format(getUploadThumbPath() + folder + "/%d_%d_%s", width, height, getFileName(id, extension));
    }

    /**
     * 파일 이름을 생성합니다.
     * @param id        파일 등록 번호
     * @param extension 파일 확장자
     * @return 생성된 파일 이름
     */
    private String getFileName(long id, String extension) {
        return extension == null || extension.isBlank() ? "" + id : id + "." + extension;
    }




    //파일 정보 조회 옵션을 나타내는 내부 정적 클래스
    @Data
    @Builder
    static class Options {
        private String gid; // 파일의 그룹 식별자 (gid)
        private String location; // 파일 저장 위치

        // 파일 검색 모드,기본값: 모든 파일 검색
        private SearchMode mode = SearchMode.ALL;
    }

    //파일 검색 모드를 정의하는 enum
    static enum SearchMode {
        ALL, // 모든 파일 검색
        DONE, // 완료된 파일 검색
        UNDONE // 미완료된 파일 검색
    }

}
