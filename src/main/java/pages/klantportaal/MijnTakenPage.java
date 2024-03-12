package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MijnTakenPage {

    public final static String PAGE_URL = "/taken";
    public final Locator takenCard;
    public final Locator geenTakenVoorDeHuidigeGebruiker;
    public final Locator takenOphalenFoutmelding;
    private final Page page;

    public MijnTakenPage(Page page) {
        this.page = page;
        this.takenCard = page.locator("//div[@class='denhaag-action denhaag-action--single']").first();
        this.geenTakenVoorDeHuidigeGebruiker = page.locator("//p[text()='Er zijn geen openstaande taken.']");
        this.takenOphalenFoutmelding = page.locator("//p[contains(., 'Er is een fout opgetreden')]");
    }
}
