package steps.gui.openforms;

import com.microsoft.playwright.Page;
import pages.openforms.InleidingPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InleidingSteps extends OpenFormsSteps {

    private final InleidingPage inleidingPage;

    public InleidingSteps(Page page) {
        super(page);
        this.inleidingPage = new InleidingPage(page);
    }

    public void link_privacy_verklaring_is_zichtbaar() {

        assertThat(inleidingPage.linkPrivacyVerklaring).isVisible();
    }
}
