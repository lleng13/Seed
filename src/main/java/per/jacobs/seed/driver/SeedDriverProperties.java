package per.jacobs.seed.driver;

public class SeedDriverProperties {
	/**
	 * all parameters have default value
	 */
	private int timeout = 3000;
	private int interval = 500;
	private String iedriverpath = "unknown path";
	private String chromedriverpath = "unknown path";
	private String baseUrl = "http://www.163.com/";
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getStepInterval() {
		return interval;
	}
	public void setStepInterval(int interval) {
		this.interval = interval;
	}
	public String getIEPath() {
		return iedriverpath;
	}
	public void setIEPath(String iedriverpath) {
		this.iedriverpath = iedriverpath;
	}
	public String getChromePath() {
		return chromedriverpath;
	}
	public void setChromePath(String chromedriverpath) {
		this.chromedriverpath = chromedriverpath;
	}
	
}
