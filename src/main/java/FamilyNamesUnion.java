import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2017/7/30 0030.
 */
public class FamilyNamesUnion {
    private static final String PATH = "D:\\union\\contact.dat";
    private static final String deviceName = "e1459dc8";
    private static final String platformVersion = "5.1.1";
    private static final String udid = "e1459dc8";
    private static final String appPackage = "com.primeton.emp.client.core";
    //    private static final String appActivity = "cn.cucsi.global.umap3.Main";
    private static final String appActivity = ".activitys.Activity2";
    private static final String AppiumServerIP = "http://127.0.0.1:4723/wd/hub";
    private static Logger logger = LoggerFactory.getLogger(Union.class);
    private static AndroidDriver<AndroidElement> driver = null;
    private static Set<String> personsSet = new HashSet<>();
    private static Queue<String> cityQueue = new LinkedList<>();
    private static String city;

    private static boolean isException = false;

    //北京市 　广东省 　
// 山东省 　江苏省 　河南省
// 　上海市 　河北省 　浙江省
// 　香港特别行政区 　陕西省 　
// 湖南省 　重庆市 　福建省 　天津市
// 　云南省 　四川省 　广西壮族自治区
// 　安徽省 　海南省 　江西省 　湖北省 　山西省
// 　辽宁省 　台湾省 　黑龙江 　内蒙古自治区 　
// 澳门特别行政区 　贵州省 　甘肃省 　青海省 　新疆维吾尔自治区
// 　西藏区 　吉林省 　宁夏回族自治区
    static {
        cityQueue.add("北京市");
        cityQueue.add("广东");
        cityQueue.add("山东");
        cityQueue.add("江苏");
        cityQueue.add("河南");
        cityQueue.add("上海");
        cityQueue.add("河北");
        cityQueue.add("浙江");
        cityQueue.add("陕西");
        cityQueue.add("湖南");
        cityQueue.add("重庆");
        cityQueue.add("福建");
        cityQueue.add("天津");
        cityQueue.add("云南");
        cityQueue.add("四川");
        cityQueue.add("广西");
        cityQueue.add("安徽");
        cityQueue.add("海南");
        cityQueue.add("江西");
        cityQueue.add("湖北");
        cityQueue.add("山西");
        cityQueue.add("辽宁");
        cityQueue.add("黑龙江");
        cityQueue.add("内蒙古");
        cityQueue.add("贵州");
        cityQueue.add("甘肃");
        cityQueue.add("青海");
        cityQueue.add("吉林");


        city = cityQueue.poll();
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                login();
                getKey();
                String befor = driver.getPageSource();
                String after = "";
                if (isException) {
                    getPersion();
                }
                do {
                    List<AndroidElement> list = driver.findElementsByAccessibilityId("54");
                    for (AndroidElement androidElement : list) {
                        androidElement.click();
                        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(WebDriver driver) {
                                String text = ((AndroidDriver) driver).findElementByAccessibilityId("32").getAttribute("text");
                                if (StringUtils.isEmpty(text)) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        });
                        String page = driver.getPageSource();
                        save(page);
                        driver.findElementByAccessibilityId("18").click();
                        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"搜同事\"]")));
                    }


                    int width = driver.manage().window().getSize().width;
                    int height = driver.manage().window().getSize().height;
                    befor = driver.getPageSource();
                    driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 3000);
                    new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver driver) {
                            if (StringUtils.equals(((AndroidDriver) driver).findElementByAccessibilityId("6").getAttribute("text"), "个人信息")) {
                                ((AndroidDriver) driver).findElementByAccessibilityId("4").click();
                                return false;
                            } else if (StringUtils.equals(((AndroidDriver) driver).findElementByAccessibilityId("20").getAttribute("text"), "搜同事")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                    after = driver.getPageSource();
                } while (!StringUtils.equals(befor, after));
                city = cityQueue.poll();
                personsSet.clear();
                isException = false;
            } catch (Exception e) {
                isException = true;
                logger.error("{}", e);
            }
        }

    }

    private static void login() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("udid", udid);
//	    	capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("newCommandTimeout", "6000");
//            capabilities.setCapability("noReset", true);
        //设置设备
        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver<>(new URL(AppiumServerIP), capabilities);

//        driver.findElement(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")).click();
//        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"中国联通总部管理部门\"]")));

    }

    private static void getKey() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc=\"63\"]")));
        driver.findElementByAccessibilityId("63").click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"考勤\"]")));
        driver.findElementByAccessibilityId("250").sendKeys(city);
        driver.findElementByAccessibilityId("251").click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"搜同事\"]")));
    }

    private static void getPersion() {
        String befor = "";
        String after = driver.getPageSource();
        if (personsSet.size() == 0) {
            return;
        }
        do {
            Document document = Jsoup.parse(after, "", Parser.htmlParser());
            Elements elements = document.getElementsByAttributeValue("content-desc", "54");
            for (Element e : elements) {
                String name = e.attr("text");
                if (!personsSet.contains(name)) {
                    return;
                }
            }
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            befor = driver.getPageSource();
            driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 3000);
            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"搜同事\"]")));
            after = driver.getPageSource();
        } while (!StringUtils.equals(befor, after));

    }

    private static void save(String page) throws IOException {
        logger.info("开始提取联系人资料  page:{}", page);
        JSONObject jsonObject = new JSONObject();
        Document document = Jsoup.parse(page, "", Parser.xmlParser());
        String name = document.getElementsByAttributeValue("content-desc", "30").attr("text");
        String position = document.getElementsByAttributeValue("content-desc", "38").attr("text");
        String room_num = document.getElementsByAttributeValue("content-desc", "44").attr("text");
        String tel = document.getElementsByAttributeValue("content-desc", "51").attr("text");
        String mobile = document.getElementsByAttributeValue("content-desc", "60").attr("text");
//        String email = document.getElementsByAttributeValue("content-desc", "65").attr("text");
        jsonObject.put("name", name);
        jsonObject.put("position", position);
        jsonObject.put("room_num", room_num);
        jsonObject.put("tel", tel);
        jsonObject.put("mobile", mobile);
//        jsonObject.put("email", email);
        logger.info("提取结果为：{}", jsonObject);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(PATH), true))) {
            bufferedWriter.write(jsonObject.toJSONString() + "\n");
            personsSet.add(name);
            bufferedWriter.flush();
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }
}
