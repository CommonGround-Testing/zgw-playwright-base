package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzakMenu {
    public final Locator menuItemDashboard;
    public final Locator menuItemTaken;

    public GzakMenu(Page page) {
        this.menuItemDashboard =  page.locator("//cds-sidenav//*[contains(text(),'Dashboard')]");
        this.menuItemTaken =  page.locator("//cds-sidenav//*[contains(text(),'Taken')]");
    }

    public Locator getMenuItem(Page page, String item){
        return page.locator("//cds-sidenav//*[contains(text(),'" + item + "')]");
    }
}