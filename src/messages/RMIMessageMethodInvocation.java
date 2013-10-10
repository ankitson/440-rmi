package messages;

import messages.RMIMessage;

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
    private Map<String, Object> arguments; //map from argument type (as string) to the argument

    public RMIMessageMethodInvocation(String objectName, String methodName, Map<String, Object> arguments) {
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

    public Map<String, Object> getArguments() {
        return arguments;
    }
}
