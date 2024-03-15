package steps.gui.klantportaal.erfpacht;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.klantportaal.ErfpachtPage;
import steps.gui.klantportaal.KlantportaalSteps;

public class ErfpachtSteps extends KlantportaalSteps {

    public final static String URL_ERFPACHT = "/erfpacht";
    protected ErfpachtPage erfpachtPage;
    public final String ERFPACHTCONTRACTEN = "Erfpachtcontracten";

    public ErfpachtSteps(Page page) {
        super(page);
        erfpachtPage = new ErfpachtPage(page);
    }

    public void navigate() {
        page.navigate(URL_ERFPACHT);
    }

    public Locator get_erfpacht_contracten() {
        return erfpachtPage.getContracten(ERFPACHTCONTRACTEN);
    }
}
