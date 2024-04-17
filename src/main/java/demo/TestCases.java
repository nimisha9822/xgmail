
package demo;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import dev.failsafe.internal.util.Assert;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        Thread.sleep(1000);
        String currURL = driver.getCurrentUrl();
        Thread.sleep(1000);
        if (currURL.contains("calendar")) {
            System.out.println("URL is correct");
        } else {
            System.out.println("URL is not correct");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        Thread.sleep(2000);

        // Switch to the month view
        WebElement dropDown = driver.findElement(
                By.xpath("//*[@id=\"gb\"]/div[2]/div[2]/div[3]/div/div/div/div[5]/div/div[1]/div[1]/div/button/span"));
        dropDown.click();
        Thread.sleep(2000);
        WebElement month = driver.findElement(By.xpath("//*[@id=\"ucc-1\"]/ul/li[3]/span[2]"));
        month.click();
        if (month.getText().equals("Month")) {
            System.out.println("Month Selected!!");
        }
        Thread.sleep(2000);

        // Click on the calendar to add a task
        WebElement create = driver.findElement(By.className("Gw6Zhc"));
        create.click();
        Thread.sleep(1000);
        WebElement task = driver.findElement(By.xpath("//div[text()=\"Task\"]"));
        task.click();
        Thread.sleep(1000);

        // Enter task details
        WebElement titleField = driver.findElement(By.xpath("//input[@placeholder = \"Add title\"]"));
        titleField.sendKeys("Crio INTV Task Automation");

        WebElement descriptionField = driver.findElement(By.xpath("//textarea[@placeholder = \"Add description\"]"));
        descriptionField.sendKeys("Crio INTV Calendar Task Automation");

        // Submit the task
        WebElement submitButton = driver.findElement(
                By.xpath("//*[@id=\"yDmH0d\"]/div/div/div[2]/span/div/div[1]/div[2]/div[2]/div[4]/button"));
        submitButton.click();
        Thread.sleep(2000);

        // check if created
        WebElement checkTask = driver.findElement(
                By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[2]/div[3]/div/div[5]/div/div[2]/div"));
        checkTask.click();
        Thread.sleep(2000);

        WebElement isPresent = driver
                .findElement(By.xpath("//*[@id=\"rAECCd\"]"));
        if (isPresent.isDisplayed()) {
            System.out.println("Task is Created");
        } else {

            System.out.println("Task is not created");
        }
        System.out.println("End Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Start Test case: testCase03");
        WebElement editTask = driver.findElement(
                By.xpath("//*[@id=\"yDmH0d\"]/div/div/div[2]/span/div/div[1]/div/div/div[2]/div[1]/span/button"));
        editTask.click();
        Thread.sleep(1000);

        // clr desc
        WebElement clrDesc = driver.findElement(By.tagName("textarea"));
        Thread.sleep(2000);
        clrDesc.clear();

        // edit desc
        WebElement descriptionField = driver.findElement(By.xpath("//textarea[@placeholder = \"Add description\"]"));
        descriptionField.sendKeys(
                "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
        Thread.sleep(2000);

        // Submit the task
        WebElement submitButton = driver.findElement(
                By.xpath("//*[@id=\"yDmH0d\"]/div/div/div[2]/span/div/div[8]/div/button"));
        submitButton.click();
        Thread.sleep(2000);

        // check if created
        WebElement checkTask = driver.findElement(
                By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[2]/div[3]/div/div[5]/div/div[2]/div"));
        checkTask.click();
        Thread.sleep(2000);

        // hee
        // check id displayed
        WebElement isPresent = driver
                .findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[2]/span/div/div[2]/div[3]/div[2]"));
        if (isPresent.getText().equals(
                "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
            System.out.println("Verified Description is updated!!");
        }
        if (isPresent.isDisplayed()) {
            System.out.println("Task Updated");
        } else {
            System.out.println("Task is not Updated");
        }

        System.out.println("End Test case: testCase03");

    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        Thread.sleep(2000);

        // // Verify the title of the task
        WebElement titleElement = driver
                .findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[2]/span/div/div[2]/div[1]/div[2]"));
        String taskTitle = titleElement.getText();
        if (taskTitle.equals("Crio INTV Task Automation")) {
            System.out.println("Task title match the expected title.");
        } else {
            System.out.println("Task title does not match the expected title.");
        }

        Thread.sleep(3000);

        // Delete the task
        WebElement deleteButton = driver.findElement(
                By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[2]/span/div/div[1]/div/div/div[2]/div[2]/span/button"));
        deleteButton.click();
        Thread.sleep(2000);

        // Confirm the task deletion
        WebElement confirmationMessage = driver.findElement(By.xpath("(//div[text()=\"Task deleted\"])[2]"));
        String message = confirmationMessage.getText();
        System.out.println(message);
        if (!message.equals("Task deleted")) {
            System.out.println("Task deletion confirmation message is not as expected.");
            // Handle the failure scenario here
        }
        System.out.println("End Test case: testCase04");
    }
}

