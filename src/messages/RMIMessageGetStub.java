package messages;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 1:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageGetStub extends RMIMessage{
    private String key;
    public RMIMessageGetStub(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
