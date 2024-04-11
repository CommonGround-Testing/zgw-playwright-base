package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class MijnZakenPage {

    public final static String PAGE_URL = "/zaken";
    public final Locator afgerondeZakenButton;
    public final Locator zaakTegel;
    public final List<Locator> overviewZaakDetailHeaders;
    public final Locator mijnZakenHeader;
    public final Locator zaakStatusTimeline;
    public final Locator aanvraagdatumLabel;
    public final Locator zaaknummer;
    public final Locator zaakDocumentenDownloadButton;
    public final Locator zaakGeenDocumenten;
    public final Locator zaakContactmomentenList;
    private final Page page;

    public MijnZakenPage(Page page) {
        this.page = page;
        this.afgerondeZakenButton = page.locator("//span[text()='Afgeronde zaken']");
        this.zaakTegel = page
                .locator("//div[@class='denhaag-card__content']");
        this.overviewZaakDetailHeaders = page
                .locator("//h3[contains(@class,'utrecht-heading')]").all();
        this.mijnZakenHeader = page
                .locator("//h2[text()='Mijn zaken']");
        this.zaakStatusTimeline = page.locator("//ol[@class='denhaag-process-steps']");
        this.aanvraagdatumLabel = page
                .locator("//dt[@class='denhaag-description-list__title' and text()='Aanvraagdatum']");
        this.zaaknummer = page
                .locator("//dt[@class='denhaag-description-list__title' and text()='Zaaknummer']");
        this.zaakDocumentenDownloadButton = page
                .locator("//button[contains(@class,'denhaag-file')]//div[text()='Download']");
        this.zaakGeenDocumenten = page.locator("//p[text()='Er zijn geen documenten.']");
        this.zaakContactmomentenList = page.locator("//ol[contains(@class,'denhaag-contact-timeline')]");
    }
}
