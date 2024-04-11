package steps.gui.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.klantportaal.KlantportaalPage;
import steps.gui.login.DigidLoginSteps;
import steps.gui.login.EherkenningSteps;
import users.User;
import users.ZGWUser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class KlantportaalSteps {

    protected final Page page;
    protected final KlantportaalPage klantportaalPage;

    private final DigidLoginSteps digidLoginSteps;
    private final EherkenningSteps eherkenningSteps;

    private static boolean isLanguageNL;

    public KlantportaalSteps(Page page) {
        this.page = page;
        isLanguageNL = true;
        klantportaalPage = new KlantportaalPage(page);
        digidLoginSteps = new DigidLoginSteps(page);
        eherkenningSteps = new EherkenningSteps(page);
    }

    /**
     * Open baseUrl
     */
    public void navigate() {
        page.navigate("");
    }

    /**
     * Open baseUrl
     *
     * @param relativeUrl to navigate to
     */
    public void navigate(String relativeUrl) {
        page.navigate(relativeUrl);
    }

    /**
     * Open a certain url and login with a user
     *
     * @param user User
     */
    public void Login(User user) {
        this.navigate();
        klantportaalPage.inloggenDigidLink.click();
        digidLoginSteps.Login(user);
    }

    /**
     * Open a certain url and login with a user that has digid-machtigen
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void log_in_op_het_klantportaal_via_digid_machtigen(User user, String relativeUrl) {
        this.navigate(relativeUrl);
        // this.login_met_ad(); TODO weer activeren na ZP-1256
        this.selecteer_optie_inloggen_met_digid_machtigen();
        digidLoginSteps.Login(user);
        digidLoginSteps.selecteer_machtiginggever();
    }

    /**
     * Open a certain url and login with a user that is an 'ondernemer'
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void een_ondernemer_logt_in_op_het_klantportaal(ZGWUser user, String relativeUrl) {
        this.navigate(relativeUrl);
        // this.login_met_ad(); TODO weer activeren na ZP-1256
        selecteer_optie_inloggen_met_eherkenning();
        eherkenningSteps.Login(user);
        klantportaalPage.gebruikersMenuOndernemerButton.isVisible();
    }

    public void machtiginggever_is(String machtiginggever) {
        klantportaalPage.headerIngelogdeNamen.waitFor();
    }

    /**
     * Get the current url
     *
     * @return url
     */
    public String getCurrentUrl() {
        return page.url();
    }

    /**
     * Klik op de optie burger 'Inloggen met DigiD'
     */
    public void selecteer_optie_inloggen_met_digid() {
        klantportaalPage.inloggenDigidLink.click();
    }

    /**
     * Klik op de optie burger 'Inloggen met eHerkenning'
     */
    public void selecteer_optie_inloggen_met_eherkenning() {
        klantportaalPage.inloggeneHerkenningLink.first().click();
    }

    /**
     * Login via digid voor iemand anders bij 'Als vrijwillig gemachtigde'
     */
    public void selecteer_optie_inloggen_met_digid_machtigen() {
        klantportaalPage.inloggenDigidMachtigenLink.click();
    }

    /**
     * Login via eHerkenning voor iemand anders bij 'Als professioneel bewindvoerder'
     */
    public void selecteer_optie_inloggen_met_eherkenning_machtiging() {
        klantportaalPage.inloggeneHerkenningMachtigenLink.click();
    }

    /**
     * Log uit indien je als burger bent ingelogd
     */
    public void log_burger_uit() {
        klantportaalPage.gebruikersMenuBurgerButton.click();
        klantportaalPage.buttonLogout.click();
    }

    /**
     * Log uit indien je als ondernemer bent ingelogd
     */
    public void log_ondernemer_uit() {
        klantportaalPage.gebruikersMenuOndernemerButton.click();
        klantportaalPage.buttonLogout.click();
    }

    public void selecteerNavigatieOptie(String menuOptie) {
        page.locator("#site-header").getByRole(AriaRole.LINK,
                new Locator
                        .GetByRoleOptions()
                        .setName(menuOptie)).click();
    }

    public void selecteerNavigatieHoofdMenu(String menuOptie) {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName(menuOptie)).click();
    }

    public Locator get_h3_header(String kopText) {
        return page.locator(String.format("//h3[contains(.,'%s')]", kopText));
    }

    public void open_menu() {
        klantportaalPage.menu.waitFor();
        klantportaalPage.menu.click();
    }

    public void burger_opent_taalmenu_en_verandert_taal() {
        klantportaalPage.languageButton.click();
        klantportaalPage.disabledLanguageOption.click();
        assertThat(klantportaalPage.getMenuButtonByText(isLanguageNL)).isHidden();
        switchLanguage();
    }

    public void ingelogde_gebruiker_is(String naam) {
        klantportaalPage.headerIngelogdeNamen.waitFor();
    }

    public void is_de_sessie_op_het_klantportaal_beeindigd() {
        assertThat(klantportaalPage.inloggenDigidLink).isVisible();
    }

    private static void switchLanguage() {
        isLanguageNL = !isLanguageNL;
    }
}
