package steps.gui.klantportaal;

import com.microsoft.playwright.Page;
import pages.klantportaal.OverzichtPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OverzichtSteps extends KlantportaalSteps {
    private final OverzichtPage overzichtPage;

    public OverzichtSteps(Page page) {
        super(page);
        overzichtPage = new OverzichtPage(page);
    }

    /**
     * Valideer dat er een H2 header op het scherm staat
     *
     */
    public void is_overzicht_zichtbaar_na_login() {
        assertThat(overzichtPage.headerPage).isVisible();
    }

    /**
     * Valideer dat de Header correct een bepaalde tekst bevat
     *
     * @param text van de header
     */
    public void welkom_header_is_zichtbaar_en_juiste_taal(String text) {
        assertThat(overzichtPage.headerPage).containsText(text);
    }

    /**
     * Open het overzicht
     *
     */
    public void navigate() {
        page.navigate("/");
    }
}
