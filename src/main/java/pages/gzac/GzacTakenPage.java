package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacTakenPage extends GzacBasePage {

    public final static String URL = "/tasks";
    public final Locator mainContent;

    public GzacTakenPage(Page page) {
        super(page);
        mainContent = page.locator("//valtimo-task-list");
    }
}