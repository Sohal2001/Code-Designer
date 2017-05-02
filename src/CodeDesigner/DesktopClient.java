package CodeDesigner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SOHAL
 */
import java.awt.Graphics;
import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.swing.*;
import javax.imageio.*;

public class DesktopClient extends JFrame
{
//*************************************************************
    class ImageThreader extends Thread
    {
        public void run()
        {
            while(true)
            {
                try
                {
                    byte[] data = obj.getDesktop();
                    InputStream in = new ByteArrayInputStream(data);
                    BufferedImage img = ImageIO.read(in);
                    setIcon(img);
                   // Thread.sleep(100);
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
    }

//**************************************************************
    private JLabel lbl;
    private IDesktop obj;
    public DesktopClient()throws RemoteException,NamingException,IOException, SQLException
    {
        CodeDesigner cd = new CodeDesigner();
        this.setSize(1000, 600);
        lbl=new JLabel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(((ImageIcon)getIcon()).getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        cd.jInternalFrame6.add(lbl);
        InitialContext cnt=new InitialContext();
        obj=(IDesktop)cnt.lookup("rmi://192.168.0.1/desktop");
        //replace compaq510 with the name or IP Address of the computer running the server.
        ImageThreader th=new ImageThreader();
        th.start();
    }
    public void setIcon(BufferedImage img)
    {
        lbl.paintComponents(null);
        lbl.setIcon(new ImageIcon(img));
    }
    public static void main(String[] args)throws RemoteException,NamingException,IOException
    {
        try {
            DesktopClient f=new DesktopClient();
            f.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DesktopClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

