package steps.gui.openforms;

import com.microsoft.playwright.Page;

public class InkomstenPartnerSteps extends UwInkomstenSteps {

    public static String TEKST_HEEFT_PARTNER_WERKGEVER = "Heeft uw partner een werkgever?";
    public static String TEKST_HEEFT_PARTNER_UITKERING = "Heeft uw partner een van deze uitkeringen? Kruis al de uitkeringen aan.";
    public static String TEKST_KRIJGT_PARTNER_PENSIOEN = "Krijgt uw partner pensioen?";
    public static String TEKST_KRIJGT_PARTNER_KINDER_ALIMENTATIE = "Krijgt uw partner alimentatie voor zijn of haar kinderen? (kinderalimentatie)";
    public static String TEKST_PARTNER_INKOMEN_ONDERHUUR = "Heeft uw partner inkomen uit onderhuur?";
    public static String TEKST_IS_PARTNER_ONDERNEMER = "Is uw partner ondernemer?";

    public InkomstenPartnerSteps(Page page) {
        super(page);
    }

    public void kies_nee_voor_alle_vragen() {
        selecteer_optie(TEKST_HEEFT_PARTNER_WERKGEVER, "Nee");
        selecteer_optie(TEKST_HEEFT_PARTNER_UITKERING, "Nee");
        selecteer_optie(TEKST_KRIJGT_PARTNER_PENSIOEN, "Nee");
        if(get_radiogroep(TEKST_KRIJGT_PARTNER_KINDER_ALIMENTATIE).isVisible()){
            selecteer_optie(TEKST_KRIJGT_PARTNER_KINDER_ALIMENTATIE, "Nee");
        }
        if(get_radiogroep(TEKST_KRIJGT_U_KINDER_ALIMENTATIE).isVisible()) {
            selecteer_optie(TEKST_KRIJGT_U_KINDER_ALIMENTATIE, "Nee");
        }
        selecteer_optie(TEKST_PARTNER_INKOMEN_ONDERHUUR, "Nee");
        selecteer_optie(TEKST_IS_PARTNER_ONDERNEMER, "Nee");
    }

}
