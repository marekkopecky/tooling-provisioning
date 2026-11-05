package org.wildfly.qa.tooling.processexecution;

/**
 * Centralized access to system properties used by tooling-process-execution.
 * <p>
 * Naming convention: {@code org.wildfly.qa.{domain}.{detail}}
 * &mdash; matches the upstream namespace (creaper, dist-diff).
 * <p>
 * All property names are public constants so downstream testsuites
 * can reference them (single source of truth).
 */
public final class ProcessExecutionProperties {

    /**
     * Server process timeout in minutes. Controls how long {@link ServerProcess}
     * waits for the server to start and complete test operations before destroying the process.
     */
    public static final String SERVER_PROCESS_TIMEOUT_MINUTES =
            "org.wildfly.qa.server.process.timeout.minutes";

    /**
     * Management client timeout in milliseconds. Used for both boot timeout and connection timeout
     * of the management client (needed e.g. for use-case with server restart).
     */
    public static final String SERVER_MANAGEMENT_TIMEOUT_MILLIS =
            "org.wildfly.qa.server.management.timeout.millis";

    private static final long DEFAULT_SERVER_PROCESS_TIMEOUT_MINUTES = 5;

    private static final int DEFAULT_SERVER_MANAGEMENT_TIMEOUT_MILLIS = 30000;

    private ProcessExecutionProperties() {}

    /**
     * Get the server process timeout in minutes.
     * @return timeout value from system property, or 5 minutes if not set
     */
    public static long serverProcessTimeoutMinutes() {
        return Long.parseLong(System.getProperty(SERVER_PROCESS_TIMEOUT_MINUTES,
                String.valueOf(DEFAULT_SERVER_PROCESS_TIMEOUT_MINUTES)));
    }

    /**
     * Get the management client boot and connection timeout in milliseconds.
     * @return timeout value from system property, or 30000 ms (30 seconds) if not set
     */
    public static int serverManagementTimeoutMillis() {
        return Integer.getInteger(SERVER_MANAGEMENT_TIMEOUT_MILLIS, DEFAULT_SERVER_MANAGEMENT_TIMEOUT_MILLIS);
    }
}
