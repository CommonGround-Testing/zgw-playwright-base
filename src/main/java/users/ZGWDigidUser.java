package users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ZGWDigidUser extends ZGWUser {
    private final String nationaliteit;
    private final String name;

    @Builder
    ZGWDigidUser(String username, String password, String nationaliteit, String name) {
        super(username, password);
        this.nationaliteit = nationaliteit;
        this.name = name;
    }
}
