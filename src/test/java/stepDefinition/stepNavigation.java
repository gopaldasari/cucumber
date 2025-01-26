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

public class stepNavigation {

    WebDriver driver;
    WebDriverWait wait;
    private static final String Programs_section_xpath = "//*[@id=\"menu-item-264\"]/a/span[1]";
    private static final String HOME_xpath = "//*[@id=\"menu-item-207\"]/a/span";

    @Given("User is on the Home page")
    public void user_is_on_the_home_page() {
        try {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://westfloridaahec.org/");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @When("User clicked on health program and navigated different health programs")
    public void user_clicked_on_health_program_and_navigated_different_health_programs() {
        try {
            WebElement programsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Programs_section_xpath)));
            programsLink.click();
            List<WebElement> dropdownOptions = driver.findElements(By.xpath("//*[@id='menu-item-264']/ul/li/a"));
            
            for (WebElement options : dropdownOptions) {
                String optionText = options.getText();
                System.out.println("Navigating to: " + optionText + " health program");
                wait.until(ExpectedConditions.elementToBeClickable(options));
                options.click();

                try {
                    WebElement txt1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/section/div/div/div/div/h1")));
                    System.out.println("Extracting Header message Of selected program page: " + txt1.getText());
                } catch (Exception e) {
                    WebElement txt1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-1479\"]/div/div[1]/div/div/div/div/h2")));
                    System.out.println("Extracting Header message Of selected program page: " + txt1.getText());
                }

                driver.navigate().back();
                programsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Programs_section_xpath)));
                programsLink.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @When("User clicked on facebook icon on home page it should redirected to facebook")
    public void user_clicked_on_facebook_icon_on_home_page_it_should_redirected_to_facebook() {
        try {
            WebElement facebook = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-10\"]/div/div[3]/div/div/div/div[1]/div[1]/div/div/a/h2")));
            facebook.click();
            WebElement msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/section/div/div/div/div/h1")));
            String actualMessage = msg.getText();
            if ("ERROR 404 PAGE".equals(actualMessage)) {
                junit.framework.Assert.fail("Test failed: Received 'Error 404 Page.' after clicking the Facebook icon.");
            } else {
                System.out.println("Facebook redirection successful: " + actualMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            junit.framework.Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }


    @Then("User able to navigate successfully")
    public void user_able_to_navigate_successfully() {
        try {
            WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HOME_xpath)));
            home.click();
            String act_url = "https://westfloridaahec.org/";
            String expect_url = driver.getCurrentUrl();
            org.junit.Assert.assertEquals(expect_url, act_url);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
                driver.quit();
            }
        }
    }

