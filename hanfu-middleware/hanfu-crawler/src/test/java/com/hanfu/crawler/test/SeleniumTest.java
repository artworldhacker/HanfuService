package com.hanfu.crawler.test;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoubing on 2016/11/3.
 */
public class SeleniumTest {
    public static void main(String[] args) throws Exception {
//        phantomjs();
//        testXpath();
        chrome();
    }


    private static void phantomjs() throws Exception {
        String phantomjsDriverFilePath = "F:\\selenium\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";

        System.setProperty("phantomjs.binary.path", phantomjsDriverFilePath);
        File phantomjsDriverFile = new File(phantomjsDriverFilePath);

        Map<String, String> environment = new HashMap<String, String>();
//        environment.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");


        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        PhantomJSDriverService service = new PhantomJSDriverService.Builder()
                .usingPhantomJSExecutable(phantomjsDriverFile)
                .usingAnyFreePort()
                .withEnvironment(environment)
                .build();
        service.start();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);

        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(), capabilities);

        try {
            test163(driver);
        }
        finally {
            // 关闭浏览器
//            driver.quit();
//            // 关闭 ChromeDriver 接口
//            service.stop();
        }
    }

    private static void chrome() throws Exception {
        String chromeDriverFilePath = "F:\\selenium\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
        File chromeDriverFile = new File(chromeDriverFilePath);

        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(chromeDriverFile)
                .usingAnyFreePort()
                .build();
        service.start();

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);

        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(), capabilities);

        try {
            test163(driver);
//            testNetworkLogin(driver);
        }
        finally {
            // 关闭浏览器
//            driver.quit();
//            // 关闭 ChromeDriver 接口
//            service.stop();
        }
    }

    private static void testXpath() throws Exception {
        String chromeDriverFilePath = "F:\\selenium\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
        File chromeDriverFile = new File(chromeDriverFilePath);

        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(chromeDriverFile)
                .usingAnyFreePort()
                .build();
        service.start();

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);

        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(), capabilities);

        try {
            driver.get("file://F:/test.html");
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='mod-jifenDetail-cont']/table/tbody/tr"));

            String date = null;
            StringBuilder sb = new StringBuilder();
            int total = 0;
            for (WebElement element : elements) {
                WebElement dateElement = findElement(element, By.cssSelector("td.date"));
                if (dateElement != null) {
                    if (date != null) {
                        break;
                    }
                    date = dateElement.getText();
                    sb.append(date + "\n");
                }
                String jifen = element.findElement(By.cssSelector("td.jifen p")).getText();
                String comment = element.findElement(By.cssSelector("td.ct p")).getText();

                total += Integer.parseInt(jifen.replace("+", ""));

                sb.append("\t" + jifen + "\t");
                sb.append(comment + "\n");
            }

            sb.append("total：" + total);

            System.out.println(sb);
        }
        finally {
            // 关闭浏览器
//            driver.quit();
//            // 关闭 ChromeDriver 接口
//            service.stop();
        }
    }

    private static WebElement findElement(SearchContext context, By selector) {
        try {
            return context.findElement(selector);
        }
        catch (NoSuchElementException e) {
        }
        return null;
    }

    private static void test(ChromeDriverService service, WebDriver driver) throws Exception {
        driver.get("http://flight.qunar.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement from_inpox = driver
                .findElement(By
                        .xpath("//div[@id='js_flighttype_tab_domestic']//input[@name='fromCity']"));
        WebElement to_inpox = driver
                .findElement(By
                        .xpath("//div[@id='js_flighttype_tab_domestic']//input[@name='toCity']"));
        WebElement from_date = driver
                .findElement(By
                        .xpath("//div[@id='js_flighttype_tab_domestic']//input[@name='fromDate']"));
        WebElement sigleWayCheckBox = driver
                .findElement(By
                        .xpath("//div[@id='js_flighttype_tab_domestic']//input[@class='inp_chk js-searchtype-oneway']"));
        if (!sigleWayCheckBox.isSelected()) {
            sigleWayCheckBox.click();
        }

        from_inpox.clear();
        from_inpox.sendKeys("BJ");
        sleep(8000);
        By bj = new By.ByXPath(
                "//div[@class='qcbox-fixed js-suggestcontainer']//td[contains(text(),'北京')]");
        if (isElementPresent(driver, bj, 20)) {
            driver.findElement(bj).click();
        }

        to_inpox.clear();
        to_inpox.sendKeys("SH");
        sleep(8000);
        By sh = new By.ByXPath(
                "//div[@class='qcbox-fixed js-suggestcontainer']//td[contains(text(),'上海')]");
        if (isElementPresent(driver, sh, 20)) {
            driver.findElement(sh).click();
        }

        // Actions actions = new Actions(driver);
        // actions.moveToElement(from_inpox).click().perform();
        // driver.findElement(
        // By.xpath("//div[@data-panel='domesticfrom-flight-hotcity-from']//a[@class='js-hotcitylist' and text()='西安']"))
        // .click();
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        // actions.moveToElement(to_inpox).click().perform();
        // driver.findElement(
        // By.xpath("//div[@data-panel='domesticto-flight-hotcity-to']//a[@class='js-hotcitylist' and text()='北京']"))
        // .click();
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        from_date.clear();
        from_date.sendKeys(getDateAfterToday(7));
        WebElement search = driver
                .findElement(By
                        .xpath("//div[@id='js_flighttype_tab_domestic']//button[@class='btn_search']"));
        search.submit();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement page2 = driver.findElement(By
                .xpath("//div[@id='hdivPager']/a[@value='2']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", page2);
        page2.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(
                By.xpath("(//div[@class='avt_trans']//p[contains(text(),'每段航班均需缴纳税费')]/ancestor::div//div[@class='a_booking']/a)[3]"))
                .click();
        driver.findElement(
                By.xpath("//div[@id='flightbarXI883']//div[@class='t_bk']/a"))
                .click();
    }

    public static String getDateAfterToday(int dateAfterToday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +dateAfterToday);
        System.out.println(cal.getTime().toString());
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(date));
        return df.format(date);
    }
    
    private static void sleep(long sleepTime){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Young
     * @param driver
     * @param by
     * @param timeOut
     * @return
     * @throws InterruptedException
     */
    public static boolean isElementPresent(WebDriver driver, final By by,
                                           int timeOut) throws InterruptedException {
        boolean isPresent = false;
        sleep(timeOut * 1000);
        List<WebElement> we = driver.findElements(by);
        if (we.size() != 0) {
            isPresent = true;
        }
        return isPresent;
    }
    private static void test2(ChromeDriverService service, WebDriver driver) {
        driver.manage().window().maximize();


        // 让浏览器访问 Baidu
        driver.get("http://login.189.cn/login");

        // 通过 id 找到 input 的 DOM
        WebElement element = driver.findElement(By.id("txtAccount"));

        // 输入关键字
        element.sendKeys("17755115684");
        element.sendKeys(Keys.TAB);

        sleep(1000);

        element = driver.findElement(By.id("txtPassword"));
        element.sendKeys("471247");

        sleep(1000);



        // 提交 input 所在的 form
        element.submit();

//        // 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().endsWith("ztree");
//            }
//        });

        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());
    }

    private static void test163(WebDriver driver) {
        driver.get("http://mail.163.com/");

        String s = driver.getPageSource();

//        sleep(1000);
        driver.switchTo().frame("x-URS-iframe");

        s = driver.getPageSource();

        WebElement element = null;

        element = driver.findElement(By.name("email"));

        element.sendKeys("xxxx");

        element = driver.findElement(By.name("password"));

        element.sendKeys("xxx");

        element = driver.findElement(By.id("dologin"));

        element.click();

        sleep(10000);

        element = driver.findElement(By.id("_mail_component_68_68"));

        element.click();

        sleep(5000);

//TODO
        element = driver.findElement(By.className("nui-editableAddr-ipt"));

        element.sendKeys("xxx");

        element = driver.findElement(By.id("_mail_button_2_184"));

        element.click();

        sleep(5000);

        element = driver.findElement(By.id("_mail_button_11_237"));

        element.click();

        sleep(5000);
//        element = driver.findElement(By.cssSelector("span[title=MyAccounts]"));
//
//        element.click();

//        sleep(3000);


//        List<WebElement> elements = null;
//
//        elements = driver.findElements(By.linkText("zhoubing"));
//
//        if (elements != null && elements.size() >= 8) {
//            element = elements.get(NumberUtils.random(0, 7));
//            element.click();
//            sleep(3000);
//        }

//        element = driver.findElement(By.cssSelector("section div.dG0 input.nui-ipt-input"));
//
//        element.sendKeys("Hello Selenium!");
//
//        sleep(3000);

//        element = driver.findElement(By.cssSelector("div.nui-toolbar-item div.js-component-button.nui-mainBtn.nui-btn.nui-btn-hasIcon.nui-mainBtn-hasIcon"));
//
//        element.click();
//
//        sleep(10000);



    }



    private static void testNetworkLogin(WebDriver driver) {
        driver.get("https://192.168.0.1/connect/PortalMain");

        waitWithTitle(driver, "Network");

        WebElement element = null;

        element = driver.findElement(By.id("LoginUserPassword_auth_username"));

        element.sendKeys("wangpeng");

        element = driver.findElement(By.id("LoginUserPassword_auth_password"));

        element.sendKeys("Hakim2193");

        element = driver.findElement(By.id("UserCheck_Login_Button"));

        element.click();
    }

    private static void waitWithTitle(WebDriver driver, final String title) {
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String t = d.getTitle();
                return t != null && t.contains(title);
            }
        });
    }

    private static void waitWithContent(WebDriver driver, final String content) {
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String html = d.getPageSource();
                return html != null && html.contains(content);
            }
        });
    }
}
