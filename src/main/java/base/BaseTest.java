package base;

import drivers.DriverManager;
import drivers.DriverManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import reports.ExtentReportManager;

import java.lang.reflect.Method;

public class BaseTest {
    protected final Logger LOG = LogManager.getLogger(getClass());

    @BeforeSuite
    public void beforeSuite() {
        LOG.info("beforeSuite executing...");
        ExtentReportManager.initializeExtentReports(); // khoi tao extentreport manager
    }

    @AfterSuite
    public void afterSuite() {
        LOG.info("afterSuite ended...");
        ExtentReportManager.flushReports(); // tong ket report
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String testName = method.getName();

        // Đưa tên Test Case vào ThreadContext để Log4j2 tự động in ra log
        ThreadContext.put("testName", testName);

        LOG.info("beforeMethod executing...");
        ExtentReportManager.createTest(method.getName());
        DriverManager driverManager = DriverManagerFactory.getDriverManager("chrome");
        driverManager.createWebDriver();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        LOG.info("afterMethod ended...");
        WebDriver currentDriver = DriverManager.getDriverOrNull();
        String methodName = result.getMethod().getMethodName();

        try {
            // Xử lý theo từng trạng thái của TestNG để báo cáo đầy đủ (Pass, Fail, Skip)
            if (result.getStatus() == ITestResult.FAILURE) {
                Throwable throwable = result.getThrowable();

                // Ghi exception va stack trace vao console va automation.log
                LOG.error("TEST FAILED: " + methodName, throwable);

                String errorMessage = (throwable != null) ? throwable.toString() : "Test failed without exception details";

                if (currentDriver != null) {
                    try {
                        // Gộp chung thông báo lỗi và ảnh chụp màn hình vào 1 dòng duy nhất trên báo cáo
                        ExtentReportManager.getTest().fail(errorMessage,
                                ExtentReportManager.getScreenshotMedia(currentDriver, methodName));
                    } catch (Exception screenshotException) {
                        LOG.error("Failed to capture screenshot", screenshotException);
                        ExtentReportManager.fail(errorMessage);
                    }
                } else {
                    ExtentReportManager.fail(errorMessage);
                }

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ExtentReportManager.pass("Test passed successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                String skipMsg = (result.getThrowable() != null) ? result.getThrowable().toString() : "Test skipped";
                ExtentReportManager.getTest().skip(skipMsg);
            }

        } finally {
            try {
                DriverManager.quitDriver();
            } finally {
                ExtentReportManager.removeTest();
                // Xóa testName khỏi ThreadContext sau khi test chạy xong để dọn sạch luồng
                ThreadContext.remove("testName");
            }
        }
    }
}
