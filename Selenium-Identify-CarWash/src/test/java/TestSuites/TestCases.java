package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.CarWash;
import Pages.FListing;
import Pages.RetrieveItems;

public class TestCases extends Base {
	

	CarWash cw = new CarWash();
	FListing fl = new FListing();
	RetrieveItems ri = new RetrieveItems();

	@BeforeTest
	public void invokeBrowser() {
		logger = report.createTest("Executing Test Cases");

		cw.invokeBrowser();
		reportPass("Browser is Invoked");
	}

	@Test(priority = 1)
	public void carwash() throws InterruptedException {

		cw.carwash();
		reportPass("Car washes are retrieved");
	}

	@Test(priority = 2)
	public void listing() throws IOException {
		fl.register();
		reportPass("Errors are obtained in free listing");
	}

	@Test(priority = 3)
	public void retrieve() throws InterruptedException, IOException {
		ri.items();
		reportPass("Sub Menus are Obtained");
	}

	@AfterTest
	public void closeBrowser() {
		ri.endReport();
		ri.closeBrowser();
	}

}
