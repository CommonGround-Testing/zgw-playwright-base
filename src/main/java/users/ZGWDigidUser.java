package users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ZGWDigidUser implements User {
    private final String username;
    private final String password;
    private final String nationaliteit;
    private final String name;
    private final String email;
    private final String initials;
    private final String lastName;
    private final String bsn;
    private final String addressZipcode;
    private final int addressHouseNumber;
    private final String addressHouseLetter;
    private final String addressStreet;
    private final String addressCity;

    @Builder
    ZGWDigidUser(String username,
                 String password,
                 String nationaliteit,
                 String name,
                 String email,
                 String initials,
                 String lastName,
                 String bsn,
                 String addressZipcode,
                 int addressHouseNumber,
                 String addressHouseLetter,
                 String addressStreet,
                 String addressCity) {
        this.username = username;
        this.password = password;
        this.nationaliteit = nationaliteit;
        this.name = name;
        this.email = email;
        this.initials = initials;
        this.lastName = lastName;
        this.bsn = bsn;
        this.addressZipcode = addressZipcode;
        this.addressHouseNumber = addressHouseNumber;
        this.addressHouseLetter = addressHouseLetter;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
    }

    @Override
    public String getTotpSecret() {
        return "";
    }
}
