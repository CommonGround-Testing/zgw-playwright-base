package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacALGDashboardPage;

public class AlgDashboardSteps extends GzacBaseSteps {

    protected final GzacALGDashboardPage dashboardPage;

    public AlgDashboardSteps(Page page) {
        super(page);
        this.dashboardPage = new GzacALGDashboardPage(page);
    }

    public void navigate() {
        page.navigate("/");
    }
}
