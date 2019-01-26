package report;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

  private static ExtentReports extent;

  public static ExtentReports getInstance(final Path reportDir) {

    if (extent == null) {
      threadSafeMakeExtent(reportDir);
    }
    return extent;
  }

  private synchronized static void threadSafeMakeExtent(final Path reportDir) {

    if (extent == null) {

      final String filePath = Paths.get(reportDir.toString(), "index.html").toString();
      extent = new ExtentReports(filePath, Boolean.TRUE);

      // optional
      extent.config().documentTitle("Rapport de tests de CRM Express").reportHeadline(makeHeadline())
          .reportName("SELENIUM CRMomega");
    }
  }

  private static String makeHeadline() {

    return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
  }
}
