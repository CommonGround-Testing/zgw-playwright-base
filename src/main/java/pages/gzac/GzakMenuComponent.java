package pages.gzac;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GzakMenuComponent {

    public final Locator mainMenu;

    public GzakMenuComponent(Page page) {
        this.mainMenu = page.getByRole(AriaRole.LIST);
    }
}
