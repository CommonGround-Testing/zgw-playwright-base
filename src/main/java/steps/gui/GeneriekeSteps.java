package steps.gui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GeneriekeSteps {

    protected final Page page;

    /**
     * Only use this method when you don't want to use the default BaseUrl
     * Note that the url must start with https:// or http://
     *
     * @param specialUrl Url starting with http or https
     */
    public void navigate(String specialUrl){
        page.navigate(specialUrl);
    }

    public GeneriekeSteps(Page page) {
        this.page = page;
    }

    /**
     * Verifieer of de tekst op het scherm staat
     *
     * @param text die zichtbaar zou moeten zijn
     */
    public void valideer_of_tekst_zichtbaar_is(String text) {
        assertThat(page.getByText(text)).isVisible();
    }

    /**
     * Verifieer of de tekst op het scherm staat binnen een bepaalde tijd
     *
     * @param text die zichtbaar zou moeten zijn
     * @param timeoutInSeconds aantal seconden dat er maximaal gewacht wordt
     */
    public void valideer_of_tekst_zichtbaar_is(String text, int timeoutInSeconds){
        page.getByText(text).waitFor(new Locator.WaitForOptions().setTimeout(timeoutInSeconds * 1000));
    }

    /**
     * Klik op de eerste link.
     *
     * @param text van de link of een deel daarvan
     */
    public void klik_link(String text) {
        page.getByRole(AriaRole.LINK).getByText(text).first().click();
    }

    /**
     * Haal het link element op
     *
     * @param text van de link of een deel daarvan
     */
    public Locator get_link(String text) {
        return page.getByRole(AriaRole.LINK).getByText(text);
    }

    /**
     * Klik op een tab.
     *
     * @param text van de tab of een deel daarvan
     */
    public void klik_tab(String text) {
        page.getByRole(AriaRole.TAB).getByText(text).click();
    }

    /**
     * Haal het tab element op
     *
     * @param text van de tab of een deel daarvan
     */
    public Locator get_tab(String text) {
        return page.getByRole(AriaRole.TAB).getByText(text);
    }

    /**
     * Klik op een knop
     *
     * @param text van de knop
     */
    public void klik_knop(String text) {
        get_knop(text).click();
    }

    /**
     * Haal de eerste knop op met een bepaalde tekst
     *
     * @param text van de knop
     * @return Locator
     */
    public Locator get_knop(String text) {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text)).first();
    }

    /**
     * Haal de radio groep op
     *
     * @param tekst van de radiogroep of een deel daarvan
     */
    public Locator get_radiogroep(String tekst) {
        return page.getByRole(AriaRole.RADIOGROUP).getByText(tekst);
    }
}
