package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/java/features/form.feature",
glue = "stepDefinition",
plugin = {"pretty", "html:target/form.html"},
monochrome = true
)

public class formRUN {

}
