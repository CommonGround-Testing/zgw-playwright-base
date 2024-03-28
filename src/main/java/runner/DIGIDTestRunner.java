package runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {

    public DIGIDTestRunner(String baseUrl) {
        super(baseUrl);
    }

    @BeforeEach
    void createContextAndPage() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = getBaseUrl();
        context = browser.newContext(options);
        page = context.newPage();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }
}
