package messages;

import messages.RMIMessage;
import util.Pair;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/9/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageMethodInvocation extends RMIMessage {

    private String objectName;
    private String methodName;

    private Pair<Class[], Object[]> arguments;

    public RMIMessageMethodInvocation(String objectName, String methodName, Pair<Class[], Object[]> arguments) {
        this.objectName = objectName;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Pair<Class[], Object[]> getArguments() {
        return arguments;
    }
}
