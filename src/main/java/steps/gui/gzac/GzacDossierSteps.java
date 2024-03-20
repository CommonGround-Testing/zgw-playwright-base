package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacDossierPage;

public class GzacDossierSteps extends GzacBaseSteps {

    protected final GzacDossierPage dossierPage;

    public GzacDossierSteps(Page page) {
        super(page);
        dossierPage = new GzacDossierPage(page);
    }

    /**
     * Haalt alle tegels op van het Dashboard
     *
     * @return
     */
    public void maak_toewijzing_ongedaan(){
        dossierPage.buttonToewijzingOngedaanMaken.click();
    }
}
