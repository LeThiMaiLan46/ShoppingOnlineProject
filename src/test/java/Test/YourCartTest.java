package Test;

import Actions.InventoryPageActions;
import Actions.LoginPageActions;
import Actions.YourCartPageActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YourCartTest extends BaseTest {
    LoginPageActions login;
    InventoryPageActions inventory;
    YourCartPageActions cart;
    @BeforeMethod
    public void getPage() {
        login = new LoginPageActions(driver);
        login.loginAction("standard_user", "secret_sauce");
        inventory = new InventoryPageActions(driver);
        cart = new YourCartPageActions(driver);

    }
    @Test
    public void ThePageDisplayCorrectly() {
        inventory.goToCartPage();
        cart.isPageDisplayed();
    }

    @Test
    public void AddedProductDisplayOnCart() {
        int currentProductsCount = inventory.getCartCount();
        String productName = "";
        if (inventory.getCartCount() == 0) {
            productName = inventory.getProductName(1);
            inventory.addProductToCart(1);
        }
        inventory.goToCartPage();
        Assert.assertTrue(cart.getProductsName().contains(productName));
    }
}
