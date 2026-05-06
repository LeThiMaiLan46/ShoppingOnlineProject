package Actions;

import UI.InventoryPageUI;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

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

    public void addProductToCart(String productName) {
        click(String.format(InventoryPageUI.ADD_TO_CART_BTN, productName));
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(getText(InventoryPageUI.CART_BADGE));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isAddButtonDisplayed(String productName) {
        return getElement(String.format(InventoryPageUI.ADD_TO_CART_BTN, productName)).isDisplayed();
    }
}
