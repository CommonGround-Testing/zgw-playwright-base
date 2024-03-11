package steps.gui.openforms;

import com.microsoft.playwright.Page;

public class UwInkomstenSteps extends OpenFormsSteps {

    public static String TEKST_HEEFT_U_WERKGEVER = "Heeft u een werkgever?";
    public static String TEKST_HEEFT_U_UITKERING = "Heeft u een van deze uitkeringen? Kruis al uw uitkeringen aan.";
    public static String TEKST_KRIJGT_U_PENSIOEN = "Krijgt u pensioen?";
    public static String TEKST_KRIJGT_U_ALIMENTATIE = "Krijgt u alimentatie van een ex-partner? (partneralimentatie)";
    public static String TEKST_HOEVEEL_ALIMENTATIE_KRIJGT_U = "Hoeveel alimentatie krijg u zelf netto per maand van uw ex-partner (partneralimentatie)";
    public static String TEKST_KRIJGT_U_KINDER_ALIMENTATIE = "Krijgt u alimentatie voor uw kinderen (kinderalimentatie)?";
    public static String TEKST_INKOMEN_UIT_ONDERHUUR = "Heeft u inkomen uit onderhuur?";
    public static String TEKST_BENT_U_ONDERNEMER = "Bent u ondernemer?";
    public static String TEKST_HOEVEEL_ONDERHUUR_KRIJGT_U = "Hoeveel krijgt u totaal aan onderhuur per maand?";

    public UwInkomstenSteps(Page page) {
        super(page);
    }

    public void kies_nee_voor_alle_vragen() {
        selecteer_optie(TEKST_HEEFT_U_WERKGEVER, "Nee");
        selecteer_optie(TEKST_HEEFT_U_UITKERING, "Nee");
        selecteer_optie(TEKST_KRIJGT_U_PENSIOEN, "Nee");
        if(get_radiogroep(TEKST_KRIJGT_U_ALIMENTATIE).isVisible()){
            selecteer_optie(TEKST_KRIJGT_U_ALIMENTATIE, "Nee");
        }
        if(get_radiogroep(TEKST_KRIJGT_U_KINDER_ALIMENTATIE).isVisible()) {
            selecteer_optie(TEKST_KRIJGT_U_KINDER_ALIMENTATIE, "Nee");
        }
        selecteer_optie(TEKST_INKOMEN_UIT_ONDERHUUR, "Nee");
        selecteer_optie(TEKST_BENT_U_ONDERNEMER, "Nee");
    }
}
