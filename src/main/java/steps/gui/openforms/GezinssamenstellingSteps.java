package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.GeneriekeOpenformsPage;

public class GezinssamenstellingSteps extends OpenFormsSteps {

    public static String TEKST_INWONENDE_PARTNER = "Heeft u een partner die bij u woont?";
    public static String TEKST_KINDEREN_ONDER_18 = "Heeft u kinderen onder de 18 jaar bij u wonen?";
    public static String TEKST_STUDENT = "Bent u HBO / WO student?";
    public static String TEKST_IS_PARTNER_STUDENT = "Is uw partner HBO / WO student?";

    // Extra opties bij partner
    public static String TEKST_GEGEVENS_PARTNER = "Gegevens van uw partner";
    public static String TEKST_ACHTERNAAM = "Achternaam";
    public static String TEKST_VOORVOEGSELS = "Voorvoegsel(s) (optioneel)";
    public static String TEKST_VOORLETTERS = "Voorletter(s)";
    public static String TEKST_BURGERSERVICENUMMER = "Burgerservicenummer";
    public static String TEKST_GEBOORTEDATUM = "Geboortedatum";
    public static String TEKST_UPLOAD_IDENTITEITS_BEWIJS = "Upload hier het Identiteitsbewijs (paspoort of ID-kaart) van uw partner. (optioneel)";

    // Extra optie bij Kind onder 18
    public static String TEKST_GEGEVENS_KINDEREN = "Gegevens van uw kinderen";
    public static String TEKST_GEGEVENS_KIND_INVULLEN = "Gegevens van kind invullen";

    public static GeneriekeOpenformsPage openformsPage;

    public GezinssamenstellingSteps(Page page) {
        super(page);
        openformsPage = new GeneriekeOpenformsPage(page);
    }

    public void vul_gegevens_partner_in() {
        openformsPage.fillTextInputField(TEKST_ACHTERNAAM, "Van der Test", false);
        openformsPage.fillTextInputField(TEKST_VOORLETTERS, "X.", false);
        openformsPage.fillTextInputField(TEKST_BURGERSERVICENUMMER, "123456782", false);
        openformsPage.fillTextInputField(TEKST_GEBOORTEDATUM, "01-01-1970", false);
    }

    public void selecteer_geen_partner_geen_kinderen_geen_student() {
        openformsPage.selectOption(TEKST_INWONENDE_PARTNER, "Nee");
        openformsPage.selectOption(TEKST_KINDEREN_ONDER_18, "Nee");
        openformsPage.selectOption(TEKST_STUDENT, "Nee");
    }

    /**
     * Let op, dit kan alleen als de gegevens van de partner niet op het scherm staan
     * anders kunnen de velden niet gevonden worden omdat ze 2 x op het scherm staan
     */
    public void vul_gegevens_kind_in(){
        klik_knop(TEKST_GEGEVENS_KIND_INVULLEN);
        openformsPage.fillTextInputField(TEKST_ACHTERNAAM, "Van der Test", false);
        openformsPage.fillTextInputField(TEKST_BURGERSERVICENUMMER, "123456782", false);
        openformsPage.fillTextInputField(TEKST_GEBOORTEDATUM, "01-01-2020", false);
    }
}
