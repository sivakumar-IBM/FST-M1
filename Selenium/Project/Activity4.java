package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {

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
    public void verifySecondHeading() {
        //Get the secondHeadingTitle
        String secondHeadingTitle = driver.findElement(By.xpath("//h2")).getText();
        System.out.println("Title of second heading is:" + secondHeadingTitle);
        Assert.assertEquals(secondHeadingTitle, "Quia quis non");

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }


}
