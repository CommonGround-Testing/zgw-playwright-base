package runner;

import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Paths;

public class TestWatcherExtension implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        ZGWTestRunner.getContext().close();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        ZGWTestRunner.getContext().close();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ZGWTestRunner.getContext().tracing().stop(new Tracing.StopOptions()
                .setPath(
                        Paths.get("./target/site/" +
                                context.getTestMethod() +
                                "trace.zip")));
        ZGWTestRunner.getContext().close();
    }
}
