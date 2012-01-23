/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import monitor.gui.MonitorWindow;
import monitor.gui.TrayMenu;
import monitor.network.PortListener;
/**
 * Program designed to automatize process of creating simulation model of network
 * @author Jaroslaw Nogala
 */
public class Monitor{

    private static MonitorWindow mJFrame; //frame with main window of application
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //preparing queue for communication between threads
        BlockingQueue<String> mQueuePackets = new LinkedBlockingQueue<String>();
        
        
        
        //creating window of application
        //create system tray for application
        mJFrame = new MonitorWindow(mQueuePackets);
        TrayMenu tray = new TrayMenu(mJFrame);
        mJFrame.setVisible(true);
        
        //update list of network interfaces
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        String[] str = new String[devices.length];
        for(int i = 0; i < devices.length; i++){
            str[i] = devices[i].name;
        }
        mJFrame.updateInterfacesList(str);
        
        //Obtain the list of network interfaces
        //change this code - it should be run from menu!!
//        System.out.println("Sluchanie portow");
        
        
        
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {}
//        
//       
//        x.killCaptorThreads();
    }
}