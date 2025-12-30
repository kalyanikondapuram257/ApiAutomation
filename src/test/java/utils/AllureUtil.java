package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class AllureUtil {

    private AllureUtil() {
        // prevent instantiation
    }

    /* =========================
       TEXT & LOG ATTACHMENTS
       ========================= */

    @Attachment(value = "{attachmentName}", type = "text/plain")
    public static String attachText(String attachmentName, String message) {
        return message;
    }

    /* =========================
       JSON ATTACHMENTS
       ========================= */

    @Attachment(value = "{attachmentName}", type = "application/json")
    public static String attachJson(String attachmentName, String json) {
        return json;
    }

    /* =========================
       REQUEST / RESPONSE
       ========================= */

    public static void attachRequest(String method, String url, String body, Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();
        sb.append("METHOD: ").append(method).append("\n");
        sb.append("URL: ").append(url).append("\n\n");

        if (headers != null && !headers.isEmpty()) {
            sb.append("HEADERS:\n");
            headers.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        }

        if (body != null) {
            sb.append("\nBODY:\n").append(body);
        }

        attachText("API Request", sb.toString());
    }

    public static void attachResponse(int statusCode, String responseBody) {
        attachText("Status Code", String.valueOf(statusCode));
        attachJson("API Response", responseBody);
    }

    /* =========================
       ENVIRONMENT INFO
       ========================= */

    public static void attachEnvironmentDetails() {
        String env = System.getProperty("env", "PROD");

        String details = """
                Environment Details
                -------------------
                ENV        : %s
                OS         : %s
                JAVA       : %s
                USER       : %s
                """.formatted(
                env,
                System.getProperty("os.name"),
                System.getProperty("java.version"),
                System.getProperty("user.name")
        );

        attachText("Environment Details", details);
    }

    /* =========================
       FILE / STREAM ATTACHMENT
       ========================= */

    public static void attachAsStream(String name, String content, String type) {
        Allure.addAttachment(
                name,
                type,
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                ".txt"
        );
    }
}
