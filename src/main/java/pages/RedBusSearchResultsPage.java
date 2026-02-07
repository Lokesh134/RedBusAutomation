package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusSearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RedBusSearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By primoFilter = By.xpath("//div[contains(text(),'Primo')]");
    private By eveningFilter = By.xpath("//div[contains(text(),'18:00-24:00')]");
    private By busRows = By.xpath("//li[contains(@class,'tupleWrapper')]");
    private By busName = By.xpath(".//div[contains(@class,'travelsName')]");
    private By endOfList = By.xpath("//span[text()='End of list']");

    public void applyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(primoFilter)).click();
        wait.until(ExpectedConditions.elementToBeClickable(eveningFilter)).click();
    }

   
    public void loadAllBuses() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int previousCount = 0;
        int sameCountCycles = 0;
        int MAX_SAME_COUNT_CYCLES = 6;

        while (sameCountCycles < MAX_SAME_COUNT_CYCLES) {

            // Always re-locate elements
            List<WebElement> rows = driver.findElements(busRows);
            int currentCount = rows.size();

            System.out.println("Buses loaded so far: " + currentCount);

            if (currentCount > previousCount) {
                previousCount = currentCount;
                sameCountCycles = 0;
            } else {
                sameCountCycles++;
            }

            // ✅ Scroll SMALL and HUMAN-LIKE
            js.executeScript("window.scrollBy(0, 400);");

            // ✅ Give RedBus time to render (controlled wait)
            try {
                Thread.sleep(1200);
            } catch (InterruptedException ignored) {
            }
        }

        System.out.println(
                "All buses loaded. Total buses: "
                + driver.findElements(busRows).size());
    }


    public void printBusNames() {
        List<WebElement> rows = driver.findElements(busRows);
        for (WebElement row : rows) {
            System.out.println(row.findElement(busName).getText());
        }
        System.out.println("Total buses: " + rows.size());
    }
}
