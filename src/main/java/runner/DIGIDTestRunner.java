package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import users.ZGWUser;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {
    protected ZGWUser zgwUser;

    public DIGIDTestRunner(String baseUrl, ZGWUser user) {
        super(baseUrl);
        zgwUser = user;
    }

    @BeforeEach
    @Override
    void createContextAndPage() {
        ContextHandler.createDigidContextAndPage(zgwUser);
    }
}
