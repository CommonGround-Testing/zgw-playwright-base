package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacDossierDetailsPage;

public class GzacDossierDetailsSteps extends GzacBaseSteps {

    public final static String TAB_ALGEMEEN = "Algemeen";
    public final static String TAB_Voortgang = "Voortgang";
    public final static String TAB_LOG = "Log";
    public final static String TAB_DOCUMENTEN = "Documenten";
    public final static String TAB_NOTITIES = "Notities";
    public final static String TEKST_GEEN_TAKEN = "Er zijn geen dossiers aan jou toegewezen";
    protected final GzacDossierDetailsPage dossierPage;

    public GzacDossierDetailsSteps(Page page) {
        super(page);
        dossierPage = new GzacDossierDetailsPage(page);
    }

    /**
     * Maak de toewijzing ongedaan
     *
     */
    public void maak_toewijzing_ongedaan() {
        dossierPage.buttonToewijzingOngedaanMaken.click();
    }
}
