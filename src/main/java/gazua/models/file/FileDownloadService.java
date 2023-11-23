package gazua.models.file;

import gazua.entities.FileInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 파일 다운로드 서비스를 제공하는 클래스.
 * 지정된 ID를 사용하여 FileInfoService를 통해 파일 정보를 가져온 후,
 * 해당 파일을 읽어서 HTTP 응답 스트림에 쓰는 역할을 수행.
 * 다운로드할 파일의 이름과 형식을 설정하고, 파일의 크기와 캐시 관련 헤더도 설정하여
 * 파일 다운로드를 처리.
 */
@Service
@RequiredArgsConstructor
public class FileDownloadService {

    private final HttpServletResponse response; // HTTP 응답 객체
    private final FileInfoService infoService; // FileInfoService 객체

    public void download(Long id) {

        // 지정된 id를 가지고 FileInfoService를 통해 FileInfo 객체를 가져옴
        FileInfo item = infoService.get(id);

        String filePath = item.getFilePath(); // 파일 경로
        File file = new File(filePath);

        if (!file.exists()) { // 파일이 존재하지 않는 경우
            throw new FileNotFoundException(); // FileNotFoundException 발생
        }

        String fileName = item.getFileName(); // 파일 이름
        try {
            fileName = new String(fileName.getBytes(), "ISO8859_1");
        } catch (UnsupportedEncodingException e) {}

        try(FileInputStream fis = new FileInputStream(file); // 파일을 읽어들이는 FileInputStream
            BufferedInputStream bis = new BufferedInputStream(fis)) { // 버퍼링된 입력 스트림

            OutputStream out = response.getOutputStream(); // 응답 스트림을 얻어옴

            // HTTP 응답 헤더 설정
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName); // 다운로드 설정
            response.setHeader("Content-Type", "application/octet-stream"); // 파일 형식
            response.setHeader("Cache-Control", "must-revalidate"); // 캐시 제어
            response.setHeader("Pragma", "public"); // 프라그마 설정
            response.setIntHeader("Expires", 0); // 캐시 만료
            response.setHeader("Content-Length", "" + file.length()); // 파일 크기 설정

            while(bis.available() > 0) { // 입력 스트림에서 읽을 데이터가 있는 동안 반복
                out.write(bis.read()); // 데이터를 읽고 응답 스트림에 기록
            }

            out.flush(); // 스트림 버퍼 비우기
        } catch (IOException e) {
            e.printStackTrace(); // 입출력 예외 발생 시 예외 정보 출력
        }
    }
}