package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Activity22 {
    public static void main(String[] args) {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Setup the Firefox driver(GeckoDriver)
        WebDriverManager.firefoxdriver().setup();
        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();
        //Action def
        Actions actions =new Actions(driver);
        //wait
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        //Launch url
        driver.get("https://www.training-support.net/selenium/popups");
        //title of pgae
        System.out.println("page titile is :"+driver.getTitle());
        //sign button
        WebElement signbutton =driver.findElement(By.xpath("//button[contains(@class,'orange')]"));
        //hover over to the button
        actions.moveToElement(signbutton).build().perform();
        //get the tooltip msg
        System.out.println("Tool tip msg is :" + signbutton.getAttribute("data-tooltip"));

        // Click the button and wait for the modal to appear
        signbutton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        // Find the input fields
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        // Enter the credentials
        username.sendKeys("admin");
        password.sendKeys("password");
        //click login
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        //get the log in status
        System.out.println(" Log in status is"+ driver.findElement(By.id("action-confirmation")).getText());
        //quit the driver
        driver.quit();



    }
}
