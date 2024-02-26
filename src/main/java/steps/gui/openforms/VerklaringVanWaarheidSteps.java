package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.VerklaringVanWaarheidPage;
import utils.formvalues.FormStepNames;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerklaringVanWaarheidSteps extends OpenFormsSteps {

    private final VerklaringVanWaarheidPage verklaringVanWaarheidPage;

    public VerklaringVanWaarheidSteps(Page page) {
        super(page);
        this.verklaringVanWaarheidPage = new VerklaringVanWaarheidPage(page);
    }

    public void rond_formulierstap_af(boolean akkoord) {

        if (akkoord) {
            verklaringVanWaarheidPage.checkboxAkkoord.click();
        }

        ga_naar_volgende_formulierstap();
    }

    public void rond_stap_verklaring_van_waarheid_af_met_akkoord(boolean akkoord) {
        controleer_actieve_formulierstap_is(FormStepNames.VERKLARING_VAN_WAARHEID);
        this.rond_formulierstap_af(akkoord);
    }

    public void controleer_tekst_bij_checkbox_is(String verwachteTekst) {
        assertThat(verklaringVanWaarheidPage.checkboxAkkoord).hasText(verwachteTekst);
    }
}
