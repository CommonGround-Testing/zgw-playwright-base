package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.GezinssamenstellingPage;

public class GezinssamenstellingSteps extends OpenFormsSteps {

    public final GezinssamenstellingPage gezinssamenstellingPage;

    public GezinssamenstellingSteps(Page page) {
        super(page);
        this.gezinssamenstellingPage = new GezinssamenstellingPage(page);
    }

    public void kies_inwonende_partner(boolean keuze) {

        if (keuze) {
            gezinssamenstellingPage.
                    radiobuttonInwonendePartnerJa.click();
        } else {
            gezinssamenstellingPage.
                    radiobuttonInwonendePartnerNee.click();
        }
    }

    public void kies_inwonende_kinderen_onder_18(boolean keuze) {

        if (keuze) {
            gezinssamenstellingPage.
                    radiobuttonInwonendeKinderenOnder18Ja.click();
        } else {
            gezinssamenstellingPage.
                    radiobuttonInwonendeKinderenOnder18Nee.click();
        }
    }

    public void kies_student(boolean keuze) {

        if (keuze) {
            gezinssamenstellingPage.radiobuttonStudentJa.click();
        } else {
            gezinssamenstellingPage.radiobuttonStudentNee.click();
        }
    }

    public void kies_student_partner(boolean keuze) {

        if (keuze) {
            gezinssamenstellingPage.radiobuttonStudentPartnerJa.click();
        } else {
            gezinssamenstellingPage.radiobuttonStudentPartnerNee.click();
        }
    }

    public void vul_gegevens_partner_in() {

        gezinssamenstellingPage.textfieldAchternaamPartner.fill("van der Test");
        gezinssamenstellingPage.textfieldAchternaamPartner.press("Tab");
        gezinssamenstellingPage.textfieldVoorletterPartner.fill("X.");
        gezinssamenstellingPage.textfieldVoorletterPartner.press("Tab");
        gezinssamenstellingPage.textfieldBsnPartner.fill("123456782");
        gezinssamenstellingPage.textfieldBsnPartner.press("Tab");
        gezinssamenstellingPage.textfieldGeboortedatumPartner.fill("01-01-1970");
        gezinssamenstellingPage.textfieldGeboortedatumPartner.press("Tab");
    }

    public void geen_partner_geen_kinderen_geen_student() {

        this.kies_inwonende_partner(false);
        this.kies_inwonende_kinderen_onder_18(false);
        this.kies_student(false);
    }

    public void wel_partner_geen_kinderen_geen_student() {

        this.kies_inwonende_partner(true);
        this.vul_gegevens_partner_in();
        this.kies_inwonende_kinderen_onder_18(false);
        this.kies_student(false);
        this.kies_student_partner(false);
    }

    public void rond_stap_gezinssamenstelling_af() {

        this.click_volgende_button();
    }
}
