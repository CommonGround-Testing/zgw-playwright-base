package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GzacMenu {
    private final Page page;
    public final Locator menuItemDashboard;
    public final static String DASHBOARD = "Dashboard";
    public final Locator menuItemDossiers;
    public final static String DOSSIERS = "Dossiers";
    public final Locator menuItemTaken;
    public final static String TAKEN = "Taken";
    public final static String SIDE_NAV_PATH = "//cds-sidenav";
    public final static String XPATH = "//*[contains(text(),'${text}')]";

    public GzacMenu(Page page) {
        this.page = page;
        menuItemDashboard =  page.locator(SIDE_NAV_PATH + XPATH.replace("${text}", DASHBOARD));
        menuItemDossiers =  page.locator(SIDE_NAV_PATH + XPATH.replace("${text}", DOSSIERS));
        menuItemTaken =  page.locator(SIDE_NAV_PATH + XPATH.replace("${text}", TAKEN));
    }
}