package com.flipkart.pageobject;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	JavascriptExecutor js;

	public LoginPage(WebDriver driver1) {
		driver=driver1;
		PageFactory.initElements(driver1, this);
		}
	By loginbutton=By.xpath("//a[.='Login']");
	By usernamefield=By.xpath("(//div[@class=\"IiD88i _351hSN\"]//input)[1]");
	By passwordfield=By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']");
	By login=By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']");
	By screen=By.xpath("//div[@class='_2MlkI1']");
	
	public void loginButtonScreen() {
		driver.findElement(loginbutton).isDisplayed();
		}

	public void loginButton() {
		driver.findElement(loginbutton).click();
		}

	public void loginScreen() {
		driver.findElement(screen).isDisplayed();
		}



	public void username1(String username) {
		driver.findElement(usernamefield).sendKeys(username);
	}

	public void password1(String password) {
		driver.findElement(passwordfield).sendKeys(password);
	}
	public void LoginClick() {
		driver.findElement(login).click();
	}
	public String homepageTitle() {
		String pagetittle=driver.getTitle();
		return pagetittle ;
	}


}






