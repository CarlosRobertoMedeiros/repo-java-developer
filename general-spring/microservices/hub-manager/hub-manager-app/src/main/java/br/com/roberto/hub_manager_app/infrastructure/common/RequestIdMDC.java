package br.com.roberto.hub_manager_app.infrastructure.common;

import org.slf4j.MDC;

public class RequestIdMDC {

    private static final String REQUEST_ID_KEY = "requestId";

    /**
     * Sets the request ID in the MDC context for the current thread.
     * This makes the request ID available to all log messages in this thread.
     *
     * @param requestId The request ID to set
     */
    public static void set(String requestId) {
        if (requestId != null && !requestId.isEmpty()) {
            MDC.put(REQUEST_ID_KEY, requestId);
        }
    }

    /**
     * Gets the current request ID from the MDC context.
     *
     * @return The request ID, or null if not set
     */
    public static String get() {
        return MDC.get(REQUEST_ID_KEY);
    }

    /**
     * Clears the request ID from the MDC context.
     * This should be called when the request processing is complete.
     */
    public static void clear() {
        MDC.remove(REQUEST_ID_KEY);
    }
}
