package runnerCukes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.internal.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(features = "/Users/esaimaninavamani/Documents/Automation/RestAPIWithCucumber/src/test/resources/features/",glue = {"steps"},
dryRun = false,tags = "@addPlace or @deletePlace",snippets = CucumberOptions.SnippetType.CAMELCASE,plugin = {"pretty",
        "json:target/jsonReports/cucumper-report.json"})
public class APIRunnerCukes extends AbstractTestNGCucumberTests {

}

