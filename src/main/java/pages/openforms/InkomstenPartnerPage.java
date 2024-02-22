package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InkomstenPartnerPage {

    public final Locator radiobuttonHeeftWerkgeverJa;
    public final Locator radiobuttonHeeftWerkgeverNee;
    public final Locator checkboxGeenUitkering;
    public final Locator radiobuttonKrijgtPensioenJa;
    public final Locator radiobuttonKrijgtPensioenNee;
    public final Locator radiobuttonKrijgtKinderalimentatieJa;
    public final Locator radiobuttonKrijgtKinderalimentatieNee;
    public final Locator radiobuttonHeeftInkomenUitOnderhuurJa;
    public final Locator radiobuttonHeeftInkomenUitOnderhuurNee;
    public final Locator radiobuttonIsOndernemerJa;
    public final Locator radiobuttonIsOndernemerNee;

    public InkomstenPartnerPage(Page page) {
        this.radiobuttonHeeftWerkgeverJa = page.locator("//input[@type='radio' and contains(@name,'data[heeftUwPartnerEenWerkgever]') and @value='j']");
        this.radiobuttonHeeftWerkgeverNee = page.locator("//input[@type='radio' and contains(@name,'data[heeftUwPartnerEenWerkgever]') and @value='n']");
        this.checkboxGeenUitkering = page.locator("//input[@type='checkbox' and contains(@name,'data[uitkeringenPartner]') and @value='nee']");
        this.radiobuttonKrijgtPensioenJa = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUwPartnerPensioen]') and @value='j']");
        this.radiobuttonKrijgtPensioenNee = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUwPartnerPensioen]') and @value='n']");
        this.radiobuttonKrijgtKinderalimentatieJa = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUwPartnerKinderAlimentatie]') and @value='j']");
        this.radiobuttonKrijgtKinderalimentatieNee = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUwPartnerKinderAlimentatie]') and @value='n']");
        this.radiobuttonHeeftInkomenUitOnderhuurJa = page.locator("//input[@type='radio' and contains(@name,'data[heeftUwPartnerInkomenUitOnderhuur]') and @value='j']");
        this.radiobuttonHeeftInkomenUitOnderhuurNee = page.locator("//input[@type='radio' and contains(@name,'data[heeftUwPartnerInkomenUitOnderhuur]') and @value='n']");
        this.radiobuttonIsOndernemerJa = page.locator("//input[@type='radio' and contains(@name,'data[isUwPartnerOndernemer]') and @value='j']");
        this.radiobuttonIsOndernemerNee = page.locator("//input[@type='radio' and contains(@name,'data[isUwPartnerOndernemer]') and @value='n']");
    }
}
