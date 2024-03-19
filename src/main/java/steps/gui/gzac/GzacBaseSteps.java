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

    /**
     * Genereer een random dossier nummer en sla deze op
     *
     */
    public void setDossierNummer() {
        dossierNummer = MathUtils.generateRandomNumber();
    }

    /**
     * Wacht tot het menu geladen is
     *
      */
    public void wachtOpLadenMenu() {
        basePage.menu.menuItemDashboard.waitFor();
    }

    /**
     * Valideer dat het tabblad op het scherm getoond wordt
     *
     * @param TabbladText de tekst in het tabblad
     * @return boolean
     */
    public boolean wordtTabbladGetoond(String TabbladText) {
        return page.locator("//valtimo-widget//li[contains(.,'" + TabbladText + "')]").isVisible();
    }

    /**
     * Valideer dat bepaalde kolommen op het scherm staan voor de tegels
     *
     * @param kolommen
     */
    public void medewerker_ziet_tegels_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//valtimo-widget//dt[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    /**
     * Valideer dat bepaalde kolommen op het scherm staan  in de tabel
     *
     * @param kolommen
     */
    public void medewerker_ziet_tabel_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//table//th[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    /**
     * Valideer dat bepaalde tabs op het scherm staan
     *
     * @param verwachteTabs
     */
    public void medewerker_ziet_tabbladen(List<String> verwachteTabs) {
        verwachteTabs.forEach(tab ->
                assertThat(page.locator("//valtimo-widget//li[contains(.,'" + tab + "')]")).isVisible()
        );
    }

    /**
     * Open een bepaalde url en log in met een gebruiker
     *
     * @param url
     * @param user
     */
    public void medewerker_logt_in_bij_GZAC(String url, ADUser user) {
        page.navigate(url);
        adLoginSteps.login_met_ad_user(user);
        wachtOpLadenMenu();
    }

    /**
     * Locator van een menu item.
     *
     * @param text van het menu item
     * @return
     */
    public Locator menuItem(String text){
        return basePage.menu.getMenuItem(text);
    }

    /**
     * Klik op de knop om een nieuw dossier aan te maken en wacht totdat het dossier is geladen
     *
     */
    public void maak_nieuw_dossier_aan() {
        klik_knop("nieuw dossier");
        basePage.dossierTitel.waitFor();
    }

    /**
     * Klik op het eerste dossier op het scherm
     *
     */
    public void open_eerste_dossier() {
        page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]").click();
    }

    /**
     * Valideer of er een nieuw dossier aangemaakt is
     *
     */
    public void nieuw_aangemaakt_dossier_is_zichtbaar() {
        assertThat(page.locator("//valtimo-form-io//dt[contains(., '" + dossierNummer + "')]")).isVisible();
    }

    /**
     * Open een generieke zaak??
     * @TODO deze url heb ik getest en doet helemaal niets
     *
     */
//    public void navigate() {
//        page.navigate("/dossiers/generieke-zaak");
//    }

    /**
     * Vul een nummer in bij een numeriek veld (dit werkt alleen voor numerieke velden)
     *
     * @param veld
     * @param number
     */
    public void vul_nummer_in(String veld, int number) {
        basePage.fillNumericInputField(veld, number);
    }

    /**
     * Vul een tekst in (dit werkt voor textarea velden en standaard input velden)
     *
     * @param veld
     * @param text
     */
    public void vul_tekst_in(String veld, String text) {
        try{
            basePage.getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
            basePage.fillTextInputField(veld, text, false);
        } catch (Exception ex){
            basePage.fillTextAreaField(veld, text, false);
        }
    }

    /**
     * Vul een datum in bij een datum veld
     *
     * @param veld
     * @param datum
     */
    public void vul_datum_in(String veld, String datum){
        basePage.getDateField(veld, false).click();
        basePage.getDateField(veld, false).fill(datum);
    }

    /**
     * Klik op een datum veld
     *
     * @param veld
     */
    public void selecteer_datum(String veld){
        basePage.getDateField(veld, false).click();
    }

    /**
     * Vul een tekst in voor een bepaalde veld
     * Met de exact boolean kun je aangeven of er een exacte match op veldnaam is of niet
     *              false dan wordt het veld gekozen waar de veldnaam in voorkomt
     *              true dan wordt het veld gekozen wat precies deze veldnaam heeft
     *
     * @param veld veld naam
     * @param text tekst om in te vullen
     * @param exact boolean om aan te geven of het een exact match is of niet
     */
    public void vul_tekst_in(String veld, String text, boolean exact) {
        basePage.fillTextInputField(veld, text, exact);
    }

    /**
     * Selecteer een waarde bij een checkbox
     *
     * @param veld
     */
    public void selecteer_checkbox(String veld) {
        basePage.checkCheckbox(veld, false);
    }

    /**
     * Selecteer een waarde in een dropdown
     *
     * @param veld naam van de dropdown
     * @param optie tekst om te selecteren
     */
    public void selecteer_waarde_in_dropdown(String veld, String optie) {
        basePage.getDropdownField(veld).click();
        basePage.getDropdownOption(veld, optie).click();
    }

    /**
     * Selecteer een optie bij een radiogroep
     *
     * @param veld naam van de radiogroep
     * @param optie die geselecteerd wordt
     */
    public void selecteer_optie(String veld, String optie) {
        basePage.selectRadioOption(veld, optie);
    }

    /**
     * Haal de Locator op van een inputveld
     *
     * @param veld van het inputveld
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator haal_veld_op(String veld) {
        return basePage.getInputField(veld, false);
    }

    /**
     * Haal de Locator op van een inputveld
     * Met de exact boolean kun je aangeven of er een exacte match op veldnaam is of niet
     *              false dan wordt het veld gekozen waar de veldnaam in voorkomt
     *              true dan wordt het veld gekozen wat precies deze veldnaam heeft
     *
     * @param veld naam van het inputveld
     * @param exact of er een exacte match gebruikt moet worden
     * @return
     */
    public Locator haal_veld_op(String veld, boolean exact) {
        return basePage.getInputField(veld, exact);
    }

    /**
     * Haal de Locator van de titel op
     *
     * @return Locator waarop een actie gedaan kan worden
     */
    public Locator paginaTitel(){
        return basePage.pageTitle;
    }
}
