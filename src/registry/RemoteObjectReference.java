package registry;

import messages.RMIMessageGetStub;
import messages.RMIMessenger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteObjectReference implements Serializable {
    String key;
    String hostName;
    int port;
    String riName;

    public RemoteObjectReference(String key, String hostName, int port, String riName) throws IOException {
        this.key = key;
        this.hostName = hostName;
        this.port = port;
        this.riName = riName;
    }

    public String getKey() {
        return key;
    }

}
