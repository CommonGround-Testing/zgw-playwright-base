package steps.gui.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
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
        page.navigate("/zaken");
    }

    /**
     * Klik op een afgeronde taak
     *
     */
    public void open_afgeronde_zaak() {
        mijnZakenPage.afgerondeZakenButton.click();
        mijnZakenPage.zaakTegel.waitFor();
        mijnZakenPage.zaakTegel.click();
    }

    /**
     * Klik op een taak
     *
     */
    public void open_lopende_zaak() {
        mijnZakenPage.zaakTegel.waitFor();
        mijnZakenPage.zaakTegel.click();
    }

    /**
     * Valideer dat de Header 'Mijn taken' op het scherm staat
     *
     */
    public void mijn_zaken_overzicht_is_zichtbaar() {
        assertThat(mijnZakenPage.mijnZakenHeader).isVisible();
    }

    /**
     * Open een specefieke zaak
     *
     * @param zaakId van de te openen zaak
     */
    public void open_zaak(String zaakId) {
        page.navigate("/zaken/zaak?id=" + zaakId);
    }

    /**
     * Haal alle headers op van het zaak detail scherm
     *
     * @return lijst met alle headers
     */
    public List<String> haal_alle_zaakdetailheaders() {
        mijnZakenPage.zaakStatusTimeline.waitFor();
        return mijnZakenPage.overviewZaakDetailHeaders.stream()
                .map(Locator::textContent)
                .collect(Collectors.toList());
    }

    /**
     * Valideer dat de timeline op het scherm staat
     *
     */
    public void controleer_dat_zaakstatus_timeline_zichtbaar_is() {
        assertThat(mijnZakenPage.zaakStatusTimeline).isVisible();
    }

    /**
     * Valideer dat er een download document knop op het scherm staat
     *
     */
    public void controleer_dat_downloadknop_van_een_document_zichtbaar_is_of_geen_documenten() {
        assertThat(mijnZakenPage.zaakDocumentenDownloadButton.first()).isVisible();
    }

    /**
     * Valideer dat er de lijst met contactmomenten op het scherm staat
     *
     */
    public void lijst_van_contactmomenten_is_zichtbaar() {
        assertThat(mijnZakenPage.zaakContactmomentenList).isVisible();
    }

    /**
     * Valideer dat de zaakdetails 'Status, details en Documenten', Aanvraagnummer en Zaaknummer op het scherm staan
     *
     */
    public void de_zaakdetails_worden_getoond() {
        final String verwachteVelden = "Status, Details, Documenten";
        final List<String> listTextFields = this.haal_alle_zaakdetailheaders();
        listTextFields.forEach(text -> Assertions.assertTrue(verwachteVelden.contains(text)));
        assertThat(mijnZakenPage.aanvraagdatumLabel).isVisible();
        assertThat(mijnZakenPage.zaaknummer).isVisible();
    }
}
