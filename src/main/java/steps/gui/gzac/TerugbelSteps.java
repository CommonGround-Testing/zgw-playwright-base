package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.TerugbelnotitieDossierPage;

public class TerugbelSteps extends GzacBaseSteps {

    private final TerugbelnotitieDossierPage terugbelPage;

    public TerugbelSteps(Page page) {
        super(page);
        this.terugbelPage = new TerugbelnotitieDossierPage(page);
    }

    public void navigate() {
        page.navigate("/dossiers/terugbelnotitie");
    }

    public void maak_nieuw_terugbel_dossier_aan() {
        terugbelPage.buttonAanmakenNieuwDossier.waitFor();
        terugbelPage.buttonAanmakenNieuwDossier.click();
        terugbelPage.textfieldOnderwerp.waitFor();
        terugbelPage.textfieldOnderwerp.fill(this.dossierNummer + " - onderwerp");
        terugbelPage.textfieldKlantvraag.fill("Ik heb een vraag");
        terugbelPage.textfieldVoorletters.fill("T.E.S.T");
        terugbelPage.textfieldAchternaam.fill("van de Vele Achternamen");
        terugbelPage.textfieldEmail.fill("testmijn@denhaag.nl");
        terugbelPage.textfieldTelefoonnummer.fill("0611111111");
        terugbelPage.buttonGeboortedatum.click();
        terugbelPage.buttonGeboortedatumVandaag.click();
        terugbelPage.buttonProductSenioren.click();
        terugbelPage.textfieldProductSenioren.fill("14070");
        terugbelPage.buttonSelecteerProductSenioren.click();
        terugbelPage.buttonVerzendNieuwDossier.waitFor();
        terugbelPage.buttonVerzendNieuwDossier.click();
    }
}