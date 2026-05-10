package UI;

public class YourCartPageUI {
    public static final String TITLE = "//span[text()=\"Your Cart\"]";
    public static final String CONTINUE_BTN = "//button[@id=\"continue-shopping\"]";
    public static final String CHECKOUT_BTN = "//button[@id=\"checkout\"]";
    public static final String PRODUCT_NAME = "//div[@class=\"inventory_item_name\"]";

    public static final String REMOVE_FROM_CART_BTN = "//div[text()= %s]/ancestor::div[@class='cart_item_label']//button[contains(text(),'Remove')]";
    public static final String CART_BADGE = "//span[@class='shopping_cart_badge']";
}
