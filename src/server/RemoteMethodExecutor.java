package server;

import messages.RMIMessageMethodInvocation;

import java.net.Socket;

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

    public RemoteMethodExecutor(Socket socket, T object, RMIMessageMethodInvocation message) {
        this.socket = socket;
        this.object = object;
    }

    public void run() {
        Method method =
    }
}
