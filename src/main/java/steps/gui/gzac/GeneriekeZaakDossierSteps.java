package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GeneriekeZaakDossierPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GeneriekeZaakDossierSteps extends GzacBaseSteps {

    private final GeneriekeZaakDossierPage zaakDossierPage;

    public GeneriekeZaakDossierSteps(Page page) {
        super(page);
        zaakDossierPage = new GeneriekeZaakDossierPage(page);
    }

    public void maak_nieuw_dossier_aan() {
        klik_knop("nieuw dossier");
        zaakDossierPage.dossierTitel.waitFor();
    }

    public void open_eerste_dossier() {
        page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]").click();
    }

    public void nieuw_aangemaakt_dossier_is_zichtbaar() {
        assertThat(this.nieuwAangemaakteDossierNummer(String.valueOf(dossierNummer))).isVisible();
    }

    public void navigate() {
        page.navigate("/dossiers/generieke-zaak");
    }

    public void klik_knop(String text){
        zaakDossierPage.getFirstButton(text).first().click();
    }

    public void vul_waarde_in(String veld, Integer number) {
        zaakDossierPage.fillNumericInputField(veld, number);
    }
    public void vul_waarde_in(String veld, String text) {
        zaakDossierPage.fillTextInputField(veld, text, false);
    }
    public void vul_waarde_in(String veld, String text, boolean exact) {
        zaakDossierPage.fillTextInputField(veld, text, exact);
    }
    public void selecteer_checkbox(String veld) {
        zaakDossierPage.checkCheckbox(veld, false);
    }
    public void selecteer_optie(String veld, String optie) {
        zaakDossierPage.selectRadioOption(veld, optie);
    }
    public Locator haal_veld_op(String veld) {
        return zaakDossierPage.getTextInputField(veld, false);
    }
    public Locator haal_veld_op(String veld, boolean exact) {
        return zaakDossierPage.getTextInputField(veld, exact);
    }
}
