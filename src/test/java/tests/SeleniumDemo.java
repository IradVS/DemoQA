package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumDemo {
	// ChromeDriver driver;
	public String baseUrl = "https://demoqa.com";
	public String DriverPath = "src/test/resources/chromedriver";
	public WebDriver driver;

	@BeforeMethod
	private void startDriver() throws InterruptedException {
		System.out.println("BeforeMethod");
		System.setProperty("webdriver.chrome.driver", DriverPath);
		ChromeDriverService service = ChromeDriverService.createDefaultService();
		driver = new ChromeDriver(service);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void sampleTestMethod() throws InterruptedException {
		Actions action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

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
	public void windowHandle() throws InterruptedException {
		Actions action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../../../div"));
		action.moveToElement(elementsSection).click().perform();

		WebElement linkSection = driver.findElement(By.xpath("//span[text()='Links']/.."));
		action.moveToElement(linkSection).perform();

		executor.executeScript("arguments[0].click();", linkSection);
		Thread.sleep(5000);

		WebElement homeLink = driver.findElement(By.xpath("//*[@id='simpleLink']"));
		wait.until(ExpectedConditions.visibilityOf(homeLink));
		String previousWindow = driver.getWindowHandle();
		action.moveToElement(homeLink).perform();
		executor.executeScript("arguments[0].click();", homeLink);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		//Thread.sleep(5000); if this is uncoment the test crashes
		driver.close();
		driver.switchTo().window(previousWindow);
	}
	
	@Test
	public void radioButton() throws InterruptedException {
		Actions action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../../../div"));
		action.moveToElement(elementsSection).click().perform();

		WebElement linkSection = driver.findElement(By.xpath("//span[text()='Radio Button']/.."));
		action.moveToElement(linkSection).perform();

		executor.executeScript("arguments[0].click();", linkSection);
		Thread.sleep(5000);
		WebElement yesRadio= driver.findElement(By.id("yesRadio"));
		action.moveToElement(yesRadio).click().perform();
		WebElement yesMessage=driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/p/span"));
		wait.until(ExpectedConditions.visibilityOf(yesMessage));
		Assert.assertEquals(yesMessage.getText(), "Yes");
	}

	@AfterMethod
	public void endMethod() {
		driver.close();
		driver.quit();
	}

}