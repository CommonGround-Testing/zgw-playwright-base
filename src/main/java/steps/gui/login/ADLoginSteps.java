package steps.gui.login;

import com.microsoft.playwright.Page;
import org.jboss.aerogear.security.otp.Totp;
import pages.ADLoginPage;
import users.ADUser;

public class ADLoginSteps {

    private final ADLoginPage adLoginPage;

    public ADLoginSteps(Page page) {
        adLoginPage = new ADLoginPage(page);
    }

    /**
     * Wacht op het veld voor naam gebruiker en vul dan alle gegevens in
     *
     * @param user
     */
    public void login_met_ad_user(ADUser user) {
        adLoginPage.gebruikersnaamInput.waitFor();
        adLoginPage.gebruikersnaamInput.fill(user.getUsername());
        adLoginPage.volgendeInput.click();
        adLoginPage.wachtwoordInput.fill(user.getPassword());
        adLoginPage.aanmeldenInput.click();
        adLoginPage.kanAuthAppNietGebruikenLink.click();
        adLoginPage.gebruikVerificatieCode.click();
        final Totp generatedOTP = new Totp(user.getTotpSecret());
        adLoginPage.codeInput.fill(generatedOTP.now());
        adLoginPage.verifierenInput.click();
    }
}
