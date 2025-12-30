package utils;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();
    //Private - ConfigReader.properties; is not possible this means it can be accessible within the same class
    //Static - single shared memory throught out the class
    //final - can't create another properties object

    static {
        try (InputStream inputStream =
                     ConfigReader.class
                         .getClassLoader()
                         .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "config.properties file not found in classpath");
            }

            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Private constructor to prevent object creation
    private ConfigReader() {
    }

    public static String getProperty(String key) {
        // JVM system property override (CI/CD friendly)
        String systemValue = System.getProperty(key);
        return systemValue != null ? systemValue : properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
	

