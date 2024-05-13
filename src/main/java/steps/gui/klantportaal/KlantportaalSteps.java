package steps.gui.klantportaal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.klantportaal.KlantportaalPage;
import steps.gui.login.DigidLoginSteps;
import steps.gui.login.EIdasLoginSteps;
import steps.gui.login.EherkenningSteps;
import users.User;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class KlantportaalSteps {

    private static boolean isLanguageNL;
    protected final Page page;
    protected final KlantportaalPage klantportaalPage;
    private final DigidLoginSteps digidLoginSteps;
    private final EherkenningSteps eherkenningSteps;
    private final EIdasLoginSteps eIdasLoginSteps;

    public KlantportaalSteps(Page page) {
        this.page = page;
        isLanguageNL = true;
        klantportaalPage = new KlantportaalPage(page);
        digidLoginSteps = new DigidLoginSteps(page);
        eherkenningSteps = new EherkenningSteps(page);
        eIdasLoginSteps = new EIdasLoginSteps(page);
    }

    private static void switchLanguage() {
        isLanguageNL = !isLanguageNL;
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
    public void login_via_digid(User user) {
        navigate();
        klantportaalPage.inloggenDigidLink.click();
        digidLoginSteps.login_via_digid(user);
    }

    /**
     * Open a certain url and login with a user that has digid-machtigen
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void login_via_digid_machtigen(User user, String relativeUrl) {
        this.navigate(relativeUrl);
        klantportaalPage.inloggenDigidMachtigenLink.click();
        digidLoginSteps.login_via_digid(user);
        digidLoginSteps.selecteer_machtiginggever();
    }

    /**
     * Open a certain url and login with a user that is a 'bewindvoerder
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void login_via_eherkenning_bewindvoerder(User user, String bsn, String relativeUrl) {
        this.navigate(relativeUrl);
        klantportaalPage.inloggeneHerkenningMachtigenLink.first().click();
        eherkenningSteps.login_via_eherkenning(user);
        eherkenningSteps.vul_bsn_in_als_bewindsvoerder(bsn);
        klantportaalPage.gebruikersMenuBurgerButton.isVisible();
    }

    /**
     * Open a certain url and login with a user that is an 'ondernemer'
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void login_via_eherkenning(User user, String relativeUrl) {
        this.navigate(relativeUrl);
        klantportaalPage.inloggeneHerkenningLink.first().click();
        eherkenningSteps.login_via_eherkenning(user);
        klantportaalPage.gebruikersMenuBurgerButton.isVisible();
    }

    /**
     * Open a certain url and login with a user that is has a European Idas
     *
     * @param user
     * @param relativeUrl can be empty string if you want to open the overview page
     */
    public void login_via_eidas(User user, String relativeUrl) {
        this.navigate(relativeUrl);
        klantportaalPage.inloggeneIdasLink.click();
        eIdasLoginSteps.login_via_eidas(user);
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
     * Login via eHerkenning voor iemand anders bij 'Als professioneel bewindvoerder'
     */
    public void selecteer_optie_inloggen_met_eherkenning_machtiging() {
        klantportaalPage.inloggeneHerkenningMachtigenLink.click();
    }

    /**
     * Log uit indien je als burger bent ingelogd
     */
    public void log_uit_via_menu() {
        klantportaalPage.gebruikersMenuBurgerButton.click();
        klantportaalPage.buttonLogout.click();
    }

    public void selecteer_navigatie_optie(String menuOptie) {
        page.locator("#site-header").getByRole(AriaRole.LINK,
                new Locator
                        .GetByRoleOptions()
                        .setName(menuOptie)).click();
    }

    public void selecteer_navigatie_hoofdMenu(String menuOptie) {
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
        assertThat(klantportaalPage.disabledLanguageOption).isHidden();
        switchLanguage();
    }

    public void valideer_dat_de_sessie_is_beeindigd() {
        assertThat(klantportaalPage.inloggenDigidLink).isVisible();
    }
}
