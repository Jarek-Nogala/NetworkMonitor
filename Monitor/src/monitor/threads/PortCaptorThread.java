/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.threads;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import monitor.network.PortReader;

/**
 *
 * @author Właściciel
 */
public class PortCaptorThread extends Thread {
    
    private NetworkInterface device;
    private JpcapCaptor captor = null;
    
    private PortReader mListener = null;
    
    public PortCaptorThread(NetworkInterface device){
        this.device = device;
    }
    
    @Override
    public void run(){
        
        Pattern patter = Pattern.compile("_");
        String[] mDeviceName = patter.split(device.name);
        try {
            //creatine timestamp for name of file
            Calendar mCalendar = Calendar.getInstance();
            File mDir = new File("./Packets/");
            if(!mDir.exists())
                mDir.mkdir();
            File mFile = new File("./Packets/" + mCalendar.getTimeInMillis() + "_" +  mDeviceName[1]);
           
            System.out.println("File path : " + mFile.getAbsolutePath()); 
            this.captor = JpcapCaptor.openDevice(device, 65535, false, 20);
            this.mListener =  new PortReader(mFile);
//            captor.processPacket(-1, new PortReader()); 
            this.captor.loopPacket(-1,this.mListener);
        } catch (IOException ex) {
            this.captor.close();
        }
        System.out.println("Thread end");
        this.captor.close();
    }
    
    @Override
    public void interrupt(){
        this.mListener.closePortReaderFile();
        this.captor.breakLoop();
        this.captor.close();
        super.interrupt();
    }
}
