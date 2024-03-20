package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.gzac.GzacDossiersPage;

import java.util.List;
import java.util.Map;

public class GzacDossiersSteps extends GzacBaseSteps {

    public final static String URL = "dossiers/";
    public final static String TAB_ALGEMEEN = "Algemeen";
    public final static String TAB_Voortgang = "Voortgang";
    public final static String TAB_LOG = "Log";
    public final static String TAB_DOCUMENTEN = "Documenten";
    public final static String TAB_NOTITIES = "Notities";
    public final static String TEKST_GEEN_TAKEN = "Er zijn geen dossiers aan jou toegewezen";
    protected final GzacDossiersPage dossiersPage;

    public GzacDossiersSteps(Page page) {
        super(page);
        dossiersPage = new GzacDossiersPage(page);
    }

    /**
     * Open een specifieke dossier pagina
     *
     * @param dossierUrl met het relatieve path
     */
    public void navigeer_naar_dossier(String dossierUrl) {
        dossiersPage.navigate(URL + dossierUrl);
    }

    public Locator tabelHeader() {
        return dossiersPage.headerRow;
    }

    public Locator alleTabelRegels() {
        return dossiersPage.tableRow;
    }
}
