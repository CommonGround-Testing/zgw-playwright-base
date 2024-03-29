package runner;

import com.microsoft.playwright.*;
import lombok.Getter;
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
    @Getter
    public static BrowserContext context;
    @Getter
    public static String baseUrl;
    @Getter
    public static Page page;

    public ZGWTestRunner(String url) {
        baseUrl = url;
    }

    @BeforeAll
    static void launchBrowser() {
        var headlessProp = System.getProperty("headless");
        var setHeadless = headlessProp != null && headlessProp.equals("true");
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
