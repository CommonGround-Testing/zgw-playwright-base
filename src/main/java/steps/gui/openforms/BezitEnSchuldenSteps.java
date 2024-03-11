package steps.gui.openforms;

import com.microsoft.playwright.Page;
import utils.formvalues.FormStepNames;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BezitEnSchuldenSteps extends OpenFormsSteps {

    public static String TEKST_HEEFT_U_GELD = "Heeft u, uw partner of uw kinderen onder de 18 jaar, geld in één of meerdere van deze vormen?";
    public static String TEKST_HOEVEEL_GELD = "Hoeveel contant geld heeft u?";

    public BezitEnSchuldenSteps(Page page) {
        super(page);
    }

    public void rond_formulierstap_af() {
        selecteer_optie(TEKST_HEEFT_U_GELD, "Contanten");
        vul_tekst_in(TEKST_HOEVEEL_GELD, "100");
        klik_volgende_knop();
    }

    public void rond_stap_bezit_en_schulden_overzicht_af() {
        assertThat(openFormsPage.linkActiveStep).hasText(FormStepNames.BEZIT_EN_SCHULDEN_OVERZICHT);
        klik_volgende_knop();
    }
}
