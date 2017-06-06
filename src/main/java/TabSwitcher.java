import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Maria on 06.06.2017.
 */
public class TabSwitcher {

    /**
     * Сохраняет индивидуальный иденттификатор текущего окна
     */
    public String storeTheCurrentTabHandle()
    {
        String winHandleBefore = getWebDriver().getWindowHandle();
        return winHandleBefore;
    }


    /**
     * Переключение на новую открытую вкладку
     */
    public void switchToNewTabOpened()
    {
        for (String winHandle : getWebDriver().getWindowHandles()) {
            getWebDriver().switchTo().window(winHandle);
        }
    }

    /**
     * Закрытие нового окна
     */
    public void closeTab(){ getWebDriver().close(); }

    /**
     * Переключение на первую вкладку
     */
    public void switchToFirstTab()
    {
        String winHandleBefore = storeTheCurrentTabHandle();
        getWebDriver().switchTo().window(winHandleBefore);
    }


}
