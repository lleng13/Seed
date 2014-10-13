package com.netease.seed.driverFactory;

import com.netease.seed.driver.SeedChromeDriver;
import com.netease.seed.driver.SeedDriver;
import com.netease.seed.driver.SeedDriverProperties;
import com.netease.seed.driver.SeedDriverType;
import com.netease.seed.driver.SeedFirefoxDriver;
import com.netease.seed.driver.SeedInternetExplorerDriver;

public class SeedDriverFactory implements SeedDriverableFactory{
	private SeedDriver driver;
    private SeedDriverProperties prop;
	public SeedDriverFactory() {
		
	}
	public SeedDriverProperties getProp() {
		return this.prop;
	}

	public void setProp(SeedDriverProperties prop) {
		this.prop = prop;
	}
	
	public SeedDriverProperties createProp() {
		return this.prop = new SeedDriverProperties();
	}
	public SeedDriver getDriver(SeedDriverType type) {
		switch(type){
			case CHROME:
				driver = new SeedChromeDriver(prop);
				break;
			case FIREFOX:
				driver = new SeedFirefoxDriver(prop);
				break;
			case INTERNET_EXPLORER:
				driver = new SeedInternetExplorerDriver(prop);
				break;
		}
		return driver;
	}
	
	public SeedDriver getDriver(String type) {
		if(type.equalsIgnoreCase("chrome"))
			driver = new SeedChromeDriver(prop);
		else if(type.equalsIgnoreCase("firefox"))
			driver = new SeedFirefoxDriver(prop);
		else if(type.equalsIgnoreCase("ie"))
			driver = new SeedInternetExplorerDriver(prop);
		else
			driver = new SeedChromeDriver(prop);//–¥¥Ì¡Àtype“≤ «SeedChromeDriver
		return driver;
			
	}
}

