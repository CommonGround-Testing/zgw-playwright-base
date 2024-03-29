package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import users.ADUser;

@ExtendWith(TestWatcherExtension.class)
abstract public class ADTestRunner extends ZGWTestRunner {
    protected ADUser adUser;

    public ADTestRunner(String baseUrl, ADUser user) {
        super(baseUrl);
        adUser = user;
    }

    @BeforeEach
    @Override
    void createContextAndPage() {
        ContextHandler.createDigidContextAndPage(adUser);
    }
}
