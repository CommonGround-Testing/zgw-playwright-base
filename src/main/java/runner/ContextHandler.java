package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import lombok.NonNull;
import steps.gui.gzac.GzacTakenSteps;
import steps.gui.klantportaal.OverzichtSteps;
import users.ADUser;
import users.ZGWUser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.Duration;

import static runner.ZGWTestRunner.*;

/**
 * Class for handling the browser context and pages
 */
public class ContextHandler {

    private final static Integer sessionTimeoutInMinutes = 13;

    /**
     * Use an existing session if available and otherwise login and put it in the context
     *
     * @param user ZGWUser
     */
    public static void createDigidContextAndPage(@NonNull ZGWUser user) {
        String stateFile = user.getUsername() + "-state.json";
        var storageStatePath = Paths.get(stateFile);
        var options = createContextOptions();

        if (isThereAnExistingValidSession(stateFile)) {
            options.setStorageStatePath(storageStatePath);
            createContextAndPage(options);
        } else {
            createContextAndPage(options);
            var overzichtSteps = new OverzichtSteps(page);
            overzichtSteps.navigate();
            overzichtSteps.Login(user);
            context.storageState(new BrowserContext.StorageStateOptions().setPath(storageStatePath));
        }
        addTracingToContext();
    }

    /**
     * Use an existing session if available and otherwise login and put it in the context
     *
     * @param user ADUser
     */
    public static void createDigidContextAndPage(@NonNull ADUser user) {
        String stateFile = user.getUsername() + "-state.json";
        var storageStatePath = Paths.get(stateFile);
        var options = createContextOptions();

        if (isThereAnExistingValidSession(stateFile)) {
            options.setStorageStatePath(storageStatePath);
            createContextAndPage(options);
        } else {
            createContextAndPage(options);
            new GzacTakenSteps(page).medewerker_logt_in_bij_GZAC(GzacTakenSteps.URL, user);
            context.storageState(new BrowserContext.StorageStateOptions().setPath(storageStatePath));
        }
        addTracingToContext();
    }

    /**
     * Create a clean context and page
     */
    public static void createCleanContextAndPage() {
        createContextAndPage(createContextOptions());
        addTracingToContext();
    }

    /**
     * Check if an existing statefile exists and check if it is still valid
     * Sessions only last 15 minutes
     *
     * @param stateFile filepath
     * @return boolean true or false
     */
    private static boolean isThereAnExistingValidSession(String stateFile) {
        var storageStatePath = Paths.get(stateFile);
        if (!new File(stateFile).exists()) {
            return false;
        }
        try {
            var creationTime = Files.getLastModifiedTime(storageStatePath).toInstant();
            var now = Clock.systemDefaultZone().instant();
            var diff = Duration.between(creationTime, now);
            if (diff.toMinutes() >= sessionTimeoutInMinutes) {
                return (!new File(stateFile).delete());
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
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
