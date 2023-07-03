package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.List;

public class Activity7 {

    WebDriver driver;
    WebDriverWait wait;

    Actions actions;

    @BeforeClass
    public void driverinititae() {
        // Set up the Firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Driver initiated");
        //Open the browser
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void postJobAndVerify() throws InterruptedException {
        //find job link and click
        driver.findElement(By.xpath("//li[@id='menu-item-26']/a")).click();

        //Sign in if not already
        WebElement signinBtn = driver.findElement(By.xpath("//div[@class='field account-sign-in']/a"));
        if (signinBtn.isDisplayed()) {
            signinBtn.click();
            driver.findElement(By.id("user_login")).sendKeys("root");
            driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
            driver.findElement(By.id("wp-submit")).click();

        } else {
            System.out.println("Sign in button already there");
        }
        Select selectJobType = new Select(driver.findElement(By.id("job_type")));
        String JobTitle = "Test Job";
        driver.findElement(By.id("job_title")).sendKeys(JobTitle);
        driver.findElement(By.id("job_location")).sendKeys("India");
        selectJobType.selectByVisibleText("Internship");
        driver.findElement(By.xpath("//iframe[@id='job_description_ifr']")).click();
        driver.findElement(By.xpath("//iframe[@id='job_description_ifr']")).sendKeys("Description of job ");
        driver.findElement(By.name("submit_job")).click();
        WebElement submitListBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("job_preview_submit_button")));
        submitListBtn.click();
        //validate success msg
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='click here']/parent::div")));
        System.out.println(successMsg.getText());
        //validate success msg
        Assert.assertEquals(successMsg.getText(), "Job listed successfully. To view your listing click here.");
        //validate job posted is available in jobs
        driver.findElement(By.xpath("//li[@id='menu-item-24']/a[text()='Jobs']")).click();
        WebElement firstJob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='position']/h3[1]")));
        Assert.assertEquals(firstJob.getText(), JobTitle);

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }

}
