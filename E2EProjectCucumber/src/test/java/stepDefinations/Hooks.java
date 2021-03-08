package stepDefinations;

import org.junit.After;
import org.openqa.selenium.WebDriver;

import resources.Base;

public class Hooks extends Base {
	
	WebDriver driver;
	
	@After
	public void teardown() {
		driver.close();
		
	}

}
