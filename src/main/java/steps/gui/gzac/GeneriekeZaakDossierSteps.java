package steps.gui.gzac;

import com.microsoft.playwright.Page;
import pages.gzac.GeneriekeZaakDossierPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GeneriekeZaakDossierSteps extends GzacBaseSteps {

    private final GeneriekeZaakDossierPage zaakDossierSteps;

    public GeneriekeZaakDossierSteps(Page page) {
        super(page);
        this.zaakDossierSteps = new GeneriekeZaakDossierPage(page);
    }

    public void maak_nieuw_dossier_aan() {
        zaakDossierSteps.buttonAanmakenNieuwDossier.click();
        zaakDossierSteps.textfieldVoorletters.waitFor();
        zaakDossierSteps.textfieldVoorletters.fill("T.E.S.T");
        zaakDossierSteps.textfieldAchternaam.fill(this.dossierNummer + "achternamen");
        zaakDossierSteps.buttonVerzendNieuwDossier.click();
    }

    public void open_eerste_dossier() {
        page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]").click();
    }

    public void nieuw_aangemaakt_dossier_is_zichtbaar() {
        assertThat(this.nieuwAangemaakteDossierNummer(String.valueOf(dossierNummer))).isVisible();
    }

    public void navigate() {
        page.navigate("/dossiers/generieke-zaak");
    }
}
