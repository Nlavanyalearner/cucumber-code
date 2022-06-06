package com.flipkart.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter 
{
	public static ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		DateFormat dateformat = new SimpleDateFormat("MM-dd-yy_hh.mm.ss");
		Date date = new Date();
		String time = dateformat.format(date);
	
		spark=new ExtentSparkReporter("C:\\Users\\HP\\eclipse-workspace\\Amazon\\Reports\\"+time+".html");
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("host", "localhost");
		report.setSystemInfo("name", "lavanya");
		report.setSystemInfo("os", "windows");
		spark.config().setDocumentTitle("flipkart automation report");
		spark.config().setReportName("extent report");
		spark.config().setTheme(Theme.DARK);
		
	}
	public void onTestSuccess(ITestResult tr) {
		test=report.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
	}
	public void onTestFailureL(ITestResult tr) {
		test=report.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenpath="C:\\Users\\HP\\eclipse-workspace\\TestNgDemo\\Screenshots\\"+tr.getName()+".png";
		File src=new File(screenpath);
		if (src.exists()) {
			try {
				test.fail("screen shot is below"+test.addScreenCaptureFromPath(screenpath));
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public void onTestSkipped(ITestResult tr) {
		
		test=report.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	public void onFinish(ITestContext testcontext) {
		report.flush();
		
	}
	
	   

}
