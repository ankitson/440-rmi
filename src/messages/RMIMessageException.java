package messages;

import messages.RMIMessage;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/9/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageException extends RMIMessage {

    Exception exception;

    public RMIMessageException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

}
