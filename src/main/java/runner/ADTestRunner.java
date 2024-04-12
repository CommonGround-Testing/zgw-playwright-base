package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import users.ADUser;

/**
 * This runner will prepare everything for your tests
 * Note that this code runs before each test
 * <p>
 * If needed you can use the CachedADTestRunner
 * That runner already takes care of the login so you can skip that part in your tests
 */
@ExtendWith(TestWatcherExtension.class)
abstract public class ADTestRunner extends ZGWTestRunner {
    protected ADUser adUser;

    public ADTestRunner(String baseUrl, ADUser user) {
        super(baseUrl);
        adUser = user;
    }

    @BeforeEach
    @Override
    protected void createContextAndPage() {
        ContextHandler.createCleanContextAndPage();
    }
}
