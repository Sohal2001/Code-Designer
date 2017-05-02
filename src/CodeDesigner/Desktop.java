package CodeDesigner;


import java.rmi.*;
import java.rmi.server.*;
import java.rmi.server.UnicastRemoteObject;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Desktop extends UnicastRemoteObject implements IDesktop
{
    public Desktop() throws RemoteException
    {

    }
    public byte[] getDesktop()
    {
        try
        {
            GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice screen=env.getDefaultScreenDevice();
            Robot robot=new Robot(screen);
            Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
            BufferedImage img=robot.createScreenCapture(new Rectangle(0,0,d.width,d.height));
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", bytes );
            bytes.flush();
            byte[] data = bytes.toByteArray();
            bytes.close();
            return data;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
