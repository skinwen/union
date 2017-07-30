import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/29 0029.
 */
public class Union {
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
    private static boolean isException = true;
    private static Set<String> departMentSet = new HashSet<>();
    private static Set<String> personsSet = new HashSet<>();
    private static Set<String> companySet = new HashSet<>();

    private static boolean departmentover = true;

    static {
        companySet.add("监事会");
        companySet.add("中国联通总部管理部门");
        companySet.add("中国联合网络通信(香港)股份有限公司");
        companySet.add("中国联合网络通信股份有限公司(A股公司)");
        companySet.add("中讯邮电咨询设计院有限公司");
        companySet.add("联通兴业通信技术有限公司");
        companySet.add("联通华盛通信有限公司");
        companySet.add("联通时科（北京）信息技术有限公司");
        companySet.add("联通宽带在线有限公司");
        companySet.add("联通系统集成有限公司");
        companySet.add("联通信息导航有限公司");
        companySet.add("联通支付有限公司");
        companySet.add("联通支付有限公司总部");
        companySet.add("联通系统集成有限公司");
        companySet.add("联通系统集成有限公司");
        companySet.add("中融信息服务有限公司");
        companySet.add("中国联通国际有限公司");
        companySet.add("中国联通研究院");
        companySet.add("中网威信电子安全服务有限公司");
        companySet.add("京都信苑饭店");
        companySet.add("联通通信建设有限公司");
        companySet.add("联通进出口有限公司");
        companySet.add("联通云数据有限公司");
        companySet.add("联通网络技术研究院");
        companySet.add("联通创新创业投资有限公司");
        companySet.add("小沃科技有限公司");
        companySet.add("联通软件研究院");
        companySet.add("联通智网科技有限公司");
        companySet.add("联通集团财务有限公司");
        companySet.add("北京联通兴业科贸有限公司");
        companySet.add("联通学院");
        companySet.add("大数据公司筹备组");
        companySet.add("中国联通北京市分公司");
//        companySet.add("中讯邮电咨询设计院有限公司");
        departMentSet.add("本部");
//        departMentSet.add("联通系统集成有限公司本部");
//        departMentSet.add("联通系统集成有限公司黑龙江省分公司");
//        departMentSet.add("联通系统集成有限公司山东省分公司");
//        departMentSet.add("联通系统集成有限公司吉林省分公司");
//        departMentSet.add("联通系统集成有限公司内蒙古分公司");
//        departMentSet.add("联通系统集成有限公司河南省分公司");
//        departMentSet.add("联通系统集成有限公司山西省分公司");
//        departMentSet.add("联通系统集成有限公司河北省分公司");
//        departMentSet.add("联通系统集成有限公司辽宁省分公司");
//        departMentSet.add("联通系统集成有限公司天津市分公司");
//        departMentSet.add("联通系统集成有限公司山西省分公司");
//        departMentSet.add("联通系统集成有限公司四川省分公司");
//        departMentSet.add("联通系统集成有限公司江苏省分公司");
//        departMentSet.add("联通系统集成有限公司重庆市分公司");
//        departMentSet.add("联通系统集成有限公司浙江省分公司");
//        departMentSet.add("联通系统集成有限公司福建省分公司");
//        departMentSet.add("联通系统集成有限公司广东省分公司");
//        departMentSet.add("联通系统集成有限公司上海市分公司");
//        departMentSet.add("联通系统集成有限公司安徽省分公司");
//        departMentSet.add("联通系统集成有限公司广西分公司");
//        departMentSet.add("联通系统集成有限公司陕西省分公司");
//        departMentSet.add("联通系统集成有限公司青海省分公司");
//        departMentSet.add("联通系统集成有限公司江西省分公司");
//        departMentSet.add("联通系统集成有限公司海南省分公司");
//        departMentSet.add("联通（山东）产业互联网有限公司");
//        departMentSet.add("联通系统集成有限公司湖北省分公司");
//        departMentSet.add("联通系统集成有限公司新疆分公司");
//        departMentSet.add("联通系统集成有限公司云南分公司");
//        departMentSet.add("联通系统集成有限公司.北京市分公司");
//        departMentSet.add("联通系统集成有限公司湖南省分公司");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(PATH)))) {
            String record = null;
            while ((record = bufferedReader.readLine()) != null) {
                if (!StringUtils.isEmpty(record)) {
                    JSONObject jsonObject = JSON.parseObject(record);
                    String name = jsonObject.getString("name");
                    personsSet.add(name);
                }
            }
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                login();
                while (true) {
                    AndroidElement department = null;
//                    if (isException) {
//                        department = getDepartment();
//                        getPerson(department);
//                    } else {
//                        List<AndroidElement> departments = driver.findElementsByAccessibilityId("325");
//
//                    }
                    if (departmentover || isException) {
                        getCompany();
                        departmentover = false;
                    }
                    department = getDepartment();
                    if (departmentover) {
                        continue;
                    }
                    if (isException) {
                        department = getPerson();
                        isException = false;
                    } else {
//                        department.click();
                        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(WebDriver driver) {
                                String desc = driver.getPageSource();
                                if (!StringUtils.contains(desc, "通讯录")) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                    }
//                    getPerson(department);
                    String befor = driver.getPageSource();
                    String after = befor;
                    do {
                        List<AndroidElement> nameList = driver.findElementsByAccessibilityId("50");


                        for (WebElement webElement : nameList) {
                            webElement.click();
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
                            driver.findElementByAccessibilityId("4").click();
                            new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
                                @Override
                                public Boolean apply(WebDriver driver) {
                                    if (StringUtils.equals(((AndroidDriver) driver).findElementByAccessibilityId("9").getAttribute("text"), "通讯录")) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            });
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
                                } else if (StringUtils.equals(((AndroidDriver) driver).findElementByAccessibilityId("9").getAttribute("text"), "通讯录")) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        after = driver.getPageSource();
                    } while (!StringUtils.equals(after, befor));

                    driver.findElementByAccessibilityId("7").click();
                    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));

                }
            } catch (Exception e) {
                isException = true;
                logger.error("{}", e);
                File file = driver.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file, new File("D:\\union\\1.png"));
            }
        }
    }

    private static AndroidElement getDepartment() {

        AndroidElement departMent = null;
        String after = driver.getPageSource();
        String befor = "";
        do {

            List<AndroidElement> departMents = driver.findElementsByAccessibilityId("324");
            if (departMentSet.size() == 0) {
                if (departMents.size() <= 1) {
                    departMentSet.add(departMents.get(0).getAttribute("text"));
                    continue;
                }
                String name = departMents.get(1).getAttribute("text");
                departMentSet.add(name);
                departMents.get(1).click();
                return departMents.get(1);
//                new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        String page = driver.getPageSource();
//                        if (StringUtils.contains(page, "通讯录") || StringUtils.contains(page, "公司信息")) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//                String page = driver.getPageSource();
//                if(StringUtils.contains(page,"公司信息")){
//                    ((AndroidDriver) driver).findElementByAccessibilityId("4").click();
//                    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
//                    departMentSet.add(name);
//                    continue;
//                }
//                if (StringUtils.contains(page, "没有") ) {
//                    ((AndroidDriver) driver).findElementByAccessibilityId("7").click();
//                    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
//                    departMentSet.add(name);
//                    continue;
//                }
//
//                new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        if (StringUtils.contains(driver.getPageSource(), "公司信息") || StringUtils.contains(driver.getPageSource(), "个人信息")) {
//                            ((AndroidDriver) driver).findElementByAccessibilityId("4").click();
//                            return false;
//                        } else if (StringUtils.contains(((AndroidDriver) driver).getPageSource(), "全国通讯录")) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//                departMents.get(0).click();
//                return departMents.get(0);
            }
            for (int i = 0; i < departMents.size(); i++) {
                AndroidElement temp = departMents.get(i);
                //如果i包括但是i+1不包括
                String departName = temp.getAttribute("text");
                logger.info("{}", departName);
                if (departMentSet.contains(departName) && (i + 1 < departMents.size() && !departMentSet.contains(departMents.get(i + 1).getAttribute("text")))) {

                    if (isException) {
                        String name = temp.getAttribute("text");
                        temp.click();
                        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"通讯录\"]")));
                        String page = driver.getPageSource();
                        if (StringUtils.contains(page, "没有")) {
                            ((AndroidDriver) driver).findElementByAccessibilityId("7").click();
                            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
                            departMentSet.add(name);
                            departMentSet.add(departMents.get(i + 1).getAttribute("text"));
//                            i--;
                            continue;
                        }
                        departMentSet.add(name);
                        return temp;
                    } else {
                        String name = departMents.get(i + 1).getAttribute("text");
                        departMents.get(i + 1).click();
                        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"通讯录\"]")));
                        String page = driver.getPageSource();
                        if (StringUtils.contains(page, "没有")) {
                            ((AndroidDriver) driver).findElementByAccessibilityId("7").click();
                            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
                            departMentSet.add(name);
                            continue;
                        }
                        departMentSet.add(name);
                        return departMents.get(i + 1);
                    }
                } else if (departMentSet.contains(temp.getAttribute("text")) && i + 1 >= departMents.size()) {
                    break;
                }
            }
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            befor = driver.getPageSource();
            driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 3000);
            after = driver.getPageSource();
        } while (!StringUtils.equals(after, befor));
        departmentover = true;
        isException = false;
        return departMent;
    }

    //定位到某一页
    private static AndroidElement getPerson() {
        AndroidElement result = null;
//        department.click();
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
//                String page = driver.getPageSource();
//                if (StringUtils.contains(page, "没有")) {
//                    ((AndroidDriver) driver).findElementByAccessibilityId("7").click();
//                    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
//                    return true;
//                }
                String desc = ((AndroidDriver) driver).findElementByAccessibilityId("61").getAttribute("text");
                if (StringUtils.equals(desc, "发短信")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        String befor = driver.getPageSource();
        String after = befor;
        do {
            List<AndroidElement> persons = driver.findElementsByAccessibilityId("50");
            for (AndroidElement person : persons) {
                String name = person.getAttribute("text");
                if (!personsSet.contains(name)) {
                    isException = false;
                    return person;
                }
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
                    } else if (StringUtils.equals(((AndroidDriver) driver).findElementByAccessibilityId("9").getAttribute("text"), "通讯录")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            after = driver.getPageSource();
        } while (!StringUtils.equals(befor, after));
        return result;
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
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc=\"72\"]")));
        driver.findElementByAccessibilityId("72").click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"中国联通总部管理部门\"]")));

    }

    private static void save(String page) throws IOException {
        logger.info("开始提取联系人资料  page:{}", page);
        JSONObject jsonObject = new JSONObject();
        Document document = Jsoup.parse(page, "", Parser.xmlParser());
        String name = document.getElementsByAttributeValue("content-desc", "27").attr("text");
        String position = document.getElementsByAttributeValue("content-desc", "32").attr("text");
        String room_num = document.getElementsByAttributeValue("content-desc", "37").attr("text");
        String tel = document.getElementsByAttributeValue("content-desc", "43").attr("text");
        String mobile = document.getElementsByAttributeValue("content-desc", "57").attr("text");
        String email = document.getElementsByAttributeValue("content-desc", "65").attr("text");
        jsonObject.put("name", name);
        jsonObject.put("position", position);
        jsonObject.put("room_num", room_num);
        jsonObject.put("tel", tel);
        jsonObject.put("mobile", mobile);
        jsonObject.put("email", email);
        logger.info("提取结果为：{}", jsonObject);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(PATH), true))) {
            bufferedWriter.write(jsonObject.toJSONString() + "\n");
            personsSet.add(name);
            bufferedWriter.flush();
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    private static AndroidElement getCompany() {
        driver.findElementByAccessibilityId("305").click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"确定\"]")));
        String after = driver.getPageSource();
        String befor = after;
        do {
            List<AndroidElement> elements = driver.findElements(By.xpath("//android.widget.TextView"));
            if (companySet.size() == 0) {
                return elements.get(0);
            }

            for (int i = 0; i < elements.size(); i++) {
                AndroidElement androidElement = elements.get(i);
                String text = androidElement.getAttribute("text");
                logger.info("{}", text);

                if (companySet.contains(text) && (i + 1 < elements.size() && !companySet.contains(elements.get(i + 1).getAttribute("text")))) {

                    if (isException) {
                        companySet.add(androidElement.getAttribute("text"));
                        androidElement.click();
                        driver.findElement(By.xpath("//android.widget.Button[@text=\"确定\"]")).click();
                        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
                        if (!isException) {
                            departMentSet.clear();
                        }
                        return androidElement;
                    } else {
                        companySet.add(elements.get(i + 1).getAttribute("text"));
                        elements.get(i + 1).click();
                        driver.findElement(By.xpath("//android.widget.Button[@text=\"确定\"]")).click();
                        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
                        if (!isException) {
                            departMentSet.clear();
                        }
                        return elements.get(i + 1);
                    }
                } else if (companySet.contains(androidElement.getAttribute("text")) && i + 1 >= elements.size()) {
                    break;
                }
            }

            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            befor = driver.getPageSource();
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(width / 2, height * 2 / 3).waitAction(3000).moveTo(width / 2, height / 3).release();
            touchAction.perform();
            after = driver.getPageSource();
        } while (!StringUtils.equals(after, befor));
        return null;
    }
}
