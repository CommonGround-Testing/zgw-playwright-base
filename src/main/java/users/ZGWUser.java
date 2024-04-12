package users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ZGWUser implements User{

    private String username;

    private String password;

    /**
     * ZGWUser doesn't use the topSecret
     *
     * @return
     */
    @Override
    public String getTotpSecret() {
        return null;
    }
}
