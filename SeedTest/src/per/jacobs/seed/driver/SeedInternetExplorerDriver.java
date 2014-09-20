package per.jacobs.seed.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedInternetExplorerDriver extends SeedDriver{
	private String IE_DRIVER_PATH;
	public SeedInternetExplorerDriver(SeedDriverProperties prop) {
		super(prop);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	@Override
	public void mouseOver(String xpath) {
		Assert.fail("Mouseover is not supported for IE now");
	}
	@Override
	protected void init() {
		System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(capabilities);
		logger.info("Using InternetExplorer...");
	}

}
