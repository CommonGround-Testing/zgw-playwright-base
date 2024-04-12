package steps.gui.klantportaal.erfpacht;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.klantportaal.ErfpachtPage;
import steps.gui.klantportaal.KlantportaalSteps;

public class ErfpachtSteps extends KlantportaalSteps {

    public final static String URL_ERFPACHT = "/erfpacht";
    public final String ERFPACHT_CONTRACTEN = "Erfpachtcontracten";
    public final String ERFPACHT_TAKEN = "Mijn taken";
    public final String ERFPACHT_ZAKEN = "Mijn lopende zaken";
    protected ErfpachtPage erfpachtPage;


    public ErfpachtSteps(Page page) {
        super(page);
        erfpachtPage = new ErfpachtPage(page);
    }

    /**
     * Open naar de Erfpacht url
     */
    public void navigate() {
        page.navigate(URL_ERFPACHT);
    }

    /**
     * Haal alle Erfpachtcontracten op die op het scherm staan
     *
     * @return Locator voor alle contracten
     */
    public Locator alle_erfpacht_contracten() {
        return erfpachtPage.getContracten(ERFPACHT_CONTRACTEN);
    }

    /**
     * Haal alle Erfpachtzaken op die op het scherm staan
     *
     * @return Locator voor alle zaken
     */
    public Locator alle_erfpacht_zaken() {
        return erfpachtPage.getZaken(ERFPACHT_ZAKEN);
    }

    /**
     * Haal alle Erfpachttaken op die op het scherm staan
     *
     * @return Locator voor alle taken
     */
    public Locator alle_erfpacht_taken() {
        return erfpachtPage.getTaken(ERFPACHT_TAKEN);
    }
}
