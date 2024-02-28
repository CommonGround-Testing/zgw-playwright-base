package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacBasePage {

    public final Locator tegelOverzichtButton;

    public GzacBasePage(Page page) {
        this.tegelOverzichtButton = page.locator("//*[@class='mdi-view-module']");
    }
}