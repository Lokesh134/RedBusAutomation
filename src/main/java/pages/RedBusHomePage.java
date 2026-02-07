package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RedBusHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By sourceButton = By.xpath("//div[contains(@class,'srcDestWrapper')]");
    private By searchSuggestion = By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
    private By searchButton = By.xpath("//button[contains(@class,'searchButtonWrapper')]");
    private By searchCategory = By.xpath("//div[contains(@class,'searchCategory')]");
    private By locationName = By.xpath(".//div[contains(@class,'listHeader')]");

    public void selectSourceAndDestination(String source, String destination) {
        driver.findElement(sourceButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestion));

        selectLocation(source);
        selectLocation(destination);
    }

    private void selectLocation(String location) {
        driver.switchTo().activeElement().sendKeys(location);

        List<WebElement> categories = wait
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategory, 2));

        List<WebElement> locations = categories.get(0).findElements(locationName);

        for (WebElement loc : locations) {
            if (loc.getText().equalsIgnoreCase(location)) {
                loc.click();
                break;
            }
        }
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
