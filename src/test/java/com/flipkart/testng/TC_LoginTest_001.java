package com.flipkart.testng;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.flipkart.pageobject.LoginPage;
import com.flipkart.utilities.ConfigRead;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_LoginTest_001 extends BaseClass {
	 ConfigRead config=new ConfigRead();
	@Test
	public void login() throws InterruptedException {
		driver.get(config.geturl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		LoginPage page=new LoginPage( driver);
//		Thread.sleep(3000);
//		page.loginButton();
		page.username1(config.getname());
		page.password1(config.getpassword());
		page.LoginClick();
		
	}
	

}
