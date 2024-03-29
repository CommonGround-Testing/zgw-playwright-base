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
        linkSelectAuthenticationWithTrustLevel = page.locator("//*[@id='authentication_type_account_test']");
        dropdownBetrouwbaarheidsniveau = page.locator("//*[@id='authentication_test_zekerheidsniveau']");
        textfieldUsername = page.locator("//*[@id='authentication_username']");
        textfieldPassword = page.locator("//*[@id='authentication_password']");
        buttonDoLogin = page.locator("//button[@type='submit']");
        buttonSelecteerMachtiginggever = page.locator("//app-mandates-table//span[text()='Selecteer']");
    }
}
