package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.GeneriekeOpenformsPage;
import users.ZGWDigidUser;

public class UwGegevensSteps extends OpenFormsSteps {

    protected final GeneriekeOpenformsPage openformsPage;

    public UwGegevensSteps(Page page) {
        super(page);
        openformsPage = new GeneriekeOpenformsPage(page);
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

    /**
     * Methode om makkelijk foutmeldingen mee te testen
     *
     * @param veld dat gechecked moet worden
     * @param tekst wat ingevuld moet worden
     * @param verwachteMelding verwachte foutmelding
     */

    public void test_foutmelding_voor_veld(String veld, String tekst, String verwachteMelding) {
        var inputField = openformsPage.getInputField(veld, false);
        openformsPage.fillTextInputField(veld, tekst, false);
        wacht_op_volgende_knop_response();
        validatie_toon_foutmelding(inputField,
                tekst,
                verwachteMelding);
    }

    public void rond_stap_uwgegevens_af(ZGWDigidUser user) {
        openformsPage.fillTextInputField("E-mailadres", user.getEmail(), false);
        klik_volgende_knop();
    }

    public void controleer_prefill_gegevens_zijn_zichtbaar(ZGWDigidUser user) {
        voorletters_zijn(user.getInitials());
        voornaam_is(user.getName());
        achternaam_is(user.getLastName());
        bsn_is(user.getBsn());
        postcode_is(user.getAddressZipcode());
        huisnummer_is(user.getAddressHouseNumber());
        huisletter_is(user.getAddressHouseLetter());
        straatnaam_is(user.getAddressStreet());
        woonplaats_is(user.getAddressCity());
    }
}
