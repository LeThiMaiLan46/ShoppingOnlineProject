package Actions;

import UI.CheckoutPageUI;
import org.openqa.selenium.WebDriver;

public class CheckoutPageActions extends BasePage{
    public CheckoutPageActions(WebDriver driver) {
        super(driver);
    }
    public boolean isPageLoaded() {
        return isDisplayed(CheckoutPageUI.TITLE)
                && isDisplayed(CheckoutPageUI.CONTINUE_BTN)
                && isDisplayed(CheckoutPageUI.CANCEL_BTN);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        sendKeys(CheckoutPageUI.FIRST_NAME_TEXT, firstName);
        sendKeys(CheckoutPageUI.LAST_NAME_TEXT, lastName);
        sendKeys(CheckoutPageUI.POSTAL_CODE_TEXT, postalCode);
        click(CheckoutPageUI.CONTINUE_BTN);
    }

    public String getErrorMessage() {
        return getText(CheckoutPageUI.ERROR_MSG);
    }

    public void clickCancelButton() {
        click(CheckoutPageUI.CANCEL_BTN);
    }

    public void clickContinueBtn() {
        click(CheckoutPageUI.CONTINUE_BTN);
    }
}
