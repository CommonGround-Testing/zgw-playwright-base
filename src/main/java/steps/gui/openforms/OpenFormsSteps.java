package steps.gui.openforms;

import com.microsoft.playwright.Page;
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

    public void navigate() {
        page.navigate("/");
    }

    public void login_via_digid(String username, String password) {
        openFormsPage.buttonAccepteerCookies.click();
        openFormsPage.buttonInloggenDigid.click();
        digidLoginSteps.login_als(username, password);
    }

    public void controleer_actieve_formulierstap_is(String stapNaam) {
        assertThat(openFormsPage.linkActiveStep).hasText(stapNaam);
    }

    public void constroleer_h1_header_is(String tekst) {
        assertThat(openFormsPage.textlabelHeaderH1).hasText(tekst);
    }

    public void controleer_h2_header_is(String tekst) {
        assertThat(openFormsPage.textlabelHeaderH2).hasText(tekst);
    }

    public void controleer_formulier_body_bevat_tekst(String tekst) {
        assertThat(page.locator("//p[contains(.,'" + tekst + "')]")).isVisible();
    }

    public void controleer_button_volgende_is_enabled_is(boolean isEnabled) {

        assertThat(openFormsPage.buttonVolgendeFormulierStap).isEnabled();
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

    public boolean geen_foutmelding_zichtbaar() {
        return page.locator("//div[@class='form-text error']").isVisible();
    }

    public void verzend_formulierstap() {
        openFormsPage.buttonVolgendeFormulierStap.click();
    }

    public void ga_naar_volgende_formulierstap() {
        openFormsPage.buttonVolgendeFormulierStap.click();
    }
}
