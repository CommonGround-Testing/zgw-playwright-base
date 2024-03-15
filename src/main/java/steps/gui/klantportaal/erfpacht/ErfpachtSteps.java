package steps.gui.klantportaal.erfpacht;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.klantportaal.ErfpachtPage;
import steps.gui.klantportaal.KlantportaalSteps;

public class ErfpachtSteps extends KlantportaalSteps {

    public final static String URL_ERFPACHT = "/erfpacht";
    protected ErfpachtPage erfpachtPage;
    public final String ERFPACHT_CONTRACTEN = "Erfpachtcontracten";
    public final String ERFPACHT_TAKEN = "Mijn taken";


    public ErfpachtSteps(Page page) {
        super(page);
        erfpachtPage = new ErfpachtPage(page);
    }

    public void navigate() {
        page.navigate(URL_ERFPACHT);
    }

    public Locator alle_erfpacht_contracten() {
        return erfpachtPage.getContracten(ERFPACHT_CONTRACTEN);
    }

    public Locator alle_erfpacht_zaken(){
        return erfpachtPage.zakenLocator;
    }

    public Locator alle_erfpacht_taken(){
        return erfpachtPage.getTaken(ERFPACHT_TAKEN);
    }
}
