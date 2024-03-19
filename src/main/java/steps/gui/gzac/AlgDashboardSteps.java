package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzakDashboardPage;

public class AlgDashboardSteps extends GzacBaseSteps {

    protected final GzakDashboardPage dashboardPage;

    public AlgDashboardSteps(Page page) {
        super(page);
        this.dashboardPage = new GzakDashboardPage(page);
    }

    public void navigate() {
        page.navigate("/");
    }
}
