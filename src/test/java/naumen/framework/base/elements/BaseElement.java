package naumen.framework.base.elements;

import naumen.framework.base.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BaseElement extends BaseEntity {
	private static final String LINK = "link=";
	private static final String ID = "id=";
	private static final String CSS = "css=";
	private static final int TIMEOUT_IS_PRESENT = 5;
	private static final int WAIT_FOR_LOAD_PAGE = 200;
	private static final int TIMEOUT_WAIT_IS_NOT_PRESENT = 1;
    private static final int CLICK_ATTEMPTS_NUM = 3;

	/**
	 * @uml.property name="name"
	 */
	protected String name;
	/**
	 * @uml.property name="locator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected By locator;
	/**
	 * @uml.property name="element"
	 * @uml.associationEnd
	 */
	protected RemoteWebElement element;

	/**
	 * @return
	 * @uml.property name="element"
	 * @return RemoteWebElement
	 */
	public RemoteWebElement getElement() {
		waitForIsElementPresent();
		return element;
	}

    /**
     * @param By
     * @return List<WebElement>
     */
    public List<WebElement> findElements(By By) {
        waitForIsElementPresent();
        return element.findElements(By);
    }

	/**
	 * @return
	 * @uml.property name="element"
	 * @return RemoteWebElement
	 */
	public RemoteWebElement getElementInvisible() {
		waitNotVisible();
		return element;
	}

	/**
	 * @param elementToSet RemoteWebElement
	 * @uml.property name="element"
	 */
	public void setElement(final RemoteWebElement elementToSet) {
		element = elementToSet;
	}

	/**
	 * Verify that the drop-down element is minimized (performed by a class member)
	 * @return true if collapsed
	 */
	public boolean isCollapsed() {
		waitAndAssertIsPresent();
		String elementClass = element.getAttribute("class");
		return elementClass.toLowerCase().contains("collapse");
	}

	/**
	 * Check that the element is enabled (performed by a class member)
	 * @return true if enabled
	 */
	public boolean isEnabled() {
		waitAndAssertIsPresent();
		String elementClass = element.getAttribute("class");
		return element.isEnabled() && (!elementClass.toLowerCase().contains("disabled"));
	}

	/**
	 * Check that the item is checked (performed by a class member)
	 * @return true if checked
	 */
	public boolean isChecked() {
		waitAndAssertIsPresent();
		String elementClass = element.getAttribute("class");
		return !elementClass.toLowerCase().contains("unchecked");
	}

	/**
	 * The simple constructor, name will be extracted
	 * @param loc By Locator
	 */
	protected BaseElement(final By loc) {
		locator = loc;
	}

	/**
	 * The main constructor
	 * @param loc By Locator
	 * @param nameOf Output in logs
	 */
	protected BaseElement(final By loc, final String nameOf) {
		locator = loc;
		name = nameOf.equals("null") ? "unknown" : nameOf;
	}

	/**
	 * easy adapting from Selenium RC locators. CSS, ID, LINK
	 * @param stringLocator String locator
	 * @param nameOfElement Name
	 */
	protected BaseElement(final String stringLocator, final String nameOfElement) {
		String clearLoc = null;
		if (stringLocator.contains(CSS)) {
			clearLoc = stringLocator.replaceFirst(CSS, "");
			locator = By.cssSelector(clearLoc);
            if (nameOfElement.equals("null"))
                name = "unknown";
            else
                name = nameOfElement;
		} else if (stringLocator.contains(ID)) {
			clearLoc = stringLocator.replaceFirst(ID, "");
			locator = By.id(clearLoc);
            if (nameOfElement.equals("null"))
                name = "unknown";
            else
                name = nameOfElement;
		} else if (stringLocator.contains(LINK)) {
			clearLoc = stringLocator.replaceFirst(LINK, "");
			locator = By.linkText(clearLoc);
            if (nameOfElement.equals("null"))
                name = "unknown";
            else
                name = nameOfElement;
		} else {
			logger.fatal("UNKNOWN LOCATOR's TYPE. Change to 'By'");
		}

	}

	/**
	 * @return
	 * @uml.property name="locator"
	 * @return Locator
	 */
	public By getLocator() {
		return locator;
	}

    /**
     * Установить имя элементу, если сразу этого сделать не удалось
     * @param newName новое имя
     */
    public void setName(String newName) {
        name = newName;
    }
	/**
	 * @return
	 * @uml.property name="name"
	 * @return Name of element
	 */
	public String getName() {
		try {
			if (name.equals("null")) {
				name = browser.getDriver().findElement(locator).getText();
			}
            if (name.equals("null")) {
                name = browser.getDriver().findElementByXPath(".//*").getText();
            }
            if (name.equals("null")) {
                throw new Exception();
            }
		} catch (Exception e) {
			name = "unknown";
		}
		return name;
	}

	/**
	 * The implementation of an abstract method for logging of BaseEntity
	 * @param message Message to display in the log
	 * @return Formatted message (containing the name and type of item)
	 */
	protected String formatLogMsg(final String message) {
		return String.format("%1$s '%2$s' %3$s %4$s", getElementType(), name, Logger.LOG_DELIMITER, message);
	}

	/**
	 * The method returns the element type (used for logging)
	 * @uml.property name="elementType"
	 * @return Type of element
	 */
	protected abstract String getElementType();

	/**
	 * Send keys.
	 * */
	 public void sendKeys(Keys key) {
		waitForIsElementPresent();
	    assertIsPresent();
	    browser.getDriver().findElement(locator).sendKeys(key);
	 }
	
	/**
	 * Click via Action.
	 * */
	 public void clickViaAction() {
		waitForIsElementPresent();
	    assertIsPresent();
	    info(getLoc("loc.clicking"));
	    Actions action = new Actions(browser.getDriver());
		action.click(getElement());
		action.perform();
	 }
	
	 /**
	 * Click via JS.
	 * */
	 public void clickViaJS() {
		waitForIsElementPresent();
	    assertIsPresent();
	    info(getLoc("loc.clicking"));
	    ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", getElement());
	 }

    /**
     * Click via JS.
     * */
    public static void clickViaJS(WebElement el) {
        for (int i=0;i<CLICK_ATTEMPTS_NUM;i++)
            try {
                ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", el);
                return;
            }
            catch (StaleElementReferenceException sere) {
                continue;
            }
    }
	 
	/**
	 * Wait for the item and click on it
	 */
	public void waitAndClick() {
		waitForIsElementPresent();
		assertIsPresent();
		info(getLoc("loc.clicking"));
		element.click();
	}

	/**
	 * ext click through .sendKeys("\n");
	 */
	public void clickExt() {
		info(getLoc("loc.clicking.extended"));
		waitForIsElementPresent();
		browser.getDriver().findElement(locator).sendKeys("\n");
	}

	/**
	 * Move mouse to this element.
	 * */
	public void moveMouseToElement() {
		waitForIsElementPresent();
		CommonFunctions.centerMouse();
		Actions action = new Actions(browser.getDriver());
		action.moveToElement(getElement());
		action.perform();
	}

	/**
	 * wait for element is present
	 * @return true, if element is shown on the page
	 */
	@SuppressWarnings("deprecation")
	public boolean waitForIsElementPresent() {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition()));
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					try {
						List<WebElement> list = driver.findElements(locator);
						for (WebElement el : list) {
							if (el instanceof RemoteWebElement && el.isDisplayed()) {
								element = (RemoteWebElement) el;
								if (element.isDisplayed()) {
                                    System.out.println("Элемент " + name + " найден на странице");
                                    return element.isDisplayed();
								}
							}
						}
						element = (RemoteWebElement) driver.findElement(locator);
					} catch (Exception e) {
						return false;
					}
					return element.isDisplayed();
				}
			});
		}catch (StaleElementReferenceException e) {
			System.out.println("WARN: Изменился стиль элемента: " + name + "\n\r" + e.getMessage());
			// делаем просто слип
			browser.sleep(browser.getTimeoutShortInt());
		}catch (Exception e) {
			System.out.println("WARN: Не удалось дождаться элемента: " + name + "\n\r" + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * wait for element is present
	 * @param timeInSeconds time for wait
	 * @return true, if element is shown on the page
	 */
	public boolean waitForIsElementPresent(long timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), timeInSeconds);
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					try {
						List<WebElement> list = driver.findElements(locator);
						for (WebElement el : list) {
							if (el instanceof RemoteWebElement && el.isDisplayed()) {
								element = (RemoteWebElement) el;
								if (element.isDisplayed()) {
									System.out.println("Элемент " + name + " найден на странице");
									return element.isDisplayed();
								}
							}
						}
						element = (RemoteWebElement) driver.findElement(locator);
					} catch (Exception e) {
						return false;
					}
					return element.isDisplayed();
				}
			});
		} catch (Exception e) {
			System.out.println("WARN: Не удалось дождаться элемента: " + name + "\n\r" + e.getMessage());
			try {
				FileWriter writer = new FileWriter(new File(name + ".html"));
				writer.write(browser.getDriver().getPageSource());
				writer.close();
			} catch (IOException e1) {
				System.out.println("Не удалось сохранить файл: " + name + ".html");
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	/** проверяет отсутствует ли элемент
	 * @return true если отсутствует
	 */
	public Boolean isElementMissed(){
		return !isPresent();
	}

	/**
	 * wait for element is not present
	 * @return true, if element is not shown on the page
	 */
	public boolean waitForElementIsNotPresent() {
		return browser.waitCondition(this, "isElementMissed", new Class[]{}, new Object[]{}, "Элемент " + getName() + " отсутствует");
	}

	/**
	 * wait for element is not present
	 * @param timeout timeout
	 * @return true, if element is not shown on the page
	 */
	public boolean waitForElementIsNotPresent(long timeout) {
		return browser.waitCondition(this, "isElementMissed", new Class[]{}, new Object[]{}, "Элемент " + getName() + " отсутствует", timeout);
	}

	/** wait and assert that element is not shown on the page
	 */
	public void waitAndAssertNotShownElement(){
		if(!waitForElementIsNotPresent()){
			logger.fatal("Элемент " + name + " присутствует на странице!");
		}
	}

	/**
	 * wait the item does not exist yet (there is no check on Visibility)
	 */
	public void waitNotVisible() {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition()));

		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					try {
						List<WebElement> list = driver.findElements(locator);
						for (WebElement el : list) {
							if (el instanceof RemoteWebElement) {
								element = (RemoteWebElement) el;
								return true;
							}
						}
						element = (RemoteWebElement) driver.findElement(locator);
						return true;
					} catch (Exception e) {
						return false;
					}
				}
			});
		} catch (Exception e) {
			boolean res = false;
			try {
				res = (element != null);
			} catch (Exception e1) {
				e1.toString();
			}
			Assert.assertTrue(formatLogMsg(getLoc("loc.is.absent")), res);
		}
		Assert.assertTrue(formatLogMsg(getLoc("loc.is.absent")), element != null);
	}

    /**
     * Click on the item
     */
    public void click() {
        if(!waitForIsElementPresent()){
            warn("Не удалось дождаться элемента " + getName());
            return;
        }else{
            info(getLoc("loc.clicking"));
        }
        for (int i=0;i<CLICK_ATTEMPTS_NUM;i++)
            try {
                element.click();
                return;
            }
            catch (StaleElementReferenceException sere) {
                continue;
            }
        warn("Элемент " + getName() + ": клик не произведен");
    }

    /**
     * Click on the item
     */
    public void clickWithoutNotification() {
        if(!waitForIsElementPresent()){
            warn("Не удалось дождаться элемента " + getName());
            return;
        }
        for (int i=0;i<CLICK_ATTEMPTS_NUM;i++)
            try {
                element.click();
                return;
            }
            catch (StaleElementReferenceException sere) {
                continue;
            }
        warn("Элемент " + getName() + ": клик не произведен");
    }

	/**
	 * Click on an item and wait for the page is loaded
	 */
	public void clickAndWait() {
		click();
		browser.waitForPageToLoad();
	}

    /**
     * Click on an item and wait for the page is loaded
     */
    public void clickAndWaitWithoutNotification() {
        clickWithoutNotification();
        browser.waitForPageToLoad();
    }

	/** click on element and repeat if expected element is not appears
	 * @param expectedLocator locator for expected element
	 */
	public void clickUntilNotPresent(By expectedLocator){
		BaseElement e = new BaseElement(expectedLocator) {
			@Override
			protected String getElementType() {
				return null;
			}
		};
		for(int i = 0; i <= 2; i++){
			click();
			if(e.waitForElementIsNotPresent(browser.getTimeoutShortInt())){
				break;
			}
		}
	}

	/**
	 * Click on an item ext click through .sendKeys("\n") and wait for the page is loaded.
	 */
	public void clickExtAndWait() {
		clickExt();
		browser.waitForPageToLoad();
		try {
			Thread.sleep(WAIT_FOR_LOAD_PAGE);
		} catch (InterruptedException e) {
			e.toString();
		}
	}

	/**
	 * Click and look forward to the emergence of a new window
	 */
	public void clickAndWaitForNewWindow() {
		int count = browser.getDriver().getWindowHandles().size();
		click();
		//info(getLoc("loc.select.next.window"));
		browser.waitForNewWindow(count);
		/**
		 * Fix for IE8: sometimes new window handle appears on the position before current window handle
		 */
		if (browser.getDriver().getWindowHandles().toArray()[count].equals(browser.getDriver().getWindowHandle())){
			count--;
		}
		browser.getDriver().switchTo().window((String) browser.getDriver().getWindowHandles().toArray()[count]);
	}

	/**
	 * Click and look forward to closing the current window
	 */
	public void clickAndWaitForCloseWindow() {
		int count = browser.getDriver().getWindowHandles().size();
		click();
		info(getLoc("loc.select.previous.window"));
		browser.waitForNewWindow(count - 2);
		browser.getDriver().switchTo().window((String) browser.getDriver().getWindowHandles().toArray()[count - 2]);
	}

	/**
	 * Get the item text (inner text)
	 * @return Text of element
	 */
	public String getText() {
		waitAndAssertIsPresent();
		try{
			return element.getText();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			waitAndAssertIsPresent();
			return element.getText();
		}
	}

	/**
	 * Check for an element on the page If the element is absent, then the test is aborted
	 */
	public void assertIsPresent() {
		if (!isPresent()) {
			fatal(getLoc("loc.is.absent"));
		}
	}

	/**
	 * Check for an element on the page If the element is present, then the test is aborted
	 */
	public void assertIsAbsent() {
		if (isPresent()) {
			fatal(getLoc("loc.is.present"));
		}
	}

	/**
	 * Wait for the item and abort the test, if the item does not appear
	 */
	public void waitAndAssertIsPresent() {
		if(!waitForIsElementPresent()){
			fatal(getLoc("loc.is.absent"));
		}
        else{
            info(getLoc("loc.is.present"));
        }
	}
    /**
     * Wait for the item and warn, if the item does not appear
     */
    public void waitAndAssertIsPresentOnlyWarn(BaseTest test) {

        if(!waitForIsElementPresent()){
            warn(getLoc("loc.is.absent"));
            String fileName = this.getClass().getPackage().getName() + "." + this.getClass().getSimpleName();
            BaseTest.makeScreen(fileName);
            test.warn();
        }
        else{
            info(getLoc("loc.is.present"));
        }
    }
	/**
	 * Check for is element present on the page
	 * @return Is element present
	 */
	public boolean isPresent() {
		WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), 1);
		browser.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					try {
						List<WebElement> list = driver.findElements(locator);
						for (WebElement el : list) {
							if (el instanceof RemoteWebElement && el.isDisplayed()) {
								element = (RemoteWebElement) el;
								if (element.isDisplayed()) {
									browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_IS_PRESENT, TimeUnit.SECONDS);
								}
								return element.isDisplayed();
							}
						}
						element = (RemoteWebElement) driver.findElement(locator);
					} catch (Exception e) {
						return false;
					}
					browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_IS_PRESENT, TimeUnit.SECONDS);
					return element.isDisplayed();
				}
			});
		} catch (Exception e) {
			e.toString();
		}
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	/**
	 * Double click on the item. Waiting for the end of renderiga
	 */
	public void doubleClick() {
		waitForIsElementPresent();
		info(getLoc("loc.clicking.double"));
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		Actions builder = new Actions(browser.getDriver());
		Action dClick = builder.doubleClick(element).build();
		dClick.perform();

	}

	/**
	 * another implementation of waiting
	 */
	public void waitForExists() {
		new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition())) {
		}.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(final WebDriver driver) {
				return (driver.findElements(locator).size() > 0);
			}
		});
	}

	/**
	 * another implementation of waiting for not exists
	 */
	public void waitForDoesNotExist() {
		browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_WAIT_IS_NOT_PRESENT, TimeUnit.SECONDS);
		new WebDriverWait(browser.getDriver(), Long.parseLong(browser.getTimeoutForCondition())) {
		}.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(final WebDriver driver) {
				return (driver.findElements(locator).size() < 1);
			}
		});
		browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_IS_PRESENT, TimeUnit.SECONDS);
	}

	/**
	 * Gets attribute value of the element
	 * @param attr Attribute name
	 * @return Attribute value
	 */
	public String getAttribute(final String attr) {
		waitForIsElementPresent();
		return browser.getDriver().findElement(locator).getAttribute(attr);
	}

	/**
	 * set value via javascript <b>document.getElementById('%1$s').value='%2$s' </b>
	 * @param elementId Element Id
	 * @param value Value
	 */
	public void setValueViaJS(final String elementId, final String value) {
		try {
			((JavascriptExecutor) browser.getDriver()).executeScript(String.format(
					"document.getElementById('%1$s').value='%2$s'", elementId, value), element);
		} catch (Exception r) {
			Logger.getInstance().warn(r.getMessage());
		}
	}

	/**
	 * Right Click
	 */
	public void clickRight() {
		waitForIsElementPresent();
		info("Clicking Right");
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		browser.getDriver().getMouse().contextClick(element.getCoordinates());
	}

	/**
	 * Focuses the element
	 */
	public void focus() {
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
	}

	/**
	 * Кликает и ждет появления нового окна
	 */
	public final void jsClickAndWaitForNewWindow() {
		Set<String> set = browser.getDriver().getWindowHandles();
		int count = browser.getDriver().getWindowHandles().size();
		jsClick();
		//info("Переключение на следующее окно");
		browser.waitForNewWindow(count);
		Iterator<String> cIter = browser.getDriver().getWindowHandles().iterator();
		String handle = null;
		while(cIter.hasNext()){
			String temp = cIter.next();
			if(!set.contains(temp)){
				handle = temp;
				break;
			}
		}
		browser.getDriver().switchTo().window(handle);
		browser.windowMaximise();
	}
	
	/**
	 * Кликает по элементу и ждет появления нового окна.
	 * Если после первого клика окно не появилось, то попытка повторяется.
	 * После пояления окна делается переключение на него.
	 * @param quantityOfTrying количество попыток
	 */
	public final void clickAndRepeatIfNewWindowNotAppeared(final Integer quantityOfTrying){
		Set<String> set = browser.getDriver().getWindowHandles();
		int count = browser.getDriver().getWindowHandles().size();
		for(int i = 0; i < quantityOfTrying; i++){
			jsClick();
			if(browser.waitForNewWindowWithoutAssertion(count)){
				break;
			}
		}
		//Ждем последний раз, и если окно не открылось завершаем тест
		browser.waitForNewWindow(count);
		Iterator<String> cIter = browser.getDriver().getWindowHandles().iterator();
		String handle = null;
		while(cIter.hasNext()){
			String temp = cIter.next();
			if(!set.contains(temp)){
				handle = temp;
				break;
			}
		}
		browser.getDriver().switchTo().window(handle);
	}

	/**
	 * Кликает по элементу и ждет закрытия окна при помощи JS. После переключается на предыдущее окно.
	 */
	public final void jsClickAndWaitForCloseWindow() {
		int count = browser.getDriver().getWindowHandles().size();
		jsClick();
		info("Переключение на предыдущее окно");
		browser.waitForWindowIsCloseAndSwitch(count);
	}

	/** Кликает по элементу с помощью Javascript
	 */
	public final void jsClick() {
		waitAndAssertIsPresent();
		info("Клик c помощью JS");
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", element);
	}

	public final void setVisibleStyle(){
		//info("Клик c помощью JS");
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.visibility = 'visible';", element);
	}

	/** Кликает по элементу с помощью Javascript
	 */
	public final void jsClickNoLog() {
		  waitAndAssertIsPresent();
		  ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", element);
	}

	/** Кликает по элементу с помощью Javascript
	 */
	public final void jsClickAndWait() {
		waitAndAssertIsPresent();
		  info("Клик c помощью JS");
		  try{
			  ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", element);
		  }catch (StaleElementReferenceException e) {
			  waitAndAssertIsPresent();
			  ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].click();", element);
		  }
		  browser.waitForPageToLoad();
	}

	/** кликает по элементу(несколько попыток)
	 */
	public void jsClickAndWaitElementDisappear(){
		Label l = new Label(locator, name);
		for(int i = 0; i <= 2; i++){
			try{
				//info("Клик c помощью JS");
				//WebElement e = browser.getDriver().findElement(locator);
				//((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].click();", e);
				clickAndWait();
				browser.waitForPageToLoad();
				if(!l.waitForIsElementPresent(10)){
					break;
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

	/** жмет по элементу без ожидания пока он станет видимым
	 * @param by локатор
	 */
	public static void jsClickNative(By by){
		WebElement element = browser.getDriver().findElement(by);
		((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].click();", element);
	}

	/** Получает значение(innerHTML) с помощью JS
	 * @return innerHTML
	 */
	public String getInnerHtml(){
		waitAndAssertIsPresent();
		return (String)((JavascriptExecutor) browser.getDriver()).executeScript("return arguments[0].innerHTML;", element);
	}
}
