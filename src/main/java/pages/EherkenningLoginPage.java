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
    public final Locator buttonVerder;
    // Logius velden
    public final Locator inputBsn;
    public final Locator buttonZoek;
    public final Locator linkGaVerder;

    public EherkenningLoginPage(Page page) {
        dropdownSelectIdentityProvider = page.locator("#identity_provider");
        buttonConfirmIdentityProviderSelection = page.locator("//*[@name='commit']");
        textfieldUsername = page.locator("#account_username");
        textfieldPassword = page.locator("#account_password");
        buttonContinue = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("Continue|Ga verder")));
        buttonLogin = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("Log in|Inloggen")));
        buttonVerder = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("Continue|Verder")));
        inputBsn = page.locator("#bsn-input");
        buttonZoek = page.locator("#search-submit");
        linkGaVerder = page.getByRole(AriaRole.LINK).getByText("Ga verder");
    }
}
