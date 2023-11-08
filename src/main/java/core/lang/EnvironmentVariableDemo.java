package core.lang;

import java.util.Map;

/**
 * Based on this demo, the getenv method call is kinda platform-dependent in the old days. This proofs what was mentioned in Thinking in Java
 */
public class EnvironmentVariableDemo {
    public static void main(String[] args) {
        Map<String,String> envVars = System.getenv();
        envVars.forEach((k,v) -> System.out.println("environment variable " + k + " has value " + v));
    }
}
