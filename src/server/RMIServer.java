package server;

import messages.RMIMessenger;
import registry.RMIRegistry;

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
            } catch (IOException e) {
                System.err.println("Unable to accept connection");
            }
        }
    }
}
