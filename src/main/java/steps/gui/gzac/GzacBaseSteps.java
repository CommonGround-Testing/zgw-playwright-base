package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacBasePage;
import steps.gui.GeneriekeSteps;
import steps.gui.login.ADLoginSteps;
import users.ADUser;
import utils.MathUtils;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GzacBaseSteps extends GeneriekeSteps {

    private final GzacBasePage basePage;
    private final ADLoginSteps adLoginSteps;
    public int dossierNummer;

    public GzacBaseSteps(Page page) {
        super(page);
        basePage = new GzacBasePage(page);
        adLoginSteps = new ADLoginSteps(page);
    }

    public void setDossierNummer() {
        dossierNummer = MathUtils.generateRandomNumber();
    }

    public void wachtOpLadenMenu() {
        basePage.menu.menuItemDashboard.waitFor();
    }

    public boolean wordtTabbladGetoond(String TabbladText) {
        return page.locator("//valtimo-widget//li[contains(.,'" + TabbladText + "')]").isVisible();
    }

    public Locator nieuwAangemaakteDossierNummer(String dossierNummer) {
        return page.locator("//valtimo-form-io//dt[contains(., '" + dossierNummer + "')]");
    }

    public void medewerker_ziet_tegels_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//valtimo-widget//dt[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    public void medewerker_ziet_tabel_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//table//th[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    public void medewerker_ziet_tabbladen(List<String> verwachteTabs) {
        verwachteTabs.forEach(tab ->
                assertThat(page.locator("//valtimo-widget//li[contains(.,'" + tab + "')]")).isVisible()
        );
    }

    public void medewerker_logt_in_bij_GZAC(String url, ADUser user) {
        page.navigate(url);
        adLoginSteps.login_met_ad_user(user);
        wachtOpLadenMenu();
    }

    public Locator menuItem(String text){
        return basePage.menu.getMenuItem(text);
    }

    public void maak_nieuw_dossier_aan() {
        klik_knop("nieuw dossier");
        basePage.dossierTitel.waitFor();
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
        basePage.fillNumericInputField(veld, number);
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
            basePage.getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
            basePage.fillTextInputField(veld, text, false);
        } catch (Exception ex){
            basePage.fillTextAreaField(veld, text, false);
        }
    }

    public void vul_datum_in(String veld, String datum){
        basePage.getDateField(veld, false).click();
        basePage.getDateField(veld, false).fill(datum);
    }

    public void selecteer_datum(String veld){
        basePage.getDateField(veld, false).click();
    }
    public void vul_tekst_in(String veld, String text, boolean exact) {
        basePage.fillTextInputField(veld, text, exact);
    }
    public void selecteer_checkbox(String veld) {
        basePage.checkCheckbox(veld, false);
    }
    public void selecteer_waarde_in_dropdown(String veld, String text) {
        basePage.getDropdownField(veld).click();
        basePage.getDropdownOption(veld, text).click();
    }
    public void selecteer_optie(String veld, String optie) {
        basePage.selectRadioOption(veld, optie);
    }
    public Locator haal_veld_op(String veld) {
        return basePage.getInputField(veld, false);
    }
    public Locator haal_veld_op(String veld, boolean exact) {
        return basePage.getInputField(veld, exact);
    }
    public Locator paginaTitel(){
        return basePage.pageTitle;
    }

}
