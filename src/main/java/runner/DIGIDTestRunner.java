package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.gui.klantportaal.OverzichtSteps;
import users.ZGWDigidUser;
import utils.Secrets;

import java.io.File;
import java.nio.file.Paths;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {

    protected OverzichtSteps overzichtSteps;
    protected ZGWDigidUser digidUser;

    public DIGIDTestRunner(String baseUrl) {
        super(baseUrl);
        digidUser = ZGWDigidUser.builder()
                .name("Sierra")
                .nationaliteit("Nederlandse")
                .username("zgwdigiduser1")
                .password(Secrets.getSecretByName("zgwdigiduser1-password")).build();
    }

    @BeforeEach
    void createContextAndPage() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = getBaseUrl();

        if (new File("digidsession.json").exists()) {
            // use existing session
            context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(Paths.get("digidsession.json")));
            page = context.newPage();
        } else {
            context = browser.newContext(options);
            page = context.newPage();
            new OverzichtSteps(page).login_als_burger_on_page(digidUser, "");
            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("digidsession.json")));        }

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }
}
