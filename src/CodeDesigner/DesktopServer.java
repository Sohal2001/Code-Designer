package CodeDesigner;

import javax.naming.*;
import java.rmi.*;
public class DesktopServer
{
    public static void main(String[] args)throws RemoteException,NamingException
    {
        Context cnt=new InitialContext();
        cnt.bind("rmi:desktop",new Desktop());
        System.out.println("Server is Ready");
    }
}

