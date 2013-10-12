package examples;

import messages.*;
import registry.RemoteObjectReference;
import util.Pair;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/12/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteListStub {

    private RemoteObjectReference ror;
    private RMIMessenger messenger;

    public RemoteListStub(RemoteObjectReference ror, RMIMessenger messenger) {
        this.ror = ror;
        this.messenger = messenger;
    }

    public Boolean add(String elem) throws Exception {
        Class[] argumentTypes = {String.class};
        Object[] arguments = {elem};

        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "add", new Pair(argumentTypes, arguments));
        messenger.sendMessage(mmi);
        RMIMessage returnValue = messenger.receiveMessage();
        if (returnValue instanceof RMIMessageException) {
            RMIMessageException ex = (RMIMessageException) returnValue;
            throw ex.getException();
        } else if (returnValue instanceof RMIMessageReturnValue) {
            RMIMessageReturnValue retVal =  (RMIMessageReturnValue) returnValue;
            if (retVal.getReturnType().equals(Boolean.class)) {
                return (Boolean) retVal.getReturnValue();
            } else {
                throw new Exception("Invalid return type. Expected boolean, but got: " + retVal.getReturnType());
            }
        } else {
            throw new Exception("Illegal message received invoking method. Message: " + returnValue);
        }
    }

    public String get(Integer index) throws Exception {
        Class[] argumentTypes = {Integer.class};
        Object[] arguments = {index};

        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "get", new Pair(argumentTypes, arguments));
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

    public Integer size() throws Exception {
        Class[] argumentTypes = {};
        Object[] arguments = {};

        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "size", new Pair(argumentTypes, arguments));
        messenger.sendMessage(mmi);
        RMIMessage returnValue = messenger.receiveMessage();
        if (returnValue instanceof RMIMessageException) {
            RMIMessageException ex = (RMIMessageException) returnValue;
            throw ex.getException();
        } else if (returnValue instanceof RMIMessageReturnValue) {
            RMIMessageReturnValue retVal =  (RMIMessageReturnValue) returnValue;
            if (retVal.getReturnType().equals(Integer.class)) {
                return (Integer) retVal.getReturnValue();
            } else {
                throw new Exception("Invalid return type. Expected Integer, but got: " + retVal.getReturnType());
            }
        } else {
            throw new Exception("Illegal message received invoking method. Message: " + returnValue);
        }
    }

    public RemoteObjectReference merge(RemoteObjectReference list) throws Exception {
        Class[] argumentTypes = {RemoteObjectReference.class};
        Object[] arguments = {list};

        RMIMessageMethodInvocation mmi = new RMIMessageMethodInvocation(ror.getKey(), "merge", new Pair(argumentTypes, arguments));
        messenger.sendMessage(mmi);
        RMIMessage returnValue = messenger.receiveMessage();
        if (returnValue instanceof RMIMessageException) {
            RMIMessageException ex = (RMIMessageException) returnValue;
            throw ex.getException();
        } else if (returnValue instanceof RMIMessageReturnValue) {
            RMIMessageReturnValue retVal =  (RMIMessageReturnValue) returnValue;
            if (retVal.getReturnType().equals(RemoteObjectReference.class)) {
                return (RemoteObjectReference) retVal.getReturnValue();
            } else {
                throw new Exception("Invalid return type. Expected ror, but got: " + retVal.getReturnType());
            }
        } else {
            throw new Exception("Illegal message received invoking method. Message: " + returnValue);
        }
    }
}
