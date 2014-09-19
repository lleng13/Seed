package per.jacobs.seed.driver;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

abstract public class SeedDriver implements SeedDrivable{
	protected RemoteWebDriver driver;
	protected WebDriverBackedSelenium selenium;
	protected JavascriptExecutor javascriptExecutor;
	protected int TIMEOUT;
	protected String baseUrl;
	
	
	protected abstract void init();
	
	
	public JavascriptExecutor getJavascriptExecutor() {
		return javascriptExecutor;
	}

	public void open(String url) {
		selenium.open(url);
		selenium.waitForPageToLoad(String.valueOf(TIMEOUT));
	}

	public void open(String url, int timeout) {
		selenium.setTimeout(String.valueOf(timeout));
		selenium.open(url);
		selenium.waitForPageToLoad(String.valueOf(TIMEOUT)); 
	}

	public void quit() {
		driver.quit();
	}

	public void click(String xpath) {
		expectElementExistOrNot(true, xpath, TIMEOUT);
		try {
			clickTheClickable(xpath, System.currentTimeMillis(), 2500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click(String xpath, int timeout) {
		expectElementExistOrNot(true, xpath, timeout);
		try {
			clickTheClickable(xpath, System.currentTimeMillis(), 2500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clickTheClickable(String xpath, long startTime, int timeout) throws Exception {
		try {
			driver.findElementByXPath(xpath).click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeout) {
				throw new Exception(e);
			} else {
				Thread.sleep(500);
				clickTheClickable(xpath, startTime, timeout);
			}
		}
	}
	
	public void type(String xpath, String text) {
		expectElementExistOrNot(true, xpath, TIMEOUT);
		WebElement we = driver.findElement(By.xpath(xpath));
		try {
			we.clear();
		} catch (Exception e) {
		}
		try {
			we.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseOver(String xpath) {
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
		selenium.selectWindow(windowTitle);
	}

	public void enterFrame(String xpath) {
		driver.switchTo().frame(driver.findElementByXPath(xpath));
	}

	public void leaveFrame() {
		driver.switchTo().defaultContent();
	}

	public void refresh() {
		driver.navigate().refresh();
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
			}
		} else {
			if (isTextPresent(text, timeout)) {
			} else {
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
			}
			//to-do
		} else {
			if (isElementPresent(xpath, timeout)) {
				//to-do
			} else {
				//to-do
			}
		}
	}

	public boolean isTextPresent(String text, int time) {
		boolean isPresent = selenium.isTextPresent(text);
		if (isPresent) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementPresent(String xpath, int time) {
		boolean isPresent = selenium.isElementPresent(xpath) && driver.findElementByXPath(xpath).isDisplayed();
		if (isPresent) {
			return true;
		} else {
			return false;
		}
	}

	public void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
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
		selenium.close();
		selenium.selectWindow(null);
	}

	public void moveToView(String xpath) {
		isElementPresent(xpath, TIMEOUT);
		WebElement we = driver.findElement(By.xpath(xpath));
		try{
			getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();", we);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
