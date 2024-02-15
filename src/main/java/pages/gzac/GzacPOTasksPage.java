package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzacPOTasksPage {
    public final Locator linkAlleTaken;

    public GzacPOTasksPage(Page page) {
        this.linkAlleTaken = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Alle taken"));
    }
}