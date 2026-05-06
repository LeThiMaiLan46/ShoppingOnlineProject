package Actions;

import UI.InventoryPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class InventoryMenuActions extends BasePage {
    public InventoryMenuActions(WebDriver driver) {
        super((driver));
    }

    public void openMenu() {
        click(InventoryPageUI.MENU_ICON);
    }

    public boolean isMenuDisplayed() {
        return isDisplayed(InventoryPageUI.MENU_PANEL);
    }

    public void closeMenu() {
        click(InventoryPageUI.MENU_CLOSE_BUTTON);
    }

    public void clickMenuItem(String item) {
        click(String.format(InventoryPageUI.MENU_ITEM, item));
    }

    public void hoverMenuItem(String item) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(String.format(InventoryPageUI.MENU_ITEM, item))).perform();
    }
}
