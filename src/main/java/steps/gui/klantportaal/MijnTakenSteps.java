package steps.gui.klantportaal;

import com.microsoft.playwright.Page;
import pages.klantportaal.MijnTakenPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MijnTakenSteps extends KlantportaalSteps {

    public final MijnTakenPage mijnTakenPage;

    public MijnTakenSteps(Page page) {
        super(page);
        mijnTakenPage = new MijnTakenPage(page);
    }

    /**
     * Valideer dat de foutmelding zichtbaar is op het scherm
     *
     */
    public void valideer_foutmelding_takenophalen_zichtbaar_is() {
        assertThat(mijnTakenPage.takenOphalenFoutmelding).isVisible();
    }

    /**
     * Valideer dat de melding over geen taken zichtbaar is op het scherm
     *
     */
    public void valideer_dat_melding_geen_openstaande_taken_zichtbaar_is() {
        assertThat(mijnTakenPage.geenTakenVoorDeHuidigeGebruiker).isVisible();
    }

    /**
     * Open een specefieke taak
     *
     * @param taakId van de te openen zaak
     */
    public void open_taak(String taakId) {
        page.navigate("/taken/taak/" + taakId);
    }
    /**
     * Valideer
     * dat er een taak op het scherm staat
     * dat er geen foutmelding op het scherm staat
     */
    public void valideer_dat_een_overzicht_van_de_taken_wordt_getoond() {
        mijnTakenPage.takenCard.waitFor();
        assertThat(mijnTakenPage.takenCard).isVisible();
        assertThat(mijnTakenPage.takenOphalenFoutmelding).isHidden();
    }

    /**
     * Open de Mijn taken pagina
     *
     */
    public void navigate() {
        page.navigate(MijnTakenPage.PAGE_URL);
    }

}
