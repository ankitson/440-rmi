package registry;

import util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/10/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */


public class RMIRegistry {

    private ConcurrentHashMap<String, Pair<Object, RemoteObjectReference>> registeredObjects;
    public ConcurrentHashMap<String, Pair<Object, RemoteObjectReference>> registeredObjectsInternal;

    public RMIRegistry() {
        registeredObjects = new ConcurrentHashMap<String, Pair<Object, RemoteObjectReference>>();
        registeredObjectsInternal = new ConcurrentHashMap<String, Pair<Object, RemoteObjectReference>>();
    }

    public Pair<Object,RemoteObjectReference> clientLookup(String className) {
        Pair<Object, RemoteObjectReference> objs = registeredObjects.get(className);
        return objs;
    }

    public Pair<Object,RemoteObjectReference> internalLookup(String className) {
        Pair<Object, RemoteObjectReference> objs = registeredObjectsInternal.get(className);
        return objs;
    }

    /**
     * If a key is already in use, it cannot be overwritten to avoid making
     * clients RemoteObjectReferences becoming stale.
     *
     */
    public boolean bind(Object obj, String key) throws IOException {
        if (registeredObjects.containsKey(key)) {
            return false;
        }

        Class intfc = obj.getClass().getInterfaces()[0];
        RemoteObjectReference remoteObj = new RemoteObjectReference(key, "localhost", 6666, intfc.toString());
        registeredObjects.put(key, new Pair(obj, remoteObj));
        registeredObjectsInternal.put(key, new Pair(obj, remoteObj));
        return true;
    }

    public Set<String> list() {
        return registeredObjects.keySet();
    }

}
