 package screenCapture;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	public static String capture3(String filename,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\HP\\eclipse-workspace\\TestNgDemo\\Screenshots\\"+System.currentTimeMillis()+filename);
		String screnpath=dest.getAbsolutePath();
		FileUtils.copyFile(source, dest);
		return screnpath;
		
		

	}

}
