package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public BasePage() {
    }

    protected WebElement getElement(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected void click(String xpath) {
        getElement(xpath).click();
    }

    protected void sendKeys(String xpath, String value) {
        WebElement element = getElement(xpath);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(String xpath) {
        return getElement(xpath).getText();
    }

    protected boolean isDisplayed(String xpath) {
        return getElement(xpath).isDisplayed();
    }

    protected List<WebElement> getListElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }
}
