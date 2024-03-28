package runner;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestWatcherExtension.class)
abstract public class ZGWTestRunner {

    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;
    // New instance for each test method.
    // Cannot use standard library because we need to get cookies fTestWatcherExtensionrom context
    protected static BrowserContext context;
    private static String baseUrl;
    protected static Page page;

    public ZGWTestRunner(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static BrowserContext getContext() {
        return context;
    }

    public static Page getPage() {
        return page;
    }

    @BeforeAll
    static void launchBrowser() {
        var headlessProp = System.getProperty("headless");
        var setHeadless = (headlessProp != null && headlessProp.equals("true"))
                ? true
                : false;
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType
                .LaunchOptions()
                .setHeadless(setHeadless));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = baseUrl;
        context = browser.newContext(options);
        page = context.newPage();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

    }
}
