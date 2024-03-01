package steps.gui.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.openforms.OpenFormsPage;
import steps.gui.login.DigidLoginSteps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class OpenFormsSteps {

    protected final Page page;
    protected final OpenFormsPage openFormsPage;
    protected final DigidLoginSteps digidLoginSteps;

    public OpenFormsSteps(Page page) {
        this.page = page;
        this.openFormsPage = new OpenFormsPage(page);
        this.digidLoginSteps = new DigidLoginSteps(page);
    }

    public void navigateToUrl() {
        page.navigate("/aanvraag-formulier-ooievaarspas/startpagina");
    }

    public void login_via_digid(String username, String password) {
        openFormsPage.buttonAccepteerCookies.click();
        openFormsPage.buttonInloggenDigid.click();
        digidLoginSteps.login_als(username, password);
        openFormsPage.textlabelHeaderH1
                .waitFor(new Locator
                        .WaitForOptions()
                        .setState(
                                WaitForSelectorState.VISIBLE));
    }

    public void controleer_actieve_formulierstap_is(String stapNaam) {
        assertThat(openFormsPage.linkActiveStep).hasText(stapNaam);
    }

    public void controleer_h1_header_is(String tekst) {

        assertThat(openFormsPage.textlabelHeaderH1).hasText(tekst);
    }

    public void controleer_h2_header_is(String tekst) {
        assertThat(openFormsPage.textlabelHeaderH2).hasText(tekst);
    }

    public void controleer_formulier_body_bevat_tekst(String tekst) {
        assertThat(page.locator("//p[contains(.,'" + tekst + "')]"))
                .isVisible(new LocatorAssertions
                        .IsVisibleOptions()
                        .setTimeout(60000));
    }

    public void controleer_button_volgende_is_enabled() {
        assertThat(openFormsPage.buttonVolgendeFormulierStap).isEnabled();
    }

    public void controleer_button_volgende_is_disabled() {
        assertThat(openFormsPage.buttonVolgendeFormulierStap).isDisabled();
    }

    public void controleer_foutmelding_is_zichtbaar_met(String tekst) {
        assertThat(page
                .locator("//div[@class='form-text error' and contains(text(),'" + tekst + "')]")).isVisible();
    }

    public void controleer_tekst_is_zichtbaar(String tekst) {
        assertThat(page.locator("//p[contains(text(),'" + tekst + "')]")).isVisible();
    }

    public void controleer_radiobutton_groep_is_zichtbaar_met(String tekst) {
        assertThat(page
                .locator("//fieldset[@role='radiogroup']/legend/span[contains(text(),'" + tekst + "')]"))
                .isVisible();
    }

    public void geen_foutmelding_zichtbaar() {
        assertThat(page.locator("//div[@class='form-text error']")).isHidden();
    }

    public void click_volgende_button() {
        var resp = page.waitForResponse(res -> res.url().contains("/_check_logic"), () -> {
        });
        openFormsPage.buttonVolgendeFormulierStap.click();
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

    public boolean button_volgende_is_enabled() {

        return openFormsPage.buttonVolgendeFormulierStap.isVisible();
    }

    public void validatie_toon_foutmelding(Locator lo, String tekst, String verwachteTekst) {
        lo.fill(tekst);
        lo.blur();

        if (verwachteTekst == null) {
            geen_foutmelding_zichtbaar();
        } else {
            controleer_foutmelding_is_zichtbaar_met(verwachteTekst);
        }
    }
}
