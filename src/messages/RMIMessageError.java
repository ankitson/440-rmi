package messages;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageError extends RMIMessage {

    private String errorMessage;
    public RMIMessageError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String toString() {
        return errorMessage;
    }
}
