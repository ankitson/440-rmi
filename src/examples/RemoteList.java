package examples;

import server.Remote440;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/12/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteList extends Remote440 implements RemoteListInterface, Serializable {
    List<String> list;

    public RemoteList() {
        list = new ArrayList<String>();
    }

    public RemoteList(List<String> list) {
        this.list = list;
    }

    public Boolean add(String elem) {
        return list.add(elem);
    }

    public String get(Integer index) {
        return list.get(index);
    }

    public Integer size() {
        return list.size();
    }

    public RemoteList merge(RemoteListInterface l1) {
        for (int i=0;i<l1.size();i++) {
            list.add(l1.get(i));
        }
        return new RemoteList(list);
    }
}
