package Actions;

import UI.YourCartPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class YourCartPageActions extends BasePage {
    public YourCartPageActions(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isDisplayed(YourCartPageUI.TITLE) && isDisplayed(YourCartPageUI.CONTINUE_BTN) &&
            isDisplayed(YourCartPageUI.CHECKOUT_BTN);

    }

    public void removeProductFromCart(int productNumber) {
        String productName = getProductName(productNumber);
        click(String.format(YourCartPageUI.REMOVE_FROM_CART_BTN, productName));
    }

    public String getProductName(int index) {
        List<WebElement> products = getListElements(YourCartPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement product : products) {
            productsName.add(product.getText());
        }
        return productsName.get(index-1);
    }

    public List<String> getProductsName() {
        List<WebElement> products = getListElements(YourCartPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement product : products) {
            productsName.add(product.getText());
        }
        return productsName;
    }

    public boolean isRemoveButtonDisplayed(int productNumber) {
        String productName = getProductName(productNumber - 1);
        return getElement(String.format(YourCartPageUI.REMOVE_FROM_CART_BTN, productName)).isDisplayed();
    }

    public void clickContinueShoppingBtn() {
        click(YourCartPageUI.CONTINUE_BTN);
    }

    public void clickCheckoutBtn() {
        click(YourCartPageUI.CHECKOUT_BTN);
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(getText(YourCartPageUI.CART_BADGE));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartBagdeDisplayed() {
        return isDisplayed(YourCartPageUI.CART_BADGE);
    }

    public boolean isCheckoutBtnDisplayed() {
        return isDisplayed(YourCartPageUI.CHECKOUT_BTN);
    }
}
