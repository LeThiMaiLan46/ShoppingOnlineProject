package Actions;

import UI.OverviewPageUI;
import org.openqa.selenium.WebDriver;

public class OverviewPageActions extends BasePage{
    public OverviewPageActions(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isDisplayed(OverviewPageUI.TITLE);
    }
}
