package LambdaTest_Advance;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Selenium_Advance {
	public static WebDriver driver;
	public static WebDriverWait Wait;

	@BeforeTest
	public void openBrowser() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/");
		Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void Testing() throws Exception {

		WebElement Element = Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@role='img']")));

		Assert.assertTrue(Element.isDisplayed(), "Element is not present on the page");
		WebElement SeeAll = driver.findElement(By.xpath("//a[text()='See All Integrations']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", SeeAll);
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();

		System.out.println("Parent= " + parentWindow);

		Actions actions = new Actions(driver);
		actions.moveToElement(SeeAll).keyDown(Keys.CONTROL).click(SeeAll).keyUp(Keys.CONTROL).build().perform();
		Thread.sleep(2000);
		Set<String> windowids = driver.getWindowHandles();
		for (String WindowIDs : windowids) {
			driver.switchTo().window(WindowIDs);
			System.out.println(WindowIDs);
			if (!WindowIDs.equals(parentWindow)) {
				System.out.println(driver.getCurrentUrl());
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.lambdatest.com/integrations", "Not Match");
				WebElement Code = driver.findElement(By.xpath("//h2[text()='Codeless Automation']"));

				jse.executeScript("arguments[0].scrollIntoView(true);", Code);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Integrate Testing Whiz with LambdaTest']")).click();
				Thread.sleep(2000);
				Assert.assertEquals(driver.getTitle(),
						"Running Automation Tests Using TestingWhiz LambdaTest | LambdaTest", "Not Match");
				driver.close();

			}

		}
		driver.switchTo().window(parentWindow);
		System.out.println(driver.getWindowHandles().size());
		driver.get("https://www.lambdatest.com/blog/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[text()='Community'])[1]")).click();
		Thread.sleep(1000);

		Assert.assertEquals(driver.getCurrentUrl(), "https://community.lambdatest.com/", "Not Match");

		driver.quit();

	}

}