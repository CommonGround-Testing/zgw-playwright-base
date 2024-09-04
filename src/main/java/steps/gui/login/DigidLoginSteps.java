package steps.gui.login;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.DigidLoginPage;
import users.User;

public class DigidLoginSteps extends LoginSteps {

    protected final DigidLoginPage digidLoginPage;
    private final Locator inloggenDigidLink;

    public DigidLoginSteps(Page page) {
        digidLoginPage = new DigidLoginPage(page);
        inloggenDigidLink = page.locator("//*[@id='oidc-digid']");
    }

    public void navigate(){
        digidLoginPage.navigate();
        inloggenDigidLink.click();
    }

    /**
     * Login met digid niveau Midden
     *
     * @param user User
     */
    public void login_via_digid(User user) {
        digidLoginPage.linkSelectAuthenticationWithTrustLevel.click();
        digidLoginPage.dropdownBetrouwbaarheidsniveau.selectOption("Substantieel");
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
