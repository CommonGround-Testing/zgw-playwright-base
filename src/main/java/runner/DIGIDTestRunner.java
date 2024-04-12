package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import users.ZGWUser;

/**
 * This runner will prepare everything for your tests
 * Note that this code runs before each test
 * <p>
 * If needed you can use the CachedDIGIDTestRunner
 * That runner already takes care of the login so you can skip that part in your tests
 */
@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {
    protected ZGWUser zgwUser;

    public DIGIDTestRunner(String baseUrl, ZGWUser user) {
        super(baseUrl);
        zgwUser = user;
    }

    @BeforeEach
    @Override
    protected void createContextAndPage() {
        ContextHandler.createCleanContextAndPage();
    }
}
