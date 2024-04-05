package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.gui.login.ADLoginSteps;
import users.ADUser;

/**
 * This cached runner will store and re-use sessions to speed up tests
 * Note that this code runs before each test so you do not have to login anyore with each test
 * <p>
 * If you need to test Login functionality you can better use the UncachedADTestRunner
 */
@ExtendWith(TestWatcherExtension.class)
abstract public class CachedADTestRunner extends ADTestRunner {

    public CachedADTestRunner(String baseUrl, ADUser user) {
        super(baseUrl, user);
    }

    @BeforeEach
    @Override
    protected void createContextAndPage() {
        ContextHandler.createDigidContextAndPage(ADLoginSteps.class, adUser);
    }
}
