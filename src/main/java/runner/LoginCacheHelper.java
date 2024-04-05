package runner;

import com.microsoft.playwright.*;

import java.util.function.Consumer;

public class LoginCacheHelper {

    public static String STORAGE = "";

    public LoginCacheHelper(Consumer<Page> consumer) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -------Start test suite-----------XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        var headlessProp = System.getProperty("headless");
        var setHeadless = (headlessProp != null && headlessProp.equals("true"))
                ? true
                : false;
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType
                .LaunchOptions()
                .setHeadless(setHeadless));
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.baseURL = "https://klantportaal-zgw.test.denhaag.nl/";
        BrowserContext contextPre = browser.newContext(options);

        Page page = contextPre.newPage();

        consumer.accept(page);
        STORAGE = contextPre.storageState();

        page.close();
        contextPre.close();
        playwright.close();

    }
}
