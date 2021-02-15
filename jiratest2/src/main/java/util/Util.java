public class UtilDriver{

    private WebDriver driver;
    private String baseUrl;

    public Page() {
            driver = new ChromeDriver();
            this.baseUrl = "https://www.seleniumeasy.com/test/";
            driver.get(baseUrl);
            new WebDriverWait(driver, 40).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            driver.manage().window().maximize();
            PopUp popUp = new PopUp(driver);
            popUp.closePopUpIfPresent();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
            }

    public WebDriver getDriver() {
            return driver;
            }

    public String getBaseUrl() {
            return baseUrl;
            }

    public void close(){
            driver.close();
            }
}