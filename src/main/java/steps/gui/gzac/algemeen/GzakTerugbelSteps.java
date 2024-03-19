package steps.gui.gzac.algemeen;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacBasePage;
import steps.gui.gzac.GzacBaseSteps;
import utils.MathUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GzakTerugbelSteps extends GzacBaseSteps {

    protected GzacBasePage basePage;

    public GzakTerugbelSteps(Page page) {
        super(page);
        basePage = new GzacBasePage(page);
    }

    /**
     * Open de terugbelnotitie pagina
     *
     */
    public void navigate() {
        page.navigate("/dossiers/terugbelnotitie");
    }

    /**
     * Klik op knop voor het aanmaken van een nieuwe dossier en vul alle gegevens in
     *
     */
    public void maak_nieuw_terugbel_dossier_aan() {
        klik_knop("nieuw dossier");
        haal_veld_op("Onderwerp", false).waitFor(new Locator.WaitForOptions().setTimeout(1000));
        vul_tekst_in("Onderwerp", MathUtils.generateRandomNumber() + " - onderwerp");
        vul_tekst_in("Vraag van de klant", "Ik heb een vraag");
        vul_tekst_in("Voorletters", "T.E.S.T");
        vul_tekst_in("Achternaam", "van de Vele Achternamen");
        vul_tekst_in("E-mailadres", "testmijn@denhaag.nl");
        vul_tekst_in("Telefoonnummer", "0611111111");
        vul_tekst_in("Ruimte voor notities", "mijn test notitie");
        vul_datum_in("Geboortedatum", "01-01-1980");
        vul_tekst_in("Kenmerk", "mijn kenmerk");
        vul_tekst_in("Straat", "Mijnstraat 123");
        vul_tekst_in("Postcode", "1234AB");
        haal_veld_op("Datum uiterlijke").click();
        selecteer_checkbox("Spoed");
        haal_veld_op("Product").click();
        selecteer_waarde_in_dropdown("Product", "14070 Senioren");
        klik_knop("Verzenden");
        assertThat(basePage.pageTitle).containsText("Dossier details");
    }
}
