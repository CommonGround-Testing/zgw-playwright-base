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

    public void is_overzicht_zichtbaar_na_login() {
        assertThat(overzichtPage.headerPage).isVisible();
    }


    public void welkom_header_is_zichtbaar_en_juiste_taal(String text) {
        assertThat(overzichtPage.headerPage).containsText(text);
    }

    public void navigate() {
        page.navigate("/");
    }
}
