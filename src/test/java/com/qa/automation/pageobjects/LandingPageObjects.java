package com.qa.automation.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LandingPageObjects {
	
	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);
    private WebDriver driver;
    private WebDriverWait wait;

    
    //Paratmerize the constructor
    public LandingPageObjects(WebDriver driver)
    {
    	this.driver = driver;
    	wait = new WebDriverWait(driver, 15);
    }
    
    //page locators
    private By landingPageLogo =By.xpath("//img[@alt='Website for automation practice']");
    private By signupLoginButton = By.xpath("//a[text()=' Signup / Login']");
    private By logoutButton = By.xpath("//a[text()=' Logout']");
    private By deleteAccButton = By.xpath("//a[text()=' Delete Account']");
    private By loggedInUserNameTxt = By.xpath("//a[text()= ' Logged in as ']/b");
    private By categorystring = By.xpath("//h2[text()='Category']");
    private By actbrandCategoryList = By.xpath("//div[@id='accordian']//div[@class='panel-heading']//a");
    
    //page methods
    public void validateAppLogo()
    {
    	 WebElement landingPageLogoEle = driver.findElement(landingPageLogo);
 	    Assert.assertEquals(true, landingPageLogoEle.isDisplayed());
    }
    
    public void ClickonSignUpBTN()
    {
    	 WebElement signupLoginButtonEle = driver.findElement(signupLoginButton);
 	    signupLoginButtonEle.click();
    }
    
    
    public void validateLogoutBTNFromHeader(String logoutButtontext)
    {
    	WebElement logoutButtonEle = driver.findElement(logoutButton);
		Assert.assertEquals(logoutButtontext, logoutButtonEle.getText().trim());
    }
    
    
    public void validateDelAccFromHeader(String deleteAccountButtontext)
    {
    	WebElement deleteAccButtonEle = driver.findElement(deleteAccButton);
		Assert.assertEquals(deleteAccountButtontext, deleteAccButtonEle.getText().trim());
    }
    
    
    public void validateUsernameLoggedInAs(String loggedInUserName)
    {
    	WebElement loggedInUserNameTxtEle = driver.findElement(loggedInUserNameTxt);
		Assert.assertEquals(loggedInUserName, loggedInUserNameTxtEle.getText().trim());
    }
    
    
    public void validateCategoryHeader(String categoryStringValue)
    {
    	 WebElement categorystringEle = driver.findElement(categorystring);
		   Assert.assertEquals(categoryStringValue,categorystringEle.getText());
    }
    
    
    public void validateCategoryList(List<String> brandcategoryNameList)
    {
    	 List<String> expectedbrandCategoryList = brandcategoryNameList;
 	    
 	    List<WebElement> actbrandCategoryListEle = driver.findElements(actbrandCategoryList);
 	    
 	    for(int i=0; i<expectedbrandCategoryList.size(); i++)
 	    	{
 	    	  //System.out.println(actbrandCategoryListEle.get(i).getText());
 	    	  Assert.assertEquals(expectedbrandCategoryList.get(i),actbrandCategoryListEle.get(i).getText());
 	    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
