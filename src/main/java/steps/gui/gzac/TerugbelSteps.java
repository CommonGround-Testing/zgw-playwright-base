package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GeneriekeZaakDossierPage;
import pages.gzac.GzacBasePage;
import utils.MathUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TerugbelSteps extends GzacBaseSteps {

    private final GeneriekeZaakDossierPage dossierPage;
    private final GzacBasePage basePage;

    public TerugbelSteps(Page page) {
        super(page);
        basePage = new GzacBasePage(page);
        dossierPage = new GeneriekeZaakDossierPage(page);
    }

    public void navigate() {
        page.navigate("/dossiers/terugbelnotitie");
    }

    public void maak_nieuw_terugbel_dossier_aan() {
        basePage.clickButton("nieuw dossier");
        dossierPage.dossierTitel.waitFor();
        dossierPage.fillTextInputField("Onderwerp", MathUtils.generateRandomNumber() + " - onderwerp", false);
        dossierPage.fillTextAreaField("Vraag van de klant", "Ik heb een vraag", false);
        dossierPage.fillTextInputField("Voorletters", "T.E.S.T", false);
        dossierPage.fillTextInputField("Achternaam", "van de Vele Achternamen", false);
        dossierPage.fillTextInputField("E-mailadres", "testmijn@denhaag.nl", false);
        dossierPage.fillTextInputField("Telefoonnummer", "0611111111", false);
        dossierPage.fillTextAreaField("Ruimte voor notities", "mijn test notitie", false);
        dossierPage.getDateField("Geboortedatum", false).fill("01-01-1980");
        dossierPage.fillTextAreaField("Kenmerk", "mijn kenmerk", false);
        dossierPage.fillTextInputField("Straat", "Mijnstraat 123", false);
        dossierPage.fillTextInputField("Postcode", "1234AB", false);
        dossierPage.getDateField("Datum uiterlijke", false).click();
        dossierPage.checkCheckbox("Spoed", false);
        dossierPage.getDropdownField("Product").click();
        dossierPage.getDropdownOption("Product", "14070 Senioren");
        basePage.clickButton("Verzenden");
        assertThat(dossierPage.pageTitle).containsText("Dossier details");
    }
}
