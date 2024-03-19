package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzakTakenPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GzakTakenSteps extends GzacBaseSteps {

    protected GzakTakenPage takenPage;
    public final static String TAB_MIJN_TAKEN = "Mijn taken";
    public final static String TAB_NIET_TOEGEWEZEN_TAKEN = "Niet toegewezen taken";
    public final static String TAB_ALLE_TAKEN = "Alle taken";

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
