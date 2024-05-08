package runner;

import com.microsoft.playwright.*;
import lombok.Getter;
import lombok.Setter;
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

    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;
    // New instance for each test method.
    // Cannot use standard library because we need to get cookies fTestWatcherExtensionrom context
    @Getter
    public static BrowserContext context;
    @Getter
    private static String baseUrl;
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
        ContextHandler.createCleanContextAndPage();
    }

    /**
     * This method should only be used if your test uses different baseUrls
     * For example when you are testing different systems (kententest)
     */
    public static void changeBaseUrl(String baseUrl){
        ContextHandler.setNewBaseUrl(baseUrl);
    }
}
