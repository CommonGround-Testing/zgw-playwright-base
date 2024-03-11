package steps.gui.openforms;

import com.microsoft.playwright.Page;

public class UwSituatieSteps extends OpenFormsSteps {

    public static String TEKST_HEEFT_U_BIJSTANDSUITKERING = "Heeft u op dit moment een bijstandsuitkering van de gemeente Den Haag?";
    public static String TEKST_SCHULDHULPVERLENINGSTRAJECT = "Zit u op dit moment in een schuldhulpverleningstraject van de gemeente Den Haag?";

    public UwSituatieSteps(Page page) {
        super(page);
    }

    public void rond_stap_uwsituatie_af() {
        selecteer_optie(TEKST_HEEFT_U_BIJSTANDSUITKERING, "Nee");
        selecteer_optie(TEKST_SCHULDHULPVERLENINGSTRAJECT, "Nee");
        klik_volgende_knop();
    }
}
