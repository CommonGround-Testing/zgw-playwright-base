package steps.gui.openforms;

import com.microsoft.playwright.Page;

public class VerklaringVanWaarheidSteps extends OpenFormsSteps {

    public static String TEKST_FORMULIER_NAAR_WAARHEID = "Ik vul dit formulier naar waarheid in.";
    public static String TEKST_FORMULIER_NAAR_WAARHEID_WAARSCHUWING = "Het verplichte veld Ik vul dit formulier naar waarheid in. is niet ingevuld.";

    public VerklaringVanWaarheidSteps(Page page) {
        super(page);
    }

    public void rond_naarwaarheid_stap_af() {
        get_checkbox(TEKST_FORMULIER_NAAR_WAARHEID).check();
        klik_volgende_knop();
    }
}
