package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzacBasePage {

    protected final Page page;
    public GzakMenu menu;

    public GzacBasePage(Page page) {
        this.page = page;
        menu = new GzakMenu(page);
    }

    public void clickButton(String text){
        getFirstButton(text).click();
    }

    private Locator getFirstButton(String text){
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text)).first();
    }
}