package per.jacobs.seed.driver;

import java.awt.AWTException;
import java.awt.Robot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;




import per.jacobs.seed.driver.tools.LogTools;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

abstract public class SeedDriver implements SeedDrivable{
	protected RemoteWebDriver driver;
	protected WebDriverBackedSelenium selenium;
	protected JavascriptExecutor javascriptExecutor;
	protected int TIMEOUT;
	protected int STEP_INTERVAL;
	protected String baseUrl;
	protected SeedDriverProperties prop;
	
	protected static Logger logger = Logger.getLogger(SeedDriver.class);
	
	protected abstract void init();
	public SeedDriver(SeedDriverProperties prop) {
		this.TIMEOUT = prop.getTimeout();
		this.STEP_INTERVAL = prop.getStepInterval();
		this.baseUrl = prop.getBaseUrl();
	}
	
	public JavascriptExecutor getJavascriptExecutor() {
		return javascriptExecutor;
	}

	public RemoteWebDriver getBrowserCore() {
		return driver;
	}


	public WebDriverBackedSelenium getBrowser() {
		return selenium;
	}


	public void open(String url) {
		pause(STEP_INTERVAL);
		try {
			selenium.open(url);
			selenium.waitForPageToLoad(String.valueOf(TIMEOUT));
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to open url " + url);
		}
		logger.info("Opened url " + url);
	}

	public void open(String url, int timeout) {
		pause(STEP_INTERVAL);
		try {
			selenium.setTimeout(String.valueOf(timeout));
			selenium.open(url);
			selenium.waitForPageToLoad(String.valueOf(TIMEOUT)); 
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to open url " + url);
		}
		logger.info("Opened url " + url);
	}

	public void quit() {
		pause(STEP_INTERVAL);
		driver.quit();
		logger.info("Quitted BrowserEmulator");
	}

	public void click(String xpath) {
		pause(STEP_INTERVAL);
		expectElementExistOrNot(true, xpath, TIMEOUT);
		try {
			clickTheClickable(xpath, System.currentTimeMillis(), 2500);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to click " + xpath);
		}
		logger.info("Clicked " + xpath);
	}

	public void click(String xpath, int timeout) {
		pause(STEP_INTERVAL);
		expectElementExistOrNot(true, xpath, timeout);
		try {
			clickTheClickable(xpath, System.currentTimeMillis(), 2500);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to click " + xpath);
		}
		logger.info("Clicked " + xpath);
	}
	
	private void clickTheClickable(String xpath, long startTime, int timeout) throws Exception {
		try {
			driver.findElementByXPath(xpath).click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeout) {
				logger.info("Element " + xpath + " is unclickable");
				throw new Exception(e);
			} else {
				pause(500);
				logger.info("Element " + xpath + " is unclickable, try again");
				clickTheClickable(xpath, startTime, timeout);
			}
		}
	}
	
	public void type(String xpath, String text) {
		pause(STEP_INTERVAL);
		expectElementExistOrNot(true, xpath, TIMEOUT);

		WebElement we = driver.findElement(By.xpath(xpath));
		try {
			we.clear();
		} catch (Exception e) {
			logger.warn("Failed to clear text at " + xpath);
		}
		try {
			
			we.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to type " + text + " at " + xpath);
		}

		logger.info("Type " + text + " at " + xpath);
	}

	public void mouseOver(String xpath) {
		pause(STEP_INTERVAL);
		expectElementExistOrNot(true, xpath, TIMEOUT);
		// First make mouse out of browser
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
		}
	}

	public void selectWindow(String windowTitle) {
		pause(STEP_INTERVAL);
		selenium.selectWindow(windowTitle);
		logger.info("Switched to window " + windowTitle);
	}

	public void enterFrame(String xpath) {
		pause(STEP_INTERVAL);
		driver.switchTo().frame(driver.findElementByXPath(xpath));
		logger.info("Entered iframe " + xpath);

	}

	public void leaveFrame() {
		pause(STEP_INTERVAL);
		driver.switchTo().defaultContent();
		logger.info("Left the iframe");
	}

	public void refresh() {
		pause(STEP_INTERVAL);
		driver.navigate().refresh();
		logger.info("Refreshed");
	}

	public void pressKeyboard(int keyCode) {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rb.keyPress(keyCode);	// press key
		rb.delay(100); 			// delay 100ms
		rb.keyRelease(keyCode);	// release key
		logger.info("Pressed key with code " + keyCode);
	}

	public void expectTextExistOrNot(boolean expectExist, final String text,
			int timeout) {
		if (expectExist) {
			try {
				new Wait() {
					public boolean until() {
						return isTextPresent(text, -1);
					}
				}.wait("Failed to find text " + text, timeout);
			} catch (Exception e) {
				e.printStackTrace();
				handleFailure("Failed to find text " + text);
			}
			logger.info("Found desired text " + text);
		} else {
			if (isTextPresent(text, timeout)) {
				handleFailure("Found undesired text " + text);
			} else {
				logger.info("Not found undesired text " + text);
			}
		}
	}

	public void expectElementExistOrNot(boolean expectExist, final String xpath,
			int timeout) {
		if (expectExist) {
			try {
				new Wait() {
					public boolean until() {
						return isElementPresent(xpath, -1);
					}
				}.wait("Failed to find element " + xpath, timeout);
			} catch (Exception e) {
				e.printStackTrace();
				handleFailure("Failed to find element " + xpath);
			}
			logger.info("Found desired element " + xpath);
		} else {
			if (isElementPresent(xpath, timeout)) {
				handleFailure("Found undesired element " + xpath);
			} else {
				logger.info("Not found undesired element " + xpath);
			}
		}
	}

	public boolean isTextPresent(String text, int time) {
		pause(time);
		boolean isPresent = selenium.isTextPresent(text);
		if (isPresent) {
			logger.info("Found text " + text);
			return true;
		} else {
			logger.info("Not found text " + text);
			return false;
		}
	}

	public boolean isElementPresent(String xpath, int time) {
		pause(time);
		boolean isPresent = selenium.isElementPresent(xpath) && driver.findElementByXPath(xpath).isDisplayed();
		if (isPresent) {
			logger.info("Found element " + xpath);
			return true;
		} else {
			logger.info("Not found element" + xpath);
			return false;
		}
	}

	public void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
			logger.info("Pause " + time + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void close() {
		logger.info("close the current window, named " + getTitle());
		selenium.close();
		selenium.selectWindow(null);
	}

	public void moveToView(String xpath) {
		pause(STEP_INTERVAL);
		isElementPresent(xpath, TIMEOUT);
		WebElement we = driver.findElement(By.xpath(xpath));
		try{
			getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();", we);
			logger.info("move to the view that xpath is " + xpath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("failed move to the view that xpath is " + xpath);
		}
	}
	protected void handleFailure(String notice) {
		String png = LogTools.screenShot(this);
		String log = notice + " >> capture screenshot at " + png;
		logger.error(log);
		Assert.fail(log);
	}
}
