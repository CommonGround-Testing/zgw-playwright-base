package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacDashboardPage;

public class GzacDashboardSteps extends GzacBaseSteps {

    protected final GzacDashboardPage dashboardPage;

    public GzacDashboardSteps(Page page) {
        super(page);
        dashboardPage = new GzacDashboardPage(page);
    }

    /**
     * Open het dashboard
     *
     */
    public void navigate() {
        page.navigate("/");
    }

    /**
     * Haalt alle tegels op van het Dashboard
     *
     * @return
     */
    public Locator haal_alle_tegels_op(){
        return dashboardPage.dashboardTiles;
    }

    /**
     * Haal de actieve tab op
     *
     * @return Locator waarop een actie kan worden uitgevoerd
     */
    public Locator haal_actieve_tab_op() {
        return super.haal_actieve_tab_op(dashboardPage.mainContent);
    }
}
