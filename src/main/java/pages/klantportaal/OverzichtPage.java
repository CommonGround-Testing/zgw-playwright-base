package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OverzichtPage {

    public final static String PAGE_URL = "/";
    public final Locator headerPage;
    public final Locator overzichtZaakTegel;
    private final Page page;

    public OverzichtPage(Page page) {
        this.page = page;
        this.headerPage = page.locator("//h2");
        this.overzichtZaakTegel = page.locator("//div[@class='denhaag-card__content']").first();
    }
}
