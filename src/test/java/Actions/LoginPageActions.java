package Actions;

import UI.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageActions extends BasePage {
    public LoginPageActions(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isDisplayed(LoginPageUI.USERNAME) && isDisplayed(LoginPageUI.PASSWORD) && isDisplayed(LoginPageUI.LOGIN_BTN);
    }

    public void loginAction(String user, String pass) {
        sendKeys(LoginPageUI.USERNAME, user);
        sendKeys(LoginPageUI.PASSWORD, pass);
        click(LoginPageUI.LOGIN_BTN);
    }

    public String getErrorMessage() {
        return getText(LoginPageUI.ERROR_MSG);
    }
}
