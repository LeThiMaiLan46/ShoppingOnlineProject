package Actions;

import UI.InventoryPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InventoryPageActions extends BasePage {
    private InventoryMenuActions menu;
    public InventoryPageActions(WebDriver driver) {
        super(driver);
        menu = new InventoryMenuActions(driver);
    }

    public InventoryMenuActions menu() {
        return menu;
    }

    public boolean isPageLoaded() {
        return Objects.equals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html")
                && isDisplayed(InventoryPageUI.TITLE)
                && isDisplayed(InventoryPageUI.MENU_ICON)
                && isDisplayed(InventoryPageUI.CART_ICON)
                && isDisplayed(InventoryPageUI.SORT_ICON);
    }

    public void addProductToCart(int productNumber) {
        String productName = getProductName(productNumber - 1);
        click(String.format(InventoryPageUI.ADD_TO_CART_BTN, productName));
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(getText(InventoryPageUI.CART_BADGE));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isAddButtonDisplayed(int productNumber) {
        String productName = getProductName(productNumber - 1);
        return getElement(String.format(InventoryPageUI.ADD_TO_CART_BTN, productName)).isDisplayed();
    }

    public boolean isRemoveButtonDisplayed(int productNumber) {
        String productName = getProductName(productNumber - 1);
        return getElement(String.format(InventoryPageUI.REMOVE_FROM_CART_BTN, productName)).isDisplayed();
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

    public void goToCartPage() {
        click(InventoryPageUI.CART_ICON);
    }
}
