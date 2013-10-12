package registry;

import messages.RMIMessageGetStub;
import messages.RMIMessenger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteObjectReference implements Serializable {
    String key;
    String hostName;
    int port;
    String riName;
    //RMIMessenger serverMessenger;

    private static final String CLASS_PATH = "./tmp/downloadedInterfaces/";

    public RemoteObjectReference(String key, String hostName, int port, String riName) throws IOException {
        this.key = key;
        this.hostName = hostName;
        this.port = port;
        this.riName = riName;
        //this.serverMessenger = new RMIMessenger(hostName, port);
        //System.out.println("server messenger, ROR constructor: " + serverMessenger);
    }

    //TODO: create object dynamically from class file downloaded from server wtf
    public Object getStub() throws IOException {
        RMIMessageGetStub message = new RMIMessageGetStub(key);
        //serverMessenger.sendMessage(message);

        String stubClassFileName = CLASS_PATH + riName +"_stub.class";
        //File stubClassFile = serverMessenger.receiveFile(stubClassFileName);


        return new Object();
        //ClassLoader classLoader = ClassLoader.
    }

    /*public RMIMessenger getServerMessenger() {
        return serverMessenger;
    }*/

    public String getKey() {
        return key;
    }

}
