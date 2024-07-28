package Pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import Base.Base;

public class FListing extends Base{
	
	By list = By.id("h_flist");
	By mobile = By.id("fmb0");
	By submit = By.xpath("//*[@id=\"add_div0\"]/div[3]/button");
	By error = By.id("fcoe");
	
	public void register() throws IOException {
		logger = report.createTest("Trying to list in Free Listing");
		try {
		openURL("websiteURLKey");
		driver.findElement(list).click();
		reportPass("Free Listing page is Opened");
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheet("Data");
		driver.findElement(mobile).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		reportPass("Invalid data is entered in the Mobile number field");
		driver.findElement(submit).click();
		reportPass("Submit the data");
		String str=driver.findElement(error).getText();
		System.out.println("Invalid Input error message :"+str);
		System.out.println("\n");
		Screenshoot("FList");
		reportPass("Error is obtained");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
