package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class UwSituatiePage {

    public final Locator radiobuttonBijstandsuitkeringJa;
    public final Locator radiobuttonBijstandsuitkeringNee;
    public final Locator radiobuttonSchuldhulpverleningJa;
    public final Locator radiobuttonSchuldhulpverleningNee;

    public UwSituatiePage(Page page) {
        this.radiobuttonBijstandsuitkeringJa = page.locator("//input[@type='radio' and contains(@name,'data[OntvangtBijstandsuitkering]') and @value='ja']");
        this.radiobuttonBijstandsuitkeringNee = page.locator("//input[@type='radio' and contains(@name,'data[OntvangtBijstandsuitkering]') and @value='nee']");
        this.radiobuttonSchuldhulpverleningJa = page.locator("//input[@type='radio' and contains(@name,'data[inSchuldhulpverlening]') and @value='ja']");
        this.radiobuttonSchuldhulpverleningNee = page.locator("//input[@type='radio' and contains(@name,'data[inSchuldhulpverlening]') and @value='nee']");
    }
}
