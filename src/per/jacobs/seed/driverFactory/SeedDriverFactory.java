package per.jacobs.seed.driverFactory;

import per.jacobs.seed.driver.SeedChromeDriver;
import per.jacobs.seed.driver.SeedDriver;
import per.jacobs.seed.driver.SeedDriverProperties;
import per.jacobs.seed.driver.SeedDriverType;
import per.jacobs.seed.driver.SeedFirefoxDriver;
import per.jacobs.seed.driver.SeedInternetExplorerDriver;

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
			case FIREFOX:
				driver = new SeedFirefoxDriver(prop);
			case INTERNET_EXPLORER:
				driver = new SeedInternetExplorerDriver(prop);
		}
		return driver;
	}
	
	public SeedDriver getDriver(String type) {
		if(type.equalsIgnoreCase("chrome"))
			driver = new SeedChromeDriver(prop);
		if(type.equalsIgnoreCase("firefox"))
			driver = new SeedFirefoxDriver(prop);
		if(type.equalsIgnoreCase("ie"))
			driver = new SeedInternetExplorerDriver(prop);
		return driver;
	}
}

