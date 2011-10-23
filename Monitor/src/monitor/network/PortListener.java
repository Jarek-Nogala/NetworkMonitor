/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.network;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import monitor.Log;
import monitor.threads.PortCaptorThread;

/**
 *
 * @author Właściciel
 */
public class PortListener {
    
    private NetworkInterface[] devices;
    private PortCaptorThread[] captor;
    
    private String TAG = "PortListener";
    
    public PortListener(){
        devices = JpcapCaptor.getDeviceList();
        captor = new PortCaptorThread[devices.length];
        int i = 0;
        for( NetworkInterface device : devices){
            captor[i] = new PortCaptorThread(device);
            i++;
        }
    }
    
    /**
     * Method for starting listening thread on choosen network interface
     * @param i
     *          the number of interface you want to listen
     */
    public void listenInterface(int i){
        if(devices.length > i){
            Log.i(TAG, "Start listening interface: " + devices[i].name);
            captor[i].start();
        }
        else throw new IllegalArgumentException("Wrong interface number: " + i + " for maximal number of " + devices.length );
    }
    
    /**
     * Method for starting listening thread on choosen network interface
     * @param name
     *          name of choosen network interface
     */
    public void listenInterface(String name){
        int i = 0;
        for(NetworkInterface device : devices){
            if(device.name.equals(name)){
                Log.i(TAG, "Start listening interface: " + devices[i].name);
                captor[i].start();
                return;
            }
            i++;
        }
        throw new IllegalArgumentException("Wrong interface name: " + name);
    }
    
    public void startAll(){
        int i = 0;
        for(NetworkInterface device : devices){
            Log.i(TAG, "Start listening interface: " + device.name);
            captor[i].start();
            i++;
        }
    }
    
    /**
     * Method for killing listening threads
     */
    public synchronized void killCaptorThreads(){
        for(int i = 0; i < captor.length; i++){
            Log.i(TAG,"interrupting listening threads");
            captor[i].interrupt();
        }
    }
}
