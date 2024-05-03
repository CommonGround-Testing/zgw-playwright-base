package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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
        this.headerIngelogdeNamen = page.locator("//h3[contains(text(), 'Gemachtigd voor')]");
        this.buttonLogout = page.locator("//span[text()='Uitloggen']");
        this.inloggenDigidMachtigenLink = page.locator("//*[@id='digid-machtigen']");
        this.inloggeneHerkenningLink = page.locator("//section[contains(.,'Als bedrijf of instelling')]//a");
        this.inloggeneHerkenningMachtigenLink = page.locator("//*[@id='eherkenning-bewindvoering']");
        this.gebruikersMenuBurgerButton = page.locator("//div[contains(@class,'denhaag-header__actions-action-user-menu')]");
        this.gebruikersMenuOndernemerButton = page.locator("//button[contains(.,'Welkom')]");
        this.menu = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Welkom"));
        this.languageButton = page.locator("//div[contains(@class,'denhaag-header__actions-action-language-switcher')]/button");
        this.disabledLanguageOption = page.locator("//ul/li/button[not(contains(@class, 'active'))]");
    }

    public Locator getMenuButtonByText(boolean isNL) {
        final var buttonText = isNL
                ? "Welkom"
                : "Welcome";
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonText));
    }
}
