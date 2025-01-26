package stepDefinition;

import java.time.Duration;

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

public class stepUserAccManagement {

	WebDriver driver;
	WebDriverWait wait;
	static String name_ver;
	static String password1;

	private static final String Myaccount_section_xpath = "//*[@id=\"menu-main-menu\"]/li[8]/a/span[1]";

	@Given("User is on the registration page")
	@Step("Step : user_is_on_the_registration_page")
	public void user_is_on_the_registration_page() {
		try {
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://westfloridaahec.org/");
			WebElement accLink = driver.findElement(By.xpath(Myaccount_section_xpath));
			accLink.click();
			System.out.println("Navigated to registration page.");
		} catch (Exception e) {
			System.err.println("Error navigating to registration page: " + e.getMessage());
			throw e;
		}
	}

	@When("User enters {string}, {string}, and {string}")
	@Step("Step : user_enters_name_email_password")
	public void user_enters_and(String email, String name, String password) {
		try {
			WebElement username_register = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("reg_username")));
			username_register.sendKeys(name);
			WebElement email_register = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
			email_register.sendKeys(email);
			WebElement password_register = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"reg_password\"]")));
			password_register.sendKeys(password);
			name_ver = name;
			password1 = password;
			System.out.println("Entered registration details.");
		} catch (Exception e) {
			System.err.println("Error entering registration details: " + e.getMessage());
			throw e;
		}
	}

	@When("User clicks the Register button")
	@Step("Step : user_clicks_the_Register_button")
	public void user_clicks_the_Register_button() {
		try {
			WebElement register = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customer_login\"]/div[2]/form/p[4]/button")));
			register.click();
			System.out.println("Clicked Register button.");
		} catch (Exception e) {
			System.err.println("Error clicking Register button: " + e.getMessage());
			throw e;
		}
	}

	@Then("A confirmation message should be displayed and logged in")
	@Step("Step : a_confirmation_message_should_be_displayed_and_logged_in")
	public void a_confirmation_message_should_be_displayed_and_logged_in() {
		try {
			WebElement verify_register = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")));
			Assert.assertEquals("Hello " + name_ver, verify_register.getText());
			System.out.println("Registration confirmed.");
		} catch (AssertionError e) {
			System.err.println("Validation failed: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Error verifying registration: " + e.getMessage());
			throw e;
		}
	}

	@Then("User should logout")
	@Step("Step : user_should_logout")
	public void user_should_logout() {
		try {
			WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")));
			logout.click();
			System.out.println("User logged out successfully.");
		} catch (Exception e) {
			System.err.println("Error during logout: " + e.getMessage());
			throw e;
		} finally {
			driver.quit();
		}
	}


	@Given("User is on the login page")
	@Step("Step : user_is_on_the_login_page")
	public void user_is_on_the_login_page() {
		try {
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://westfloridaahec.org/");
			WebElement accLink = driver.findElement(By.xpath(Myaccount_section_xpath));
			accLink.click();
			System.out.println("Navigated to login page.");
		} catch (Exception e) {
			System.err.println("Error navigating to login page: " + e.getMessage());
			throw e;
		}
	}

	@When("User enters {string} and {string}")
	@Step("Step : user_enters_and_email_and_password")
	public void user_enters_and(String email, String password) {
		try {
			WebElement username_login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]")));
			username_login.sendKeys(email);
			WebElement password_login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]")));
			password_login.sendKeys(password);
			System.out.println("Entered login details.");
		} catch (Exception e) {
			System.err.println("Error entering login details: " + e.getMessage());
			throw e;
		}
	}

	@When("User clicks the Login button")
	@Step("Step : user_clicks_the_login_button")
	public void user_clicks_the_login_button() {
		try {
			WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/button")));
			login.click();
			System.out.println("Clicked Login button.");
		} catch (Exception e) {
			System.err.println("Error clicking Login button: " + e.getMessage());
			throw e;
		}
	}

	@Then("User should be redirected to their profile page")
	@Step("Step : user_should_be_redirected_to_their_profile_page")
	public void user_should_be_redirected_to_their_profile_page() {
		try {
			WebElement verify_login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")));
			Assert.assertEquals("Hello John Doe", verify_login.getText());
			System.out.println("Login confirmed.");
		} catch (AssertionError e) {
			System.err.println("Login validation failed: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Error verifying login: " + e.getMessage());
			throw e;
		}
	}

	@Then("An error message should be displayed")
	@Step("Step : an_error_message_should_be_displayed")
	public void an_error_message_should_be_displayed() {
		try {
			WebElement error_message = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div")));
			Assert.assertTrue(error_message.isDisplayed());
			System.out.println("Error message displayed for invalid login.");
		} catch (Exception e) {
			System.err.println("Error handling invalid login: " + e.getMessage());
			throw e;
		}
		finally {
			driver.quit();
		}

	}

	@Then("User should be redirected to their profile page and update password and logout")
	@Step("Step : user_should_be_redirected_to_their_profile_page_and_update_password_and_logout")
	public void user_should_be_redirected_to_their_profile_page_and_update_password_and_logout() {
		try {
			WebElement accountDetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[6]/a")));
			accountDetails.click();

			WebElement currentPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password_current")));
			currentPassword.sendKeys(password1);

			WebElement newPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password_1")));
			newPassword.sendKeys("NewPassword123!");

			WebElement confirmPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password_2")));
			confirmPassword.sendKeys("NewPassword123!");

			WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/div[2]/form/p[5]/button")));
			if(saveButton.isEnabled())
			{
				saveButton.click();
				System.out.println("Password updated and user logged out.");
				WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")));
				logout.click();
				
			}
			else
			{
				System.out.println("Save Changes button is not working");
				driver.navigate().back();
				WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")));
				logout.click();
				System.out.println("Password not updated and user logged out.");
			}
		} catch (Exception e) {
			System.err.println("Error updating password: " + e.getMessage());
			throw e;
		} finally {
			driver.quit();
		}
	}
}

