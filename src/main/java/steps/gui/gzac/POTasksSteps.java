package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GzacPOTasksPage;

public class POTasksSteps extends GzacBaseSteps {

    private final GzacPOTasksPage poTasksPage;

    public POTasksSteps(Page page) {
        super(page);
        this.poTasksPage = new GzacPOTasksPage(page);
    }

    /**
     * Open de pagina met taken
     *
     */
    public void navigate() {
        page.navigate("/tasks");
    }

    /**
     * Klik op de 'alle taken' link
     *
     */
    public void open_lijst_alle_taken() {
        poTasksPage.linkAlleTaken.click();
    }
}
