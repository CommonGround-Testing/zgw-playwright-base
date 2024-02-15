package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GeneriekeZaakDossierPage {

    public final Locator buttonAanmakenNieuwDossier;
    public final Locator textfieldVoorletters;
    public final Locator textfieldAchternaam;
    public final Locator buttonVerzendNieuwDossier;
    public final Locator headerGeneriekeZaak;
    public final Locator headerTable;
    public final Locator tableCellEersteDossier;

    public GeneriekeZaakDossierPage(Page page) {
        this.buttonAanmakenNieuwDossier = page.locator("//button[contains(., 'Creëer Nieuw Dossier')]");
        this.textfieldVoorletters = page.locator("//input[contains(@id, 'machtiginggever-voorletter')]");
        this.textfieldAchternaam = page.locator("//input[contains(@id, 'machtiginggever-achternaam')]");
        this.buttonVerzendNieuwDossier = page.locator("//button[contains(., 'Start')]");
        this.headerGeneriekeZaak = page.locator("//h2[contains(text(), 'Generieke zaak')]");
        this.headerTable = page.locator("//th[contains(text(),'Referentienummer')]");
        this.tableCellEersteDossier = page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]");
    }
}