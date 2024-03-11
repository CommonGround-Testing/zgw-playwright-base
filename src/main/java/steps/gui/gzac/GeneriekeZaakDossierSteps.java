package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GeneriekeZaakDossierPage;
import pages.gzac.GzacBasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GeneriekeZaakDossierSteps extends GzacBaseSteps {

    private final GeneriekeZaakDossierPage zaakDossierPage;
    private final GzacBasePage basePage;

    public GeneriekeZaakDossierSteps(Page page) {
        super(page);
        basePage = new GzacBasePage(page);
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

    /**
     *
     * Note that this only works on numeric fields
     *
     * @param veld - text of the field you want to enter the text for
     * @param number - number you want to enter into the field
     */
    public void vul_nummer_in(String veld, int number) {
        zaakDossierPage.fillNumericInputField(veld, number);
    }

    /**
     *
     * Note that this only works on input and textarea fields
     *
     * @param veld - text of the field you want to enter the text for
     * @param text - text you want to enter into the field
     */
    public void vul_tekst_in(String veld, String text) {
        try{
            zaakDossierPage.getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
            zaakDossierPage.fillTextInputField(veld, text, false);
        } catch (Exception ex){
            zaakDossierPage.fillTextAreaField(veld, text, false);
        }
    }

    public void vul_datum_in(String veld, String datum){
        zaakDossierPage.getDateField(veld, false).click();
        zaakDossierPage.getDateField(veld, false).fill(datum);
    }

    public void selecteer_datum(String veld){
        zaakDossierPage.getDateField(veld, false).click();
    }
    public void vul_tekst_in(String veld, String text, boolean exact) {
        zaakDossierPage.fillTextInputField(veld, text, exact);
    }
    public void selecteer_checkbox(String veld) {
        zaakDossierPage.checkCheckbox(veld, false);
    }
    public void selecteer_waarde_in_dropdown(String veld, String text) {
        zaakDossierPage.getDropdownField(veld).click();
        zaakDossierPage.getDropdownOption(veld, text).click();
    }
    public void selecteer_optie(String veld, String optie) {
        zaakDossierPage.selectRadioOption(veld, optie);
    }
    public Locator haal_veld_op(String veld) {
        return zaakDossierPage.getInputField(veld, false);
    }
    public Locator haal_veld_op(String veld, boolean exact) {
        return zaakDossierPage.getInputField(veld, exact);
    }
    public Locator paginaTitel(){
        return zaakDossierPage.pageTitle;
    }
}
