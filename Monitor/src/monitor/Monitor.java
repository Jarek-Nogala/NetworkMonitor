/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import monitor.gui.MonitorWindow;
import monitor.gui.TrayMenu;
import monitor.network.PortListener;
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
        mJFrame = new MonitorWindow();
        TrayMenu tray = new TrayMenu(mJFrame);
        mJFrame.setVisible(true);
        
        //Obtain the list of network interfaces
        //change this code - it should be run from menu!!
//        System.out.println("Sluchanie portow");
//        PortListener x = new PortListener();
//        x.startAll();
//        
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {}
//        
//       
//        x.killCaptorThreads();
    }
}