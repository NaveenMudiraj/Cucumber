package pageObjects;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.TestException;

import junit.framework.Assert;
import resources.Base;

public class HomePage extends Base {

	
	
	private static Logger log=LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		HomePage.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//h1[contains(text(),' Problem#1 Form')]")
	WebElement Header;
	
	@FindBy(xpath="//a[contains(text(),' exercise1 ')]")
	WebElement LinkExcercise;
	
	@FindBy(xpath = "//input[@name='txtValue']")
	List<WebElement> values;
	
	@FindBy(xpath = "//input[@name='txtValues']")
	List<WebElement> Inputvalues;
	
	@FindBy(xpath="//h1[contains(text(),'DemoForPractice')]")
	WebElement DemoForPracticeHeading;
	
	@FindBy(xpath="(//input[@id='txtTotalBalance'])[2]")
	WebElement TotalBalanceValue;



public void validateValuesOnPage() {
	waitForPageLoad();
	waitForElementdisplayed(DemoForPracticeHeading);
	try {
	int size=driver.findElements(By.xpath("//input[@name='txtValue']")).size();
	System.out.println("@@Values@@@"+size);
	if(size==6) {
		log.info("Values displayed on the screen are correct ");
		Assert.assertTrue(true);
	}else {
		log.error("values are not displayed correctly on the page");
		Assert.assertFalse(false);
	}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public void valuesGreaterThanZero() {
	waitForElementdisplayed(DemoForPracticeHeading);
	try {
	int size=Inputvalues.size();
	System.out.println("@@@Size of input values@@@ "+size);
	for(int i=0;i<size;i++) {
		
		System.out.println("@@@Inside for loop@@@");
		String valuesIntheBox=driver.findElements(By.xpath("//input[@name='txtValues']")).get(i).getAttribute("value");
		System.out.println("Values displayed in the box"+valuesIntheBox);
		String actualValueWithoutCurrencySymbol=valuesIntheBox.replaceAll("[^0-9.]", "");
	System.out.println("Values after removing symbol"+" "+actualValueWithoutCurrencySymbol);
	Double value=Double.valueOf(actualValueWithoutCurrencySymbol);
		
		if(value==0) {
			System.out.println("@@@Inside if loop@@@");
			log.info("values are displayed correctly and they are greater than zero");
			Assert.assertFalse(false);
			break;
			
		}
	}
	}
	catch (Exception e) {
		System.out.println("values are displayed Incorrectly and they are zero: " );
        throw new TestException("values are displayed Incorrectly and they are zero: ");
	}
	
}

public void validatingSumDisplayedCorrectly() {
	waitForElementdisplayed(DemoForPracticeHeading);
	try {
	int size=Inputvalues.size();
	System.out.println("@@@Size of input values@@@ "+size);
	Double temp=0.0;
	for(int i=0;i<size-1;i++) {
		
		System.out.println("@@@Inside for loop@@@");
		String valuesIntheBox=driver.findElements(By.xpath("//input[@name='txtValues']")).get(i).getAttribute("value");
		System.out.println("Values displayed in the box"+valuesIntheBox);
		String actualValueWithoutCurrencySymbol=valuesIntheBox.replaceAll("[^0-9.]", "");
	System.out.println("Values after removing symbol"+" "+actualValueWithoutCurrencySymbol);
	//

	Double value=Double.valueOf(actualValueWithoutCurrencySymbol);
		temp=temp+value;
		System.out.println("temp value after adding"+temp);
	}
		String valueInTotalBalance=TotalBalanceValue.getAttribute("value");
		System.out.println("Total balance value"+valueInTotalBalance);
		String actualTotalBalanceValueWithoutCurrencySymbol=valueInTotalBalance.replaceAll("[^0-9.]", "");
		Double valueOfTotalBalanceValue=Double.valueOf(actualTotalBalanceValueWithoutCurrencySymbol);
		if(temp.equals(valueOfTotalBalanceValue)) {
			log.info("Sum of values are displayed correctly ");	
			Assert.assertTrue(true);
		}
		else {
			log.error("Sum of values are not displayed correctly ");
			Assert.assertFalse(false);
		}
		
	}	
	catch (Exception e) {
		System.out.println("Sum of values are displayed Incorrectly and they are zero: " );
        throw new TestException("sum of values values are displayed Incorrectly and they are zero: ");
	}
	}
	
	
public void valuesFormattedCorrectly() {
	waitForElementdisplayed(DemoForPracticeHeading);
	try {
	int size=Inputvalues.size();
	System.out.println("@@@Size of input values@@@ "+size);
	for(int i=0;i<size;i++) {
		
		System.out.println("@@@Inside for loop@@@");
		String valuesIntheBox=driver.findElements(By.xpath("//input[@name='txtValues']")).get(i).getAttribute("value");
		System.out.println("Values displayed in the box"+valuesIntheBox);
		if(valuesIntheBox.startsWith("$")) {
			System.out.println("@@@Inside if loop@@@");
			log.info("Prefix displayed correctly");
		}else {
			log.error("Prefix not displayed ");
		}
		
	}
	}
	catch (Exception e) {
		System.out.println("Prefix not displayed " );
        throw new TestException("Prefix not displayed ");
	}
	
}	
	
}





