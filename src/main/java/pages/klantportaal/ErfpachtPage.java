package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ErfpachtPage {

    private final Page page;
    private final static String h3Header = "//h3[contains(text(),'${text}')]";
    private final static String contractsPath = "/../..//tbody/tr";

    public ErfpachtPage(Page page) {
        this.page = page;
    }

    public Locator getContracten(String text){
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + contractsPath;
        return page.locator(fullPath);
    }
}
