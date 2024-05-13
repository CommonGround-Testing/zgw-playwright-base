package steps.gui.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import pages.klantportaal.MijnTakenPage;
import pages.klantportaal.MijnZakenPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MijnZakenSteps extends KlantportaalSteps {

    private final MijnZakenPage mijnZakenPage;

    public MijnZakenSteps(Page page) {
        super(page);
        mijnZakenPage = new MijnZakenPage(page);
    }

    /**
     * Open de Mijn zaken pagina
     *
     */
    public void navigate() {
        page.navigate(MijnTakenPage.PAGE_URL);
    }

    /**
     * Klik op een afgeronde taak
     *
     */
    public void open_eerste_afgeronde_zaak() {
        mijnZakenPage.afgerondeZakenButton.click();
        mijnZakenPage.zaakTegel.first().waitFor();
        mijnZakenPage.zaakTegel.first().click();
    }

    /**
     * Klik op een taak
     *
     */
    public void open_eerste_lopende_zaak() {
        mijnZakenPage.zaakTegel.first().waitFor();
        mijnZakenPage.zaakTegel.first().click();
    }

    /**
     * Valideer dat de Header 'Mijn taken' op het scherm staat
     *
     */
    public void valideer_dat_mijn_zaken_overzicht_zichtbaar_is() {
        assertThat(mijnZakenPage.mijnZakenHeader).isVisible();
    }

    /**
     * Open een specefieke zaak
     *
     * @param zaakId van de te openen zaak
     */
    public void open_zaak(String zaakId) {
        page.navigate("/zaken/zaak/" + zaakId);
        page.getByText("Status").waitFor();
    }

    /**
     * Haal alle headers op van het zaak detail scherm
     *
     * @return lijst met alle headers
     */
    public List<String> haal_alle_zaakdetailheaders_op() {
        mijnZakenPage.zaakStatusTimeline.waitFor();
        return mijnZakenPage.overviewZaakDetailHeaders.stream()
                .map(Locator::textContent)
                .collect(Collectors.toList());
    }

    /**
     * Valideer dat de timeline op het scherm staat
     *
     */
    public void valideer_dat_zaakstatus_timeline_zichtbaar_is() {
        assertThat(mijnZakenPage.zaakStatusTimeline).isVisible();
    }

    public void klik_download_link(){
        mijnZakenPage.zaakDocumentenDownloadButton.first().click();
    }

    /**
     * Valideer dat er een download document knop op het scherm staat
     *
     */
    public void valideer_dat_downloadknop_van_een_document_zichtbaar_is() {
        assertThat(mijnZakenPage.zaakDocumentenDownloadButton.first()).isVisible();
    }

    /**
     * Valideer dat er de lijst met contactmomenten op het scherm staat
     *
     */
    public void valideer_dat_lijst_van_contactmomenten_zichtbaar_is() {
        assertThat(mijnZakenPage.zaakContactmomentenList).isVisible();
    }

    /**
     * Valideer dat de zaakdetails 'Status, details en Documenten', Aanvraagnummer en Zaaknummer op het scherm staan
     *
     */
    public void valideer_dat_de_zaakdetails_zichtbaar_zijn() {
        final String verwachteVelden = "Status, Details, Documenten";
        final List<String> listTextFields = this.haal_alle_zaakdetailheaders_op();
        listTextFields.forEach(text -> Assertions.assertTrue(verwachteVelden.contains(text)));
        assertThat(mijnZakenPage.aanvraagdatumLabel).isVisible();
        assertThat(mijnZakenPage.zaaknummer).isVisible();
    }
}
