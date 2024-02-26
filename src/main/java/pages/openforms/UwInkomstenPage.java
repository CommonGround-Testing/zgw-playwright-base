package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class UwInkomstenPage {

    public final Locator radiobuttonHeeftWerkgeverJa;
    public final Locator radiobuttonHeeftWerkgeverNee;
    public final Locator checkboxGeenUitkering;
    public final Locator radiobuttonKrijgtPensioenJa;
    public final Locator radiobuttonKrijgtPensioenNee;
    public final Locator radiobuttonKrijgtPartneralimentatieJa;
    public final Locator radiobuttonKrijgtPartneralimentatieNee;
    public final Locator radiobuttonKrijgtKinderalimentatieJa;
    public final Locator radiobuttonKrijgtKinderalimentatieNee;
    public final Locator radiobuttonHeeftInkomenUitOnderhuurJa;
    public final Locator radiobuttonHeeftInkomenUitOnderhuurNee;
    public final Locator textfieldBedragOnderhuur;
    public final Locator radiobuttonIsOndernemerJa;
    public final Locator radiobuttonIsOndernemerNee;

    public UwInkomstenPage(Page page) {
        this.radiobuttonHeeftWerkgeverJa = page.locator("//input[@type='radio' and contains(@name,'data[heeftUEenWerkgever]') and @value='ja']");
        this.radiobuttonHeeftWerkgeverNee = page.locator("//input[@type='radio' and contains(@name,'data[heeftUEenWerkgever]') and @value='nee']");
        this.checkboxGeenUitkering = page.locator("//input[@type='checkbox' and contains(@name,'data[uitkeringen]') and @value='nee']");
        this.radiobuttonKrijgtPensioenJa = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUPensioen]') and @value='ja']");
        this.radiobuttonKrijgtPensioenNee = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUPensioen]') and @value='nee']");
        this.radiobuttonKrijgtPartneralimentatieJa = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUPartneralimentatie]') and @value='ja']");
        this.radiobuttonKrijgtPartneralimentatieNee = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUPartneralimentatie]') and @value='nee']");
        this.radiobuttonKrijgtKinderalimentatieJa = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUKinderalimentatie]') and @value='ja']");
        this.radiobuttonKrijgtKinderalimentatieNee = page.locator("//input[@type='radio' and contains(@name,'data[krijgtUKinderalimentatie]') and @value='nee']");
        this.radiobuttonHeeftInkomenUitOnderhuurJa = page.locator("//input[@type='radio' and contains(@name,'data[heeftUInkomenUitOnderhuur]') and @value='ja']");
        this.radiobuttonHeeftInkomenUitOnderhuurNee = page.locator("//input[@type='radio' and contains(@name,'data[heeftUInkomenUitOnderhuur]') and @value='nee']");
        this.textfieldBedragOnderhuur = page.locator("//input[@type='text' and contains(@name,'data[bedragOnderhuur]')]");
        this.radiobuttonIsOndernemerJa = page.locator("//input[@type='radio' and contains(@name,'data[bentUOndernemer]') and @value='ja']");
        this.radiobuttonIsOndernemerNee = page.locator("//input[@type='radio' and contains(@name,'data[bentUOndernemer]') and @value='nee']");
    }
}
