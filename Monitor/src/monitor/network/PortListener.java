package monitor.network;

import java.util.concurrent.BlockingQueue;
import javax.swing.JTextArea;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import monitor.Log;
import monitor.threads.PortCaptorThread;

/**
 *
 * @author Jarosław Nogała
 */
public class PortListener {
    
    private NetworkInterface[] devices;
    private PortCaptorThread[] captor;
    private BlockingQueue<String> mQueuePackets;

    private String TAG = "PortListener";
    
    public PortListener(BlockingQueue<String> mQueuePackets){
        devices = JpcapCaptor.getDeviceList();
        captor = new PortCaptorThread[devices.length];
        this.mQueuePackets = mQueuePackets;
        
        int i = 0;
        for( NetworkInterface device : devices){
            captor[i] = new PortCaptorThread(device,mQueuePackets);
            i++;
        }
    }
    
    /**
     * Method for starting listening thread on chosen network interface
     * @param i
     *          the number of interface you want to listen
     */
    public void listenInterface(int i){
        if(devices.length > i){
//            Log.i(TAG, "Start listening interface: " + devices[i].name);
            captor[i].start();
        }
        else throw new IllegalArgumentException("Wrong interface number: " + i + " for maximal number of " + devices.length );
    }
    
    /**
     * Method for starting listening thread on chosen network interface
     * @param name
     *          name of chosen network interface
     */
    public void listenInterface(String name){
        int i = 0;
        for(NetworkInterface device : devices){
            if(device.name.equals(name)){
//                Log.i(TAG, "Start listening interface: " + devices[i].name);
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
//            Log.i(TAG, "Start listening interface: " + device.name);
            captor[i].start();
            i++;
        }
    }
    
    /**
     * Method for killing listening threads
     */
    public synchronized void killCaptorThreads(){
        for(int i = 0; i < captor.length; i++){
//            Log.i(TAG,"interrupting listening threads");
            captor[i].interrupt();
            
        }
        int i = 0;
        for( NetworkInterface device : devices){
            captor[i] = new PortCaptorThread(device,mQueuePackets);
            i++;
        }
    }
}
