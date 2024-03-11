package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OpenFormsPage {

    public final Locator buttonAccepteerCookies;
    public final Locator linkActiveStep;
    public final Locator textlabelHeaderH1;
    public final Locator textlabelHeaderH2;

    public OpenFormsPage(Page page) {
        buttonAccepteerCookies = page.getByRole(AriaRole.BUTTON).locator(":scope.cookie-notice__accept");
        linkActiveStep = page.getByRole(AriaRole.LINK).locator(":scope.utrecht-link--openforms-active");
        textlabelHeaderH1 = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Ooievaarspas aanvragen"));
        textlabelHeaderH2 = page.locator("//h2").first();
    }
}
