package Test;

import Actions.InventoryPageActions;
import Actions.LoginPageActions;
import Actions.YourCartPageActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest{
    InventoryPageActions inventory;
    LoginPageActions login;
    YourCartPageActions yourCart;
    @BeforeMethod
    public void getPage() {
        login = new LoginPageActions(driver);
        login.loginAction("standard_user", "secret_sauce");
        inventory = new InventoryPageActions(driver);
    }

    @Test
    public void ThePageDisplayCorrectly() {
        inventory.isPageLoaded();
    }

    @Test
    public void AddProductToCartSuccessfully() {
        int currentProductsCount = inventory.getCartCount();
        inventory.addProductToCart(1);
        Assert.assertTrue(inventory.isRemoveButtonDisplayed(1));
        Assert.assertEquals(inventory.getCartCount(), currentProductsCount + 1);
        currentProductsCount = inventory.getCartCount();
        inventory.addProductToCart(2);
        inventory.addProductToCart(3);
        inventory.addProductToCart(4);
        Assert.assertTrue(inventory.isRemoveButtonDisplayed(2));
        Assert.assertTrue(inventory.isRemoveButtonDisplayed(3));
        Assert.assertTrue(inventory.isRemoveButtonDisplayed(4));
        Assert.assertEquals(inventory.getCartCount(), currentProductsCount + 3);
    }

    @Test
    public void RemoveProductFromCart() {
        int currentProductsCount = inventory.getCartCount();
        if(currentProductsCount == 0) {
            inventory.addProductToCart(1);
            inventory.addProductToCart(2);
            inventory.addProductToCart(3);
            inventory.addProductToCart(4);
        }
        currentProductsCount = inventory.getCartCount();
        inventory.removeProductFromCart(1);
        Assert.assertTrue(inventory.isAddButtonDisplayed(1));
        Assert.assertEquals(inventory.getCartCount(), currentProductsCount - 1);
        currentProductsCount = inventory.getCartCount();
        inventory.removeProductFromCart(2);
        inventory.removeProductFromCart(3);
        Assert.assertTrue(inventory.isAddButtonDisplayed(2));
        Assert.assertTrue(inventory.isAddButtonDisplayed(3));
        Assert.assertEquals(inventory.getCartCount(), currentProductsCount - 2);
    }

    @Test
    public void DirectToYourCartPage() {
        yourCart = new YourCartPageActions(driver);
        inventory.goToCartPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

}
