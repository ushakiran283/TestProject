// Copyright (c) 2017 Peel Technologies Inc. All Rights Reserved.
package pageobjects;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBaseSetup;
import config.ExtrenalSource;
import io.appium.java_client.android.AndroidDriver;

public class LoginToAmazon extends TestBaseSetup {

	public By signInButton = By.xpath("//android.widget.Button[@text='Already a customer? Sign in']");
	public By clickEmail = By.xpath("//android.view.View[@text='ap_ra_email_or_phone']");
	public By enterEmail= By.xpath("//android.widget.TextView[@text='durgaprasad.battini@gmail.com']");
	public By continueButton = By.xpath("//android.widget.Button[@text='Continue']");
	public By enterPwd = By.xpath("//android.widget.EditText[@text='Amazon password']");
	public By loginButton = By.xpath("//android.widget.Button[@text='Login']");
	public By clickSearch = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
	
	public LoginToAmazon(AndroidDriver<?> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void Login() throws IOException {
		driver.findElement(signInButton).click();
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP 
		}
		driver.context((String) contextNames.toArray()[0]); 
		WebElement mobile = driver.findElement(clickEmail);
		mobile.click();
		WebElement email = driver.findElement(enterEmail);
		email.click();
		driver.findElement(continueButton).click();
		WebElement pwd = driver.findElement(enterPwd);
		pwd.click();
		ExtrenalSource data = new ExtrenalSource();
		pwd.sendKeys(data.getUsernamePwd());
		driver.findElement(loginButton).click();
		Assert.assertTrue(isElementAvailable(driver, clickSearch), "Search bar is not displayed");
	}

	public void search() {
		//Search 65-inch TV(Not the first/last)
		WebElement search = driver.findElement(clickSearch);
		search.click();
		WebElement search1 = driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_bar_with_buttons_frame"));
		search1.sendKeys("65 inch tv 4k");
	}
	
	public boolean isElementAvailable(AndroidDriver driver, By by) {
		boolean isElementAvailable = false;
		try {
			isElementAvailable = driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
		} catch (AssertionError e) {
		}
		return isElementAvailable;
	}

}
