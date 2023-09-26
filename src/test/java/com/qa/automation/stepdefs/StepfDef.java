package com.qa.automation.stepdefs;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Condition.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.core.WebDriverFactory;
import com.qa.automation.pageobjects.LandingPageObjects;
import com.qa.automation.pageobjects.ProductPageObjects;
import com.qa.automation.pageobjects.SignupLoginPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepfDef {
	
	private static final Logger logger = LogManager.getLogger(StepfDef.class);

	WebDriver driver;
	WebDriverWait wait;
	String baseUrl = "https://automationexercise.com/";
	int implictlyWaitTimeoutSec = 20;
	Scenario scn;
	
	LandingPageObjects landingPageObjects;
	SignupLoginPageObjects signupLoginPageObjects;
	ProductPageObjects productPageObjects;

	@Before
	public void setUp(Scenario scn) throws Exception
	{
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		wait = new WebDriverWait(driver, implictlyWaitTimeoutSec);
		landingPageObjects = new LandingPageObjects(driver);
		signupLoginPageObjects = new SignupLoginPageObjects(driver);
		productPageObjects = new ProductPageObjects(driver);
	}

	@After(order =1)
	public void cleanUp()
	{
		WebDriverFactory.quitDriver();
		scn.log("Browser got closed");
		logger.info("Browser got closed");
	}
	
	@After(order =2)// this will execute first,higher number
	public void takeScreenShot(Scenario s)
	{
		if (s.isFailed()) 
		{
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Failed step name: " + s.getName());
		}else
		{
			scn.log("test case is passed, no screenshot captured");
		}
	}

	@Given("user navigate to the home application url")
	public void user_navigate_to_the_home_application_url() {
	    // Write code here that turns the phrase above into concrete actions
	    WebDriverFactory.navigateToTheUrl(baseUrl);
	}

	@When("application logo is displayed")
	public void application_logo_is_displayed() {
//	    WebElement landingPageLogoEle = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
//	    Assert.assertEquals(true, landingPageLogoEle.isDisplayed());
		landingPageObjects.validateAppLogo();
	}

	@Then("title of the landing page is {string}")
	public void title_of_the_landing_page_is(String landingPageTitle) {
	   Assert.assertEquals(landingPageTitle, driver.getTitle());
	}

	@Given("user clicks on Signup\\/login button form top header section")
	public void user_clicks_on_signup_login_button_form_top_header_section() {
	    // Write code here that turns the phrase above into concrete actions
//	    WebElement signupLoginButtonEle = driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
//	    signupLoginButtonEle.click();
		landingPageObjects.ClickonSignUpBTN();
	}

	@When("user redirected to login page with title as {string}")
	public void user_redirected_to_login_page_with_title_as(String loginPageTitle) {
	    // Write code here that turns the phrase above into concrete actions
//		wait.until(ExpectedConditions.titleContains("Automation Exercise - Signup / Login"));
//		Assert.assertEquals(loginPageTitle, driver.getTitle());
		signupLoginPageObjects.validateSignUpLoginPageTitle(loginPageTitle);
	}
	
	@When("url for the login page contains {string} as keyword")
	public void url_for_the_login_page_contains_as_keyword(String loginPageUrlKeyword) {
	    // Write code here that turns the phrase above into concrete actions
//		wait.until(ExpectedConditions.urlContains(loginPageUrlKeyword));
//		Assert.assertEquals(true, driver.getCurrentUrl().contains(loginPageUrlKeyword));
		signupLoginPageObjects.validateLoginKeywordInURL(loginPageUrlKeyword);
	}
	
	@When("user able to see {string} section on login page")
	public void user_able_to_see_section_on_login_page(String loginPageSecHeader) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement loginSecHeaderEle = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
//		Assert.assertEquals(loginPageSecHeader, loginSecHeaderEle.getText());
		signupLoginPageObjects.validateLoginPageSectionHeader(loginPageSecHeader);

	}
	
	@When("user enters valid registered email id as {string}")
	public void user_enters_valid_registered_email_id_as(String userRegEmailIDtxt) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement loginEmailIdFieldEle = driver.findElement(By.xpath("//input[@placeholder='Email Address' and @data-qa='login-email']"));
//		loginEmailIdFieldEle.sendKeys(userRegEmailIDtxt);
		signupLoginPageObjects.validateSendTextToLoginField(userRegEmailIDtxt);
	}
	
	@When("user enters valid password as {string}")
	public void user_enters_valid_password_as(String userRegPasswordtxt) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement loginPasswordFieldEle = driver.findElement(By.xpath("//input[@placeholder='Password' and @data-qa='login-password']"));
//		loginPasswordFieldEle.sendKeys(userRegPasswordtxt);
		signupLoginPageObjects.validateSendTextToPasswordField(userRegPasswordtxt);
	}

	@When("click on login button")
	public void click_on_login_button() {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement loginButtonEle = driver.findElement(By.xpath("//button[text()='Login']"));
//		loginButtonEle.click();
		signupLoginPageObjects.ClickOnLoginBTn();
	}

	@Then("after login user able to see {string} button at top header of application")
	public void after_login_user_able_to_see_button_at_top_header_of_application(String logoutButtontext) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement logoutButtonEle = driver.findElement(By.xpath("//a[text()=' Logout']"));
//		Assert.assertEquals(logoutButtontext, logoutButtonEle.getText().trim());
		landingPageObjects.validateLogoutBTNFromHeader(logoutButtontext);
	}
	
	@Then("user is able to see {string} button at top header section of application")
	public void user_is_able_to_see_button_at_top_header_section_of_application(String deleteAccountButtontext) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement deleteAccButtonEle = driver.findElement(By.xpath("//a[text()=' Delete Account']"));
//		Assert.assertEquals(deleteAccountButtontext, deleteAccButtonEle.getText().trim());
		landingPageObjects.validateDelAccFromHeader(deleteAccountButtontext);
	}

	@Then("with {string} as user name just after Logged in as button")
	public void with_as_user_name_just_after_logged_in_as_button(String loggedInUserName) {
	    // Write code here that turns the phrase above into concrete actions
//		WebElement loggedInUserNameTxtEle = driver.findElement(By.xpath("//a[text()= ' Logged in as ']/b"));
//		Assert.assertEquals(loggedInUserName, loggedInUserNameTxtEle.getText().trim());
		landingPageObjects.validateUsernameLoggedInAs(loggedInUserName);
	}
	
	
	@When("user header over to products page")
	public void user_header_over_to_products_page() {
//		driver.navigate().to(baseUrl + "/products");
		String prodPageUrl = productPageObjects.productPageURL();
		driver.navigate().to(baseUrl + prodPageUrl);
	}

	@When("user redirected to products page with title as {string}")
	public void user_redirected_to_products_page_with_title_as(String pageTitle) {
//		wait.until(ExpectedConditions.titleContains(pageTitle));
//		Assert.assertEquals(pageTitle, driver.getTitle());
		productPageObjects.validateprodPageTitle(pageTitle);  
	}
	
	@When("url for the login page contains the {string} as keyword")
	public void url_for_the_login_page_contains_the_as_keyword(String KeywordInUrl) {
//		wait.until(ExpectedConditions.urlContains(KeywordInUrl));
//		Assert.assertEquals(true, driver.getCurrentUrl().contains(KeywordInUrl));
		productPageObjects.validateprodpageUrlKeyword(KeywordInUrl);
	}
	
	@When("user search for a product {string}")
	public void user_search_for_a_product(String productname) {
//	   WebElement productSearchBoxEle = driver.findElement(By.xpath("//input[@id='search_product']"));
//	   productSearchBoxEle.sendKeys(productname);	
		productPageObjects.SearchForProd(productname);
	}
	
	@When("click on search button")
	public void click_on_search_button() {
//		WebElement productSearchBTNEle = driver.findElement(By.xpath("//button[@id='submit_search']"));
//		productSearchBTNEle.click();  
		productPageObjects.ClickOnProdSearchBtn();
	}
	
	@Then("from the product list the first product contain the {string} as keyword")
	public void from_the_product_list_the_first_product_contain_the_as_keyword(String productnameKeyword) {
//	   List<WebElement> searchedproductListEle = driver.findElements(By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p"));
//	   Assert.assertEquals(true, searchedproductListEle.get(0).getText().contains(productnameKeyword));
		productPageObjects.ValidateProdListFirstProdName(productnameKeyword);
	}
	
	
	
	@When("user is able to see {string} header")
	public void user_is_able_to_see_header(String categoryStringValue) {
	    // Write code here that turns the phrase above into concrete actions
//		 WebElement categorystringEle = driver.findElement(By.xpath("//h2[text()='Category']"));
//		   Assert.assertEquals(categoryStringValue,categorystringEle.getText());
		landingPageObjects.validateCategoryHeader(categoryStringValue);
	}

	@Then("under Category below list is displayed")
	public void under_category_below_list_is_displayed(List<String> brandcategoryNameList) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
//	    List<String> expectedbrandCategoryList = brandcategoryNameList;
//	    
//	    List<WebElement> actbrandCategoryListEle = driver.findElements(By.xpath("//div[@id='accordian']//div[@class='panel-heading']//a"));
//	    
//	    for(int i=0; i<expectedbrandCategoryList.size(); i++)
//	    	{
//	    	  //System.out.println(actbrandCategoryListEle.get(i).getText());
//	    	  Assert.assertEquals(expectedbrandCategoryList.get(i),actbrandCategoryListEle.get(i).getText());
//	    	}
		landingPageObjects.validateCategoryList(brandcategoryNameList);
		
	}
	
	
	
	
	
	


}
