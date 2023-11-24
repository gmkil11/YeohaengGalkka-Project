package gazua.controllers.admins.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MemberAdminForm {
    private List<Long> userNos;
}
