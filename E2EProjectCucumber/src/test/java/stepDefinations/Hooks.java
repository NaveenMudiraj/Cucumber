package stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import pageObjects.HomePage;
import resources.Base;

public class Hooks extends Base {
	private static Logger log = LogManager.getLogger(Hooks.class.getName());
	
	@Before()
	public void launch(){
		log.info("@@@Browser is launched@@@");
		
	}
	

	@After()
	public void teardown() {
		log.info("@@@Quit the browser@@@");
		driver.quit();

	}

}
