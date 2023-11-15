package gazua.models.file;

import gazua.commons.MemberUtil;
import gazua.commons.exceptions.AuthorizationException;
import gazua.entities.FileInfo;
import gazua.entities.Member;
import gazua.repositories.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
/**
 * 파일 삭제를 수행하는 서비스 클래스.
 */
public class FileDeleteService {
    private final MemberUtil memberUtil; // 회원 관련 유틸리티 클래스
    private final FileInfoService infoService; // 파일 정보 조회 서비스
    private final FileInfoRepository repository; // 파일 정보 저장소

    /**
     * 파일을 삭제하는 메소드.
     * @param id 삭제할 파일의 ID
     */
    public void delete(Long id) {
        FileInfo item = infoService.get(id); // 주어진 ID에 해당하는 파일 정보 조회

        /** 파일 삭제 권한 체크 S */
        String createdBy = item.getCreatedBy(); // 파일 업로드한 사용자 아이디
        Member member = memberUtil.getMember(); // 현재 로그인한 회원 정보 조회

        // 파일을 업로드한 사용자와 현재 로그인한 회원이 같은지, 또는 관리자 권한인지 확인
        if (createdBy != null && !createdBy.isBlank() && !memberUtil.isAdmin()
                && (!memberUtil.isLogin()
                || (memberUtil.isLogin() && !member.getEmail().equals(createdBy)))) {

            throw new AuthorizationException("UnAuthorized.delete.file");
        }
        /** 파일 삭제 권한 체크 E */

        /**
         * 1. 파일 삭제
         * 2. thumbs 삭제
         * 3. 파일 정보 삭제
         */

        File file = new File(item.getFilePath());
        if (file.exists()) file.delete(); // 파일 삭제

        String[] thumbsPath = item.getThumbsPath();
        if (thumbsPath != null && thumbsPath.length > 0) {
            // 썸네일 이미지 파일들을 삭제
            Arrays.stream(thumbsPath).forEach(p -> {
                File thumb = new File(p);
                if (thumb.exists()) thumb.delete();
            });
        }

        repository.delete(item); // 파일 정보 삭제
        repository.flush();
    }
}