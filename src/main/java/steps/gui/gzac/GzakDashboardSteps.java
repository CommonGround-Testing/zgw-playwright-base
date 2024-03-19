package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzakDashboardPage;

public class GzakDashboardSteps extends GzacBaseSteps {

    protected final GzakDashboardPage dashboardPage;

    public GzakDashboardSteps(Page page) {
        super(page);
        dashboardPage = new GzakDashboardPage(page);
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
}
