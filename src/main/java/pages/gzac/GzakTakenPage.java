package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzakTakenPage extends GzacBasePage {

    public final static String URL = "/tasks";
    public final Locator mainContent;

    public GzakTakenPage(Page page) {
        super(page);
        mainContent = page.locator("//valtimo-task-list");
    }
}