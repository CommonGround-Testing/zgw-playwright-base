package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacPODashboardPage;
import steps.gui.login.ADLoginSteps;

public class PODashboardSteps extends GzacBaseSteps {

    private final GzacPODashboardPage dashboardPage;
    private final ADLoginSteps adLoginSteps;

    public PODashboardSteps(Page page) {
        super(page);
        this.dashboardPage = new GzacPODashboardPage(page);
        this.adLoginSteps = new ADLoginSteps(page);
    }

    /**
     * Open het dashboard
     *
     */
    public void navigate() {
        page.navigate("/");
    }

    /**
     * Klik op de knop voor het tegeloverzicht
     *
     */
    public void tegelOverzichtOpenen() {
        dashboardPage.tegelOverzichtButton.click();
    }
}
