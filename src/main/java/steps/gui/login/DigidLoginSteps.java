package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.DigidLoginPage;

public class DigidLoginSteps {

    private final DigidLoginPage digidLoginPage;
    private final Page page;

    public DigidLoginSteps(Page page) {
        this.page = page;
        this.digidLoginPage = new DigidLoginPage(page);
    }

    public void login_als(String username, String password) {
        digidLoginPage.linkSelectAuthenticationWithTrustLevel.click();
        digidLoginPage.dropdownBetrouwbaarheidsniveau.selectOption("Midden");
        digidLoginPage.textfieldUsername.fill(username);
        digidLoginPage.textfieldPassword.fill(password);
        digidLoginPage.buttonDoLogin.click();
    }

    public void selecteer_machtiginggever() {
        digidLoginPage.buttonSelecteerMachtiginggever.click();
    }
}
