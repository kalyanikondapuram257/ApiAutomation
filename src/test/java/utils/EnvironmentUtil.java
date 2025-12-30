package utils;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentUtil {

    private static String baseUri;

    private static final Map<String, String> ENV_BASE_URI_MAP = new HashMap<>();

    static {
        ENV_BASE_URI_MAP.put("DEV", ConfigReader.getProperty("DEV"));
        ENV_BASE_URI_MAP.put("QA", ConfigReader.getProperty("QA"));
        ENV_BASE_URI_MAP.put("STAGE", ConfigReader.getProperty("STAGE"));
        ENV_BASE_URI_MAP.put("PROD", ConfigReader.getProperty("PROD"));
    }

    /**
     * Entry point for setting environment
     */
    public static void initializeEnvironment(String manualEnv) {

        // 1️⃣ Check if env is passed via Maven
        String mavenEnv = System.getProperty("env");

        if (mavenEnv != null && !mavenEnv.isBlank()) {
            setEnvironmentFromSystem(mavenEnv);
        } else {
            setEnvironmentManually(manualEnv);
        }
    }

    /**
     * Called when env is passed via Maven command
     */
    private static void setEnvironmentFromSystem(String envName) {
        setEnvironmentInternal(envName);
    }

    /**
     * Called when env is set manually in code
     */
    private static void setEnvironmentManually(String envName) {
        if (envName == null || envName.isBlank()) {
            throw new IllegalArgumentException("Manual environment value cannot be null or empty");
        }
        setEnvironmentInternal(envName);
    }

    /**
     * Common logic
     */
    private static void setEnvironmentInternal(String envName) {
        String env = envName.toUpperCase();

        if (!ENV_BASE_URI_MAP.containsKey(env)) {
            throw new IllegalArgumentException("Unknown environment: " + envName);
        }

        baseUri = ENV_BASE_URI_MAP.get(env);

        if (baseUri == null || baseUri.isBlank()) {
            throw new IllegalStateException("Base URI not configured for environment: " + env);
        }
    }

    public static String getBaseUri() {
        return baseUri;
    }
}

  