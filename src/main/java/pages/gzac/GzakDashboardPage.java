package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzakDashboardPage extends GzacBasePage {

    public final Locator dashboardTiles;

    public GzakDashboardPage(Page page) {
        super(page);
        dashboardTiles = page.locator("//div[@class='widget-configuration']");
    }
}