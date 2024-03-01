package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DigidLoginPage {
    public final Locator linkSelectAuthenticationWithTrustLevel;
    public final Locator dropdownBetrouwbaarheidsniveau;
    public final Locator textfieldUsername;
    public final Locator textfieldPassword;
    public final Locator buttonDoLogin;
    public final Locator buttonSelecteerMachtiginggever;

    public DigidLoginPage(Page page) {
        this.linkSelectAuthenticationWithTrustLevel = page.locator("//*[@id='authentication_type_account_test']");
        this.dropdownBetrouwbaarheidsniveau = page.locator("//*[@id='authentication_test_zekerheidsniveau']");
        this.textfieldUsername = page.locator("//*[@id='authentication_username']");
        this.textfieldPassword = page.locator("//*[@id='authentication_password']");
        this.buttonDoLogin = page.locator("//button[@type='submit']");
        this.buttonSelecteerMachtiginggever = page.locator("//app-mandates-table//span[text()='Selecteer']");
    }
}
