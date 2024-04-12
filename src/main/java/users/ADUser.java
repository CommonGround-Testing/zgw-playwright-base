package users;

public abstract class ADUser implements User{

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract String getTotpSecret();
}
