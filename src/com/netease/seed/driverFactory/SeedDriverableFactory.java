package com.netease.seed.driverFactory;

import com.netease.seed.driver.SeedDriver;

public interface SeedDriverableFactory {
	public SeedDriver getDriver(String type);
}
