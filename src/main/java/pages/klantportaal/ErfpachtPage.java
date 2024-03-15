package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ErfpachtPage {

    private final Page page;
    private final static String h3Header = "//h3[contains(text(),'${text}')]";
    private final static String contractsPath = "/../..//tbody/tr";
    private final String takenPath = "/../..//a";
    public final Locator zakenLocator;

    public ErfpachtPage(Page page) {
        this.page = page;
        zakenLocator = page.locator("//div[@class='denhaag-card__content']");
    }

    public Locator getContracten(String text){
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + contractsPath;
        return page.locator(fullPath);
    }

    public Locator getTaken(String text){
        var headerPath = h3Header.replace("${text}", text);
        var fullPath = headerPath + takenPath;
        return page.locator(fullPath);
    }
}
