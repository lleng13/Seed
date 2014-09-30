package com.netease.seed.driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedChromeDriver extends SeedDriver{
	private ChromeDriverService chromeServer;
	private String CHROME_DRIVER_PATH;
	public SeedChromeDriver(SeedDriverProperties prop) {
		super(prop);
		CHROME_DRIVER_PATH = prop.getChromePath();
		init();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	@Override
	public void mouseOver(String xpath) {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rb.mouseMove(0, 0);
		
		// Then hover
		WebElement we = driver.findElement(By.xpath(xpath));
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(we).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to mouseover " + xpath);
		}
		logger.info("Mouseover " + xpath);
	}
	@Override
	public void quit() {
		pause(STEP_INTERVAL);
		driver.quit();
		chromeServer.stop();
		logger.info("Stopped ChomeDriverService");
		logger.info("Quitted BrowserEmulator");
	}
	@Override
	protected void init() {
		this.chromeServer = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File(CHROME_DRIVER_PATH))
				.usingAnyFreePort().build();
		try {
			chromeServer.start();
			logger.info("Started ChomeDriverService");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new RemoteWebDriver(chromeServer.getUrl(), capabilities);
		logger.info("Using chrome...");
	}
	
	
}
