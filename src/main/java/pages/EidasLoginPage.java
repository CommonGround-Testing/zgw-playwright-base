package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

public class EidasLoginPage {

    private final Page page;
    public final Locator countryLink;
    public final Locator buttonContinue;
    public final Locator cardAtlas;
    public final Locator inputUsername;
    public final Locator inputPassword;
    public final Locator buttonLogin;
    public final Locator buttonSubmit;

    public EidasLoginPage(Page page) {
        this.page = page;
        countryLink = page.locator("id=country-NL");
        buttonContinue = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("continue"));
        cardAtlas = page.locator("id=stelselicon-Atlas-atlas");
        inputUsername = page.locator("id=username");
        inputPassword = page.locator("id=password");
        buttonLogin = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("LOG IN"));
        buttonSubmit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("SUBMIT"));
    }

    /**
     * Navigate to baseurl
     *
     * @return resonse
     */
    public Response navigate() {
        return page.navigate("/");
    }
}
