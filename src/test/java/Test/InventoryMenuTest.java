package Test;

import Actions.InventoryPageActions;
import Actions.LoginPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InventoryMenuTest extends BaseTest {
    private InventoryPageActions inventory;
    private LoginPageActions login;

    @BeforeMethod
    public void getPage() {
        login = new LoginPageActions(driver);
        login.loginAction("standard_user", "secret_sauce");
        inventory = new InventoryPageActions(driver);
    }

    @Test
    public void OpenMenuCorrrectly() {
        inventory.menu().openMenu();
        Assert.assertTrue(inventory.menu().isMenuDisplayed());
    }

    @Test
    public void ChangeTextWhenHoverMenuItems() {
        inventory.menu().openMenu();
        inventory.menu().hoverMenuItem("All Items");
        inventory.menu().hoverMenuItem("About");
        inventory.menu().hoverMenuItem("Logout");
        inventory.menu().hoverMenuItem("Reset App State");

        //verify CSS change
    }

    @Test
    public void ClickAllItems() {
        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("All Items");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void ClickAbout() {
        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("About");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
        driver.navigate().back();
    }

    @Test
    public void ClickResetAppState() {
        inventory.addProductToCart(2);
        Assert.assertTrue(inventory.getCartCount() > 0);

        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("Reset App State");
        Assert.assertEquals(inventory.getCartCount(), 0);
    }

    @Test
    public void ClickLogout() {
        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("Logout");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        login.loginAction("standard_user", "secret_sauce");

    }

}
