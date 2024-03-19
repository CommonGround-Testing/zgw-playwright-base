package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzakDashboardPage extends GzacBasePage {

    protected Locator dashboardTile;

    public GzakDashboardPage(Page page) {
        super(page);
        dashboardTile = page.locator("//div[@class='widget-configuration']");
    }
}