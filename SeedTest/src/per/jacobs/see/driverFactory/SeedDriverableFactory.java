package per.jacobs.see.driverFactory;

import per.jacobs.seed.driver.SeedDriver;

public interface SeedDriverableFactory {
	public SeedDriver getDriver(String type);
}
