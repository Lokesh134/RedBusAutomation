package Base;


import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties prop;

    public void setup() {
        prop = ConfigReader.initProperties();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get(prop.getProperty("url"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + prop.getProperty("zoom") + "'");
    }

    public void tearDown() {
        driver.quit();
    }
}
