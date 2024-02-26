package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.UwSituatiePage;
import utils.formvalues.FormStepNames;

public class UwSituatieSteps extends OpenFormsSteps {

    private final UwSituatiePage uwSituatiePage;

    public UwSituatieSteps(Page page) {
        super(page);
        this.uwSituatiePage = new UwSituatiePage(page);
    }

    public void kies_uitkering(boolean keuze) {

        if (keuze) {
            uwSituatiePage.radiobuttonBijstandsuitkeringJa.click();
        } else {
            uwSituatiePage.radiobuttonBijstandsuitkeringNee.click();
        }
    }

    public void kies_schuldhulpverlening(boolean keuze) {

        if (keuze) {
            uwSituatiePage.radiobuttonSchuldhulpverleningJa.click();
        } else {
            uwSituatiePage.radiobuttonSchuldhulpverleningNee.click();
        }
    }

    public void burger_heeft_uitkering_is(boolean keuze) {

        this.kies_uitkering(keuze);
    }

    public void burger_zit_in_schuldhulpverlening_is(boolean keuze) {

        this.kies_schuldhulpverlening(keuze);
    }

    public void rond_stap_uwsituatie_af() {
        this.controleer_actieve_formulierstap_is(FormStepNames.UW_SITUATIE);
        this.kies_uitkering(false);
        this.kies_schuldhulpverlening(false);
        ga_naar_volgende_formulierstap();
    }
}
