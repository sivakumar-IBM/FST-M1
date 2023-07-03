package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {

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
    public void verifyPageHeading() {
        //Get the  pageHeadingTitle
        String pageHeadingTitle = driver.findElement(By.xpath("//h1[@class='entry-title']")).getText();
        System.out.println("Title of heading page is:" + pageHeadingTitle);
        Assert.assertEquals(pageHeadingTitle, "Welcome to Alchemy Jobs");

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }


}
