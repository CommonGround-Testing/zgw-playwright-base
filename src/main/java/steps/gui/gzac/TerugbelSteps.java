package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacBasePage;
import utils.MathUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TerugbelSteps extends GzacBaseSteps {

    protected GzacBasePage basePage;

    public TerugbelSteps(Page page) {
        super(page);
        basePage = new GzacBasePage(page);
    }

    public void navigate() {
        page.navigate("/dossiers/terugbelnotitie");
    }

    public void maak_nieuw_terugbel_dossier_aan() {
        klik_knop("nieuw dossier");
        basePage.getInputField("Onderwerp", false).waitFor(new Locator.WaitForOptions().setTimeout(1000));
        basePage.fillTextInputField("Onderwerp", MathUtils.generateRandomNumber() + " - onderwerp", false);
        basePage.fillTextAreaField("Vraag van de klant", "Ik heb een vraag", false);
        basePage.fillTextInputField("Voorletters", "T.E.S.T", false);
        basePage.fillTextInputField("Achternaam", "van de Vele Achternamen", false);
        basePage.fillTextInputField("E-mailadres", "testmijn@denhaag.nl", false);
        basePage.fillTextInputField("Telefoonnummer", "0611111111", false);
        basePage.fillTextAreaField("Ruimte voor notities", "mijn test notitie", false);
        basePage.getDateField("Geboortedatum", false).fill("01-01-1980");
        basePage.fillTextAreaField("Kenmerk", "mijn kenmerk", false);
        basePage.fillTextInputField("Straat", "Mijnstraat 123", false);
        basePage.fillTextInputField("Postcode", "1234AB", false);
        basePage.getDateField("Datum uiterlijke", false).click();
        basePage.checkCheckbox("Spoed", false);
        basePage.getDropdownField("Product").click();
        basePage.getDropdownOption("Product", "14070 Senioren");
        klik_knop("Verzenden");
        assertThat(basePage.pageTitle).containsText("Dossier details");
    }
}
