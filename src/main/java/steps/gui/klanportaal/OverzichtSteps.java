package steps.gui.klanportaal;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import pages.klantportaal.OverzichtPage;

public class OverzichtSteps extends KlantportaalSteps {
    private final OverzichtPage overzichtPage;


    public OverzichtSteps(Page page) {
        super(page);
        this.overzichtPage = new OverzichtPage(page);

    }

    public void is_overzicht_zichtbaar_na_login() {
        overzichtPage.overzichtZaakTegel.waitFor();
        Assertions.assertTrue(overzichtPage.headerPage.isVisible() &&
                overzichtPage.overzichtZaakTegel.isVisible());
    }


    public void welkom_header_is_zichtbaar_en_juiste_taal(String text) {
        Assertions.assertTrue(overzichtPage.headerPage.isVisible()
                && overzichtPage.headerPage.allTextContents().contains(text));
    }

    public void navigate() {
        page.navigate("/");
    }
}
