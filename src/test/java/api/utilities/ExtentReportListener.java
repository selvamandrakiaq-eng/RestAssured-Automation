package api.utilities;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance()
                .createTest(result.getMethod().getMethodName());
        TEST.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TEST.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getInstance().flush();
    }
}
