/*
 * Copyright (c) 2014 all contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package per.jacobs.seed;




import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;





import per.jacobs.seed.driver.SeedDriver;
import per.jacobs.seed.driver.SeedDriverProperties;
import per.jacobs.seed.driverFactory.SeedDriverFactory;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * Fruit as a SeedDriver Proxy
 */
public class Fruit {
	private SeedDriverFactory factory;
	private SeedDriver driver;
	private final int STEP_INTERVAL = Integer.parseInt(FruitSettings.STEP_INTERVAL);
	private final int TIMEOUT = Integer.parseInt(FruitSettings.TIMEOUT);
	private final String CHROME_DRIVER_PATH = FruitSettings.CHROME_DRIVER_PATH;
	private final String IE_DRIVER_PATH = FruitSettings.IE_DRIVER_PATH;
	private static Logger logger = Logger.getLogger(Fruit	.class.getName());
	private final String DEFAULT_BROWSER = FruitSettings.DEFAULT_BROWSER;
	public Fruit(String browserType) {
		init(browserType);
	}
	
	public Fruit() {
		init(DEFAULT_BROWSER);
	}
	
	private void init(String type) {
		factory = new SeedDriverFactory();
		SeedDriverProperties prop = factory.createProp();
		prop.setStepInterval(STEP_INTERVAL);
		prop.setTimeout(TIMEOUT);
		prop.setChromePath(CHROME_DRIVER_PATH);
		prop.setIEPath(IE_DRIVER_PATH);
		factory.setProp(prop);
		driver = factory.getDriver(type);
		logger.info("Starting Fruit");
	}
	
	public RemoteWebDriver getBrowserCore() {
		return driver.getBrowserCore();
	}


	public WebDriverBackedSelenium getBrowser() {
		return driver.getBrowser();
	}
	
	
	public JavascriptExecutor getJavaScriptExecutor() {
		return driver.getJavascriptExecutor();
	}


	public void open(String url) {
		driver.open(url);
	}

	public void open(String url,int timeout) {
		driver.open(url, timeout);
	}

	public void quit() {
		driver.quit();
	}

	public void click(String xpath) {
		driver.click(xpath);
	}

	public void click(String xpath, int timeout) {
		driver.click(xpath, timeout);
	}


	public void type(String xpath, String text) {
		driver.type(xpath, text);
	}

	public void mouseOver(String xpath) {
		driver.mouseOver(xpath);
	}

	public void selectWindow(String windowTitle) {
		driver.selectWindow(windowTitle);
	}

	public void enterFrame(String xpath) {
		driver.enterFrame(xpath);
	}

	public void leaveFrame() {
		driver.leaveFrame();
	}
	
	public void refresh() {
		driver.refresh();
	}

	public void pressKeyboard(int keyCode) {
		driver.pressKeyboard(keyCode);
	}


	public void expectTextExistOrNot(boolean expectExist, final String text, int timeout) {
		driver.expectTextExistOrNot(expectExist, text, timeout);
	}

	public void expectElementExistOrNot(boolean expectExist, final String xpath, int timeout) {
		driver.expectElementExistOrNot(expectExist, xpath, timeout);
	}

	public boolean isTextPresent(String text, int time) {
		return driver.isTextPresent(text, time);
	}

	public boolean isElementPresent(String xpath, int time) {
		return driver.isElementPresent(xpath, time);
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
		return driver.getTitle();
	}
	public void close() {
		driver.close();
	}
	
	public void moveToView(String xpath) {
		driver.moveToView(xpath);
	}
}
