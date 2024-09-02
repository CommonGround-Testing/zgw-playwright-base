package runner;

import com.microsoft.playwright.*;
import lombok.Getter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * This runner will prepare everything that is needed for your tests to run
 * Note that this code runs before each test
 */
@ExtendWith(TestWatcherExtension.class)
abstract public class ZGWTestRunner {

    // New instance for each test method.
    // Cannot use standard library because we need to get cookies fTestWatcherExtensionrom context
    @Getter
    public static BrowserContext context;
    @Getter
    public static Page page;
    // Shared between all tests in this class.
    protected static Playwright playwright;
    protected static Browser browser;
    @Getter
    public static String baseUrl;

    public ZGWTestRunner(String url) {
        baseUrl = url;
    }

    @BeforeAll
    static void launchBrowser() {
        var headlessProp = System.getProperty("headless");
        var setHeadless = headlessProp != null && headlessProp.equals("true");
        var envBrowser = System.getProperty("browser");
        if (envBrowser == null) {
            envBrowser = "chrome";
        }
        playwright = Playwright.create();
        if (envBrowser.equals("chrome")) {
            browser = playwright.chromium().launch(new BrowserType
                    .LaunchOptions()
                    .setHeadless(setHeadless));
        } else {
            if (envBrowser.equals("firefox")) {
                browser = playwright.firefox().launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(setHeadless));
            }
        }
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    /**
     * This method should only be used if your test uses different baseUrls
     * For example when you are testing different systems (kententest)
     * <p>
     * Note that this will turn any current page invalid so after calling
     * this method you have to initiate the steps again!
     * <p>
     * Therefore this method should only be called from a Runner that does not
     * instantiate anything in it's constructor
     */
    public static void changeBaseUrl(String baseUrl) {
        ContextHandler.setNewBaseUrl(baseUrl);
    }

    @BeforeEach
    void createContextAndPage() {
        ContextHandler.createCleanContextAndPage();
    }
}
