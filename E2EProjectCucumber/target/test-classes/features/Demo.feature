Feature: Home Page validation
Background:
Given user initialize the browser
And Navigate to the Site
Then User enters valid page title of the page

@validatingtherightCountOfValuesOnPage @Regression
Scenario: validating the right count of values displayed on Page
Then user validates whether right count of values appear on the page.




@validatingthevalueontheScreenGreaterThanZero @Regression
Scenario: validating the values are greater than zero
Then user validates the values are greater than zero




@validatingtheSumDisplayedCorrectly @Regression
Scenario: validating the sum of all values matching the Totalbalance
Then user validates the sum of all the values matches with Total balance




@validatingthevaluesAreFormattedCorrectly @Regression
Scenario: validating the format of values 
Then user validates values are formatted correctly


