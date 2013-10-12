package examples;

import server.Remote440;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/11/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteHello extends Remote440 {

    private String message;

    public RemoteHello(String message) {
        this.message = message;
    }

    public String sayHello(String message2, String message3, int num) {
        return message + message2 + message3 + num;
    }
}
