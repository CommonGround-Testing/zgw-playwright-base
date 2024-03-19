package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzacBasePage {
    public final Locator pageTitle;
    public final Locator textfieldVoorletters;
    public final Locator textfieldAchternaam;
    public final Locator buttonVerzendNieuwDossier;
    public final Locator headerGeneriekeZaak;
    public final Locator headerTable;
    public final Locator tableCellEersteDossier;
    public final Locator dossierTitel;
    public final String dossierModalPath;
    public final String parentPath;
    public final String inputFieldPath;
    public final String textAreaFieldPath;
    public final String radioInputPath;
    public final String dropdownPath;
    public final String dropdownOption;
    public final String containsTextLocator;
    public final String numericOnlyPath;
    public final String exactTextPath;
    public final String notHidden;
    public final Locator activeTab;
    private final Locator scopeActive;

    public final Page page;
    public GzakMenu menu;

    public GzacBasePage(Page page) {
        this.page = page;
        menu = new GzakMenu(page);
        notHidden = "[not(contains(@type,'hidden'))]";
        pageTitle = page.locator("//valtimo-page-title//h2");
        dossierModalPath = "//div[contains(@class,'modal-content')]";
        parentPath = "/..";
        inputFieldPath = "//input";
        textAreaFieldPath = "//textarea";
        dropdownPath = "//select/..";
        dropdownOption = "//div[contains(@role,'option')]";
        radioInputPath = "//span[contains(text(),'${text}')]//../input";
        containsTextLocator = "//*[contains(text(),'${text}')]";
        numericOnlyPath = "//label[normalize-space()='${text} , numeric only,']";
        exactTextPath = "//*[text(),'${text}']";
        dossierTitel = page.locator("//div[contains(@class,'modal-content')]//h4");
        textfieldVoorletters = page.locator("//input[contains(@id, 'machtiginggever-voorletter')]");
        textfieldAchternaam = page.locator("//input[contains(@id, 'machtiginggever-achternaam')]");
        buttonVerzendNieuwDossier = page.locator("//button[contains(., 'Start')]");
        headerGeneriekeZaak = page.locator("//h2[contains(text(), 'Generieke zaak')]");
        headerTable = page.locator("//th[contains(text(),'Referentienummer')]");
        tableCellEersteDossier = page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]");
        scopeActive = page.locator(":scope.cds--tabs__nav-item--selected:visible");
        activeTab = page.locator("//valtimo-widget-dashboard").getByRole(AriaRole.TAB).filter(new Locator.FilterOptions().setHas(scopeActive));
    }
}