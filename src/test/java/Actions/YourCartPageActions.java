package Actions;

import UI.InventoryPageUI;
import UI.YourCartPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class YourCartPageActions extends BasePage {
    public YourCartPageActions(WebDriver driver) {
    }

    public boolean isPageDisplayed() {
        return isDisplayed(YourCartPageUI.TITLE)
                && isDisplayed(YourCartPageUI.CONTINUE_BTN)
                && isDisplayed(YourCartPageUI.CHECKOUT_BTN);
    }

    public void removeProductFromCart(int productNumber) {
        String productName = getProductName(productNumber - 1);
        click(String.format(InventoryPageUI.REMOVE_FROM_CART_BTN, productName));
    }

    public String getProductName(int index) {
        List<WebElement> products = getListElements(InventoryPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement product : products) {
            productsName.add(product.getText());
        }
        return productsName.get(index);
    }

    public List<String> getProductsName() {
        List<WebElement> products = getListElements(YourCartPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement product : products) {
            productsName.add(product.getText());
        }
        return productsName;
    }
}
