package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.EherkenningLoginPage;
import users.User;

public class EherkenningSteps extends LoginSteps {

    private final EherkenningLoginPage eherkenningPage;

    public EherkenningSteps(Page page) {
        this.eherkenningPage = new EherkenningLoginPage(page);
    }

    /**
     * Login met eHerkenning
     *
     * @param user User
     */
    public void login_via_eherkenning(User user) {

        eherkenningPage.dropdownSelectIdentityProvider.selectOption("Digidentity AD 1.13 (preproduction)");
        eherkenningPage.buttonConfirmIdentityProviderSelection.click();

        eherkenningPage.textfieldUsername.fill(user.getUsername());
        eherkenningPage.buttonContinue.click();
        eherkenningPage.textfieldPassword.fill(user.getPassword());
        eherkenningPage.buttonLogin.click();
        eherkenningPage.buttonVerder.click();
    }
}
