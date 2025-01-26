package stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import junit.framework.Assert;

public class stepForm {

    WebDriver driver;
    WebDriverWait wait;

    private static final String Programs_section_xpath = "//*[@id=\"menu-item-264\"]/a/span[1]";
    private static final String firstname_xpath = "//*[@id=\"cog-input-auto-0\"]";
    private static final String lastname_xpath = "//*[@id=\"cog-input-auto-1\"]";
    private static final String phone_xpath = "//*[@id=\"cog-1\"]";
    private static final String email_xpath = "//*[@id=\"cog-2\"]";
    private static final String country_xpath = "//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[4]/fieldset[2]/div[1]/div[1]/div/label[1]/span[1]/span";
    private static final String submit_xpath = "button[type='submit']";
    private static final String success_msg_xpath = "//div[@class='cog-confirmation__message cog-content cog-html cog-input' and @role='alert']";
    private static final String missing_field_msg_xpath = "//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[2]/fieldset/div[3]";
    private static final String invalid_email_msg_xpath = "//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[3]/div[2]/div[3]";

    @Given("User is on the form submission page")
    @Step("Step : user_is_on_the_form_submission_page")
    public void user_is_on_the_form_submission_page() {
        try {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().window().maximize();
            driver.get("https://westfloridaahec.org/");
            
            WebElement programsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Programs_section_xpath)));
            programsLink.click();

            List<WebElement> dropdownOptions = driver.findElements(By.xpath("//*[@id='menu-item-264']/ul/li/a"));
            for (WebElement option : dropdownOptions) {
                if (option.getText().equalsIgnoreCase("Healthy Aging")) {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @When("User enters {string} and {string} and {string} and {string}")
    @Step("Step : user_enters_firstNmae_lastName_phone_and_email")
    public void user_enters_(String firstName, String lastName, String phone, String email) {
        try {
            WebElement firstname = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstname_xpath)));
            firstname.sendKeys(firstName);

            WebElement lastname = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastname_xpath)));
            lastname.sendKeys(lastName);

            WebElement phone_number = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(phone_xpath)));
            phone_number.sendKeys(phone);

            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(email_xpath)));
            emailField.sendKeys(email);

            WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(country_xpath)));
            country.click();

            WebElement submit_btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(submit_xpath)));
            submit_btn.click();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Then("User should be redirected to the secure area")
    @Step("Step : user_should_be_redirected_to_the_secure_area")
    public void user_should_be_redirected_to_the_secure_area() {
        try {
            WebElement msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(success_msg_xpath)));
            Assert.assertEquals("Thank you for filling out the form. Your response has been recorded.", msg.getText());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            driver.quit();
        }
    }

    @Then("An error message should be displayed for the missing fields")
    @Step("Step : an_error_message_should_be_displayed_for_the_missing_fields")
    public void an_error_message_should_be_displayed_for_the_missing_fields() {
        try {
            WebElement msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(missing_field_msg_xpath)));
            Assert.assertEquals("Last is required.", msg.getText());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            driver.quit();
        }
    }

    @Then("An error message should be displayed for the invalid email")
    @Step("Step : an_error_message_should_be_displayed_for_the_invalid_email")
    public void an_error_message_should_be_displayed_for_the_invalid_email() {
        try {
            WebElement msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(invalid_email_msg_xpath)));
            Assert.assertEquals("Email must be formatted as name@address.xyz.", msg.getText());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            driver.quit();
        }
    }
}
