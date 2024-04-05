package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADLoginPage {

    protected Page page;
    public final Locator gebruikersnaamInput;
    public final Locator wachtwoordInput;
    public final Locator volgendeInput;
    public final Locator aanmeldenInput;
    public final Locator kanAuthAppNietGebruikenLink;
    public final Locator gebruikVerificatieCode;
    public final Locator codeInput;
    public final Locator verifierenInput;

    public void navigate(){
        page.navigate("");
    }

    public ADLoginPage(Page page) {
        this.page = page;
        gebruikersnaamInput = page.locator("//input[@type='email']");
        wachtwoordInput = page.locator("//input[@name='passwd']");
        volgendeInput = page.locator("//input[@value='Next']");
        aanmeldenInput = page.locator("//input[@value='Sign in']");
        kanAuthAppNietGebruikenLink = page.locator("//*[@id='signInAnotherWay']");
        gebruikVerificatieCode = page.locator("//div[contains(text(),'Use a verification code')]");
        codeInput = page.locator("//input[@name='otc']");
        verifierenInput = page.locator("//input[@value='Verify']");
    }
}
