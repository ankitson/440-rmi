package server;

import messages.*;
import registry.RMIRegistry;
import registry.RemoteObjectReference;
import util.Pair;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIServerThread implements Runnable {
    private RMIMessenger messenger;
    private RMIRegistry rmiRegistry;

    public RMIServerThread(RMIMessenger messenger, RMIRegistry registry) {
        this.messenger = messenger;
        this.rmiRegistry = registry;
    }

    public void run() {
        RMIMessage received;
        while (true) {
            try {
                received = messenger.receiveMessage();
                if (received instanceof RMIMessageMethodInvocation) {
                    RMIMessageMethodInvocation mmi = (RMIMessageMethodInvocation) received;
                    String objectName = mmi.getObjectName();
                    Object o = rmiRegistry.internalLookup(objectName).getFirst();
                    executeMethod(o, mmi);
                } else if (received instanceof RMIMessageLookupRequest) {
                    String className = ((RMIMessageLookupRequest) received).getClassName();
                    RemoteObjectReference ror = rmiRegistry.clientLookup(className).getSecond();
                    RMIMessageRemoteObject mro = new RMIMessageRemoteObject(ror);
                    messenger.sendMessage(mro);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Illegal message received. Error: " + e);
            } catch (IOException e) {
                System.err.println("IO exception: " + e);
                return;
            }
        }
    }

    private void executeMethod(Object object, RMIMessageMethodInvocation mmi) {
        Pair<Class[],Object[]> argPair = mmi.getArguments();
        Class[] argumentTypes;
        Object[] arguments;
        if (argPair != null) {
            argumentTypes = argPair.getFirst();
            arguments = argPair.getSecond();
        } else {
            argumentTypes = null;
            arguments = null;
        }

        RMIMessage retValue = null;
        try {
            Method method = object.getClass().getMethod(mmi.getMethodName(), argumentTypes);
            Object retObj = method.invoke(object, arguments);
            Class retType = method.getReturnType();
            System.out.println("return type: " + retType);
            if (Remote440.class.isAssignableFrom(retType)) {
                System.out.println("method returned a remote type. returning ror to client");
                String uniqueKey = UUID.randomUUID().toString();
                while (rmiRegistry.registeredObjectsInternal.containsKey(uniqueKey)) {
                    uniqueKey = UUID.randomUUID().toString();
                }

                RemoteObjectReference ror = new RemoteObjectReference(uniqueKey, "localhost", 6666, retType.toString());
                rmiRegistry.registeredObjectsInternal.put(uniqueKey, new Pair(retObj, ror));

                retValue = new RMIMessageReturnValue(ror, RemoteObjectReference.class);
            } else {
                System.out.println("returning standard thing");
                retValue = new RMIMessageReturnValue(retObj, retType);
            }
        } catch (IllegalAccessException|NoSuchMethodException e) {
            retValue = new RMIMessageError("Error invoking method. Message: " + e);
        } catch (InvocationTargetException e) {
            retValue = new RMIMessageException((Exception) e.getTargetException());
        } catch (IOException e) {
            System.err.println("IO exception. Message: " + e);
        }

        try {
            messenger.objectOutputStream.writeObject(retValue);
        } catch (IOException e) {
            System.err.println("Unable to write return value to client: " + e);
        }

    }
}
