package server;

import examples.RemoteHello;
import messages.*;
import registry.RMIRegistry;
import registry.RemoteObjectReference;
import util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Adi
 * Date: 10/11/13
 * Time: 5:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMIServer {

    public RMIRegistry rmiRegistry;
    public static ServerSocket listenSocket;
    private static final int LISTEN_PORT = 6666;

    public RMIServer(RMIRegistry rmiRegistry) {
        this.rmiRegistry = rmiRegistry;

    }

    public void listen() {
        try {
            listenSocket = new ServerSocket(LISTEN_PORT);
        } catch (IOException e) {
            System.err.println("Unable to listen on port " + LISTEN_PORT + ".Message: " + e);
        }

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = listenSocket.accept();
                RMIMessenger messenger = new RMIMessenger(clientSocket);
                RMIServerThread rst = new RMIServerThread(messenger, rmiRegistry);
                new Thread(rst).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = listenSocket.accept();
                RMIMessenger messenger = new RMIMessenger(clientSocket);
                RMIMessage received;
                try {
                    received = messenger.receiveMessage();
                } catch (ClassNotFoundException e) {
                    System.err.println("Illegal message received. Error: " + e);
                    continue;
                }
                if (received instanceof RMIMessageMethodInvocation) {
                    System.out.println("Received method invocation message");
                    RMIMessageMethodInvocation mmi = (RMIMessageMethodInvocation) received;
                    String objectName = mmi.getObjectName();
                    Object o = rmiRegistry.lookup(objectName).getFirst();
                    RemoteMethodEecutor rme = new RemoteMethodEecutor(clientSocket, o, mmi);
                    Thread methodRunner = new Thread(rme);
                    methodRunner.start();
                } else if (received instanceof RMIMessageLookupRequest) {
                    String className = ((RMIMessageLookupRequest) received).getClassName();
                    System.out.println("received lookup request for class: " + className);
                    RemoteObjectReference ror = rmiRegistry.lookup(className).getSecond();
                    RMIMessageRemoteObject mro = new RMIMessageRemoteObject(ror);
                    messenger.sendMessage(mro);
                }
            } catch (IOException e) {
                System.err.println("Unable to accept connection");
            }
        }*/
    }

    public static void main(String[] args) throws IOException {
        RMIRegistry registry = new RMIRegistry();
        RemoteHello remoteHello = new RemoteHello("Hi from server");
        registry.bind(remoteHello, "RemoteHello");
        RMIServer server = new RMIServer(registry);
        server.listen();
    }
}
