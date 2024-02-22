package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.InkomstenPartnerPage;

public class InkomstenPartnerSteps extends OpenFormsSteps {

    private final InkomstenPartnerPage inkomstenPartnerPage;

    public InkomstenPartnerSteps(Page page) {
        super(page);
        this.inkomstenPartnerPage = new InkomstenPartnerPage(page);
    }

    public void kies_heeft_werkgever(boolean keuze) {

        if (keuze) {
            inkomstenPartnerPage.radiobuttonHeeftWerkgeverJa.click();
        } else {
            inkomstenPartnerPage.radiobuttonHeeftWerkgeverNee.click();
        }
    }

    public void kies_geen_uitkering() {

        inkomstenPartnerPage.checkboxGeenUitkering.click();
    }

    public void kies_krijgt_pensioen(boolean keuze) {

        if (keuze) {
            inkomstenPartnerPage.radiobuttonKrijgtPensioenJa.click();
        } else {
            inkomstenPartnerPage.radiobuttonKrijgtPensioenNee.click();
        }
    }

    public void kies_krijgt_kinderalimentatie(boolean keuze) {

        if (keuze) {
            inkomstenPartnerPage.radiobuttonKrijgtKinderalimentatieJa.click();
        } else {
            inkomstenPartnerPage.radiobuttonKrijgtKinderalimentatieNee.click();
        }
    }

    public void kies_heeft_inkomen_uit_onderhuur(boolean keuze) {

        if (keuze) {
            inkomstenPartnerPage.radiobuttonHeeftInkomenUitOnderhuurJa.click();
        } else {
            inkomstenPartnerPage.radiobuttonHeeftInkomenUitOnderhuurNee.click();
        }
    }

    public void kies_is_ondernemer(boolean keuze) {

        if (keuze) {
            inkomstenPartnerPage.radiobuttonIsOndernemerJa.click();
        } else {
            inkomstenPartnerPage.radiobuttonIsOndernemerNee.click();
        }
    }

    public void rond_formulierstap_af() {

        ga_naar_volgende_formulierstap();
    }
}
