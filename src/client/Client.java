package client;

import examples.RemoteHelloStub;
import examples.RemoteListStub;
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
        RemoteHelloStub rhs = new RemoteHelloStub(ror, client.messenger);
        try {
            List<Float> floats = new ArrayList<Float>();
            floats.add(3.14f);
            floats.add(2.15f);
            System.out.println("rhs hello: " + rhs.sayHello("a", "b", 2, floats));
            RemoteObjectReference newRHS = rhs.newHello("abc");
            RemoteHelloStub newRemoteHelloStub = new RemoteHelloStub(newRHS, client.messenger);
            System.out.println("new stub hello: " + newRemoteHelloStub.sayHello("a", "c", 3, floats));

            RMIMessageLookupRequest lookupNewHello = new RMIMessageLookupRequest("RemoteHello2");
            client.messenger.sendMessage(lookupNewHello);
            RemoteObjectReference ror2 = ((RMIMessageRemoteObject) client.messenger.receiveMessage()).getRemoteObjectReference();
            RemoteHelloStub rhs2 = new RemoteHelloStub(ror2, client.messenger);

            System.out.println("rhs2 hello: " + rhs2.sayHello("c", "d", 3, floats));

            RMIMessageLookupRequest list1lookup = new RMIMessageLookupRequest("RemoteList1");
            client.messenger.sendMessage(list1lookup);
            RemoteObjectReference list1ror = ((RMIMessageRemoteObject) client.messenger.receiveMessage()).getRemoteObjectReference();
            RemoteListStub list1 = new RemoteListStub(list1ror, client.messenger);

            RMIMessageLookupRequest list2lookup = new RMIMessageLookupRequest("RemoteList2");
            client.messenger.sendMessage(list2lookup);
            RemoteObjectReference list2ror = ((RMIMessageRemoteObject) client.messenger.receiveMessage()).getRemoteObjectReference();
            RemoteListStub list2 = new RemoteListStub(list2ror, client.messenger);

            list1.add("a");
            list1.add("b");

            list2.add("c");
            list2.add("d");
            list2.add("e");

            list1.merge(list2ror);
            System.out.print("list1 elements after merge: [");
            for (int i=0;i<list1.size();i++) {
                System.out.print(list1.get(i) + ",");
            }
            System.out.println("]");

        } catch (Exception e) {
            System.out.println("exception testing : " + e);
        }
    }
}
