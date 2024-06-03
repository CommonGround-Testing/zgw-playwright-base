package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.gui.login.DigidLoginSteps;
import users.User;

/**
 * This cached runner will store and re-use sessions to speed up tests
 * Note that this code runs before each test so you do not have to login anyore with each test
 * <p>
 * If you need to test Login functionality you can better use the UncachedDIGIDTestRunner
 */
@ExtendWith(TestWatcherExtension.class)
abstract public class CachedDIGIDTestRunner extends DIGIDTestRunner {

    public CachedDIGIDTestRunner(String baseUrl, User user) {
        super(baseUrl, user);
    }

    @BeforeEach
    @Override
    protected void createContextAndPage() {
        var environment = "test";
        if (getBaseUrl().contains("acc")) {
            environment = "acc";
        }
        String stateFile = digidUser.getUsername() + "-" + environment + "-state.json";
        ContextHandler.createDigidContextAndPage(DigidLoginSteps.class, digidUser, stateFile);
    }
}
