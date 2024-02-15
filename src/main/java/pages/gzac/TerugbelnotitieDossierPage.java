package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TerugbelnotitieDossierPage {

    public final Locator buttonAanmakenNieuwDossier;
    public final Locator textfieldOnderwerp;
    public final Locator textfieldKlantvraag;
    public final Locator textfieldVoorletters;
    public final Locator textfieldAchternaam;
    public final Locator textfieldEmail;
    public final Locator textfieldTelefoonnummer;
    public final Locator buttonGeboortedatum;
    public final Locator buttonGeboortedatumVandaag;
    public final Locator buttonProductSenioren;
    public final Locator textfieldProductSenioren;
    public final Locator buttonSelecteerProductSenioren;
    public final Locator buttonVerzendNieuwDossier;

    public TerugbelnotitieDossierPage(Page page) {
        this.buttonAanmakenNieuwDossier = page.getByLabel("Table action bar").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Creëer Nieuw Dossier"));
        this.textfieldOnderwerp = page.locator("//input[contains(@id, 'klantVraag.onderwerp')]");
        this.textfieldKlantvraag = page.locator("//textarea[contains(@id, 'klantVraag.vraag')]");
        this.textfieldVoorletters = page.locator("//input[contains(@id, 'klant.contactgegevens.voorletters')]");
        this.textfieldAchternaam = page.locator("//input[contains(@id, 'klant.contactgegevens.achternaam')]");
        this.textfieldEmail = page.locator("//input[contains(@id, 'klant.contactgegevens.email')]");
        this.textfieldTelefoonnummer = page.locator("//input[contains(@id, 'klant.contactgegevens.telefoon')]");
        this.buttonGeboortedatum = page.locator("//*[contains(@id, 'blok1')]//*[@class='fa fa-calendar']");
        this.buttonGeboortedatumVandaag = page.locator("//*[contains(@class, 'flatpickr-calendar animate open arrowBottom arrowCenter')]//" +
                "*[@class='flatpickr-day today']");
        this.buttonProductSenioren = page.locator("//*[contains(@class, 'formio-component-afhandeling.product')]//" +
                "*[@class='form-control ui fluid selection dropdown']");
        this.textfieldProductSenioren = page.locator("//*[contains(@class, 'formio-component-afhandeling.product')]//" +
                "*[@class='choices__input choices__input--cloned']");
        this.buttonSelecteerProductSenioren = page.locator("//span[contains(., '14070 Senioren')]");
        this.buttonVerzendNieuwDossier = page.locator("//button[contains(., 'Verzenden')]");
    }
}