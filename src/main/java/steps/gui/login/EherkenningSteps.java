package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.EherkenningLoginPage;
import users.User;

public class EherkenningSteps implements LoginSteps {

    private final EherkenningLoginPage eherkenningPage;

    public EherkenningSteps(Page page) {
        this.eherkenningPage = new EherkenningLoginPage(page);
    }

    /**
     * Login met eHerkenning
     *
     * @param user User
     */
    public void Login(User user) {

        eherkenningPage.dropdownSelectIdentityProvider.selectOption("Digidentity AD 1.13 (preproduction)");
        eherkenningPage.buttonConfirmIdentityProviderSelection.click();

        eherkenningPage.textfieldUsername.fill(user.getUsername());
        eherkenningPage.buttonContinue.click();
        eherkenningPage.textfieldPassword.fill(user.getPassword());
        eherkenningPage.buttonLogin.click();

        // TODO: verifieren dat dit op TEST ook goed werkt.
        //  Momenteel staat daar nog een extra scherm tussen dat er niet hoort, op ACC gaat het wel goed.
        eherkenningPage.buttonGaVerder.click();
    }
}
