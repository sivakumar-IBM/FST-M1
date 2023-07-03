package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity8 {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void driverinititae() {
        // Set up the Firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Driver initiated");
        //Open the browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");

    }

    @Test
    public void verifyBackEndSiteLogin() throws InterruptedException {
        //Enter Username
        driver.findElement(By.id("user_login")).sendKeys("root");
        //Pwd
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        //click login
        driver.findElement(By.id("wp-submit")).click();
        //verify Dashboard is available to confirm login success
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='Dashboard']")).isDisplayed());

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }

}
