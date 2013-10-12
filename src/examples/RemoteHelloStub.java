package examples;

import messages.*;
import registry.RemoteObjectReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteHelloStub {

    private RemoteObjectReference ror;
    private RMIMessenger messenger;

    public RemoteHelloStub(RemoteObjectReference ror, RMIMessenger messenger) {
        this.ror = ror;
        this.messenger = messenger;
        System.out.println("messenger cosntructor: " + this.messenger);
    }

    public String sayHello(String message2, String message3, int num) throws Exception {
        Map<Class, Object> arguments = new HashMap<Class, Object>();
        arguments.put(String.class, message2);
        arguments.put(String.class, message3);
        arguments.put(Integer.class, num);
        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "sayHello", arguments);
        System.out.println("messenger: " + messenger);
        messenger.sendMessage(mmi);
        RMIMessage returnValue = messenger.receiveMessage();
        if (returnValue instanceof RMIMessageException) {
            RMIMessageException ex = (RMIMessageException) returnValue;
            throw ex.getException();
        } else if (returnValue instanceof RMIMessageReturnValue) {
            System.out.println("got a return value");
            RMIMessageReturnValue retVal =  (RMIMessageReturnValue) returnValue;
            if (retVal.getReturnType().equals(String.class)) {
                System.out.println("returning string return value");
                return (String) retVal.getReturnValue();
            } else {
                System.err.println("invalid return type");
                throw new Exception("Invalid return type. Expected string");
            }
        } else {
            throw new Exception("Illegal message received invoking method. Message: " + returnValue);
        }
    }
}
