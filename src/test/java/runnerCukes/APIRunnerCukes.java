package runnerCukes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "/Users/esaimaninavamani/Documents/Automation/RestAPIWithCucumber/src/test/resources/features",glue = {"steps"},
dryRun = false,snippets = CucumberOptions.SnippetType.CAMELCASE)
public class APIRunnerCukes extends AbstractTestNGCucumberTests {
}

