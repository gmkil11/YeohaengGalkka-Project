package gazua.commons.exceptions;

import gazua.commons.Utils;
import org.springframework.http.HttpStatus;

/**
 * 권한 부여 예외 클래스. 인증이나 권한 문제로 인해 발생하는 예외 처리
 */
public class AuthorizationException extends CommonException {

    /**
     * 인증되지 않은 접근에 대한 예외를 생성합니다.
     */
    public AuthorizationException() {
        super(Utils.getMessage("UnAuthorization", "error"), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 코드를 인자로 받는 생성자.
     * 인증되지 않은 접근에 대한 예외를 생성하며, 코드에 해당하는 오류 메시지를 사용.
     *
     * @param code 오류 코드
     */
    public AuthorizationException(String code) {
        super(Utils.getMessage(code, "error"), HttpStatus.UNAUTHORIZED);
    }
}
