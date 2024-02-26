package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import runner.ZGWTestRunner;

public class DossierFormLocatorHelper {


    public static Locator getLocator(FormioType type, String visibleLabelOrText) {
        var page = ZGWTestRunner.getPage();
        return switch (type) {
            case ERRORMSG -> getErrorMsgByLabel(page, visibleLabelOrText);
            case SUBMITBUTTON -> getSubmitKnop(page, visibleLabelOrText);
            case COMBOBOX -> getDropdownByLabel(page, visibleLabelOrText);
            case TEXTINPUT -> getInputTextFieldByLabel(page, visibleLabelOrText);
            case DATEPICKER -> getCurrentDateFromOpenDatePicker(page);
            case EMAILINPUT -> getInputEmailFieldByLabel(page, visibleLabelOrText);
            case COMBOBOXOPTION -> getDropdownOptionByLabel(page, visibleLabelOrText);
            case COMBOBOXSEARCH -> getDropdownSearchBoxByLabel(page, visibleLabelOrText);
        };
    }

    private static Locator getInputTextFieldByLabel(Page page, String labelText) {
        return page.locator("//label[contains(text(),'" + labelText + "')]/..//div/*[@type='text']");
    }

    private static Locator getInputEmailFieldByLabel(Page page, String labelText) {
        return page.locator("//label[contains(text(),'" + labelText + "')]/..//div/*[@type='email']");
    }

    private static Locator getErrorMsgByLabel(Page page, String labelText) {
        return page.locator("//label[contains(text(),'" + labelText + "')]/../div[contains(@class,'formio-errors')]");
    }

    private static Locator getCurrentDateFromOpenDatePicker(Page page) {
        return page.locator("//div[contains(@class,'open')]//span[@aria-current='date']");
    }

    private static Locator getSubmitKnop(Page page, String submitText) {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(submitText));
    }

    private static Locator getDropdownOptionByLabel(Page page, String optionText) {
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(optionText)).locator("span");
    }

    private static Locator getDropdownByLabel(Page page, String dropDownLabel) {
        return page.locator("//label[contains(text(), '" + dropDownLabel + "')]/../div/div[@aria-invalid='false']/div");
    }

    private static Locator getDropdownSearchBoxByLabel(Page page, String dropDownLabel) {
        return page.locator("//label[contains(text(), '" + dropDownLabel + "')]/../div/div/input");
    }
}
