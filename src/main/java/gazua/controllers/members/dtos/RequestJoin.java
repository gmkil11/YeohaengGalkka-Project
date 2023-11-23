package gazua.controllers.members.dtos;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestJoin {
    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String userNm;

    private String mobile;

    @AssertTrue
    boolean agree;
}
