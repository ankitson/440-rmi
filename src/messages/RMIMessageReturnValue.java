package messages;

import messages.RMIMessage;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/9/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class RMIMessageReturnValue extends RMIMessage {

    Object returnValue;
    Class returnType;

    public RMIMessageReturnValue(Object returnValue, Class returnType) {
        this.returnValue = returnValue;
        this.returnType = returnType;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public Class getReturnType() {
        return returnType;
    }
}
