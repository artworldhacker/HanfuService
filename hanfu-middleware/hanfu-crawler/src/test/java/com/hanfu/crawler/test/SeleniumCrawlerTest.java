package com.hanfu.crawler.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

/**
 * Created by wangpeng on 2017/1/15.
 */
public class SeleniumCrawlerTest {


    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
       /* driver.get("https://www.baidu.com/");
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("小坦克 博客园");
        WebElement searchButton = driver.findElement(By.id("su"));
        searchButton.submit();*/
       /* driver.get("http://www.douban.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("小坦克");
        searchBox.submit();*/

        driver.get("http://www.cnblogs.com");

        List<WebElement> buttons = driver.findElements(By.tagName("div"));
        System.out.println("Button:" + buttons.size());

        //driver.close();
    }
}
