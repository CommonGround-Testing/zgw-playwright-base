package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

public class DigidLoginPage {

    protected final Page page;
    public final Locator linkSelectAuthenticationWithTrustLevel;
    public final Locator dropdownBetrouwbaarheidsniveau;
    public final Locator textfieldUsername;
    public final Locator textfieldPassword;
    public final Locator buttonDoLogin;
    public final Locator buttonSelecteerMachtiginggever;

    /**
     * Navigate to baseurl
     *
     * @return resonse
     */
    public Response navigate() {
        page.navigate("");
    }

    public DigidLoginPage(Page page) {
        this.page = page;
        linkSelectAuthenticationWithTrustLevel = page.locator("//*[@id='authentication_type_account_test']");
        dropdownBetrouwbaarheidsniveau = page.locator("//*[@id='authentication_test_zekerheidsniveau']");
        textfieldUsername = page.locator("//*[@id='authentication_username']");
        textfieldPassword = page.locator("//*[@id='authentication_password']");
        buttonDoLogin = page.locator("//button[@type='submit']");
        buttonSelecteerMachtiginggever = page.locator("//app-mandates-table//span[text()='Selecteer']");
    }
}
