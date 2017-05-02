package CodeDesigner;

import java.rmi.Remote;
import java.rmi.RemoteException;
 public interface IDesktop extends Remote
{
    public byte[] getDesktop() throws RemoteException;
}