package com.qa.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupLoginPageObjects {
	
	private static final Logger logger = LogManager.getLogger(SignupLoginPageObjects.class);
    private WebDriver driver;
    private WebDriverWait wait;
    
    //Paratmerize the constructor
    public SignupLoginPageObjects(WebDriver driver)
    {
    	this.driver = driver;
    	wait = new WebDriverWait(driver, 15);
    }
    
    //page locators
    private By loginSecHeader =By.xpath("//h2[text()='Login to your account']");
    private By loginEmailIdField = By.xpath("//input[@placeholder='Email Address' and @data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@placeholder='Password' and @data-qa='login-password']");
    private By loginButton = By.xpath("//button[text()='Login']");
    
//    page methods
    public void validateSignUpLoginPageTitle(String loginPageTitle)
    {
    	wait.until(ExpectedConditions.titleContains("Automation Exercise - Signup / Login"));
		Assert.assertEquals(loginPageTitle, driver.getTitle());
    }
    
    
    public void validateLoginKeywordInURL(String loginPageUrlKeyword)
    {
    	wait.until(ExpectedConditions.urlContains(loginPageUrlKeyword));
		Assert.assertEquals(true, driver.getCurrentUrl().contains(loginPageUrlKeyword));
    }
    
    
    public void validateLoginPageSectionHeader(String loginPageSecHeader)
    {
    	WebElement loginSecHeaderEle = driver.findElement(loginSecHeader);
		Assert.assertEquals(loginPageSecHeader, loginSecHeaderEle.getText());
    }
    
    
    public void validateSendTextToLoginField(String userRegEmailIDtxt)
    {
    	WebElement loginEmailIdFieldEle = driver.findElement(loginEmailIdField);
		loginEmailIdFieldEle.sendKeys(userRegEmailIDtxt);
    }
    
    
    public void validateSendTextToPasswordField(String userRegPasswordtxt)
    {
    	WebElement loginPasswordFieldEle = driver.findElement(loginPasswordField);
		loginPasswordFieldEle.sendKeys(userRegPasswordtxt);
    }
    
    
    public void ClickOnLoginBTn()
    {
    	WebElement loginButtonEle = driver.findElement(loginButton);
		loginButtonEle.click();
    }
    
    
    
    
    
    
    
    
    
    
    

}
