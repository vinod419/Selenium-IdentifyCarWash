package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class RetrieveItems extends Base{
	
	By fitness = By.xpath("//*[@id=\"hotkeys_text_24\"]");
	By gym = By.xpath("//span[@title='Gym']");
	By opt = By.xpath("//span[@class='meditle1 lng_commn']");
	
	public void items() throws IOException {
		logger = report.createTest("Getting the submenu's");
		try {
		openURL("websiteURLKey");	
		driver.findElement(fitness).click();
		driver.findElement(gym).click();
		reportPass("Gym option is clicked");
		Screenshoot("Gym");
		System.out.println("Gym Sub-Menu items :");
		List<WebElement> options = driver.findElements(opt);
		for(WebElement option :options){
		    System.out.println(option.getText());
		}
		reportPass("Sub menus are obtained Successfully.");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
