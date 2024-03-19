package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzakTakenPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GzakTakenSteps extends GzacBaseSteps {

    protected GzakTakenPage takenPage;
    public final static String URL = GzakTakenPage.URL;
    public final static String TAB_MIJN_TAKEN = "Mijn taken";
    public final static String TAB_NIET_TOEGEWEZEN_TAKEN = "Niet toegewezen taken";
    public final static String TAB_ALLE_TAKEN = "Alle taken";
    public final static String TEKST_AANMAAKDATUM = "Aanmaakdatum";
    public final static String TEKST_NAAM = "Naam";
    public final static String TEKST_EIGENAAR = "Eigenaar";
    public final static String TEKST_OPLEVERINGSDATUM = "Opleveringsdatum";
    public final static String TEKST_CONTEXT = "Context";
    public final static String TEKST_GEEN_TAKEN = "Je hebt geen taken op je naam staan";

    public GzakTakenSteps(Page page) {
        super(page);
        takenPage = new GzakTakenPage(page);
    }

    /**
     * Open de taken pagina
     */
    public void navigate() {
        page.navigate(GzakTakenPage.URL);
    }

    /**
     * Haal de actieve tab op
     *
     * @return Locator waarop een actie kan worden uitgevoerd
     */
    public Locator haal_actieve_tab_op() {
        return super.haal_actieve_tab_op(takenPage.mainContent);
    }
}
