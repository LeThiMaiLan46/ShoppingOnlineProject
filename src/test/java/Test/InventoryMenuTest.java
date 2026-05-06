package Test;

import Actions.InventoryPageActions;
import Actions.LoginPageActions;
import UI.InventoryPageUI;
import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InventoryMenuTest extends BaseTest {
    private InventoryPageActions inventory;
    private LoginPageActions login;

    @BeforeMethod
    public void getPage() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
    public void HoverMenuItems() {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
        driver.navigate().back();
    }

    @Test
    public void ClickResetAppState() {
        inventory.addProductToCart("Sauce Labs Backpack");
        Assert.assertTrue(inventory.getCartCount() > 0);

        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("Reset App State");
        Assert.assertEquals(inventory.getCartCount(), 0);
        Assert.assertTrue(inventory.isAddButtonDisplayed("Sauce Labs Backpack"));
    }

    @Test
    public void ClickLogout() {
        inventory.menu().openMenu();
        inventory.menu().clickMenuItem("Logout");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        driver.navigate().back();
    }

}
