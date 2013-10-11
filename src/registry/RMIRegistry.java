package registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/10/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMIRegistry {

    private Map<Integer, RemoteObjectReference> registeredRemoteObjects;

    public RMIRegistry() {
        registeredRemoteObjects = new HashMap<Integer, RemoteObjectReference>();
    }

    public RemoteObjectReference lookup(String className) {
        try {
            Class c = Class.forName(className);
            Object o = c.newInstance();
        } catch (ClassNotFoundException e) {

        }
    }

}
