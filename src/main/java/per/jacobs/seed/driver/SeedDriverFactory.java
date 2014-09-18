package per.jacobs.seed.driver;

public class SeedDriverFactory {
	private SeedDriver driver;
	public SeedDriverFactory() {
		
	}
	
	public SeedDriver getDriver(SeedDriverType type) {
		switch(type){
			case CHROME:
				driver = new SeedChromeDriver();
			case FIREFOX:
				driver = new SeedFirefoxDriver();
			case INTERNET_EXPLORER:
				driver = new SeedInternetExplorerDriver();
		}
		return driver;
	}
	
	public SeedDriver getDriver(String type) {
		if(type.equalsIgnoreCase("chrome"))
			driver = new SeedChromeDriver();
		if(type.equalsIgnoreCase("firefox"))
			driver = new SeedFirefoxDriver();
		if(type.equalsIgnoreCase("ie"))
			driver = new SeedInternetExplorerDriver();
		return driver;
	}
}

