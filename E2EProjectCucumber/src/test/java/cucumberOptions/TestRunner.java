package cucumberOptions;



import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(  
	    features = "src/test/java/features",plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber-reports/Cucumber.json"},
	    glue={"stepDefinations"},monochrome = true,
	    tags= {"@Regression"}) 
public class TestRunner extends AbstractTestNGCucumberTests  {
	
}
