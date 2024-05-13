package steps.gui.klantportaal;

import com.microsoft.playwright.Page;
import pages.klantportaal.MijnGegevensPage;
import users.ZGWDigidUser;
import utils.TestDataGenerator;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MijnGegevensSteps extends KlantportaalSteps {
    public final MijnGegevensPage mijnGegevensPage;

    public MijnGegevensSteps(Page page) {
        super(page);
        mijnGegevensPage = new MijnGegevensPage(page);
    }

    /**
     * Valideer of nationaliteit en de naam correct op het scherm staan
     *
     * @param user gegevens
     */
    public void valideer_dat_nationaliteit_en_naam_zichtbaar_zijn(ZGWDigidUser user) {
        assertThat(mijnGegevensPage.mijnGegevensNationaliteit).hasText(user.getNationaliteit());
        assertThat(mijnGegevensPage.mijnGegevensNaam).hasText(user.getName());
    }

    /**
     * Klik op de knop met de tekst 'Telefoonnummer aanpassen
     */
    public void selecteer_telefoonnummer_aanpassen() {
        mijnGegevensPage.telefoonNummerAanpassenKnop.click();
    }

    /**
     * Maak een random telefoonnummer aan en vul dit in
     *
     * @return String met ingevulde telefoonnummer
     */
    public String vul_nieuw_telefoonnummer_in_en_sla_op() {
        this.selecteer_telefoonnummer_aanpassen();
        var nieuwTelefoonnummer = TestDataGenerator.telefoonNummer();
        mijnGegevensPage.telefoonnummerInput.clear();
        mijnGegevensPage.telefoonnummerInput.fill(nieuwTelefoonnummer);
        mijnGegevensPage.opslaanButton.click();
        return nieuwTelefoonnummer;
    }

    /**
     * Valideer dat het telefoonnummer op het scherm staat
     *
     * @param telNummer
     */
    public void valideer_dat_nieuw_telefoonnummer_zichtbaar_is(String telNummer) {
        assertThat(mijnGegevensPage.telefoonNummer).hasText(telNummer);
    }

    /**
     * Valideer dat het email adres op het scherm staat
     *
     * @param email
     */
    public void valideer_dat_nieuw_emailadres_zichtbaar_is(String email) {
        assertThat(mijnGegevensPage.emailAdres).hasText(email);
    }

    /**
     * Maak een random email adres aan en vul dit in
     *
     * @return email adres
     */
    public String vul_nieuw_emailadres_in_en_sla_op() {
        mijnGegevensPage.emailAdresAanpassenKnop.click();
        var emailAdres = TestDataGenerator.emailAdres();
        mijnGegevensPage.emailAdresInput.clear();
        mijnGegevensPage.emailAdresInput.fill(emailAdres);
        mijnGegevensPage.opslaanButton.click();
        return emailAdres;
    }

    /**
     * Valideer dat de koppen op het scherm staan
     *
     * @param verwachteKoppen
     */
    public void valideer_verwachte_headers(List<String> verwachteKoppen) {
        verwachteKoppen.forEach(kop ->
                assertThat(page.locator(
                        String.format("//h3[contains(.,'%s')]", kop))).isVisible());
    }

    /**
     * Open de Mijn gegevens pagina
     *
     */
    public void navigate() {
        page.navigate(MijnGegevensPage.PAGE_URL);
    }
}
