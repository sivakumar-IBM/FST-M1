package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Activity3 {

    WebDriver driver;

    @BeforeClass
    public void driverinititae() {
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        System.out.println("Driver initiated");
        //Open the browser
        driver.get("https://www.training-support.net/selenium/login-form");
    }

    @Test
    public void openbrowser() {

        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String confirmMessage = driver.findElement(By.id("action-confirmation")).getText();
        System.out.println("Confirmation message is :" + confirmMessage);
        Assert.assertEquals(confirmMessage, "Welcome Back, admin");

    }

    @AfterClass
    public void closedriver() {
        driver.close();
        System.out.println("Driver closed");
    }


}
