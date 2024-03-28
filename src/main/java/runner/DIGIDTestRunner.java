package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestWatcherExtension.class)
abstract public class DIGIDTestRunner extends ZGWTestRunner {

    public DIGIDTestRunner(String baseUrl) {
        super(baseUrl);
    }

    @BeforeEach
    void createContextAndPage() {
        super.createContextAndPage();
    }
}
