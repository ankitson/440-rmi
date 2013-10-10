package messages;

import messages.RMIMessage;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/9/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageReturnValue<T> extends RMIMessage {

    T returnValue;

    public RMIMessageReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public T getReturnValue() {
        return returnValue;
    }
}
