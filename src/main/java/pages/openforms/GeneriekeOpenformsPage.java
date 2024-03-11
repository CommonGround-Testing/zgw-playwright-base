package pages.openforms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GeneriekeOpenformsPage {
    private final Page page;
    private final String textLabel;
    private final String spanText;
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

    public GeneriekeOpenformsPage(Page page) {
        this.page = page;
        notHidden = "[not(contains(@type,'hidden'))]";
        parentPath = "/../..";
        inputFieldPath = "//input";
        textAreaFieldPath = "//textarea";
        dropdownPath = "//select/..";
        dropdownOption = "//div[contains(@role,'option')]";
        radioInputPath = "//label[contains(text(),'${text}')]/../..//input";
        containsTextLocator = "//*[contains(text(),'${text}')]";
        numericOnlyPath = "//label[normalize-space()='${text} , numeric only,']";
        exactTextPath = "//label//*[text(),'${text}']";
        textLabel = "//label[contains(text(),'${text}')]";
        spanText = "//span[contains(text(),'${text}')]";
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
        inputField.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        inputField.fill(text);
    }

    public void fillTextAreaField(String field, String text, boolean exact){
        getTextAreaField(field, exact).fill(text);
    }

    public void checkCheckbox(String field, boolean exact){
        getInputField(field, exact).check();
    }

    public Locator getCheckbox(String field){
        return getInputField(field, false);
    }

    public Locator getInputField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = textLabel.replace("${text}", field);
        }
        var fullXPath = fieldLocator + parentPath + inputFieldPath + notHidden;
        return page.locator(fullXPath);
    }

    public Locator getTextAreaField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = textLabel.replace("${text}", field);
        }
        var fullXPath = fieldLocator + parentPath + textAreaFieldPath;
        return page.locator(fullXPath);
    }

    public Locator getDateField(String field, boolean exact){
        String fieldLocator;
        if(exact){
            fieldLocator = exactTextPath.replace("${text}", field);
        } else {
            fieldLocator = textLabel.replace("${text}", field);
        }
        var fullXPath = fieldLocator + parentPath + inputFieldPath + notHidden;
        return page.locator(fullXPath);
    }

    public Locator getNumericInputField(String field){
        String fieldLocator;
        fieldLocator = numericOnlyPath.replace("${text}", field);
        var fullXPath = fieldLocator + parentPath + inputFieldPath;
        return page.locator(fullXPath);
    }

    public Locator getRadioGroup(String field){
        return page.locator(spanText.replace("${text}", field));
    }

    public void selectOption(String field, String option){
        String fieldLocator = spanText.replace("${text}", field);
        var fullXPath = fieldLocator +  parentPath + radioInputPath.replace("${text}", option);
        page.locator(fullXPath).click();
    }

    public Locator getDropdownField(String field){
        String fieldLocator = textLabel.replace("${text}", field);
        var fullXPath = fieldLocator + parentPath + dropdownPath;
        return page.locator(fullXPath);
    }

    public Locator getDropdownOption(String field, String text){
        String fieldLocator = textLabel.replace("${text}", field);
        var fullXPath = fieldLocator + parentPath + dropdownOption + containsTextLocator.replace("${text}", text);
        return page.locator(fullXPath);
    }
}