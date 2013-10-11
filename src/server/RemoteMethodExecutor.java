package server;

import messages.*;
import util.Pair;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Adi
 * Date: 10/11/13
 * Time: 5:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteMethodExecutor<T> implements Runnable {

    Socket socket;
    T object;
    RMIMessageMethodInvocation methodInvocationMessage;
    String objectName;
    String methodName;
    Map<String, Object> argumentMap;


    public RemoteMethodExecutor(Socket socket, T object, RMIMessageMethodInvocation methodInvocationMessage) {
        this.socket = socket;
        this.object = object;
        this.objectName = methodInvocationMessage.getObjectName();
        this.methodName = methodInvocationMessage.getMethodName();
        this.argumentMap = methodInvocationMessage.getArguments();
        this.methodInvocationMessage = methodInvocationMessage;
    }

    public void run() {
        Pair<Class[],Object[]> argPair = parseArgumentMap(argumentMap);
        Class[] argumentTypes = argPair.getFirst();
        Object[] arguments = argPair.getSecond();

        RMIMessage retValue;
        try {
            Method method = object.getClass().getMethod(methodName, argumentTypes);
            Object retObj = method.invoke(object, arguments);
            Class retType = method.getReturnType();

            //does not handle case when ret type should be a remote obj ref
            retValue = new RMIMessageReturnValue(retObj, retType);
        } catch (IllegalAccessException|NoSuchMethodException e) {
            System.err.println("Error invoking method. Message: " + e);
            retValue = new RMIMessageError("Error invoking method. Mesage: " + e);
        } catch (InvocationTargetException e) {
            //todo: is this cast from Throwable -> Exception ok?
            retValue = new RMIMessageException((Exception) e.getTargetException());
        }

        try {
            new ObjectOutputStream(socket.getOutputStream()).writeObject(retValue);
        } catch (IOException e) {
            System.err.println("Unable to write return value to client: " + e);
        }

    }

    private Pair<Class[], Object[]> parseArgumentMap(Map<String, Object> argumentMap) {
        Class[] argumentTypes = new Class[argumentMap.size()];
        Object[] arguments = new Object[argumentMap.size()];
        int i = 0;
        try {
            for (Map.Entry<String, Object> entry : argumentMap.entrySet()) {
                argumentTypes[i] = Class.forName(entry.getKey());
                arguments[i] = entry.getValue();
                i++;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error parsing argument map " + argumentMap.toString() +". Message: " + e);
            return null;
        }
        return new Pair(argumentTypes,arguments);
    }

    public static void main(String[] args) throws NoSuchMethodException
    {
        System.out.println("hey");
        Class[] argTypes = {String[].class};
        Method mainMethod = RemoteMethodExecutor.class.getMethod("main", argTypes);
        System.out.println("mainMethod ret type: " + mainMethod.getReturnType().getClass());

        //this is how to check if return type of method is void via reflection
        System.out.println("void: " + mainMethod.getReturnType().equals(Void.TYPE));
    }
}
