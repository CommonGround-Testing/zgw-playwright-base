package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GezinssamenstellingPage {

    public final Locator radiobuttonInwonendePartnerJa;
    public final Locator radiobuttonInwonendePartnerNee;
    public final Locator radiobuttonInwonendeKinderenOnder18Ja;
    public final Locator radiobuttonInwonendeKinderenOnder18Nee;
    public final Locator radiobuttonStudentJa;
    public final Locator radiobuttonStudentNee;
    public final Locator textfieldAchternaamPartner;
    public final Locator textfieldVoorletterPartner;
    public final Locator textfieldBsnPartner;
    public final Locator textfieldGeboortedatumPartner;
    public final Locator radiobuttonStudentPartnerJa;
    public final Locator radiobuttonStudentPartnerNee;

    public GezinssamenstellingPage(Page page) {
        this.radiobuttonInwonendePartnerJa = page.locator("//input[@type='radio' and contains(@name,'data[inwonendePartner]') and @value='ja']");
        this.radiobuttonInwonendePartnerNee = page.locator("//input[@type='radio' and contains(@name,'data[inwonendePartner]') and @value='nee']");
        this.radiobuttonInwonendeKinderenOnder18Ja = page.locator("//input[@type='radio' and contains(@name,'data[inwonendeKinderen18]') and @value='ja']");
        this.radiobuttonInwonendeKinderenOnder18Nee = page.locator("//input[@type='radio' and contains(@name,'data[inwonendeKinderen18]') and @value='nee']");
        this.radiobuttonStudentJa = page.locator("//input[@type='radio' and contains(@name,'data[HOStudent]') and @value='ja']");
        this.radiobuttonStudentNee = page.locator("//input[@type='radio' and contains(@name,'data[HOStudent]') and @value='nee']");
        this.textfieldAchternaamPartner = page.locator("//*[@name = 'data[achternaamPartner]']");
        this.textfieldVoorletterPartner = page.locator("//*[@name = 'data[voorletterPartner]']");
        this.textfieldBsnPartner = page.locator("//*[@name = 'data[burgerservicenummerPartner]']");
        this.textfieldGeboortedatumPartner = page.locator("//input[@placeholder='dd-mm-yyyy' and @type!='hidden']");
        this.radiobuttonStudentPartnerJa = page.locator("//input[@type='radio' and contains(@name,'data[HOStudentPartner]') and @value='ja']");
        this.radiobuttonStudentPartnerNee = page.locator("//input[@type='radio' and contains(@name,'data[HOStudentPartner]') and @value='nee']");
    }
}
