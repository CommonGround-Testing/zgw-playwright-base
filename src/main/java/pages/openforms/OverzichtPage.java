package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OverzichtPage {

    public final Locator buttonBevestigen;

    public OverzichtPage(Page page) {
        this.buttonBevestigen = page.locator("//button[text()='Bevestig']");
    }
}
