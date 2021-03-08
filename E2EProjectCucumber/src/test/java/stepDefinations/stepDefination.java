package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.HomePage;
import resources.Base;

public class stepDefination extends Base {

	@Given("^user initialize the browser$")
	public void user_initialize_the_browser() throws Throwable {
		initializeDriver();
	}

	@Given("^Navigate to the Site$")
	public void navigate_to_the_Site() throws Throwable {
		navigateToURL();
	}

	@Then("^User enters valid page title of the page$")
	public void user_enters_valid_page_title_of_the_page() throws Throwable {
		getPageTitle();
	}

	@Then("^user validates whether right count of values appear on the page\\.$")
	public void user_validates_whether_right_count_of_values_appear_on_the_page() throws Throwable {
		HomePage home = new HomePage(driver);
		home.validateValuesOnPage();
	}

	@Then("^user validates the values are greater than zero$")
	public void user_validates_the_values_are_greater_than_zero() throws Throwable {
		HomePage home = new HomePage(driver);
		home.valuesGreaterThanZero();
	}

	@Then("^user validates the sum of all the values matches with Total balance$")
	public void user_validates_the_sum_of_all_the_values_matches_with_Total_balance() throws Throwable {
		HomePage home = new HomePage(driver);
		home.validatingSumDisplayedCorrectly();
	}

	@Then("^user validates values are formatted correctly$")
	public void user_validates_values_are_formatted_correctly() throws Throwable {
		HomePage home = new HomePage(driver);
		home.valuesFormattedCorrectly();
	}

}