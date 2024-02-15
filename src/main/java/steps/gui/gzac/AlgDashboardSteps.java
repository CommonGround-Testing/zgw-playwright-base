package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacALGDashboardPage;
import steps.gui.login.ADLoginSteps;

public class AlgDashboardSteps extends GzacBaseSteps {

    private final GzacALGDashboardPage dashboardPage;
    private final ADLoginSteps adLoginSteps;

    public AlgDashboardSteps(Page page) {
        super(page);
        this.dashboardPage = new GzacALGDashboardPage(page);
        this.adLoginSteps = new ADLoginSteps(page);
    }

    public void navigate() {
        page.navigate("/");
    }
}
