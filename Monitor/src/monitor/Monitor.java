/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import monitor.gui.MonitorWindow;
import monitor.network.PortListener;
import monitor.gui.TrayMenu;
/**
 * Program designed to automatize process of creating simulation model of network
 * @author Jaroslaw Nogala
 */
public class Monitor {

    private static MonitorWindow mJFrame; //frame with main window of application
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creating window of application
        //create system tray for application
//        mJFrame = new MonitorWindow();
//        TrayMenu tray = new TrayMenu(mJFrame);
//        mJFrame.setVisible(true);
        
        //Obtain the list of network interfaces
        System.out.println("Sluchanie portow");
        PortListener x = new PortListener();
        
        
//        x.killCaptorThreads();
        
        
//        for each network interface
//        for (int i = 0; i < devices.length; i++) {
//            //print out its name and description
//            System.out.println(i + ": " + devices[i].name + "(" + devices[i].description + ")");
//
//            //print out its datalink name and description
//            System.out.println(" datalink: " + devices[i].datalink_name + "(" + devices[i].datalink_description + ")");
//
//            //print out its MAC address
//            System.out.print(" MAC address:");
//            for (byte b : devices[i].mac_address) {
//                System.out.print(Integer.toHexString(b & 0xff) + ":");
//            }
//            System.out.println();
//
//            //print out its IP address, subnet mask and broadcast address
//            for (NetworkInterfaceAddress a : devices[i].addresses) {
//                System.out.println(" address:" + a.address + " " + a.subnet + " " + a.broadcast);
//            }
//        }
//        JpcapCaptor captor = null;
//        try {
//            captor = JpcapCaptor.openDevice(device[0], 65535, false, 20);
//            
//            while(true){
//              //capture a single packet and print it out
//              if(captor.getPacket() != null)
//                System.out.println(captor.getPacket());
//            }
//            //captor.processPacket(10,new PacketPrinter());
//            //captor.close();
//        } catch (IOException ex) { captor.close(); }
    }
    
    
    
}
