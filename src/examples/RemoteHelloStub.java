package examples;

import messages.*;
import registry.RemoteObjectReference;
import util.Pair;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteHelloStub implements Serializable {

    private RemoteObjectReference ror;
    private RMIMessenger messenger;

    public RemoteHelloStub(RemoteObjectReference ror, RMIMessenger messenger) {
        this.ror = ror;
        this.messenger = messenger;
    }

    public String sayHello(String message2, String message3, Integer num, List<Float> floats) throws Exception {
        Class[] argumentTypes = {String.class, String.class, Integer.class, List.class};
        Object[] arguments = {message2, message3, num, floats};

        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "sayHello", new Pair(argumentTypes, arguments));
        messenger.sendMessage(mmi);
        RMIMessage returnValue = messenger.receiveMessage();
        if (returnValue instanceof RMIMessageException) {
            RMIMessageException ex = (RMIMessageException) returnValue;
            throw ex.getException();
        } else if (returnValue instanceof RMIMessageReturnValue) {
            RMIMessageReturnValue retVal =  (RMIMessageReturnValue) returnValue;
            if (retVal.getReturnType().equals(String.class)) {
                return (String) retVal.getReturnValue();
            } else {
                throw new Exception("Invalid return type. Expected string, but got: " + retVal.getReturnType());
            }
        } else {
            throw new Exception("Illegal message received invoking method. Message: " + returnValue);
        }
    }

    public RemoteObjectReference newHello(String msg) throws ClassNotFoundException, IOException {
        Class[] argumentTypes = {String.class};
        Object[] arguments = {msg};
        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "newHello", new Pair(argumentTypes, arguments));
        messenger.sendMessage(mmi);
        RMIMessageReturnValue retVal = (RMIMessageReturnValue) messenger.receiveMessage();
        RemoteObjectReference newHelloRemote = (RemoteObjectReference) retVal.getReturnValue();
        return newHelloRemote;
    }
}
