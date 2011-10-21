/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.network;

import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import monitor.threads.PortCaptorThread;

/**
 *
 * @author Właściciel
 */
public class PortListener {
    
    private NetworkInterface[] devices;
    private PortCaptorThread[] captor;
    
    public PortListener(){
        System.out.println("PortListener");
        devices = JpcapCaptor.getDeviceList();
        captor = new PortCaptorThread[devices.length];
        int i = 0;
        for( NetworkInterface device : devices){
            captor[i] = new PortCaptorThread(device);
            System.out.println("Start ");
            captor[i].start();
            i++;
        }
    }
    
    public void killCaptorThreads(){
        for(int i = 0; i < captor.length; i++){
            captor[i].interrupt();
            try {
                captor[i].join();
            } catch (InterruptedException ex) {
            }
        }
    }
            
}
