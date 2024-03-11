package steps.gui.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.openforms.GeneriekeOpenformsPage;
import pages.openforms.OpenFormsPage;
import steps.gui.GeneriekeSteps;
import steps.gui.login.DigidLoginSteps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OpenFormsSteps extends GeneriekeSteps {

    protected final OpenFormsPage openFormsPage;
    protected final GeneriekeOpenformsPage genericPage;
    protected final DigidLoginSteps digidLoginSteps;

    public OpenFormsSteps(Page page) {
        super(page);
        openFormsPage = new OpenFormsPage(page);
        genericPage  = new GeneriekeOpenformsPage(page);
        digidLoginSteps = new DigidLoginSteps(page);
    }

    public void open_pagina() {
        page.navigate("/aanvraag-formulier-ooievaarspas/startpagina");
    }

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

    public void valideer_actieve_stap(String stapNaam) {
        assertThat(openFormsPage.linkActiveStep).hasText(stapNaam);
    }

    public void controleer_h1_header_is(String tekst) {

        assertThat(openFormsPage.textlabelHeaderH1).hasText(tekst);
    }

    public void controleer_h2_header_is(String tekst) {
        assertThat(openFormsPage.textlabelHeaderH2).hasText(tekst);
    }

    public void controleer_foutmelding_is_zichtbaar_met(String tekst) {
        assertThat(get_alert().getByText(tekst)).isVisible();
    }

    public Locator get_tekst(String tekst){
        return page.getByText(tekst);
    }

    public Locator get_input_veld(String tekst){
        return genericPage.getInputField(tekst, false);
    }

    public Locator get_alert(){
        return page.locator("//div[contains(@role,'alert')]//div");
    }

    public void geen_foutmelding_zichtbaar() {
        assertThat(page.locator("//div[@class='form-text error']")).isHidden();
    }

    public void tekstveld_bevat_prefill_gegevens
            (String inputName, String prefillWaarde) {
        assertThat(
                page.locator(
                        String.format("//input[@name='%s' and contains(@value,\"%s\")]",
                                inputName,
                                prefillWaarde)))
                .isVisible();
    }

    public void validatie_toon_foutmelding(Locator lo, String tekst, String verwachteTekst) {
        lo.blur();
        lo.waitFor(new Locator.WaitForOptions().setTimeout(1000));
        page.keyboard().press("Enter");
        page.keyboard().press("Tab");
        if (verwachteTekst == null) {
            geen_foutmelding_zichtbaar();
        } else {
            controleer_foutmelding_is_zichtbaar_met(verwachteTekst);
        }
    }

    public void klik_volgende_knop() {
        wacht_op_volgende_knop_response();
        klik_knop("Volgende");
    }

    public void wacht_op_volgende_knop_response(){
        try{
            page.setDefaultTimeout(2000);
            page.waitForResponse(res -> res.url().contains("/_check_logic"), () -> {});
        } catch (Exception ex) {
            //ignore
        } finally {
            page.setDefaultTimeout(30000);
        }
    }

    public Locator get_checkbox(String tekst){
        return genericPage.getCheckbox(tekst);
    }

    /**
     * Selecteer een optie bij een radiobox of checkboxgroup
     *
     * @param tekst van de radio- of checkboxgroep
     * @param optie die geselecteerd moet worden
     */
    public void selecteer_optie(String tekst, String optie) {
        genericPage.selectOption(tekst, optie);
    }

    /**
     *
     * Note that this only works on input and textarea fields
     *
     * @param veld - text of the field you want to enter the text for
     * @param text - text you want to enter into the field
     */
    public void vul_tekst_in(String veld, String text) {
        genericPage.getInputField(veld, false).waitFor(new Locator.WaitForOptions().setTimeout(500));
        genericPage.fillTextInputField(veld, text, false);
    }
}
