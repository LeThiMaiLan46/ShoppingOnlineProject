package Test;

import Actions.CheckoutPageActions;
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
    CheckoutPageActions checkout;
    int currentProductsCount;
    @BeforeMethod
    public void getPage() {
        login = new LoginPageActions(driver);
        login.loginAction("standard_user", "secret_sauce");
        inventory = new InventoryPageActions(driver);
        cart = new YourCartPageActions(driver);
        checkout = new CheckoutPageActions(driver);
    }
    @Test
    public void ThePageDisplayCorrectly() {
        inventory.goToCartPage();
        Assert.assertTrue(cart.isPageLoaded());
    }

    @Test
    public void AddedProductDisplayOnCart() {
        currentProductsCount = cart.getCartCount();
        String productName = "";
        if (inventory.getCartCount() == 0) {
            productName = inventory.getProductName(1);
            inventory.addProductToCart(1);
            currentProductsCount++;
        }
        inventory.goToCartPage();
        Assert.assertEquals(cart.getCartCount(), currentProductsCount, "Display correctly product count");
        Assert.assertTrue(cart.getProductsName().contains(productName), "Added product displays on cart");
        Assert.assertTrue(cart.isRemoveButtonDisplayed(1), "Remove button displays");
    }

    @Test
    public void RemoveMultipleProductsFromCart() {
        currentProductsCount = cart.getCartCount();
        if(currentProductsCount == 0) {
            inventory.addProductToCart(1);
            inventory.addProductToCart(2);
            inventory.addProductToCart(3);
        }

        currentProductsCount = cart.getCartCount();
        String name1 = cart.getProductName(2);
        cart.removeProductFromCart(2);
        String name2 = cart.getProductName(3);
        cart.removeProductFromCart(3);
        Assert.assertEquals(cart.getCartCount(), currentProductsCount - 2, "Display correctly product count");
        Assert.assertFalse(cart.getProductsName().contains(name1), "Removed product disappear from cart");
        Assert.assertFalse(cart.getProductsName().contains(name2), "Removed product disappear from cart");
    }

    @Test
    public void RemoveSingleProductFromCart() {
        currentProductsCount = cart.getCartCount();
        if(currentProductsCount == 0) {
            inventory.addProductToCart(2);
            inventory.addProductToCart(3);
        }

        currentProductsCount = cart.getCartCount();
        String name1 = cart.getProductName(2);
        cart.removeProductFromCart(2);
        Assert.assertEquals(cart.getCartCount(), currentProductsCount - 1, "Display correctly product count");
        Assert.assertFalse(cart.getProductsName().contains(name1), "Removed product disappear from cart");
    }

    @Test
    public void ContinueShoppingBtnFunction() {
        currentProductsCount = cart.getCartCount();
        cart.clickContinueShoppingBtn();
        Assert.assertTrue(inventory.isPageLoaded(), "Back to inventory page");
        if (currentProductsCount == 0) {
            Assert.assertEquals(inventory.isCartBagdeDisplayed(), cart.isCartBagdeDisplayed(), "Cart badge disappear when no item in cart");
        } else {
            Assert.assertEquals(cart.getCartCount(), inventory.getCartCount(), "The cart badge number will be consistent");
        }
    }

    @Test
    public void HiddenCartBadgeWhenNoItem() {
        currentProductsCount = cart.getCartCount();
        if (currentProductsCount == 0) {
            Assert.assertFalse(cart.isCartBagdeDisplayed(), "Cart badge disappear when no item in cart");
        }
        inventory.addProductToCart(1);
        inventory.goToCartPage();
        Assert.assertTrue(cart.isCartBagdeDisplayed(), "Cart badge display");
        cart.removeProductFromCart(1);
        Assert.assertFalse(cart.isCartBagdeDisplayed(), "Cart badge disappear when no item in cart");
        Assert.assertNull(cart.getProductsName(), "There is no product on cart");
    }

    @Test
    public void CheckoutWhenNoItemInCart() {
        inventory.goToCartPage();
        Assert.assertFalse(cart.isCheckoutBtnDisplayed(), "Checkout button disable when no item in cart");
    }

    @Test
    public void CheckoutWhenExistItemInCart() {
        inventory.addProductToCart(1);
        inventory.goToCartPage();
        Assert.assertTrue(cart.isCheckoutBtnDisplayed(), "Checkout button enable when exist item in cart");
        cart.clickCheckoutBtn();
        Assert.assertTrue(checkout.isPageLoaded(), "Navigate to Checkout page successfully");
    }
}
