package resources;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;




public class Base  {

	 public static WebDriver driver;
	public static Properties prop;
	
public static WebDriverWait wait;

//WebDriverWait wait =new WebDriverWait(driver, 30);

public WebDriver initializeDriver() throws IOException
{
 prop= new Properties();
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");

prop.load(fis);
String browserName=prop.getProperty("browser");
System.out.println(browserName);

if(browserName.equals("chrome"))
{
	 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
	driver= new ChromeDriver();
		//execute in chrome driver
	
}
else if (browserName.equals("firefox"))
{
	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
	 driver= new FirefoxDriver();
	//firefox code
}
else if (browserName.equals("IE"))
{
	DesiredCapabilities dc = new DesiredCapabilities();
	  dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
	  dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
	  dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
	  dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	  dc.setCapability(InternetExplorerDriver.
	  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  dc.setCapability("EnableNativeEvents", false);
	  dc.setCapability("ignoreZoomSetting", true); dc.setJavascriptEnabled(true);
	  dc.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,false);

	  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\IEDriverServer.exe");
		 driver= new InternetExplorerDriver(dc);
}
else if (browserName.equals("Edge")){
	System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\msedgedriver.exe");

	// Start Edge Session
	 driver = new EdgeDriver();
	
}

driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
driver.manage().deleteAllCookies();
driver.manage().window().maximize();

return driver;


}

public void getScreenshot(String result) throws IOException
{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("C://test//"+result+"screenshot.png"));
	
}

public static void getScreenshot(String testcasename,WebDriver driver) {
	
    
  
    try {
    	TakesScreenshot screenshot = (TakesScreenshot) driver;
    	File source=screenshot.getScreenshotAs(OutputType.FILE);
    	String destination=System.getProperty("user.dir")+"\\reports"+testcasename+".png";
        FileUtils.copyFile(source, new File(destination));
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

public void waitForElementClickable(WebElement element) {
wait =new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void waitForElementdisplayed(WebElement element) {
	wait =new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(element));
}

public void selectByname(WebElement ele,String str) {
	wait =new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(ele));
	Select s=new Select(ele);
	s.selectByVisibleText(str);
	
}

public void switchToChildWindow() {
	String ParentWindow=driver.getWindowHandle();
	Set<String> ChildWindows=driver.getWindowHandles();
	Iterator<String> it=ChildWindows.iterator();
	while(it.hasNext()) {
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
	}
}

public void switchToParentWindow() {
	String ParentWindow=driver.getWindowHandle();
	Set<String> ChildWindows=driver.getWindowHandles();
	
		driver.switchTo().window(ParentWindow);
	
}

public void scrollToEle(WebElement ele) throws InterruptedException {
	Thread.sleep(5000);
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();", ele);
	
}

public void scrollDown() throws InterruptedException {
	Thread.sleep(5000);
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	
}

public void scrollDownUsingRobot() throws InterruptedException, AWTException {
	Thread.sleep(5000);
	Robot robo=new Robot();
	robo.keyPress(KeyEvent.VK_PAGE_DOWN);
	robo.keyRelease(KeyEvent.VK_PAGE_DOWN);
	
	
}

public void jclick(WebElement ele) throws InterruptedException {
	Thread.sleep(5000);
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", ele);
	
}

public void waitForAlertDisplayed() {
	wait =new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.alertIsPresent());
}

public void actionsClick(WebElement ele) {
	wait =new WebDriverWait(driver, 30);
	Actions ac =new Actions(driver);
	ac.moveToElement(ele).click().build().perform();
}
public void actionsDClick(WebElement ele) {
	wait =new WebDriverWait(driver, 30);
	Actions ac =new Actions(driver);
	ac.moveToElement(ele).doubleClick().build().perform();
}

public void takeScreenshot() {
	File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String screenshotBase=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
			
}

public String getPageTitle() {
	
	try {
        System.out.print(String.format("The title of the page is: %s\n\n", driver.getTitle()));
        return driver.getTitle();
    } catch (Exception e) {
        throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
    }
}

public  void navigateToURL() throws IOException {
	 prop= new Properties();
	 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
	 prop.load(fis);
	 String browserName=prop.getProperty("url");
    System.out.println("Navigating to: " + browserName);
    System.out.println("Thread id = " + Thread.currentThread().getId());

    try {
        driver.navigate().to(browserName);
    } catch (Exception e) {
        System.out.println("URL did not load: " + browserName);
        throw new TestException("URL did not load");
    }
}

public void waitForPageLoad() {
	
	try {
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
    } catch (Exception e) {
        System.out.println("Page did not load: " );
        throw new TestException("Page did not load");
    }
}
}
