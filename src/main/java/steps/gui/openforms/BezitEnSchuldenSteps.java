package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.BezitEnSchuldenPage;

public class BezitEnSchuldenSteps {

    private final Page page;
    private final BezitEnSchuldenPage bezitEnSchuldenPage;

    public BezitEnSchuldenSteps(Page page) {
        this.page = page;
        this.bezitEnSchuldenPage = new BezitEnSchuldenPage(page);
    }

    public void rond_formulierstap_af() {

        bezitEnSchuldenPage.checkboxContanten.click();
        bezitEnSchuldenPage.textfieldContantGeld.fill("100");
        bezitEnSchuldenPage.textfieldContantGeld.tap();
        //ga_naar_volgende_formulierstap();
    }
}
