package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VerklaringVanWaarheidPage {

    public final Locator textlabelCheckboxAkkoord;
    public final Locator checkboxAkkoord;

    public VerklaringVanWaarheidPage(Page page) {
        this.textlabelCheckboxAkkoord = page.locator("//label[contains(@for,'checkboxAkkoord')]");
        this.checkboxAkkoord = page.locator("//*[@name = 'data[checkboxAkkoord]']");
    }
}
