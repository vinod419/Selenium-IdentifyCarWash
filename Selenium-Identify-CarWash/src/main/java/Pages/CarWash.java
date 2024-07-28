package Pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Base.Base;
public class CarWash extends Base{
	By city = By.id("city");
	By choice = By.id("Hyderabad");
	By care = By.id("hotkeys_text_5");
	By wash = By.xpath("//span[text()='Car Wash']");
	By Ratings = By.xpath("//span[@class='green-box']");
	By Names = By.xpath("//span[@class='lng_cont_name']");
	By cont = By.xpath("//ul[contains(@class,'padding')]//child::li//p[contains(@class,'contact')]//span/a");//ul[contains(@class,'padding')]//child::li//p[contains(@class,'contact')]//span/a
	By vote = By.xpath("//p/a/span[@class='rt_count lng_vote']");
	By rate =By.className("lng_srtfltr");
	public void carwash() throws InterruptedException {
		logger = report.createTest("Getting Car Washes greater than 4.0 rating.");
		try {
		openURL("websiteURLKey");
		Screenshoot("Home");
		driver.findElement(city).click();
		driver.findElement(choice).click();
		driver.findElement(care).click();
		Thread.sleep(3000);
		driver.findElement(wash).click();
		Thread.sleep(3000);
		Screenshoot("Car wash");;
		driver.findElement(rate).click();
		reportPass("Car wash Page is clicked");
		List<WebElement> ratings = driver.findElements(Ratings);
		List<WebElement> names = driver.findElements(Names);
		List<WebElement> contacts = driver.findElements(cont);
		List<WebElement> voting = driver.findElements(vote);
		System.out.println("List of Car Wash Services with >4 Ratings");
		System.out.println(voting.size());
		System.out.println(contacts.size());
		for (int i = 0; i<voting.size() ; i++) {
			float rate = Float.parseFloat(ratings.get(i).getText());
			String str = voting.get(i).getText();
			String[] s= str.split(" ");
			int votes = Integer.parseInt(s[0]);
			//System.out.println(i + " " +votes+" "+ratings.get(i).getText()+" "+names.get(i).getText());ii<voting.size()
			if (rate > 4 && votes > 20  ) {
				System.out.println(ratings.get(i).getText() + " - "
						+ names.get(i).getText() +" "+"voting" +"-"+ votes);
				
				reportPass("Car Washes name and ratings are obtained.");
			}
		}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}

