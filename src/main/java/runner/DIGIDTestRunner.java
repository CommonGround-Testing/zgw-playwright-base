package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.gui.klantportaal.OverzichtSteps;
import users.ZGWDigidUser;

import java.io.File;
import java.nio.file.Paths;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {

    protected ZGWDigidUser digidUser;
    private String stateFile;

    public DIGIDTestRunner(String baseUrl, ZGWDigidUser user) {
        super(baseUrl);
        digidUser = user;
        stateFile = digidUser.getUsername() + "-state.json";
    }

    @BeforeEach
    void createContextAndPage() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = getBaseUrl();

        if (new File(stateFile).exists()) {
            // use existing session
            options.setStorageStatePath(Paths.get(stateFile));
            context = browser.newContext(options);
            page = context.newPage();
        } else {
            context = browser.newContext(options);
            page = context.newPage();
            new OverzichtSteps(page).login_als_burger_on_page(digidUser, "");
            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(stateFile)));
        }

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page.navigate("/");
    }
}
