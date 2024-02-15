package steps.gui.klanportaal;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import pages.klantportaal.MijnGegevensPage;
import users.ZGWDigidUser;
import utils.TestDataGenerator;

import java.util.List;

public class MijnGegevensSteps extends KlantportaalSteps {
    public final MijnGegevensPage mijnGegevensPage;

    public MijnGegevensSteps(Page page) {
        super(page);
        this.mijnGegevensPage = new MijnGegevensPage(page);
    }

    public void zijn_nationaliteit_en_naam_zichtbaar_en_correct(ZGWDigidUser user) {
        mijnGegevensPage.mijnGegevensNationaliteit.waitFor();
        Assertions.assertTrue(user.getNationaliteit().equals(mijnGegevensPage.mijnGegevensNationaliteit.allTextContents()) &&
                user.getName().equals(mijnGegevensPage.mijnGegevensNaam.allTextContents()));
    }

    public void selecteer_telefoonnummer_aanpassen() {
        mijnGegevensPage.telefoonNummerAanpassenKnop.click();
    }

    public String vul_nieuw_telefoonnummer_in_en_sla_op() {
        this.selecteer_telefoonnummer_aanpassen();
        final String nieuwTelefoonnummer = TestDataGenerator.telefoonNummer();
        mijnGegevensPage.telefoonnummerInput.fill(nieuwTelefoonnummer);
        mijnGegevensPage.opslaanButton.click();
        return nieuwTelefoonnummer;
    }

    public void is_nieuw_telefoonnummer_zichtbaar(String telNummer) {
        mijnGegevensPage.mijnGegevensNationaliteit.waitFor();
        Assertions.assertTrue(telNummer.equals(mijnGegevensPage.telefoonNummer.allTextContents()));
    }

    public void is_nieuw_emailadres_zichtbaar(String email) {
        mijnGegevensPage.mijnGegevensNationaliteit.waitFor();
        Assertions.assertTrue(email.equals(mijnGegevensPage.emailAdres.allTextContents()));
    }

    public void selecteer_emailadres_aanpassen() {
        mijnGegevensPage.emailAdresAanpassenKnop.click();
    }

    public String vul_nieuw_emailadres_in_en_sla_op() {
        this.selecteer_emailadres_aanpassen();

        final String emailAdres = TestDataGenerator.emailAdres();

        mijnGegevensPage.emailAdresInput.fill(emailAdres);
        mijnGegevensPage.opslaanButton.click();
        return emailAdres;
    }

    public void zijn_de_volgende_gegevens_zichtbaar_in_mijn_gegevens(List<String> verwachteKoppen) {
        verwachteKoppen.forEach(kop -> Assertions.assertTrue(this.wordt_kop_h3_getoond(kop)));
    }

    public void navigate() {
        page.navigate("/account");
    }
}
