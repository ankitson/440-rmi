package examples;

import server.Remote440;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * This is used to test simple method invocation (with complex types like List),
 * and to test method invocation where the return type is a remote type.
 */
public class RemoteHello extends Remote440 implements Serializable {

    private String message;

    public RemoteHello(String message) {
        this.message = message;
    }

    public String sayHello(String message2, String message3, Integer num, List<Float> floats) {
        return message + message2 + message3 + num + floats.toString();
    }

    public RemoteHello newHello(String message2) {
        return new RemoteHello(message2);
    }

}
