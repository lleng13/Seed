package per.jacobs.seed.driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedChromeDriver extends SeedDriver{
	private ChromeDriverService chromeServer;
	private String CHROME_DRIVER_PATH;
	public SeedChromeDriver() {
		init();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	@Override
	public void mouseOver(String xpath) {
		
	}
	@Override
	protected void init() {
		this.chromeServer = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File(CHROME_DRIVER_PATH))
				.usingAnyFreePort().build();
		try {
			chromeServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new RemoteWebDriver(chromeServer.getUrl(), capabilities);
	}
	
	
}
