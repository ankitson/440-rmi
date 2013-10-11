package registry;

import util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/10/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */

/*
 *
 *
 */
public class RMIRegistry {

    private Map<String, Pair<Object, RemoteObjectReference>> registeredObjects;

    public RMIRegistry() {
        registeredObjects = new HashMap<String, Pair<Object, RemoteObjectReference>>();
    }

    public RemoteObjectReference lookup(String className) {
        Pair<Object, RemoteObjectReference> objs = registeredObjects.get(className);
        if (objs != null) {
            return objs.getSecond();
        }
        return null;
    }

    /**
     * If a key is already in use, it cannot be overwritten to avoid making
     * clients RemoteObjectReferences becoming stale.
     *
     */
    public boolean bind(Object obj, String key) {
        if (registeredObjects.containsKey(key)) {
            return false;
        }
        RemoteObjectReference remoteObj = new RemoteObjectReference(obj);
        registeredObjects.put(key, new Pair(obj, remoteObj));
        return true;
    }

    public Set<String> list() {
        return registeredObjects.keySet();
    }

}
