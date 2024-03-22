package steps.gui.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.openforms.OpenFormsPage;
import steps.gui.GeneriekeSteps;
import steps.gui.login.DigidLoginSteps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OpenFormsSteps extends GeneriekeSteps {

    protected final OpenFormsPage openFormsPage;
    protected final DigidLoginSteps digidLoginSteps;
    private final Page page;

    public OpenFormsSteps(Page page) {
        super(page);
        openFormsPage = new OpenFormsPage(page);
        digidLoginSteps = new DigidLoginSteps(page);
    }

    /**
     * Open de pagina voor aanvraag ooievaarspas
     */
    public void open_pagina() {
        page.navigate("/aanvraag-formulier-ooievaarspas/startpagina");
    }

    /**
     * Login via Digid
     *
     * @param username
     * @param password
     */
    public void login_via_digid(String username, String password) {
        openFormsPage.buttonAccepteerCookies.click();
        get_tekst("Inloggen met DigiD").click();
        digidLoginSteps.login_als(username, password);
        openFormsPage.textlabelHeaderH1
                .waitFor(new Locator
                        .WaitForOptions()
                        .setState(
                                WaitForSelectorState.VISIBLE));
    }

    /**
     * Valideer dat de stap als actief staat
     *
     * @param stapNaam die actief moet zijn
     */
    public void valideer_actieve_stap(String stapNaam) {
        assertThat(openFormsPage.linkActiveStep).hasText(stapNaam);
    }

    /**
     * Valideer dat een bepaalde h1 header op het scherm staat
     *
     * @param tekst
     */
    public void controleer_h1_header_is(String tekst) {

        assertThat(openFormsPage.textlabelHeaderH1).hasText(tekst);
    }

    /**
     * Valideer dat een bepaalde h2 header op het scherm staat
     *
     * @param tekst
     */
    public void controleer_h2_header_is(String tekst) {
        assertThat(openFormsPage.textlabelHeaderH2).hasText(tekst);
    }

    /**
     * Valideer dat er een bepaalde foutmelding op het scherm staat
     *
     * @param tekst
     */
    public void controleer_foutmelding_is_zichtbaar_met(String tekst) {
        assertThat(get_alert().getByText(tekst)).isVisible();
    }

    /**
     * Haal de Locator op van een tekst op het scherm
     *
     * @param tekst
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator get_tekst(String tekst) {
        return page.getByText(tekst);
    }

    /**
     * Haal de Locator van een inputveld op
     *
     * @param tekst
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator get_input_veld(String tekst) {
        return openFormsPage.getInputField(tekst, false);
    }

    /**
     * Haal de Locator van een alert op
     *
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator get_alert() {
        return page.locator("//div[contains(@role,'alert')]//div");
    }

    /**
     * Valideer dat er geen foutmelding zichtbaar is op het scherm
     */
    public void geen_foutmelding_zichtbaar(Locator locator) {
        locator.blur();
        locator.waitFor(new Locator.WaitForOptions().setTimeout(200));
        page.keyboard().press("Enter");
        page.keyboard().press("Tab");
        assertThat(page.locator("//div[@class='form-text error']")).isHidden();
    }

    /**
     * Valideer dat een bepaald veld de goede waarde heeft
     *
     * @param inputName
     * @param prefillWaarde
     */
    public void tekstveld_bevat_prefill_gegevens
    (String inputName, String prefillWaarde) {
        assertThat(
                page.locator(
                        String.format("//input[@name='%s' and contains(@value,\"%s\")]",
                                inputName,
                                prefillWaarde)))
                .isVisible();
    }

    /**
     * Valideer dat er een bepaalde foutmelding getoond wordt op het scherm
     *
     * @param locator
     * @param verwachteTekst
     */
    public void validatie_toon_foutmelding(Locator locator, String verwachteTekst) {
        locator.blur();
        locator.waitFor(new Locator.WaitForOptions().setTimeout(1000));
        page.keyboard().press("Enter");
        page.keyboard().press("Tab");
        controleer_foutmelding_is_zichtbaar_met(verwachteTekst);
    }

    /**
     * Klik op de volgende knop
     */
    public void klik_volgende_knop() {
        wacht_op_volgende_knop_response();
        klik_knop("Volgende");
    }

    /**
     * Wacht totdat de validatie in de backend is uitgevoerd
     */
    public void wacht_op_volgende_knop_response() {
        try {
            page.setDefaultTimeout(2000);
            page.waitForResponse(res -> res.url().contains("/_check_logic"), () -> {
            });
        } catch (Exception ex) {
            //ignore
        } finally {
            page.setDefaultTimeout(30000);
        }
    }

    /**
     * Haal de Locator op van de checkbox
     *
     * @param tekst
     * @return Locator waarop een actie uitgevoerd kan worden
     */
    public Locator get_checkbox(String tekst) {
        return openFormsPage.getCheckbox(tekst);
    }

    /**
     * Selecteer een optie bij een radiobox of checkboxgroup
     *
     * @param tekst van de radio- of checkboxgroep
     * @param optie die geselecteerd moet worden
     */
    public void selecteer_optie(String tekst, String optie) {
        openFormsPage.selectOption(tekst, optie);
    }

    /**
     * Note that this only works on input and textarea fields
     *
     * @param veld - text of the field you want to enter the text for
     * @param text - text you want to enter into the field
     */
    public void vul_tekst_in(String veld, String text) {
        openFormsPage.getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
        openFormsPage.fillTextInputField(veld, text, false);
    }

    public void test_foutmelding_voor_veld(String veld, String tekst, String verwachteMelding) {
        Locator inputField = fillFieldAndWaitForValidation(veld, tekst);
        validatie_toon_foutmelding(inputField, verwachteMelding);
    }

    private Locator fillFieldAndWaitForValidation(String veld, String tekst) {
        var inputField = openFormsPage.getInputField(veld, false);
        openFormsPage.fillTextInputField(veld, tekst, false);
        wacht_op_volgende_knop_response();
        return inputField;
    }

    public void test_geen_foutmelding_voor_veld(String veld, String tekst) {
        Locator inputField = fillFieldAndWaitForValidation(veld, tekst);
        geen_foutmelding_zichtbaar(inputField);
    }

    /**
     * Verifieer of de tekst op het scherm staat
     *
     * @param text die zichtbaar zou moeten zijn
     */
    public void valideer_of_tekst_zichtbaar_is(String text) {
        assertThat(page.getByText(text)).isVisible();
    }

    /**
     * Verifieer of de tekst op het scherm staat binnen een bepaalde tijd
     *
     * @param text             die zichtbaar zou moeten zijn
     * @param timeoutInSeconds aantal seconden dat er maximaal gewacht wordt
     */
    public void valideer_of_tekst_zichtbaar_is(String text, int timeoutInSeconds) {
        page.getByText(text).waitFor(new Locator.WaitForOptions().setTimeout(timeoutInSeconds * 1000));
    }

    /**
     * Klik op een link.
     *
     * @param text van de link of een deel daarvan
     */
    public void klik_link(String text) {
        page.getByRole(AriaRole.LINK).getByText(text).click();
    }

    /**
     * Haal het link element op
     *
     * @param text van de link of een deel daarvan
     */
    public Locator get_link(String text) {
        return page.getByRole(AriaRole.LINK).getByText(text);
    }

    /**
     * Klik op een tab.
     *
     * @param text van de tab of een deel daarvan
     */
    public void klik_tab(String text) {
        page.getByRole(AriaRole.TAB).getByText(text).click();
    }

    /**
     * Haal het tab element op
     *
     * @param text van de tab of een deel daarvan
     */
    public Locator get_tab(String text) {
        return page.getByRole(AriaRole.TAB).getByText(text);
    }

    /**
     * Klik op een knop
     *
     * @param text van de knop
     */
    public void klik_knop(String text) {
        get_knop(text).click();
    }

    /**
     * Haal de eerste knop op met een bepaalde tekst
     *
     * @param text van de knop
     * @return Locator
     */
    public Locator get_knop(String text) {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text)).first();
    }

    /**
     * Haal de radio groep op
     *
     * @param tekst van de radiogroep of een deel daarvan
     */
    public Locator get_radiogroep(String tekst) {
        return page.getByRole(AriaRole.RADIOGROUP).getByText(tekst);
    }
}
