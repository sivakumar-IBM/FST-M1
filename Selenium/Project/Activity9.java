package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Activity9 {

    WebDriver driver;
    WebDriverWait wait;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String randomnumber =sdf.format(new Date());


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
    public void verifyJobPostingUsingBackEnd() throws InterruptedException {
        //Enter Username
        driver.findElement(By.id("user_login")).sendKeys("root");
        //Pwd
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        //click login
        driver.findElement(By.id("wp-submit")).click();
        //verify Dashboard is available to confirm login success
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='Dashboard']")).isDisplayed());
        //click Joblisting
        driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='Job Listings']")).click();
        //click Add New
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='page-title-action' and text()='Add New']"))).click();
        //Enter Job title
        driver.findElement(By.id("post-title-0")).sendKeys("Test Job-" +randomnumber);
        //Location
        driver.findElement(By.id("_job_location")).sendKeys("India");
        //Click publish button
        driver.findElement(By.xpath("//button[contains(@class,'publish')]")).click();
        driver.findElement(By.xpath("//button[@class='components-button editor-post-publish-button editor-post-publish-button__button is-primary' and text()='Publish']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='editor-post-publish-panel__header-published' and text()='Published']")));
        //Go to home page
        driver.findElement(By.xpath("//a[@class='components-button edit-post-fullscreen-mode-close has-icon']")).click();
        //click Joblisting
        driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='Job Listings']")).click();
        //Assert the job posted
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='job_position']/a[1]")).getText(),"Test Job-" +randomnumber);
    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }

}
