/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.threads;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import monitor.Log;
import monitor.network.PortReader;

/**
 *
 * @author Jarosław Nogała
 */
public class PortCaptorThread extends Thread {
    
    private NetworkInterface device;
    private JpcapCaptor captor = null;
    private PortReader mListener = null;
    
    private boolean flag = false;
    
    private String TAG = "PortCaptorThread";
    /**
     * Constructor
     * @param device
     *          it's the NetworkInterface from which packets will be sniffed
     */
    public PortCaptorThread(NetworkInterface device){
        this.device = device;
    }
    
    @Override
    public void run(){
        try {
            //creatine timestamp for name of file
            Calendar mCalendar = Calendar.getInstance();
            File mDir = new File("./Packets/");
            if(!mDir.exists())
                mDir.mkdir();
            File mFile = new File("./Packets/" + mCalendar.getTimeInMillis() + "_" +  device.name);
           
            //using interface to listen for packets
            this.captor = JpcapCaptor.openDevice(device, 65535, false, 20);
            this.captor.setPacketReadTimeout(100);
            this.mListener =  new PortReader(mFile);
            this.captor.loopPacket(-1,this.mListener);
        } catch (IOException ex) {
            if(this.captor != null)
                this.captor.close();
        } finally {
            //don't do anything, no needed
        }
        
        Log.i(TAG,"Thread end");
        if(this.captor != null)
            this.captor.close();
    }
    
    @Override
    public synchronized void interrupt(){
        Log.i("PortCaptorThread", "Listening thread should die.");
        this.captor.breakLoop();
        try {
            this.sleep(500);
        } catch (InterruptedException ex) {}
        this.captor.close();
        //this.mListener.closePortReaderFile();
        super.interrupt();
    }
}
