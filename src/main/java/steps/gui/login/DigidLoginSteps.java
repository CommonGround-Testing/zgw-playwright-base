package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.DigidLoginPage;

public class DigidLoginSteps {

    private final DigidLoginPage digidLoginPage;

    public DigidLoginSteps(Page page) {
        digidLoginPage = new DigidLoginPage(page);
    }

    /**
     * Login met digid niveau Midden
     *
     * @param username
     * @param password
     */
    public void login_als(String username, String password) {
        digidLoginPage.linkSelectAuthenticationWithTrustLevel.click();
        digidLoginPage.dropdownBetrouwbaarheidsniveau.selectOption("Midden");
        digidLoginPage.textfieldUsername.fill(username);
        digidLoginPage.textfieldPassword.fill(password);
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
