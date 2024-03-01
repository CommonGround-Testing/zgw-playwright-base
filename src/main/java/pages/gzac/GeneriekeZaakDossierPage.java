package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GeneriekeZaakDossierPage extends GzacBasePage {

    public final Locator textfieldVoorletters;
    public final Locator textfieldAchternaam;
    public final Locator buttonVerzendNieuwDossier;
    public final Locator headerGeneriekeZaak;
    public final Locator headerTable;
    public final Locator tableCellEersteDossier;
    public final Locator dossierTitel;
    public final String dossierModalPath;
    private final String parentPath;
    private final String inputFieldPath;
    private final String radioInputPath;
    private final String containsTextLocator;
    private final String numericOnlyPath;
    private final String exactTextPath;

    public GeneriekeZaakDossierPage(Page page) {
        super(page);
        dossierModalPath = "//div[contains(@class,'modal-content')]";
        parentPath = "/..";
        inputFieldPath = "//input";
        radioInputPath = "//span[contains(text(),'${text}')]//../input";
        containsTextLocator = "//*[contains(text(),'${text}')]";
        numericOnlyPath = "//label[normalize-space()='${text} , numeric only,']";
        exactTextPath = "//*[text(),'${text}']";
        dossierTitel = page.locator("//div[contains(@class,'modal-content')]//h4");
        textfieldVoorletters = page.locator("//input[contains(@id, 'machtiginggever-voorletter')]");
        textfieldAchternaam = page.locator("//input[contains(@id, 'machtiginggever-achternaam')]");
        buttonVerzendNieuwDossier = page.locator("//button[contains(., 'Start')]");
        headerGeneriekeZaak = page.locator("//h2[contains(text(), 'Generieke zaak')]");
        headerTable = page.locator("//th[contains(text(),'Referentienummer')]");
        tableCellEersteDossier = page.locator("//table[contains(@class,'table-striped')]/tbody/tr[1]/td[1]");
    }

    /*
    * Fill inputfield for a certail item
    * provide text of the field and the value for input
    * */
    public void fillNumericInputField(String field, Integer number){
        getNumericInputField(field).fill(number.toString());
    }

    public void fillTextInputField(String field, String text, boolean exact){
        getTextInputField(field, exact).fill(text);
    }

    public void checkCheckbox(String field, boolean exact){
        getTextInputField(field, exact).check();
    }

    public void selectRadioOption(String field, String option){
        getRadioField(field, option).click();
    }


    public Locator getTextInputField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = containsTextLocator.replace("${text}", field);
        }
        var fullXPath = dossierModalPath + fieldLocator + parentPath + inputFieldPath;
        return page.locator(fullXPath);
    }

    public Locator getNumericInputField(String field){
        String fieldLocator;
        fieldLocator = numericOnlyPath.replace("${text}", field);
        var fullXPath = dossierModalPath + fieldLocator + parentPath + inputFieldPath;
        return page.locator(fullXPath);
    }

    public Locator getRadioField(String field, String option){
        String fieldLocator;
        fieldLocator = containsTextLocator.replace("${text}", field);
        var fullXPath = dossierModalPath + fieldLocator + parentPath + radioInputPath.replace("${text}", option);
        return page.locator(fullXPath);
    }
}