package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class KlantportaalPage {
    public final Locator inloggenDigidLink;
    public final Locator disabledLanguageOption;
    public final Locator languageButton;
    public final Locator menu;
    public final Locator gebruikersMenuOndernemerButton;
    public final Locator gebruikersMenuBurgerButton;
    public final Locator inloggeneHerkenningMachtigenLink;
    public final Locator inloggeneHerkenningLink;
    public final Locator inloggenDigidMachtigenLink;
    public final Locator buttonLogout;
    public final Locator headerIngelogdeNamen;
    Page page;


    public KlantportaalPage(Page page) {
        this.page = page;
        this.inloggenDigidLink = page.locator("//*[@id='oidc-digid']");
        this.headerIngelogdeNamen = page.locator("//h3[contains(., 'U bent ingelogd namens')]");
        this.buttonLogout = page.locator("//span[text()='Uitloggen']");
        this.inloggenDigidMachtigenLink = page.locator("//*[@id='digid-machtigen']");
        this.inloggeneHerkenningLink = page.locator("//*[@id='eherkenning']");
        this.inloggeneHerkenningMachtigenLink = page.locator("//*[@id='eherkenning-bewindvoering']");
        this.gebruikersMenuBurgerButton = page.locator("//button[contains(.,'Welkom Sierra')]");
        this.gebruikersMenuOndernemerButton = page.locator("//button[contains(.,'Welkom')]");
        this.menu = page.locator("//button[contains(text(), 'Welkom')]");
        this.languageButton = page.locator("//div[contains(@class, 'language-switcher')]/button");
        this.disabledLanguageOption = page.locator("//ul/li/button[not(contains(@class, 'active'))]");
    }
}
