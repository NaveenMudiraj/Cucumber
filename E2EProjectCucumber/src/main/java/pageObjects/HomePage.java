package pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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

	@FindBy(xpath = "//label[contains(text(),'lbl_val')]")
	List<WebElement> list_LabelsBeforeValues;

	@FindBy(xpath = "//label[contains(text(),'txt_val')]")
	List<WebElement> list_LabelsAfterValues;

	@FindBy(xpath = "//label[contains(text(),'lbl_ttl_val')]")
	WebElement lbl_LabelsBeforeTotalBalance;

	@FindBy(xpath = "//label[contains(text(),'txt_ttl_val')]")
	WebElement lbl_LabelAfterTotalBalance;

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate the number of values displayed on page 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void validateValuesOnPage() {
		waitForPageLoad();
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Values.size();
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

	/*************************************************************************************
	 * Method name-validateValuesOnPage
	 * Method Description- This Method is used to validate values entered are greater than zero 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void valuesGreaterThanZero() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			log.info("@@@Size of input values@@@ " + size);
			for (int i = 0; i < size; i++) {
				String valuesIntheBox = list_Inputvalues.get(i).getAttribute("value");
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

	/*************************************************************************************
	 * Method name-validateValuesOnPage
	 * Method Description- This Method is used to validate sum is displayed correctly 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/
	public void validatingSumDisplayedCorrectly() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			log.info("@@@Size of input values@@@ " + size);
			Double temp = 0.0;
			for (int i = 0; i < size - 1; i++) {
				String valuesIntheBox = list_Inputvalues.get(i).getAttribute("value");
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

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate values are prefixed with $ symbol 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void valuesFormattedCorrectly() {
		waitForElementdisplayed(lbl_DemoForPracticeHeading);
		try {
			int size = list_Inputvalues.size();
			log.info("@@@Size of input values@@@ " + size);
			for (int i = 0; i < size; i++) {

				String valuesIntheBox = list_Inputvalues.get(i).getAttribute("value");
				System.out.println("Values displayed in the box" + valuesIntheBox);
				if (valuesIntheBox.startsWith("$")) {
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

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate the labels before values box 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void validateLabelBeforeInputBox() {
		try {
			waitForElementdisplayed(lbl_DemoForPracticeHeading);
			int sizeofLabels = list_LabelsBeforeValues.size();
			log.info("@@@Size of input values@@@ " + sizeofLabels);
			for (int i = 0; i < sizeofLabels; i++) {
				String value = list_LabelsBeforeValues.get(i).getText().trim();
				log.info("@@@ values from UI@@@ " + value);
				i++;
				String exvalue = "lbl_val_" + "" + i + "";
				if (value.equals(exvalue.trim())) {
					log.info("@@@values are matchings@@@ " + exvalue);
					i--;
				} else {
					log.info("@@@values are not matchings@@@ " + exvalue);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			log.info("values are not matchings");
			throw new TestException("labels not displayed correctly ");
		}

	}

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate the labels after values entered box 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/
	public void validateLabelAfterInputBox() {
		try {
			waitForElementdisplayed(lbl_DemoForPracticeHeading);
			int sizeofLabels = list_LabelsAfterValues.size();
			log.info("@@@Size of input values@@@ " + sizeofLabels);
			for (int i = 0; i < sizeofLabels; i++) {
				String value = list_LabelsAfterValues.get(i).getText().trim();
				log.info("@@@ values from UI@@@ " + value);
				i++;
				String exvalue = "txt_val_" + "" + i + "";
				if (value.equals(exvalue.trim())) {
					log.info("@@@values are matchings@@@ " + exvalue);
					i--;
				} else {
					log.info("@@@values are not matchings@@@ " + exvalue);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			log.info("values are not matchings");
			throw new TestException("labels not displayed correctly ");
		}

	}

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate the labels before Total balance 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/
	public void validateLblbefreTtlBal() {
		try {

			waitForElementdisplayed(lbl_DemoForPracticeHeading);
			String actualValue = lbl_LabelsBeforeTotalBalance.getText();
			System.out.println("Actual value" + actualValue);
			String expectedValue = "lbl_ttl_val";
			if (actualValue.equals(expectedValue)) {
				log.info("Both values are matching" + expectedValue);
				Assert.assertTrue(true);
			} else {
				log.info("Both values are not matching" + expectedValue);
				Assert.assertFalse(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("values are not matching");
		}
	}

	/*************************************************************************************
	 * Method name-validateValuesOnPage 
	 * Method Description- This Method is used to validate the labels before After balance 
	 * Author name:Naveen Mudiraj
	 *****************************************************************************************/

	public void validateLblAfterTtlBal() {
		try {

			waitForElementdisplayed(lbl_DemoForPracticeHeading);
			String actualValue = lbl_LabelAfterTotalBalance.getText();
			System.out.println("Actual value" + actualValue);
			String expectedValue = "txt_ttl_val";
			if (actualValue.equals(expectedValue)) {
				log.info("Both values are matching" + expectedValue);
				Assert.assertTrue(true);
			} else {
				log.info("Both values are not matching" + expectedValue);
				Assert.assertFalse(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException("values are not matching");
		}
	}
}
