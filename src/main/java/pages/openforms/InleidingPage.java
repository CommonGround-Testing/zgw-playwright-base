package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InleidingPage {

    public final Locator linkPrivacyVerklaring;

    public InleidingPage(Page page) {
        this.linkPrivacyVerklaring = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("wet op de privacy (AVG)"));
    }
}
