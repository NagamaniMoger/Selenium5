package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {

//	     Create Driver
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			System.out.println("chrome browser started");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			System.out.println("firefox browser started");
			break;

		default:
			System.out.println("Do not know how to start" + browser + "starting chrome instead");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();

	}

	@Test
	public void noSuchElementExceptionTest() {

		// open webpage
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		System.out.println("page opened");

		// click on Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();

		System.out.println("clicked on button");

		// to fix no suchElementException we can use thread.sleep
		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// implicit wait -> another way to to avoid noSuchElementException
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2Input = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

		// Verify row2 input field displayed
		// visibilityOfElementLocated-> this method not only locates the element but
		// also returns the element so line no 77 is not required instead we can store
		// the returned element in a variable
		// WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input")); 
		Assert.assertTrue(row2Input.isDisplayed(), "row2 is not displayed");

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
//      close browser
		driver.quit();
		System.out.println("Test Finished");
	}

}
