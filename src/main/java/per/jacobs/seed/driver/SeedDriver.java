package per.jacobs.seed.driver;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedDriver implements SeedDrivable{
	private RemoteWebDriver driver;
	private WebDriverBackedSelenium selenium;
	private JavascriptExecutor javaScriptExecutor;
	private int TIMEOUT;
	
	public JavascriptExecutor getJavaScriptExecutor() {
		return javaScriptExecutor;
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
		// TODO Auto-generated method stub
		
	}

	public void click(String xpath, int timeout) {
		// TODO Auto-generated method stub
		
	}

	public void type(String xpath, String text) {
		// TODO Auto-generated method stub
		
	}

	public void mouseOver(String xpath) {
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
		// TODO Auto-generated method stub
		
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

	public void expectTextExistOrNot(boolean expectExist, String text,
			int timeout) {
		// TODO Auto-generated method stub
		
	}

	public void expectElementExistOrNot(boolean expectExist, String xpath,
			int timeout) {
		// TODO Auto-generated method stub
		
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
			getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView();", we);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
