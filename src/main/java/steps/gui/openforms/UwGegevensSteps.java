package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.UwGegevensPage;
import users.ZGWDigidUser;
import utils.formvalues.FormStepNames;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UwGegevensSteps extends OpenFormsSteps {

    private final UwGegevensPage uwGegevensPage;

    public UwGegevensSteps(Page page) {
        super(page);
        this.uwGegevensPage = new UwGegevensPage(page);
    }

    public void link_persoonsgegevens_aanpassen_is_zichtbaar() {

        assertThat(uwGegevensPage.linkPersoonsgegevensAanpassen).isVisible();
    }

    public void voorletters_zijn(String verwachteVoorletters) {
        tekstveld_bevat_prefill_gegevens("data[voorlettersPrefill]", verwachteVoorletters);
    }

    public void voornaam_is(String verwachteVoornaam) {

        tekstveld_bevat_prefill_gegevens("data[voornaamPrefill]", verwachteVoornaam);
    }

    public void achternaam_is(String verwachteAchternaam) {

        tekstveld_bevat_prefill_gegevens("data[achternaamPrefill]", verwachteAchternaam);
    }

    public void bsn_is(String verwachteBsn) {

        tekstveld_bevat_prefill_gegevens("data[BsnPrefill]", verwachteBsn);
    }

    public void postcode_is(String verwachtePostcode) {

        tekstveld_bevat_prefill_gegevens
                ("data[postcodePrefill]", verwachtePostcode);
    }

    public void huisnummer_is(int verwachteHuisnummer) {

        tekstveld_bevat_prefill_gegevens
                ("data[huisnummerPrefill]", String.valueOf(verwachteHuisnummer));
    }

    public void huisletter_is(String verwachteHuisletter) {

        tekstveld_bevat_prefill_gegevens("data[HuisletterPrefill]", verwachteHuisletter);
    }

    public void straatnaam_is(String verwachteStraatnaam) {

        tekstveld_bevat_prefill_gegevens("data[straatPrefill]", verwachteStraatnaam);
    }

    public void woonplaats_is(String verwachteWoonplaats) {

        tekstveld_bevat_prefill_gegevens("data[plaatsPrefill]", verwachteWoonplaats);
    }

    public void validatie_email_toont_foutmelding(String emailAdres, String verwachteTekst) {
        validatie_toon_foutmelding(uwGegevensPage.textfieldEmailAdres,
                emailAdres,
                verwachteTekst);
    }

    public void validatie_telnr_toont_foutmelding(String telnr, String verwachteTekst) {
        validatie_toon_foutmelding(uwGegevensPage.textfieldTelefoonnummer,
                telnr,
                verwachteTekst);
    }

    public void rond_formulierstap_af(ZGWDigidUser user) {

        uwGegevensPage.textfieldEmailAdres.fill(user.getEmail());
        uwGegevensPage.textfieldEmailAdres.press("Tab");
        ga_naar_volgende_formulierstap();
    }

    public void controleer_prefill_gegevens_zijn_zichtbaar(ZGWDigidUser user) {
        this.voorletters_zijn(user.getInitials());
        this.voornaam_is(user.getName());
        this.achternaam_is(user.getLastName());
        this.bsn_is(user.getBsn());
        this.postcode_is(user.getAddressZipcode());
        this.huisnummer_is(user.getAddressHouseNumber());
        this.huisletter_is(user.getAddressHouseLetter());
        this.straatnaam_is(user.getAddressStreet());
        this.woonplaats_is(user.getAddressCity());
    }

    public void rond_stap_uwgegevens_af(ZGWDigidUser user) {
        this.controleer_actieve_formulierstap_is(FormStepNames.UW_GEGEVENS);
        this.rond_formulierstap_af(user);
    }
}
