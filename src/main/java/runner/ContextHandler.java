package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import lombok.NonNull;
import steps.gui.login.LoginSteps;
import users.User;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.Duration;

import static runner.ZGWTestRunner.*;

/**
 * Class for handling the browser context and pages
 */
public class ContextHandler {

    private final static Integer sessionTimeoutInMinutes = 13;
    private final static String REPORTING_PATH = "./target/site/";
    private static Browser.NewContextOptions currentOptions;

    /**
     * Use an existing session if available and otherwise login and store this session
     * The sessions are stored in -state.json files which can be re-used for multiple tests
     * <p>
     * The sessionfile will be stored in ./target/site/ (where the report is also stored)
     *
     * @param stepsClass with LoginSteps class to be used for Login
     * @param user       that will be logged in
     * @param stateFile  with name of the file for sessionstorage.
     */
    public static void createDigidContextAndPage(Type stepsClass, User user, String stateFile) {
//        String stateFile = user.getUsername() + "-" + environment + "-state.json";
        var storageStatePath = Paths.get(REPORTING_PATH + stateFile);
        var options = createContextOptions();

        if (isThereAnExistingValidSession(storageStatePath)) {
            options.setStorageStatePath(storageStatePath);
            createContextAndPage(options);
            page.navigate("");
            addTracingToContext();
        } else {
            createContextAndPage(options);
            addTracingToContext();
            var loginSteps = instantiateLoginSteps(stepsClass);
            if (loginSteps != null) {
                loginSteps.navigate();
                loginSteps.login_via_digid(user);
                context.storageState(new BrowserContext.StorageStateOptions().setPath(storageStatePath));
            }
        }
    }

    /**
     * Create a clean context and page
     */
    public static void createCleanContextAndPage() {
        createContextAndPage(createContextOptions());
        addTracingToContext();
    }

    /**
     * Switches baseUrl.
     * This can only be done by closing the current browser and starting a new one
     *
     * @param baseUrl with new baseurl
     */
    public static void setNewBaseUrl(String baseUrl) {
        removeTracingToContext();
        context.close();

        currentOptions.baseURL = baseUrl;
        currentOptions.setIgnoreHTTPSErrors(true);
        currentOptions.setBypassCSP(true);
        context = browser.newContext(currentOptions);
        page = context.newPage();
        addTracingToContext();
    }

    /**
     * Check if an existing statefile exists and check if it is still valid
     * Sessions only last 15 minutes
     *
     * @param storagePath path+name of stateFile
     * @return boolean true or false
     */
    private static boolean isThereAnExistingValidSession(Path storagePath) {
        var stateFile = storagePath.toString();
        if (!new File(stateFile).exists()) {
            return false;
        }
        try {
            var creationTime = Files.getLastModifiedTime(storagePath).toInstant();
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
     * Create a new steps class for the loginsteps that are needed to create a new session
     *
     * @param stepsClass to be instantiated
     * @return LoginSteps
     */
    private static LoginSteps instantiateLoginSteps(Type stepsClass) {
        try {
            var myClass = Class.forName(stepsClass.getTypeName());
            var constructor = myClass.getDeclaredConstructors();
            return (LoginSteps) constructor[0].newInstance(page);
        } catch (Exception ex) {
            System.out.println("Something went wrong while trying to instantiate the Login steps : " + ex.getLocalizedMessage());
            return null;
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
        options.baseURL = baseUrl;
        if (LoginCacheHelper.STORAGE != null || !LoginCacheHelper.STORAGE.isEmpty()) {
            options.storageState = LoginCacheHelper.STORAGE;
        }
        currentOptions = options;
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

    /**
     * Adds tracing to the context for the test report
     */
    private static void removeTracingToContext() {
        context.tracing().stop();
    }
}
