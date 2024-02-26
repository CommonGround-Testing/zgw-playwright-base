package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class UwGegevensPage {

    public final Locator linkPersoonsgegevensAanpassen;
    public final Locator textfieldEmailAdres;
    public final Locator textfieldTelefoonnummer;

    public UwGegevensPage(Page page) {
        this.linkPersoonsgegevensAanpassen = page
                .getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("persoonsgegevens aanpassen."));
        this.textfieldEmailAdres = page.locator("//*[@name='data[email]']");
        this.textfieldTelefoonnummer = page.locator("//*[@name='data[Telefoonnummer]']");
    }
}
