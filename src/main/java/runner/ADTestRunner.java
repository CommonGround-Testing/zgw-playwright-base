package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.gui.login.ADLoginSteps;
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
    protected void createContextAndPage() {
        ContextHandler.createDigidContextAndPage(ADLoginSteps.class, adUser);
    }
}
