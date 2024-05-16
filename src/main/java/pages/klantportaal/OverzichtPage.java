package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class OverzichtPage {

    public final static String PAGE_URL = "/";
    public final Locator pageHeader;
    public final Locator subHeader;
    public final Locator linkAlleTaken;
    public final Locator linkTaak;
    public final Locator linkAlleZaken;
    public final Locator zaakTegels;

    public OverzichtPage(Page page) {
        pageHeader = page.locator("//h1");
        subHeader = page.locator("//h3");
        linkAlleTaken = page.getByRole(AriaRole.LINK).getByText("Bekijk alle taken");
        linkTaak = page.getByRole(AriaRole.LINK).locator(":scope.denhaag-action--single");
        linkAlleZaken = page.getByRole(AriaRole.LINK).getByText("Bekijk alle zaken");
        zaakTegels = page.locator("//div[@class='denhaag-card__content']");
    }
}
