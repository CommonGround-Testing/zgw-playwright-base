package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzakMenu {
    public final Locator menuItemDashboard;
    public final static String DASHBOARD = "Dashboard";
    public final Locator menuItemDossiers;
    public final static String DOSSIERS = "Dossiers";
    public final Locator menuItemTaken;
    public final static String TAKEN = "Taken";
    private final static String sideNavPath = "//cds-sidenav";
    private final static String xpath = "//*[contains(text(),'${text}')]";

    public GzakMenu(Page page) {
        this.menuItemDashboard =  page.locator(sideNavPath + xpath.replace("${text}", DASHBOARD));
        this.menuItemDossiers =  page.locator(sideNavPath + xpath.replace("${text}", DOSSIERS));
        this.menuItemTaken =  page.locator(sideNavPath + xpath.replace("${text}", TAKEN));
    }

    public Locator getMenuItem(Page page, String text){
        return page.locator(sideNavPath + xpath.replace("${text}", text));
    }
}