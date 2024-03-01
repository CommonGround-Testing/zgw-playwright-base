package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzacBasePage {

    protected final Page page;
    public final Locator tegelOverzichtButton;
    public final Locator pageTitle;

    public GzacBasePage(Page page) {
        this.page = page;
        tegelOverzichtButton = page.locator("//*[@class='mdi-view-module']");
        pageTitle = page.locator("//valtimo-page-title//h2");
    }

    public void clickButton(String text){
        getFirstButton(text).click();
    }

    public Locator getFirstButton(String text){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text)).first();
    }
}