package users;

public interface User {
    public String getUsername();

    public String getPassword();

    public String getTotpSecret();
}
