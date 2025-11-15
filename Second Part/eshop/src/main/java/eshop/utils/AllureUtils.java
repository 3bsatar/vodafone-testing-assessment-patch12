package eshop.utils;
import io.qameta.allure.Allure;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class AllureUtils {
    public static final String ALLURE_Results_PATH = "test-outputs/allure-results";

    private AllureUtils() {
        // Private constructor to prevent instantiation
        super();
    }

    public static void attacheLogsToAllureReport() {
        try {
            File logsFile = FileUtils.getLatestFile(Logsutil.LOGS_PATH);
            if (!logsFile.exists()) {
                Logsutil.warn("Logs file does not exist: " + Logsutil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logsFile.getPath())));
            Logsutil.info("Logs file attached to Allure report: " + logsFile.getName());
        } catch (Exception e) {
            Logsutil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }
    }

    public static void attachScreenshotToAllureReport(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            Logsutil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    }

    public static void setEnvironment(String browser, String url, String version) {
        try {
            Path envFile = Path.of("test-outputs/allure-results/environment.properties");

            Properties props = new Properties();
            props.setProperty("Browser", browser);
            props.setProperty("URL", url);
            props.setProperty("Version", version);

            Files.createDirectories(envFile.getParent());
            props.store(Files.newOutputStream(envFile, StandardOpenOption.CREATE), "Allure Environment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
