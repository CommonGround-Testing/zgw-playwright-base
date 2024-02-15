package steps.gui.klanportaal;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.klantportaal.KlantportaalPage;
import steps.gui.login.DigidLoginSteps;
import steps.gui.login.EherkenningSteps;
import users.ZGWUser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class KlantportaalSteps {

    protected final Page page;
    protected final KlantportaalPage klantportaalPage;

    private final DigidLoginSteps digidLoginSteps;
    private final EherkenningSteps eherkenningSteps;

    public KlantportaalSteps(Page page) {
        this.page = page;
        this.klantportaalPage = new KlantportaalPage(page);
        this.digidLoginSteps = new DigidLoginSteps(page);
        this.eherkenningSteps = new EherkenningSteps(page);
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void login_als_burger_on_page(ZGWUser user, String url) {
        this.navigate(url);
        klantportaalPage.inloggenDigidLink.click();
        digidLoginSteps.login_als(user.getUsername(), user.getPassword());
        klantportaalPage.gebruikersMenuBurgerButton.isVisible();
    }

    public void log_in_op_het_klantportaal_via_digid_machtigen(ZGWUser user, String url) {
        this.navigate(url);
        // this.login_met_ad(); TODO weer activeren na ZP-1256
        this.selecteer_optie_inloggen_met_digid_machtigen();
        digidLoginSteps.login_als(user.getUsername(), user.getPassword());
        digidLoginSteps.selecteer_machtiginggever();
    }

    public void een_ondernemer_logt_in_op_het_klantportaal(ZGWUser user, String url) {
        this.navigate(url);
        // this.login_met_ad(); TODO weer activeren na ZP-1256
        this.selecteer_optie_inloggen_met_eherkenning();
        this.eherkenningSteps.login_als(user.getUsername(), user.getPassword());
        klantportaalPage.gebruikersMenuOndernemerButton.isVisible();
    }

    public void machtiginggever_is(String machtiginggever) {
        klantportaalPage.headerIngelogdeNamen.waitFor();
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public void selecteer_optie_inloggen_met_digid() {
        klantportaalPage.inloggenDigidLink.click();
    }

    public void selecteer_optie_inloggen_met_eherkenning() {
        klantportaalPage.inloggeneHerkenningLink.click();
    }

    public void selecteer_optie_inloggen_met_digid_machtigen() {
        klantportaalPage.inloggenDigidMachtigenLink.click();
    }

    public void selecteer_optie_inloggen_met_eherkenning_machtiging() {
        klantportaalPage.inloggeneHerkenningMachtigenLink.click();
    }

    public void log_burger_uit() {
        klantportaalPage.gebruikersMenuBurgerButton.click();
        klantportaalPage.buttonLogout.click();
    }

    public void log_ondernemer_uit() {
        klantportaalPage.gebruikersMenuOndernemerButton.click();
        klantportaalPage.buttonLogout.click();
    }

    public void selecteerNavigatieOptie(String menuOptie) {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName(menuOptie)).click();
    }


    public boolean wordt_kop_h3_getoond(String kopText) {
        return page.locator(String.format("//h3[contains(.,'%s')]", kopText)).isVisible();
    }

    public void open_menu() {
        klantportaalPage.menu.click();
    }

    public void burger_opent_taalmenu_en_verandert_taal() {
        klantportaalPage.languageButton.click();
        klantportaalPage.disabledLanguageOption.click();
    }

    public void ingelogde_gebruiker_is(String naam) {
        klantportaalPage.headerIngelogdeNamen.waitFor();
    }

    public void is_de_sessie_op_het_klantportaal_beeindigd() {
        assertThat(klantportaalPage.inloggenDigidLink).isVisible();
    }
}
