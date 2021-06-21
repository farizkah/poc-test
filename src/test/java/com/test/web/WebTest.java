package com.test.web;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.bukalapak.com");
	}
	
	@Test
	public void searchProduct() {
		driver.findElement(By.id("v-omnisearch__input")).sendKeys("iphone 12"+ Keys.ENTER);
		String txtSearchLabel = driver.findElement(By.cssSelector("[data-test='title']")).getText();
		assertTrue(txtSearchLabel.contains("iphone 12"));
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
