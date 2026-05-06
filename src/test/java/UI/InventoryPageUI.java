package UI;

public class InventoryPageUI {
    public static final String CURRENT_URL = "https://www.saucedemo.com/inventory.html";
    public static final String TITLE = "//span[text()=\"Products\"]";
    public static final String MENU_ICON = "//button[@id=\"react-burger-menu-btn\"]";
    public static final String CART_ICON = "//a[@class='shopping_cart_link']";
    public static final String SORT_ICON = "//select[@class=\"product_sort_container\"]";

    public static final String MENU_PANEL = "//div[@class='bm-menu-wrap']";
    public static final String MENU_CLOSE_BUTTON = "//button[@id='react-burger-cross-btn']";
    public static final String MENU_ITEM = "//a[text()='%s']";

    public static final String ADD_TO_CART_BTN = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(),'Add to cart')]";
    public static final String REMOVE_FROM_CART_BTN = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(),'Remove')]";
    public static final String CART_BADGE = "//span[@class='shopping_cart_badge']";

}
