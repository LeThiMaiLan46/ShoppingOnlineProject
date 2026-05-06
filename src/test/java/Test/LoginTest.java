package Test;

import Actions.InventoryPageActions;
import Actions.LoginPageActions;
import Utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void getPage() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = System.getProperty("user.dir") + "/src/test/java/TestData/testData.xlsx";
        return ExcelUtils.getAllData(filePath, "Login");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(Map<String,String> data) {
        LoginPageActions loginPageActions = new LoginPageActions(driver);

        String username = data.get("username");
        String password = data.get("password");
        String expectedResult = data.get("expectedResult");
        String expectedError = data.get("errorMessage");

        loginPageActions.loginAction(username, password);

        if("success".equalsIgnoreCase(expectedResult)) {
            InventoryPageActions inventoryPageActions = new InventoryPageActions(driver);
            Assert.assertTrue(inventoryPageActions.isPageLoaded(), "Login successfully. The Inventory page displays");
        } else {
            String actualError = loginPageActions.getErrorMessage();
            Assert.assertEquals(actualError, expectedError, "Login unsuccessfully.");
        }
    }


}
