// Copyright (c) 2017 Peel Technologies Inc. All Rights Reserved.
package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBaseSetup;
import pageobjects.LoginToAmazon;

public class LoginTests extends TestBaseSetup {
	LoginToAmazon loginToAmazon;

	SoftAssert s_assert = new SoftAssert();

	@BeforeClass
	public void setUp() throws IOException {
		loginToAmazon = new LoginToAmazon(driver, wait);

	}

	@Test(priority = 1)
	public void test01() throws InterruptedException, IOException {
		loginToAmazon.Login();
	}
	
	@Test(priority = 2)
	public void test02() throws InterruptedException, IOException {
		loginToAmazon.search();
	}	

}
