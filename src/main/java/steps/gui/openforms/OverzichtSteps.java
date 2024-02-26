package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.OverzichtPage;
import utils.formvalues.FormStepNames;

public class OverzichtSteps extends OpenFormsSteps {

    private final OverzichtPage overzichtPage;

    public OverzichtSteps(Page page) {
        super(page);
        this.overzichtPage = new OverzichtPage(page);
    }

    public void rond_stap_overzicht_af() {
        this.controleer_actieve_formulierstap_is(FormStepNames.OVERZICHT);
        overzichtPage.buttonBevestigen.click();
    }
}
