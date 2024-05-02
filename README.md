# SeleniumAdvance
===================================================================

package LambdaTest_Selenium_Advance;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Selenium_Advance {
	public static WebDriver driver;

	@BeforeTest
	public void openBrowser() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com");
		Thread.sleep(2000);
	}

	@AfterTest
	public void CloseBrowser() {
		System.out.println("BrowserWindows New Tab is Completed");
		driver.close();
	}

	@Test
	public void Testing() throws Exception {
		driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();
		Thread.sleep(2000);

		String parentwindow = driver.getWindowHandle();

		driver.findElement(By.id("tabButton")).click();

		Set<String> windowids = driver.getWindowHandles();

		for (String windowid : windowids) {
			driver.switchTo().window(windowid);

			if (!windowid.equals(parentwindow)) {
				System.out.println("New Tab is Opened");
				System.out.println(driver.getTitle());
				System.out.println(driver.getCurrentUrl());
				Thread.sleep(2000);
				driver.close();

			}
		}
		driver.switchTo().window(parentwindow);
		System.out.println("navigate from New Tab" + driver.getTitle());
		;

	}
}
