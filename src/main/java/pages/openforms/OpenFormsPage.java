package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OpenFormsPage {
    public final Locator inloggenDigidButton;
    public final Locator uitloggenDigidButton;
    public final Locator aanvraagZonderDigidButton;

    public final Locator cookieButton;
    public final Locator buttonAccepteerCookies;

    public final Locator linkActiveStep;
    public final Locator textlabelHeaderH1;
    public final Locator textlabelHeaderH2;
    public final Locator headerFirstFormStep;


    public OpenFormsPage(Page page) {
        this.inloggenDigidButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Inloggen"));
        this.uitloggenDigidButton = page.locator("//button[text()='Uitloggen']");
        this.aanvraagZonderDigidButton = page.locator("//button[text()='Aanvragen zonder DigiD']");

        this.cookieButton = page.locator("//form[@action='/cookies/accept/']/button");
        buttonAccepteerCookies = page.getByRole(AriaRole.BUTTON).locator(":scope.cookie-notice__accept");

        linkActiveStep = page.getByRole(AriaRole.LINK).locator(":scope.utrecht-link--openforms-active");
        textlabelHeaderH1 = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Ooievaarspas aanvragen"));
        textlabelHeaderH2 = page.locator("//h2").first();
        this.headerFirstFormStep = page.locator("//h2[contains(text(),'Inleiding')]");
    }
}
