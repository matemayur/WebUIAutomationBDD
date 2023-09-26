package com.qa.automation.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPageObjects {
	
	private static final Logger logger = LogManager.getLogger(ProductPageObjects.class);
    private WebDriver driver;
    private WebDriverWait wait;

    
    //Paratmerize the constructor
    public ProductPageObjects(WebDriver driver)
    {
    	this.driver = driver;
    	wait = new WebDriverWait(driver, 15);
    }
    
    //page locators
    private By productSearchBox =By.xpath("//input[@id='search_product']");
    private By productSearchBTN = By.xpath("//button[@id='submit_search']");
    private By searchedproductList = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p");
    
    //page methods
    public String productPageURL()
    {
    	String prodPageUrl = "/products";
    	return prodPageUrl;
    }
    
    
    public void validateprodPageTitle(String pageTitle)
    {
    	wait.until(ExpectedConditions.titleContains(pageTitle));
		Assert.assertEquals(pageTitle, driver.getTitle());
    }
    
    public void validateprodpageUrlKeyword(String KeywordInUrl)
    {
    	wait.until(ExpectedConditions.urlContains(KeywordInUrl));
		Assert.assertEquals(true, driver.getCurrentUrl().contains(KeywordInUrl));
    }
    
    public void SearchForProd(String productname)
    {
       WebElement productSearchBoxEle = driver.findElement(productSearchBox);
  	   productSearchBoxEle.sendKeys(productname);	
    }
    
    public void ClickOnProdSearchBtn()
    {
    	WebElement productSearchBTNEle = driver.findElement(productSearchBTN);
		productSearchBTNEle.click();
    }
    
    
    public void ValidateProdListFirstProdName(String productnameKeyword)
    {
    	 List<WebElement> searchedproductListEle = driver.findElements(searchedproductList);
  	   Assert.assertEquals(true, searchedproductListEle.get(0).getText().contains(productnameKeyword));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
