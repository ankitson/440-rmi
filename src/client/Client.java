package client;

import examples.RemoteHelloStub;
import messages.RMIMessageLookupRequest;
import messages.RMIMessageRemoteObject;
import messages.RMIMessenger;
import registry.RemoteObjectReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public RMIMessenger messenger;
    private String hostName;
    private int port;

    public Client(String hostName, int port) throws IOException {
        this.hostName = hostName;
        this.port = port;
        messenger = new RMIMessenger(hostName, port);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RMIMessageLookupRequest lookupRequest = new RMIMessageLookupRequest("RemoteHello");
        Client client = new Client("localhost", 6666);
        client.messenger.sendMessage(lookupRequest);
        RemoteObjectReference ror = ((RMIMessageRemoteObject) client.messenger.receiveMessage()).getRemoteObjectReference();
        System.out.println("ROR recvd: " + ror.getKey());
        RemoteHelloStub rhs = new RemoteHelloStub(ror, client.messenger);
        try {
            List<Float> floats = new ArrayList<Float>();
            floats.add(3.14f);
            floats.add(2.15f);
            System.out.println("rhs hello: " + rhs.sayHello("a", "b", 2, floats));
            RemoteObjectReference newRHS = rhs.newHello("abc");
            RemoteHelloStub newRemoteHelloStub = new RemoteHelloStub(newRHS, client.messenger);
            System.out.println("new stub hello: " + newRemoteHelloStub.sayHello("a", "c", 3, floats));
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }
}
