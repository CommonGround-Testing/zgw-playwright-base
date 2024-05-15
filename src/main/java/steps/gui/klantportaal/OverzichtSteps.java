package steps.gui.klantportaal;

import com.microsoft.playwright.Page;
import pages.klantportaal.OverzichtPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OverzichtSteps extends KlantportaalSteps {
    public final OverzichtPage overzichtPage;

    public OverzichtSteps(Page page) {
        super(page);
        overzichtPage = new OverzichtPage(page);
    }

    /**
     * Valideer dat de Header correct een bepaalde tekst bevat
     *
     * @param text van de header
     */
    public void valideer_overzicht_header(String text) {
        overzichtPage.pageHeader.waitFor();
        assertThat(overzichtPage.pageHeader).containsText(text);
    }

    /**
     * Open het overzicht
     */
    public void navigate() {
        page.navigate(OverzichtPage.PAGE_URL);
    }

    /**
     * Op het overzicht zie je 4 lopende zaken
     * 1 - 2
     * 3 - 4
     *
     * @param nummer van de kaart die je wilt openen
     */
    public void klik_op_lopende_zaak_kaart(int nummer){
        overzichtPage.zaakTegels.nth(nummer-1).click();
    }

    /**
     * Klik op de link voor alle taken
     */
    public void klik_op_alle_taken_link(){
        overzichtPage.linkAlleTaken.click();
    }

    /**
     * Klik op een taak op het overzicht
     *
     * @param nummer van de taak
     */
    public void klik_op_taak(int nummer){
        overzichtPage.linkTaak.nth(nummer -1).click();
    }
}
