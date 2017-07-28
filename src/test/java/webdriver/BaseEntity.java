package webdriver;

/**
 * Created by user on 12.10.2016.
 */
public abstract class BaseEntity {
/*
    //protected static int stepNumber = 1;
    protected static Browser browser = Browser.getInstance();
//    protected static boolean isLogged = false;
  //  protected static int screenIndex = 0;

    /**
     * killing process by Image name
     */
/*
    public static void checkAndKill() {
        try {
            String line;
            Process p = Runtime.getRuntime().exec(
                    String.format("taskkill /IM %1$s.exe /F",
                            Browser.currentBrowser.toString()));
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            while (((line = input.readLine()) != null)) {
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * Only for IE
     */
/*
    public void acceptUnsignedSertificate() {
        if (Browser.currentBrowser == Browser.Browsers.IEXPLORE) {
            browser.navigate("javascript:document.getElementById('overridelink').click()");
        }
    }

    /**
     * Before Class method
*/
/*
    @BeforeClass
    public static void before() {
    //    this.context = context;
        browser = Browser.getInstance();
        browser.windowMaximise();
     //   stepNumber = 1;

    }

    /**
     * Close browser after each test Class
     */
/*
    @AfterClass
    public static void after() {

        if (browser.isBrowserAlive()) {
            browser.exit();
            checkAndKill();
        }
    }

*/
}
