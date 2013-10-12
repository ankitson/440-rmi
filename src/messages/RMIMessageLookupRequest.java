package messages;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageLookupRequest extends RMIMessage {
    private String className;

    public RMIMessageLookupRequest(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
