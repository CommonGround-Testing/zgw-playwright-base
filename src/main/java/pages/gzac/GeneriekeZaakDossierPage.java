package pages.gzac;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GeneriekeZaakDossierPage  {
    private final Page page;
    public final Locator pageTitle;
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
    private final String textAreaFieldPath;
    private final String radioInputPath;
    private final String dropdownPath;
    private final String dropdownOption;
    private final String containsTextLocator;
    private final String numericOnlyPath;
    private final String exactTextPath;
    private final String notHidden;

    public GeneriekeZaakDossierPage(Page page) {
        this.page = page;
        notHidden = "[not(contains(@type,'hidden'))]";
        pageTitle = page.locator("//valtimo-page-title//h2");
        dossierModalPath = "//div[contains(@class,'modal-content')]";
        parentPath = "/..";
        inputFieldPath = "//input";
        textAreaFieldPath = "//textarea";
        dropdownPath = "//select/..";
        dropdownOption = "//div[contains(@role,'option')]";
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
    public void fillNumericInputField(String field, int number){
        getNumericInputField(field).fill(String.valueOf(number));
    }

    public void fillTextInputField(String field, String text, boolean exact){
        var inputField = getInputField(field, exact);
        inputField.waitFor(new Locator.WaitForOptions().setTimeout(300));
        inputField.fill(text);
    }

    public void fillTextAreaField(String field, String text, boolean exact){
        getTextAreaField(field, exact).fill(text);
    }

    public void checkCheckbox(String field, boolean exact){
        getInputField(field, exact).check();
    }

    public void selectRadioOption(String field, String option){
        getRadioField(field, option).click();
    }

    public Locator getInputField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = containsTextLocator.replace("${text}", field);
        }
        var fullXPath = dossierModalPath + fieldLocator + parentPath + inputFieldPath + notHidden;
        return page.locator(fullXPath);
    }

    public Locator getTextAreaField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = containsTextLocator.replace("${text}", field);
        }
        var fullXPath = dossierModalPath + fieldLocator + parentPath + textAreaFieldPath;
        return page.locator(fullXPath);
    }

    public Locator getDateField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = containsTextLocator.replace("${text}", field);
        }
        var fullXPath = dossierModalPath + fieldLocator + parentPath + inputFieldPath + notHidden;
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

    public Locator getDropdownField(String field){
        String fieldLocator;
        fieldLocator = containsTextLocator.replace("${text}", field);
        var fullXPath = dossierModalPath + fieldLocator + parentPath + dropdownPath;
        return page.locator(fullXPath);
    }

    public Locator getDropdownOption(String field, String text){
        String fieldLocator = containsTextLocator.replace("${text}", field);
        var fullXPath = dossierModalPath + fieldLocator + parentPath + dropdownOption + containsTextLocator.replace("${text}", text);
        return page.locator(fullXPath);
    }
}