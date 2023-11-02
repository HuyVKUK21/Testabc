package interfaces_object;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceDB<T> extends Remote {
    public ArrayList<T> selectAll() throws RemoteException;
    public int insert(T t)  throws RemoteException;
    public int update(T t)  throws RemoteException;
    public int delete(T t)  throws RemoteException;

}
