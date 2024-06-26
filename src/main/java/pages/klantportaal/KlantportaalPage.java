package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class KlantportaalPage {
    public final Locator disabledLanguageOption;
    public final Locator languageButton;
    public final Locator menu;
    public final Locator gebruikersMenuBurgerButton;
    public final Locator inloggenDigidLink;
    public final Locator inloggenDigidMachtigenLink;
    public final Locator inloggeneHerkenningLink;
    public final Locator inloggeneHerkenningMachtigenLink;
    public final Locator inloggeneIdasLink;
    public final Locator buttonLogout;
    Page page;


    public KlantportaalPage(Page page) {
        this.page = page;
        this.inloggenDigidLink = page.locator("//*[@id='oidc-digid']");
        this.inloggenDigidMachtigenLink = page.locator("//*[@id='digid-machtigen']");
        this.inloggeneHerkenningLink = page.locator("//section[contains(.,'Als bedrijf of instelling')]//a");
        this.inloggeneHerkenningMachtigenLink = page.locator("//*[@id='eherkenning-bewindvoering']");
        this.inloggeneIdasLink = page.locator("id=eidas");
        this.gebruikersMenuBurgerButton = page.locator("//div[contains(@class,'denhaag-header__actions-action-user-menu')]");
        this.menu = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Welkom"));
        this.languageButton = page.locator("//div[contains(@class,'denhaag-header__actions-action-language-switcher')]/button");
        this.disabledLanguageOption = page.locator("//ul/li/button[not(contains(@class, 'active'))]");
        this.buttonLogout = page.locator("//span[text()='Uitloggen']");
    }

    public Locator getMenuButtonByText(boolean isNL) {
        final var buttonText = isNL
                ? "Welkom"
                : "Welcome";
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonText));
    }
}
