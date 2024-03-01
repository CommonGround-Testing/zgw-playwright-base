package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.FormioType;

import static utils.DossierFormLocatorHelper.getLocator;

public class TerugbelnotitieDossierFormPage extends GeneriekeZaakDossierPage {

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
    public final Locator datumUiterlijkeBeantwoording;
    public final Locator dossierOverzichtHeaderInhoud;

    public TerugbelnotitieDossierFormPage(Page page) {
        super(page);
        this.textfieldOnderwerp = getLocator(FormioType.TEXTINPUT, "Onderwerp");
        this.textfieldKlantvraag = getLocator(FormioType.TEXTINPUT, "Vraag van de klant");
        this.textfieldVoorletters = getLocator(FormioType.TEXTINPUT, "Voorletters");
        this.textfieldAchternaam = getLocator(FormioType.TEXTINPUT, "Achternaam");
        this.textfieldEmail = getLocator(FormioType.EMAILINPUT, "E-mailadres");
        this.textfieldTelefoonnummer = getLocator(FormioType.TEXTINPUT, "Telefoonnummer");
        this.buttonGeboortedatum = getLocator(FormioType.TEXTINPUT, "Geboortedatum");
        this.buttonGeboortedatumVandaag = getLocator(FormioType.DATEPICKER, "");
        this.datumUiterlijkeBeantwoording = getLocator(FormioType.TEXTINPUT, "Datum uiterlijke beantwoording TBN");
        this.buttonProductSenioren = getLocator(FormioType.COMBOBOX, "Product");
        this.textfieldProductSenioren = getLocator(FormioType.COMBOBOXSEARCH, "Product");
        this.buttonSelecteerProductSenioren = getLocator(FormioType.COMBOBOXOPTION, "Senioren");
        this.buttonVerzendNieuwDossier = getLocator(FormioType.SUBMITBUTTON, "Verzenden");
        this.dossierOverzichtHeaderInhoud = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Inhoud"));
    }
}