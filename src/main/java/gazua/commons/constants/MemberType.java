package gazua.commons.constants;

import java.util.Arrays;
import java.util.List;

public enum MemberType {
    USER("일반회원"), // 일반 회원
    ADMIN("어드민"); // 관리자

    private final String title;

    MemberType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<String[]> getList() {
        List<String[]> types = Arrays.asList(
                new String[] { USER.name(), USER.title },
                new String[] { ADMIN.name(), ADMIN.title});
        return types;
    }
}
