package steps.gui.klantportaal;

import com.microsoft.playwright.Page;
import pages.klantportaal.MijnTakenPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MijnTakenSteps extends KlantportaalSteps {

    private final MijnTakenPage mijnTakenPage;

    public MijnTakenSteps(Page page) {
        super(page);
        mijnTakenPage = new MijnTakenPage(page);
    }

    public void taken_ophalen_foutmelding_is_zichtbaar() {
        assertThat(mijnTakenPage.takenOphalenFoutmelding).isVisible();
    }

    public void melding_geen_openstaande_taken_wordt_getoond() {
        assertThat(mijnTakenPage.geenTakenVoorDeHuidigeGebruiker).isVisible();
    }

    public void een_overzicht_van_de_taken_voor_de_burger_wordt_getoond() {
        assertThat(mijnTakenPage.takenCard).isVisible();
        assertThat(mijnTakenPage.takenOphalenFoutmelding).isHidden();
    }

    public void navigate() {
        page.navigate(MijnTakenPage.PAGE_URL);
    }

}
