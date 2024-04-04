package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ErfpachtPage {

    private final static String h3Header = "//h3[contains(text(),'${text}')]";
    private final Page page;

    public ErfpachtPage(Page page) {
        this.page = page;
    }

    public Locator getContracten(String text) {
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + "/../..//tbody/tr";
        return page.locator(fullPath);
    }

    public Locator getTaken(String text) {
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + "/../..//a";
        return page.locator(fullPath);
    }

    public Locator getZaken(String text) {
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + "/../../div//a";
        return page.locator(fullPath);
    }
}
