package com.spiceJet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJetUsingTestng {
	public static WebDriver driver;
	public ExtentSparkReporter sparkreporter;
	public ExtentReports reports;
	public ExtentTest test;
	WebDriverWait wait;
	public static String capture3() throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\HP\\eclipse-workspace\\TestNgDemo\\Screenshots\\"+System.currentTimeMillis()+".png");
		String screnpath=dest.getAbsolutePath();
		FileUtils.copyFile(source, dest);
		String path =capture3 ();
		return screnpath;
	
	}
	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		DateFormat dateformat = new SimpleDateFormat("MM-dd-yy_hh.mm.ss");
		Date date = new Date();
		String time = dateformat.format(date);
		sparkreporter=new ExtentSparkReporter("C:\\Users\\HP\\eclipse-workspace\\TestNgDemo\\Reports\\"+time+".html");
		sparkreporter.config().setDocumentTitle("selenium extent reports");
		sparkreporter.config().setReportName("spicejet report");
		sparkreporter.config().setTheme(Theme.DARK);
		reports=new ExtentReports();
		wait=new WebDriverWait(driver, 20);
	    reports.attachReporter(sparkreporter);
		reports.setSystemInfo("lavanya", "author");
		reports.setSystemInfo("hostname", "localhost");
		
	}
	
	 
		
		
	
	@Test(priority=0)
	public void launchapp() throws IOException {
		try {
			test=reports.createTest("lavunching spicejet application");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://www.spicejet.com/");
			test.log(Status.PASS, "launch the application").addScreenCaptureFromPath(capture3());
			
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL,"unable to launch" ).addScreenCaptureFromPath(capture3());
		}
		
	}
	@Test(priority = 1)
	public void sourceLocation() {
		try {
			if(driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//input")).isDisplayed()) {
				WebElement source=	driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//input"));
				wait.until(ExpectedConditions.visibilityOf(source) );
				source.click();
				driver.findElement(By.xpath("(//div[.='Tirupati'])[2]")).click();
				test.log(Status.PASS, "source location application").addScreenCaptureFromPath(capture3());
				}else {
					test.log(Status.PASS, "unable to select source").addScreenCaptureFromPath(capture3());
					
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test(priority = 2)
	public void destnation() throws IOException {
		
		if(driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']//div[2]//input")).isDisplayed()) {
			
			driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']//div[2]//input")).click();
			WebElement dest=driver.findElement(By.xpath("//div[.='Rajiv Gandhi International Airport']"));
			wait.until(ExpectedConditions.visibilityOf(dest));
			test.log(Status.PASS, "dest location").addScreenCaptureFromPath(capture3());
			}else {
				test.log(Status.PASS, "unable to select").addScreenCaptureFromPath(capture3());
			}

	}
	@Test(priority = 3)
	public void dateOfJurney() throws IOException { 
		try {
			driver.findElement(By.xpath("(//div[@data-testid='undefined-calendar-day-27'])[2]")).click();
			test.log(Status.PASS, "date of journey").addScreenCaptureFromPath(capture3());
		
	}catch (Exception e) {
		test.log(Status.PASS, "unable to select date").addScreenCaptureFromPath(capture3());
	}

	}
	@Test(priority = 4)
	public void passengerType() throws IOException {
		try {
			test=reports.createTest("passenger type", "specification of passenger is needed");
			driver.findElement(By.xpath("(//div[.='Senior Citizen'])[4]")).click();
			test.log(Status.PASS," select to passenger ",MediaEntityBuilder.createScreenCaptureFromPath(capture3()).build());
		    test.info(MarkupHelper.createLabel("citen was selected ",ExtentColor.GREEN) );
		}
			catch (Exception e) {
				test.log(Status.FAIL,"failed to select to passenger",MediaEntityBuilder.createScreenCaptureFromPath(capture3()).build());
			    
			}  

	}
	@AfterTest
	public void closingBrowser() {
		reports.flush();
		
		driver.quit();
		
	}



}
