package rx.thinkinginjava4th.holdingyourobjects;

/**
 * Reference in Thinking in Java's annotation:
 * This was not available before Java SE5, because it was thought to be too tightly coupled to the operating system,
 * and thus to violate "write once, run anywhere."
 * The fact that it is included now suggests that the Java designers are becoming more pragmatic.
 */
public class GetEnvMain {
    public static void main(String[] args) {
        final String envVar = "TESTME";
        String environmentVariableValue = System.getenv(envVar);
        System.out.println("Value of environment variable " + envVar + " is " + environmentVariableValue);
        System.out.println("Trial to get the environment variable value from getProperty method: " + System.getProperty(envVar));
    }
}
