package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BezitEnSchuldenPage {

    public final Locator checkboxContanten;

    public final Locator textfieldContantGeld;

    public BezitEnSchuldenPage(Page page) {
        this.checkboxContanten = page.locator("//input[@type='checkbox' and contains(@name,'data[welkVermogen]') and @value='d']");
        this.textfieldContantGeld = page.locator("//*[@name='data[contantGeld]']");
    }
}
