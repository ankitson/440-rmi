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
public class RMIMessenger {

    private String hostName;
    private int port;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private BufferedInputStream inputStream;

    public RMIMessenger(String hostName, int port) throws UnknownHostException, IOException {
        this.hostName = hostName;
        this.port = port;
        socket = new Socket(hostName, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new BufferedInputStream(socket.getInputStream());
    }

    public void sendMessage(RMIMessage message) throws IOException {
        outputStream.writeObject(message);
    }

    public File receiveFile(String filePath) throws IOException {
        FileOutputStream fis = new FileOutputStream(filePath);
        int c;
        while ( (c = inputStream.read()) != -1) {
            fis.write(c);
        }
        fis.close();
        return new File(filePath);
    }
}
