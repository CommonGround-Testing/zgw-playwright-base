package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.DigidLoginPage;
import users.User;

public class DigidLoginSteps implements LoginSteps {

    private final DigidLoginPage digidLoginPage;

    public DigidLoginSteps(Page page) {
        digidLoginPage = new DigidLoginPage(page);
    }

    /**
     * Login met digid niveau Midden
     *
     * @param user User
     */
    public void Login(User user) {
        digidLoginPage.linkSelectAuthenticationWithTrustLevel.click();
        digidLoginPage.dropdownBetrouwbaarheidsniveau.selectOption("Midden");
        digidLoginPage.textfieldUsername.fill(user.getUsername());
        digidLoginPage.textfieldPassword.fill(user.getPassword());
        digidLoginPage.buttonDoLogin.click();
    }

    /**
     * Klik op de selecteer machtiginggever knop
     *
     */
    public void selecteer_machtiginggever() {
        digidLoginPage.buttonSelecteerMachtiginggever.click();
    }
}
