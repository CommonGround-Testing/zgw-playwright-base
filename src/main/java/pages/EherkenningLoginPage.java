package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class EherkenningLoginPage {

    public final Locator dropdownSelectIdentityProvider;
    public final Locator buttonConfirmIdentityProviderSelection;
    public final Locator textfieldUsername;
    public final Locator textfieldPassword;
    public final Locator buttonContinue;
    public final Locator buttonLogin;
    public final Locator buttonGaVerder;

    public EherkenningLoginPage(Page page) {
        this.dropdownSelectIdentityProvider = page.locator("//*[@id='identity_provider']");
        this.buttonConfirmIdentityProviderSelection = page.locator("//*[@name='commit']");
        this.textfieldUsername = page.locator("//*[@id='account_username']");
        this.textfieldPassword = page.locator("//*[@id='account_password']");
        this.buttonContinue = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("Continue|Ga Verder")));
        this.buttonLogin = page.locator("//button[contains(., 'Log in')]");
        this.buttonGaVerder = page.locator("//button[contains(., 'Verder')]");
    }
}
