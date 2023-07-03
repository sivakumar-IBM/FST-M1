package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Activity1 {

    WebDriver driver;

    @BeforeClass
    public void driverinititae() {
        // Set up the Firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        System.out.println("Driver initiated");
        //Open the browser
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void verifyTitleOfWebsite() {
        //Get the Title
        String websiteTitle = driver.getTitle();
        System.out.println("Title of the page is:" + websiteTitle);
        Assert.assertEquals(websiteTitle, "Alchemy Jobs – Job Board Application");

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }


}
