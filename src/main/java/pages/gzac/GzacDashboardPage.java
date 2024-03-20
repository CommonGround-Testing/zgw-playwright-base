package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacDashboardPage extends GzacBasePage {

    public final Locator dashboardTiles;
    public final Locator mainContent;

    public GzacDashboardPage(Page page) {
        super(page);
        dashboardTiles = page.locator("//div[@class='widget-configuration']");
        mainContent = page.locator("//valtimo-widget-dashboard");
    }
}