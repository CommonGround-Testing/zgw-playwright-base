package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ADLoginPage {

    private final Page page;
    public final Locator gebruikersnaamInput;
    public final Locator wachtwoordInput;
    public final Locator volgendeInput;
    public final Locator aanmeldenInput;
    public final Locator kanAuthAppNietGebruikenLink;
    public final Locator gebruikVerificatieCode;
    public final Locator codeInput;
    public final Locator verifierenInput;

    public ADLoginPage(Page page) {
        this.page = page;
        this.gebruikersnaamInput = page.locator("//input[@type='email']");
        this.wachtwoordInput = page.locator("//input[@name='passwd']");
        this.volgendeInput = page.locator("//input[@value='Volgende']");
        this.aanmeldenInput = page.locator("input[@value='Aanmelden']");
        this.kanAuthAppNietGebruikenLink = page.locator("//*[@id='signInAnotherWay']");
        this.gebruikVerificatieCode = page.locator("//div[contains(text(),'Gebruik een verificatiecode')]");
        this.codeInput = page.locator("//input[@name='otc']");
        this.verifierenInput = page.locator("//input[@value='Verifiëren']");
    }
}
