package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Activity8 {
    public static void main(String[] args) {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Setup the Firefox driver(GeckoDriver)
        WebDriverManager.firefoxdriver().setup();
        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
        //launch url
        driver.get("https://www.training-support.net/selenium/dynamic-controls");
        System.out.println("Title of page is :" + driver.getTitle());
//        Find the toggle button and click it
        WebElement button =driver.findElement(By.id("toggleCheckbox"));
        button.click();
        // Wait for the checkbox to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("dynamicCheckbox")));
        System.out.println(button.isDisplayed());
        // Click the button again
        button.click();
        // Wait for the element to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicCheckbox")));
        System.out.println(button.isDisplayed());
        //Check the checkbox
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        // Close the browser
        driver.quit();
    }
}
