package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumDemo {
	//ChromeDriver driver;
	public String baseUrl="https://demoqa.com";
	public String DriverPath="src/test/resources/chromedriver";
	public WebDriver driver;

	@BeforeTest
	private void startTesting() {
		System.out.println("BeforeTest");
	}

	@BeforeMethod
	private void startDriver() throws InterruptedException {
		System.out.println("BeforeMethod");
		System.setProperty("webdriver.chrome.driver", DriverPath);
		ChromeDriverService service=ChromeDriverService.createDefaultService();
		driver = new ChromeDriver(service);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		// Thread.sleep(3000);
	}

	@Test
	public void sampleTestMethod() throws InterruptedException {
		Actions action = new Actions(driver);
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    
        WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../../../div"));
		action.moveToElement(elementsSection).click().perform();
		
		WebElement buttonSection = driver.findElement(By.xpath("//span[text()='Buttons']/.."));
		action.moveToElement(buttonSection).perform();
		
		executor.executeScript("arguments[0].click();", buttonSection);
		Thread.sleep(5000);
		
		WebElement singleClickButton = driver.findElement(By.xpath("//div[@class='mt-4'][2]//button"));
		singleClickButton.click();
		
		WebElement dynamicMessage = driver.findElement(By.cssSelector("#dynamicClickMessage"));
		Assert.assertEquals(dynamicMessage.getText(), "You have done a dynamic click");
	}
	@Test
	public void sampleMethod2() throws InterruptedException {
		Actions action = new Actions(driver);
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    
        WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../../../div"));
		action.moveToElement(elementsSection).click().perform();
		
		WebElement buttonSection = driver.findElement(By.xpath("//span[text()='Links']/.."));
		action.moveToElement(buttonSection).perform();
		
		executor.executeScript("arguments[0].click();", buttonSection);
		Thread.sleep(5000);
		
		
	}

	@AfterMethod
	public void endMethod() {
		driver.close();
		driver.quit();
	}

}