package pages.gzac;

import com.microsoft.playwright.Page;

public class GzacBasePage {

    protected final Page page;
    public GzakMenu menu;

    public GzacBasePage(Page page) {
        this.page = page;
        menu = new GzakMenu(page);
    }
}