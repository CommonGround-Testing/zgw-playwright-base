package steps.gui.klanportaal;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import pages.klantportaal.MijnTakenPage;

public class MijnTakenSteps extends KlantportaalSteps {

    private final MijnTakenPage mijnTakenPage;

    public MijnTakenSteps(Page page) {
        super(page);
        this.mijnTakenPage = new MijnTakenPage(page);
    }

    public boolean taken_ophalen_foutmelding_is_zichtbaar() {
        return mijnTakenPage.takenOphalenFoutmelding.isVisible();
    }

    public boolean taken_voor_gebruiker_zijn_zichtbaar() {
        return mijnTakenPage.takenCard.isVisible()
                || mijnTakenPage.geenTakenVoorDeHuidigeGebruiker.isVisible();
    }

    public void navigate() {
        page.navigate("/taken");
    }

    public void een_overzicht_van_de_taken_voor_de_burger_wordt_getoond() {
        mijnTakenPage.takenCard.waitFor();
        Assertions.assertTrue(this.taken_voor_gebruiker_zijn_zichtbaar());
        Assertions.assertFalse(this.taken_ophalen_foutmelding_is_zichtbaar());
    }


}
