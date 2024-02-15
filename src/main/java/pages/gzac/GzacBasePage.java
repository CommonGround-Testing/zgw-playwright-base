package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacBasePage {

    public final Locator dashboardText;
    public final Locator tegelOverzichtButton;

    public GzacBasePage(Page page) {
        this.dashboardText = page.locator("//valtimo-menu-item-text//*[contains(text(),'Dashboard')]");
        this.tegelOverzichtButton = page.locator("//*[@class='mdi-view-module']");
    }
}