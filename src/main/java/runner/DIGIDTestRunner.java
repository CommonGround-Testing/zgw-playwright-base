package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import users.ZGWDigidUser;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {

    protected ZGWDigidUser digidUser;

    public DIGIDTestRunner(String baseUrl, ZGWDigidUser user) {
        super(baseUrl);
        digidUser = user;
    }

    @BeforeEach
    @Override
    void createContextAndPage() {
        ContextHandler.createDigidContextAndPage(digidUser);
    }
}
