package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features/userAccManagement.feature",
    glue = "stepDefinition",
    plugin = {
        "pretty",
        "html:target/user.html", // HTML Report
    },
    monochrome = true // Clean console output
)
public class userAccManagementRUN {

}
