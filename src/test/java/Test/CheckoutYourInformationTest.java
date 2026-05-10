package Test;

import Actions.*;
import Utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

public class CheckoutYourInformationTest extends BaseTest{
    LoginPageActions login;
    InventoryPageActions inventory;
    YourCartPageActions cart;
    CheckoutPageActions checkout;
    OverviewPageActions overview;

    @BeforeMethod
    public void getPage() {
        login = new LoginPageActions(driver);
        login.loginAction("standard_user", "secret_sauce");
        inventory = new InventoryPageActions(driver);
        cart = new YourCartPageActions(driver);
        checkout = new CheckoutPageActions(driver);
        overview = new OverviewPageActions(driver);
        inventory.addProductToCart(1);
        inventory.goToCartPage();
        cart.clickCheckoutBtn();
    }

    @DataProvider(name = "checkoutData")
    public Object[][] getYourInformationData() {
        String filePath = System.getProperty("user.dir") + "/src/test/java/TestData/testData.xlsx";
        return ExcelUtils.getAllData(filePath, "Checkout");
    }

    @Test
    public void ThePageDisplayCorrectly(){
        Assert.assertTrue(checkout.isPageLoaded(), "The page displays correctly");
    }



    @Test(dataProvider = "checkoutData")
    public void FillInYourInformation(Map<String,String> data) {
        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String postalCode = data.get("postalCode");

        String expectedResult = data.get("expectedResult");
        String expectedError = data.get("errorMessage");

        checkout.fillInformation(firstName, lastName, postalCode);

        if("success".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-step-one"));
            Assert.assertTrue(overview.isPageLoaded(), "The Overview page displays");
        } else {
            String actualError = checkout.getErrorMessage();
            Assert.assertEquals(actualError, expectedError, "Continue checkout process unsuccessfully.");
        }
    }

    @Test
    public void CancelCheckoutProcess() {
        checkout.clickCancelButton();
        Assert.assertTrue(cart.isPageLoaded(), "After clicking Cancel, will navigate to inventory page");
    }

    @Test
    public void ContinueCheckoutProcess() {
        checkout.fillInformation("Mai", "Lan", "12345");
        checkout.clickContinueBtn();
        Assert.assertTrue(overview.isPageLoaded(), "Overview page displays successfully");
    }


}
