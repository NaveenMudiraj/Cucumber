package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	private static Logger log = LogManager.getLogger(Base.class.getName());

	public static WebDriverWait wait;
	
	/*************************************************************************************
	 * Method name-initializeDriver 
	 * Method Description- This Method is used to initializeDriver
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability("EnableNativeEvents", false);
			dc.setCapability("ignoreZoomSetting", true);
			dc.setJavascriptEnabled(true);
			dc.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, false);
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(dc);
		} else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\msedgedriver.exe");

			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;

	}
	
	/*************************************************************************************
	 * Method name-getScreenshot 
	 * Method Description- This Method is used to getScreenshot
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public static void getScreenshot(String testcasename, WebDriver driver) {

		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "\\reports" + testcasename + ".png";
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/*************************************************************************************
	 * Method name-waitForElementdisplayed 
	 * Method Description- This Method is used to waitForElementdisplayed
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/
	public void waitForElementdisplayed(WebElement element) {
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
		}

		catch (NoSuchElementException e) {
			System.out.println(e);
			log.info("No Element found");
		}
	}
	/*************************************************************************************
	 * Method name-takeScreenshot 
	 * Method Description- This Method is used to takeScreenshot
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void takeScreenshot() {
		try {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotBase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			log.info("Screenshot not taken ");
			e.printStackTrace();
		}

	}
	/*************************************************************************************
	 * Method name-getPageTitle 
	 * Method Description- This Method is used to getPageTitle
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public String getPageTitle() {

		try {
			log.info(String.format("The title of the page is: %s\n\n", driver.getTitle()));
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
		}
	}

	/*************************************************************************************
	 * Method name-navigateToURL 
	 * Method Description- This Method is used to navigateToURL
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/
	public void navigateToURL() throws IOException {
	/*	prop = new Properties();
	//	FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\test.html");
	/prop.load(fis);*/
		String browserName =System.getProperty("user.dir") + "\\src\\main\\java\\resources\\test.html";
		log.info("Navigating to: " + browserName);
		log.info("Thread id = " + Thread.currentThread().getId());

		try {
			driver.navigate().to(browserName);
		} catch (Exception e) {
			log.info("URL did not load: " + browserName);
			throw new TestException("URL did not load");
		}
	}
	
	/*************************************************************************************
	 * Method name-waitForPageLoad 
	 * Method Description- This Method is used to waitForPageLoad
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void waitForPageLoad() {

		try {
			driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.info("Page did not load: ");
			throw new TestException("Page did not load");
		}
	}
}
