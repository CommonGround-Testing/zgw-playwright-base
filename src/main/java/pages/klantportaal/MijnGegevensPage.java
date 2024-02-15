package pages.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MijnGegevensPage {


    public final static String PAGE_URL = "/account";
    public final Locator telefoonNummerAanpassenKnop;
    public final Locator telefoonnummerInput;
    public final Locator emailAdresInput;
    public final Locator opslaanButton;
    public final Locator mijnGegevensNationaliteit;
    public final Locator mijnGegevensNaam;
    public final Locator telefoonNummer;
    public final Locator emailAdres;
    public final Locator emailAdresAanpassenKnop;

    public MijnGegevensPage(Page page) {
        this.telefoonNummerAanpassenKnop = page.locator("//b[text()='Telefoonnummer']/../../div/a/span[text()='Aanpassen']");
        this.telefoonnummerInput = page.getByLabel("telefoonnummer");
        this.emailAdresInput = page.getByLabel("e-mailadres");
        this.opslaanButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Opslaan"));
        this.mijnGegevensNationaliteit = page.locator("//b[text()='Nationaliteit']/../../div/span");
        this.mijnGegevensNaam = page.locator("//b[text()='Voornamen']/../../div/span");
        this.telefoonNummer = page.locator("//b[text()='Telefoonnummer']/../../div/span");
        this.emailAdres = page.locator("//b[text()='E-mailadres']/../../div/span");
        this.emailAdresAanpassenKnop = page.locator("//b[text()='E-mailadres']/../../div/a/span[text()='Aanpassen']");
    }
}
