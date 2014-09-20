package per.jacobs.seed.driver;

public interface SeedDrivable {
	/**
	 * Open the URL
	 * @param url
	 *            the target URL
	 */
	public void open(String url);
	/**
	 * Open the URL
	 * @param url
	 *            the target URL
	 * @param timeout
	 * 			timeout = the max wait time
	 */
	public void open(String url,int timeout);
	/**
	 * Quit the browser
	 */
	public void quit();
	/**
	 * Click the page element
	 * @param xpath
	 *            the element's xpath
	 */
	public void click(String xpath);
	/**
	 * Click the page element
	 * @param xpath
	 *            the element's xpath
	 * @param timeout
	 * 			timeout = the max wait time
	 */
	public void click(String xpath, int timeout);
	/**
	 * Type text at the page element<br>
	 * Before typing, try to clear existed text;
	 * @param xpath
	 *            the element's xpath
	 * @param text
	 *            the input text
	 */
	public void type(String xpath, String text);
	/**
	 * Hover on the page element
	 * @param xpath
	 *            the element's xpath
	 */
	public void mouseOver(String xpath);
	/**
	 * Switch window/tab
	 * @param windowTitle
	 *            the window/tab's title
	 */
	public void selectWindow(String windowTitle);
	/**
	 * Enter the iframe
	 * @param xpath
	 *            the iframe's xpath
	 */
	public void enterFrame(String xpath);

	/**
	 * Leave the iframe
	 */
	public void leaveFrame();
	/**
	 * Refresh the current browser window
	 */
	public void refresh();
	/**
	 * Mimic system-level keyboard event
	 * @param keyCode
	 *            such as KeyEvent.VK_TAB, KeyEvent.VK_F11
	 */
	public void pressKeyboard(int keyCode);
	/**
	 * Expect some text exist or not on the page<br>
	 * Expect text exist, but not found after timeout -- Assert fail<br>
	 * Expect text not exist, but found after timeout -- Assert fail
	 * @param expectExist
	 *            true or false
	 * @param text
	 *            the expected text
	 * @param timeout
	 *            timeout in millisecond
	 */
	public void expectTextExistOrNot(boolean expectExist, final String text, int timeout);
	/**
	 * Expect an element exist or not on the page<br>
	 * Expect element exist, but not found after timeout -- Assert fail<br>
	 * Expect element not exist, but found after timeout -- Assert fail<br>
	 * Here <b>exist</b> means <b>visible</b>
	 * @param expectExist
	 *            true or false
	 * @param xpath
	 *            the expected element's xpath
	 * @param timeout
	 *            timeout in millisecond
	 */
	public void expectElementExistOrNot(boolean expectExist, final String xpath, int timeout);
	/**
	 * Is the text present on the page
	 * @param text
	 *            the expected text
	 * @param time           
	 *            wait a moment (in millisecond) before search text on page;<br>
	 *            minus time means search text at once
	 * @return boolean
	 * 			  if exist return true
	 */
	public boolean isTextPresent(String text, int time);
	/**
	 * Is the element present on the page<br>
	 * Here <b>present</b> means <b>visible</b>
	 * @param xpath
	 *            the expected element's xpath
	 * @param time           
	 *            wait a moment (in millisecond) before search element on page;<br>
	 *            minus time means search element at once
	 * @return boolean
	 * 			  if exist return true
	 */
	public boolean isElementPresent(String xpath, int time);
	/**
	 * Pause
	 * @param time in millisecond
	 */
	public void pause(int time);
	/**
	 * getTitle
	 * @return String
	 * 		get current window title
	 */
	public String getTitle();
	/**
	 * close
	 * @param void
	 * 		close the current tab, if the tab is the last quit the browser
	 */
	public void close();
	/**
	 * moveToView
	 * Control the scrolls(both the vertical and horizontal) to get the target view into window scope
	 * 
	 * @param xpath
	 * 		the target xpath
	 */
	public void moveToView(String xpath);
}
