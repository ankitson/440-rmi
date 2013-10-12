package messages;

import registry.RemoteObjectReference;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessageRemoteObject extends RMIMessage {

    private RemoteObjectReference ror;
    public RMIMessageRemoteObject(RemoteObjectReference ror) {
        this.ror = ror;
    }

    public RemoteObjectReference getRemoteObjectReference() {
        return ror;
    }
}
