package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.BezitEnSchuldenPage;
import utils.formvalues.FormStepNames;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BezitEnSchuldenSteps extends OpenFormsSteps {

    private final Page page;
    private final BezitEnSchuldenPage bezitEnSchuldenPage;

    public BezitEnSchuldenSteps(Page page) {
        super(page);
        this.page = page;
        this.bezitEnSchuldenPage = new BezitEnSchuldenPage(page);
    }

    public void rond_formulierstap_af() {

        bezitEnSchuldenPage.checkboxContanten.click();
        bezitEnSchuldenPage.textfieldContantGeld.fill("100");
        bezitEnSchuldenPage.textfieldContantGeld.press("Tab");
        ga_naar_volgende_formulierstap();
    }

    public void rond_stap_bezit_en_schulden_overzicht_af() {
        assertThat(openFormsPage.linkActiveStep).hasText(FormStepNames.BEZIT_EN_SCHULDEN_OVERZICHT);
        this.ga_naar_volgende_formulierstap();
    }
}
