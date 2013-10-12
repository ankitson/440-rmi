package examples;

/**
 * Created with IntelliJ IDEA.
 * User: ankit
 * Date: 10/12/13
 * Time: 12:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RemoteListInterface {
    public Boolean add(String elem);
    public String get(Integer index);
    public Integer size();
    public RemoteList merge(RemoteListInterface l1);
}
