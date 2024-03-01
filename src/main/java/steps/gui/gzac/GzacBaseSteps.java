package steps.gui.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.gzac.GzacBasePage;
import steps.gui.login.ADLoginSteps;
import users.ADUser;
import utils.MathUtils;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GzacBaseSteps {

    protected final Page page;
    private final GzacBasePage basePage;
    private final ADLoginSteps adLoginSteps;
    protected int dossierNummer;

    public GzacBaseSteps(Page page) {
        this.page = page;
        basePage = new GzacBasePage(page);
        adLoginSteps = new ADLoginSteps(page);
    }

    public void setDossierNummer() {
        dossierNummer = MathUtils.generateRandomNumber();
    }

    public void klik_op_knop(String text){
        basePage.clickButton(text);
    }

    public void wachtOpLadenMenu() {
        basePage.menu.menuItemDashboard.waitFor();
    }

    public boolean wordtTabbladGetoond(String TabbladText) {
        return page.locator("//valtimo-widget//li[contains(.,'" + TabbladText + "')]").isVisible();
    }

    public Locator nieuwAangemaakteDossierNummer(String dossierNummer) {
        return page.locator("//valtimo-form-io//dt[contains(., '" + dossierNummer + "')]");
    }

    public void medewerker_ziet_tegels_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//valtimo-widget//dt[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    public void medewerker_ziet_tabel_met_kolommen(List<String> kolommen) {
        kolommen.forEach(tabelHeader ->
                assertThat(page.locator("//table//th[contains(.,'" + tabelHeader + "')]")).isVisible()
        );
    }

    public void medewerker_ziet_tabbladen(List<String> verwachteTabs) {
        verwachteTabs.forEach(tab ->
                assertThat(page.locator("//valtimo-widget//li[contains(.,'" + tab + "')]")).isVisible()
        );
    }

    public void medewerker_logt_in_bij_GZAC(String url, ADUser user) {
        page.navigate(url);
        adLoginSteps.login_met_ad_user(user);
        wachtOpLadenMenu();
    }

    public Locator menuItem(String text){
        return basePage.menu.getMenuItem(text);
    }
}
