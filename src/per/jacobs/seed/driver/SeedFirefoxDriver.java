package per.jacobs.seed.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedFirefoxDriver extends SeedDriver{
	public SeedFirefoxDriver(SeedDriverProperties prop) {
		super(prop);
		init();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	@Override
	public void mouseOver(String xpath) {
		Assert.fail("Mouseover is not supported for Firefox now");
	}
	@Override
	protected void init() {
		driver = new FirefoxDriver();
		logger.info("Using firefox...");
	}
	
}
