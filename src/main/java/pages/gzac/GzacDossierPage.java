package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzacDossierPage extends GzacBasePage {

    public final Locator mainContent;
    public final Locator buttonToewijzingOngedaanMaken;

    public GzacDossierPage(Page page) {
        super(page);
        mainContent = page.locator("//valtimo-task-list");
        buttonToewijzingOngedaanMaken = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
    }
}