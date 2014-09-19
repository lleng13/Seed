package per.jacobs.seed.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedFirefoxDriver extends SeedDriver{
	public SeedFirefoxDriver() {
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	@Override
	public void mouseOver(String xpath) {
		
	}
	@Override
	protected void init() {
		driver = new FirefoxDriver();
	}
	
}
