package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.UwInkomstenPage;
import utils.formvalues.FormStepNames;

public class UwInkomstenSteps extends OpenFormsSteps {

    private final UwInkomstenPage uwInkomstenPage;

    public UwInkomstenSteps(Page page) {
        super(page);
        this.uwInkomstenPage = new UwInkomstenPage(page);
    }

    public void kies_heeft_werkgever(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonHeeftWerkgeverJa.click();
        } else {
            uwInkomstenPage.radiobuttonHeeftWerkgeverNee.click();
        }
    }

    public void kies_geen_uitkering() {

        uwInkomstenPage.checkboxGeenUitkering.click();
    }

    public void kies_krijgt_pensioen(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonKrijgtPensioenJa.click();
        } else {
            uwInkomstenPage.radiobuttonKrijgtPensioenNee.click();
        }
    }

    public void kies_krijgt_partneralimentatie(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonKrijgtPartneralimentatieJa.click();
        } else {
            uwInkomstenPage.radiobuttonKrijgtPartneralimentatieNee.click();
        }
    }

    public void kies_krijgt_kinderalimentatie(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonKrijgtKinderalimentatieJa.click();
        } else {
            uwInkomstenPage.radiobuttonKrijgtKinderalimentatieNee.click();
        }
    }

    public void kies_heeft_inkomen_uit_onderhuur(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonHeeftInkomenUitOnderhuurJa.click();
        } else {
            uwInkomstenPage.radiobuttonHeeftInkomenUitOnderhuurNee.click();
        }
    }

    public void vul_bedrag_onderhuur_in(String bedrag) {

        uwInkomstenPage.textfieldBedragOnderhuur.fill(bedrag);
        uwInkomstenPage.textfieldBedragOnderhuur.press("Tab");
    }

    public void kies_is_ondernemer(boolean keuze) {

        if (keuze) {
            uwInkomstenPage.radiobuttonIsOndernemerJa.click();
        } else {
            uwInkomstenPage.radiobuttonIsOndernemerNee.click();
        }
    }

    public void rond_stap_uwinkomsten_overzicht_af() {
        this.controleer_actieve_formulierstap_is(FormStepNames.UW_INKOMSTEN_OVERZICHT);
        this.ga_naar_volgende_formulierstap();
    }

    public void kies_nee_voor_alle_vragen_geen_partner() {
        this.kies_heeft_werkgever(false);
        this.kies_geen_uitkering();
        this.kies_krijgt_pensioen(false);
        this.kies_krijgt_partneralimentatie(false);
        this.kies_heeft_inkomen_uit_onderhuur(false);
        this.kies_is_ondernemer(false);
    }

    public void kies_nee_voor_alle_vragen_wel_partner() {
        this.kies_heeft_werkgever(false);
        this.kies_geen_uitkering();
        this.kies_krijgt_pensioen(false);
        this.kies_krijgt_kinderalimentatie(false);
        this.kies_heeft_inkomen_uit_onderhuur(false);
        this.kies_is_ondernemer(false);
    }

    public void geef_inkomsten_uit_onderhuur_op_met_bedrag(String bedrag) {
        this.kies_heeft_inkomen_uit_onderhuur(true);
        this.vul_bedrag_onderhuur_in(bedrag);
    }

    public void rond_stap_uwinkomsten_af() {
        this.controleer_actieve_formulierstap_is(FormStepNames.UW_INKOMSTEN);
        this.ga_naar_volgende_formulierstap();
    }
}
