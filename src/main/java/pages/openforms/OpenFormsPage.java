package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OpenFormsPage {

    public final Locator buttonInloggenDigid;
    public final Locator buttonAccepteerCookies;
    public final Locator linkActiveStep;
    public final Locator buttonVolgendeFormulierStapDisabled;
    public final Locator buttonVolgendeFormulierStap;
    public final Locator buttonVolgendeFormulierStapLoading;
    public final Locator textlabelHeaderH1;
    public final Locator textlabelHeaderH2;
    private final Page page;
    public final Locator loader;

    public OpenFormsPage(Page page) {
        this.page = page;
        this.buttonInloggenDigid = page.getByText("Inloggen met DigiD");
        this.buttonAccepteerCookies = page.locator("//button[contains(@class,'cookie-notice__accept')]");
        this.linkActiveStep = page.locator("//a[contains(@class,'utrecht-link--openforms-active')]");
        this.buttonVolgendeFormulierStapDisabled = page.locator("//button[@type='submit' and @aria-disabled='true' and text()='Volgende']");
        this.buttonVolgendeFormulierStap = page.locator("//button[@type='submit' and not(@aria-disabled='true') and text()='Volgende']");
        this.buttonVolgendeFormulierStapLoading = page.locator("//button[@type='submit' and @name='next' and contains(@class,'openforms-loading')]");
        this.textlabelHeaderH1 = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Ooievaarspas aanvragen"));
        this.textlabelHeaderH2 = page.locator("//h2").first();
        this.loader = page.getByRole(AriaRole.STATUS);
    }

    public Locator getButtonVolgendeFormulierStap() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Volgende"));
    }
}
