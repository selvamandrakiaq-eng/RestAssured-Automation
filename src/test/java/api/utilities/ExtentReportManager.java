package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            new File("Reports").mkdirs();
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("Reports/Petstore_API_Report.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "Petstore API Automation");
            extent.setSystemInfo("Framework", "REST Assured + TestNG");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
