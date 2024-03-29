package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import lombok.NonNull;
import steps.gui.klantportaal.OverzichtSteps;
import users.ZGWDigidUser;

import java.io.File;
import java.nio.file.Paths;

import static runner.ZGWTestRunner.*;

/**
 * Class for handling the browser context and pages
 */
public class ContextHandler {

    /**
     * Use an existing session if available and otherwise login and store this new session
     *
     * @param user
     */
    public static void getOrStartNewDigidSession(@NonNull ZGWDigidUser user) {
        String stateFile = user.getUsername() + "-state.json";
        var options = createContextOptions();
        var storageStatePath = Paths.get(stateFile);

        if (new File(stateFile).exists()) {
            options.setStorageStatePath(storageStatePath);
            createContextAndPage(options);
        } else {
            createContextAndPage(options);
            new OverzichtSteps(page).login_als_burger_on_page(user, "");
            context.storageState(new BrowserContext.StorageStateOptions().setPath(storageStatePath));
        }
        addTracingToContext();
    }

    /**
     * Create new contextoptions with baseUrl
     * You can still add other options later if needed
     *
     * @return Browser.NewContextOptions
     */
    private static Browser.NewContextOptions createContextOptions() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = ZGWTestRunner.getBaseUrl();
        return options;
    }

    /**
     * set the context and create a new page
     *
     * @param options with options
     */
    private static void createContextAndPage(@NonNull Browser.NewContextOptions options) {
        context = browser.newContext(options);
        page = context.newPage();
    }

    /**
     * Adds tracing to the context for the test report
     */
    private static void addTracingToContext() {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }
}
