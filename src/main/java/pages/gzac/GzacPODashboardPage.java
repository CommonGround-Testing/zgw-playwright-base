package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacPODashboardPage {
    public final Locator tegelOverzichtButton;

    public GzacPODashboardPage(Page page) {
        tegelOverzichtButton = page.locator("//*[@class='mdi-view-module']");
    }
}