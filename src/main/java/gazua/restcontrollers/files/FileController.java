package gazua.restcontrollers.files;

import gazua.commons.rest.JSONData;
import gazua.entities.FileInfo;
import gazua.models.file.FileDeleteService;
import gazua.models.file.FileDownloadService;
import gazua.models.file.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 파일 업로드, 다운로드, 삭제와 관련된 요청을 처리하는 REST 컨트롤러
 */
@RestController("restFileController")
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService uploadService;
    private final FileDownloadService downloadService;
    private final FileDeleteService deleteService;

    /**
     * 파일 업로드 처리 요청을 처리하는 메서드
     * @param files    업로드되는 파일들
     * @param gid      파일 그룹 식별자
     * @param location 파일이 위치하는 곳
     * @return 업로드된 파일 정보를 담은 JSON 응답 데이터
     */
    @PostMapping("/upload")
    public ResponseEntity<JSONData<List<FileInfo>>> uploadPs(MultipartFile[] files, String gid, String location) {
        List<FileInfo> items = uploadService.upload(files, gid, location);

        JSONData<List<FileInfo>> data = new JSONData<>(items);

        return ResponseEntity.ok(data);
    }

    /**
     * 파일 다운로드 요청을 처리하는 메서드
     * @param id 다운로드할 파일의 고유 번호
     */
    @RequestMapping("/download/{id}")
    public void download(@PathVariable Long id) {
        downloadService.download(id);
    }

    /**
     * 파일 삭제 요청을 처리하는 메서드
     * @param id 삭제할 파일의 고유 번호
     * @return 삭제된 파일의 고유 번호를 담은 JSON 응답 데이터
     */
    /*@RequestMapping("/delete/{id}")
    public ResponseEntity<JSONData<Long>> delete(Long id) {
        deleteService.delete(id);

        JSONData<Long> data = new JSONData<>();
        data.setSuccess(true);
        data.setData(id);

        return ResponseEntity.ok(data);
    }*/
}
