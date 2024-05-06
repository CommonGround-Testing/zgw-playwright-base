package steps.gui.login;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.EidasLoginPage;
import users.User;

public class EIdasLoginSteps extends LoginSteps {

    protected final EidasLoginPage eidasLoginPage;

    public EIdasLoginSteps(Page page) {
        eidasLoginPage = new EidasLoginPage(page);
    }

    /**
     * Login via eIdas optie
     *
     * @param user User
     */
    public void login_via_eidas(User user) {
        // First eIdas page with country selection
        eidasLoginPage.countryLink.waitFor();
        eidasLoginPage.countryLink.click();
        eidasLoginPage.buttonContinue.click();
        // Second eIdas page with 3 cards (digid, eherkenning and Atlas)
        eidasLoginPage.cardAtlas.waitFor();
        eidasLoginPage.cardAtlas.click();
        // Login page with username & password
        eidasLoginPage.inputUsername.waitFor();
        eidasLoginPage.inputUsername.fill(user.getUsername());
        eidasLoginPage.inputPassword.fill(user.getPassword());
        eidasLoginPage.buttonLogin.click();
        // Last page for submitting
        eidasLoginPage.buttonSubmit.waitFor();
        eidasLoginPage.buttonSubmit.click();
    }
}
