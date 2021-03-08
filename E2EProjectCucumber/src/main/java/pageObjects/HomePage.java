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

import resources.Base;

public class HomePage extends Base {

	private static Logger log = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		HomePage.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h1[contains(text(),' Problem#1 Form')]")
	WebElement lbl_Header;

	@FindBy(xpath = "//a[contains(text(),' exercise1 ')]")
	WebElement lbl_LinkExcercise;

	@FindBy(xpath = "//input[@name='txtValue']")
	List<WebElement> list_Values;

	@FindBy(xpath = "//input[@name='txtValues']")
	List<WebElement> list_Inputvalues;

	@FindBy(xpath = "//h1[contains(text(),'DemoForPractice')]")
	WebElement lbl_DemoForPracticeHeading;

	@FindBy(xpath = "(//input[@id='txtTotalBalance'])[2]")
	WebElement txt_TotalBalanceValue;

	public void validateValuesOnPage() {
		waitForPageLoad();
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = driver.findElements(By.xpath("//input[@name='txtValue']")).size();
			log.info("@@Values@@@" + size);
			if (size == 6) {
				log.info("Values displayed on the screen are correct ");

			} else {
				log.error("values are not displayed correctly on the page");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void valuesGreaterThanZero() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			log.info("@@@Size of input values@@@ " + size);
			for (int i = 0; i < size; i++) {
				String valuesIntheBox = driver.findElements(By.xpath("//input[@name='txtValues']")).get(i)
						.getAttribute("value");
				log.info("Values displayed in the box" + valuesIntheBox);
				String actualValueWithoutCurrencySymbol = valuesIntheBox.replaceAll("[^0-9.]", "");
				log.info("Values after removing symbol" + " " + actualValueWithoutCurrencySymbol);
				Double value = Double.valueOf(actualValueWithoutCurrencySymbol);
				if (value == 0) {

					log.info("values are displayed correctly and they are greater than zero");

					break;

				}
			}
		} catch (Exception e) {
			log.info("values are displayed Incorrectly and they are zero: ");
			throw new TestException("values are displayed Incorrectly and they are zero: ");
		}

	}

	public void validatingSumDisplayedCorrectly() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			log.info("@@@Size of input values@@@ " + size);
			Double temp = 0.0;
			for (int i = 0; i < size - 1; i++) {
				String valuesIntheBox = driver.findElements(By.xpath("//input[@name='txtValues']")).get(i)
						.getAttribute("value");
				log.info("Values displayed in the box" + valuesIntheBox);
				String actualValueWithoutCurrencySymbol = valuesIntheBox.replaceAll("[^0-9.]", "");
				log.info("Values after removing symbol" + " " + actualValueWithoutCurrencySymbol);
				//

				Double value = Double.valueOf(actualValueWithoutCurrencySymbol);
				temp = temp + value;
				log.info("temp value after adding" + temp);
			}
			String valueInTotalBalance = txt_TotalBalanceValue.getAttribute("value");
			log.info("Total balance value" + valueInTotalBalance);
			String actualTotalBalanceValueWithoutCurrencySymbol = valueInTotalBalance.replaceAll("[^0-9.]", "");
			Double valueOfTotalBalanceValue = Double.valueOf(actualTotalBalanceValueWithoutCurrencySymbol);
			if (temp.equals(valueOfTotalBalanceValue)) {
				log.info("Sum of values are displayed correctly ");

			} else {
				log.error("Sum of values are not displayed correctly ");

			}

		} catch (Exception e) {
			log.error("Sum of values are displayed Incorrectly and they are zero: ");
			throw new TestException("sum of values values are displayed Incorrectly and they are zero: ");
		}
	}

	public void valuesFormattedCorrectly() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			System.out.println("@@@Size of input values@@@ " + size);
			for (int i = 0; i < size; i++) {

				System.out.println("@@@Inside for loop@@@");
				String valuesIntheBox = driver.findElements(By.xpath("//input[@name='txtValues']")).get(i)
						.getAttribute("value");
				System.out.println("Values displayed in the box" + valuesIntheBox);
				if (valuesIntheBox.startsWith("$")) {
					System.out.println("@@@Inside if loop@@@");
					log.info("Prefix displayed correctly");
				} else {
					log.error("Prefix not displayed ");
				}

			}
		} catch (Exception e) {
			System.out.println("Prefix not displayed ");
			throw new TestException("Prefix not displayed ");
		}

	}

}
