package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	
	public void invokeBrowser() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(
					"C:\\Users\\vinod\\eclipse-workspace\\LoginCredential\\src\\main\\resources\\Config\\Config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		if (prop.getProperty("browserName").matches("chrome")) {
			
			ChromeOptions option = new ChromeOptions();
			option.addArguments("start-maximized");
		     option.addArguments("--disable-blink-features=AutomationControlled");
		     option.addArguments("--disable-notifications"); // Disabling any notifications
		     System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		     driver = new ChromeDriver(option);
		
		}

		
		if (prop.getProperty("browserName").matches("firefox")) {
			driver = new FirefoxDriver();
		}

		// To maximize the Browser Window
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	
	public void openURL(String websiteURLKey) {
		driver.get(prop.getProperty(websiteURLKey));
	}

	
	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
	}

	
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}


	public void Screenshoot(String fileName) throws IOException {
     	TakesScreenshot t=(TakesScreenshot)driver;
        File src=t.getScreenshotAs(OutputType.FILE);
        File dest=new File("./screenshot"+fileName+".png");
        FileUtils.copyFile(src, dest);
	}

	
	public void endReport() {
		report.flush();
	}

	public void closeBrowser() {
		driver.quit();
	}

}
