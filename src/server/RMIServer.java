package server;

import examples.RemoteHello;
import examples.RemoteList;
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
    }

    public static void main(String[] args) throws IOException {
        RMIRegistry registry = new RMIRegistry();
        RemoteHello remoteHello = new RemoteHello("Hi from server");
        RemoteHello remoteHello2 = new RemoteHello("Hi from server 2");
        RemoteList rl1 = new RemoteList();
        RemoteList rl2 = new RemoteList();
        registry.bind(remoteHello, "RemoteHello");
        registry.bind(remoteHello2, "RemoteHello2");
        registry.bind(rl1, "RemoteList1");
        registry.bind(rl2, "RemoteList2");
        RMIServer server = new RMIServer(registry);
        server.listen();
    }
}
