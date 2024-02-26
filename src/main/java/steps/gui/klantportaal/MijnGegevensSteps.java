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
        this.mijnGegevensPage = new MijnGegevensPage(page);
    }

    public void zijn_nationaliteit_en_naam_zichtbaar_en_correct(ZGWDigidUser user) {
        assertThat(mijnGegevensPage.mijnGegevensNationaliteit).hasText(user.getNationaliteit());
        assertThat(mijnGegevensPage.mijnGegevensNaam).hasText(user.getName());
    }

    public void selecteer_telefoonnummer_aanpassen() {
        mijnGegevensPage.telefoonNummerAanpassenKnop.click();
    }

    public String vul_nieuw_telefoonnummer_in_en_sla_op() {
        this.selecteer_telefoonnummer_aanpassen();
        var nieuwTelefoonnummer = TestDataGenerator.telefoonNummer();
        mijnGegevensPage.telefoonnummerInput.clear();
        mijnGegevensPage.telefoonnummerInput.fill(nieuwTelefoonnummer);
        mijnGegevensPage.opslaanButton.click();
        return nieuwTelefoonnummer;
    }

    public void is_nieuw_telefoonnummer_zichtbaar(String telNummer) {
        assertThat(mijnGegevensPage.telefoonNummer).hasText(telNummer);
    }

    public void is_nieuw_emailadres_zichtbaar(String email) {
        assertThat(mijnGegevensPage.emailAdres).hasText(email);
    }

    public String vul_nieuw_emailadres_in_en_sla_op() {
        mijnGegevensPage.emailAdresAanpassenKnop.click();
        var emailAdres = TestDataGenerator.emailAdres();
        mijnGegevensPage.emailAdresInput.clear();
        mijnGegevensPage.emailAdresInput.fill(emailAdres);
        mijnGegevensPage.opslaanButton.click();
        return emailAdres;
    }

    public void zijn_de_volgende_gegevens_zichtbaar_in_mijn_gegevens(List<String> verwachteKoppen) {
        verwachteKoppen.forEach(kop ->
                assertThat(page.locator(
                        String.format("//h3[contains(.,'%s')]", kop))).isVisible());
    }

    public void navigate() {
        page.navigate(MijnGegevensPage.PAGE_URL);
    }
}
