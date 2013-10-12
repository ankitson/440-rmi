package messages;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 1:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMIMessenger implements Serializable {

    private String hostName;
    private int port;
    private Socket socket;
    public ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public RMIMessenger(String hostName, int port) throws UnknownHostException, IOException {
        this.hostName = hostName;
        this.port = port;
        socket = new Socket(hostName, port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public RMIMessenger(Socket socket) throws IOException {
        this.socket = socket;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(RMIMessage message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    public RMIMessage receiveMessage() throws IOException, ClassNotFoundException {
        return (RMIMessage) objectInputStream.readObject();

    }
}
