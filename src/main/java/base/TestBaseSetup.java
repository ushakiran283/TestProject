// Copyright (c) 2017 Peel Technologies Inc. All Rights Reserved.
package base;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import config.AvailablePorts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class TestBaseSetup {
	public AndroidDriver driver;
	public WebDriverWait wait;
	protected AppiumDriverLocalService service;
	
	AvailablePorts ap = new AvailablePorts();

	
	@BeforeSuite
	public void startAppium() throws Exception {
		initializeTestBaseSetup();
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
	}

	
	public void initializeTestBaseSetup() throws NumberFormatException, Exception {
		int port = Integer.parseInt(ap.getPort());
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(port)
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, ap.getPort())
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "error"));
		service.start();
		System.out.println("Appium Url:" + service.getUrl().toString());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "MI A2");
		// capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability(CapabilityType.VERSION, 10);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		capabilities.setCapability("autoAcceptAlerts", "true");
		capabilities.setCapability("automationName", "UiAutomator2");
		driver = new AndroidDriver(new URL(service.getUrl().toString()), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterSuite
	public void stopAppium() throws InterruptedException, IOException {

		service.stop();
		System.err.println("Closing Appium");

	}

	
}
