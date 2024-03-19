package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacBasePage;
import pages.gzac.GzakMenu;
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
     * Haal een menu item op
     *
     * @param text van het menu item
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator getMenuItem(String text){
        return page.locator(GzakMenu.SIDE_NAV_PATH + GzakMenu.XPATH.replace("${text}", text));
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
        return getMenuItem(text);
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
     * Note that this only works on numeric fields
     *
     * @param veld - text of the field you want to enter the text for
     * @param number - number you want to enter into the field
     */
    public void vul_nummer_in(String veld, int number) {
        fillNumericInputField(veld, number);
    }

    /**
     * Vul een tekst in (dit werkt voor textarea velden en standaard input velden)
     * De veldnaam match wordt gedaan via een 'bevat' dus 'adres' zal ook het veld 'Mijn adres' of 'buitenlandsadres' vinden
     *
     * @param veld waar het ingevuld wordt
     * @param text die ingevuld wordt
     */
    public void vul_tekst_in(String veld, String text) {
        try{
            getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
            fillTextInputField(veld, text, false);
        } catch (Exception ex){
            fillTextAreaField(veld, text, false);
        }
    }

    /**
     * Vul een tekst in (dit werkt voor texarea velden en standaard input velden)
     * Deze methode hoeft alleen gebruikt te worden indien je een exacte match op veldnaam nodig hebt
     *
     * @param veld waar het ingevuld wordt
     * @param text die ingevuld wordt
     * @param exact true voor een exacte match
     */
    public void vul_tekst_in(String veld, String text, boolean exact) {
        fillTextInputField(veld, text, true);
    }

    /**
     * Vul een datum in bij een datum veld
     *
     * @param veld
     * @param datum
     */
    public void vul_datum_in(String veld, String datum){
        getDateField(veld, false).click();
        getDateField(veld, false).fill(datum);
    }

    /**
     * Klik op een datum veld
     *
     * @param veld
     */
    public void selecteer_datum(String veld){
        getDateField(veld, false).click();
    }

    /**
     * Selecteerd een checkbox
     *
     * @param veld tekst bij de checkbox
     */
    public void selecteer_checkbox(String veld) {
        checkCheckbox(veld, false);
    }

    /**
     * Selecteerd een waarde in een dropdown
     *
     * @param veld
     * @param text
     */
    public void selecteer_waarde_in_dropdown(String veld, String text) {
        getDropdownField(veld).click();
        getDropdownOption(veld, text).click();
    }

    /**
     * Selecteerd een optie voor een radiogroep
     *
     * @param veld van de radiogroep
     * @param optie die geselecteerd moet worden
     */
    public void selecteer_optie(String veld, String optie) {
        selectRadioOption(veld, optie);
    }

    /**
     * Haal een input veld op
     * De veldnaam match wordt gedaan via een 'bevat' dus 'adres' zal ook het veld 'Mijn adres' of 'buitenlandsadres' vinden
     *
     * @param veld wat opgehaald moet worden
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator haal_veld_op(String veld) {
        return getInputField(veld, false);
    }

    /**
     * Haal een input veld op
     * Deze methode hoeft alleen gebruikt te worden indien er een exacte matcn op veldnaam moet plaatsvinden
     *
     * @param veld wat opgehaald moet worden
     * @param exact true indien er een exacte match moet plaatsvinden
     * @return Locator waarop de actie uitgevoerd kan worden
     */
    public Locator haal_veld_op(String veld, boolean exact) {
        return getInputField(veld, exact);
    }

    /**
     * Haal de Locator van de titel op
     *
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator paginaTitel(){
        return basePage.pageTitle;
    }

    private Locator getNumericInputField(String field){
        String fieldLocator;
        fieldLocator = basePage.numericOnlyPath.replace("${text}", field);
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.inputFieldPath;
        return page.locator(fullXPath);
    }

    private void fillTextInputField(String field, String text, boolean exact){
        var inputField = getInputField(field, exact);
        inputField.waitFor(new Locator.WaitForOptions().setTimeout(300));
        inputField.fill(text);
    }

    private Locator getInputField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = basePage.exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        }
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.inputFieldPath + basePage.notHidden;
        return page.locator(fullXPath);
    }

    private void fillNumericInputField(String field, int number){
        getNumericInputField(field).fill(String.valueOf(number));
    }

    private void fillTextAreaField(String field, String text, boolean exact){
        getTextAreaField(field, exact).fill(text);    }

    private void checkCheckbox(String field, boolean exact){
        getInputField(field, exact).check();
    }

    private void selectRadioOption(String field, String option){
        getRadioField(field, option).click();
    }

    private Locator getTextAreaField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = basePage.exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        }
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.textAreaFieldPath;
        return page.locator(fullXPath);
    }

    private Locator getDateField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = basePage.exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        }
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.inputFieldPath + basePage.notHidden;
        return page.locator(fullXPath);
    }

    private Locator getRadioField(String field, String option){
        String fieldLocator;
        fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.radioInputPath.replace("${text}", option);
        return page.locator(fullXPath);
    }

    private Locator getDropdownField(String field){
        String fieldLocator;
        fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.dropdownPath;
        return page.locator(fullXPath);
    }

    private Locator getDropdownOption(String field, String text){
        String fieldLocator = basePage.containsTextLocator.replace("${text}", field);
        var fullXPath = basePage.dossierModalPath + fieldLocator + basePage.parentPath + basePage.dropdownOption + basePage.containsTextLocator.replace("${text}", text);
        return page.locator(fullXPath);
    }
}
