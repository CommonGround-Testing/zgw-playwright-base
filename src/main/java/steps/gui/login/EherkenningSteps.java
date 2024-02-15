package steps.gui.login;

import com.microsoft.playwright.Page;
import pages.EherkenningLoginPage;

public class EherkenningSteps {

    private final Page page;
    private final EherkenningLoginPage eherkenningPage;

    public EherkenningSteps(Page page) {
        this.page = page;
        this.eherkenningPage = new EherkenningLoginPage(page);
    }

    public void login_als(String username, String password) {

        eherkenningPage.dropdownSelectIdentityProvider.selectOption("Digidentity AD 1.13 (preproduction)");
        eherkenningPage.buttonConfirmIdentityProviderSelection.click();

        eherkenningPage.textfieldUsername.fill(username);
        eherkenningPage.buttonContinue.click();
        eherkenningPage.textfieldPassword.fill(password);
        eherkenningPage.buttonLogin.click();

        // TODO: verifieren dat dit op TEST ook goed werkt.
        //  Momenteel staat daar nog een extra scherm tussen dat er niet hoort, op ACC gaat het wel goed.
        eherkenningPage.buttonGaVerder.click();
    }
}
