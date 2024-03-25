package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Map;

public class GzacDossiersPage extends GzacBasePage {
    public final static String TAB_MIJN_DOSSIERS = "Mijn dossiers";
    public final static String TAB_NIET_TOEGEWEZEN_DOSSIERS = "Niet-toegewezen dossiers";
    public final static String TAB_ALLE_DOSSIERS = "Alle dossiers";
    public final Locator dossierModal;

    public final Locator mainContent;
    public final Locator headerRow;
    public final Locator tableRow;
    private final Locator submitButton;

    public GzacDossiersPage(Page page) {
        super(page);
        mainContent = page.locator("//valtimo-carbon-list");
        headerRow = mainContent.locator("//thead");
        tableRow = mainContent.locator("//tbody/tr");
        submitButton = page.locator("");
        dossierModal = page.getByRole(AriaRole.DIALOG);
    }
}