package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Activity3 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Set capb
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
        //URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Test method
    @Test
    public void addActivities() throws InterruptedException {
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        //Navigate to site
        driver.get("https://v1.training-support.net/selenium");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Get Started!']"))).click();
        driver.findElement(AppiumBy.androidUIAutomator("UiScrollable(new UiSelector()).flingForward()"));
        driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".scrollTextIntoView(\"To-Do List \uF0AE Elements get added at run time \")")).click();
//        driver.findElement(AppiumBy.xpath("//android.view.View[contains(@text,'To-Do List')]")).click();
        String[] taskList = new String[3];

        taskList[0]="Add tasks to list";
        taskList[1]="Get number of tasks";
        taskList[2]="Clear the list";
        // Add All tasks
        for (String ac : taskList) {
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']"))).sendKeys(ac);
            driver.findElement(AppiumBy.androidUIAutomator("text(\"Add Task\")")).click();
        }
        List<WebElement> taskLists = driver.findElements(AppiumBy.xpath("//android.view.View[@text=='Add more tasks to this list.']/following-sibling::android.view.View[@text!='']"));
        System.out.println("no of tasks added :" + taskLists.size());

    }

        // Tear down method
        @AfterClass
        public void tearDown() {
            // Close the app
//        driver.quit();
        }

    }

