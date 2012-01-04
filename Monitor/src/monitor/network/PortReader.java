/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import monitor.Log;

/**
 *
 * @author Właściciel
 */
public class PortReader implements PacketReceiver {
    
    BufferedWriter out;
    BlockingQueue<String> mQueuePackets;// JTextArea that is updated in display panel
    
    public PortReader(File mFile, BlockingQueue<String> mQueuePackets) {
        this.mQueuePackets = mQueuePackets;
        
        try{
            
            if(!mFile.exists())
                if(!mFile.createNewFile())
                    System.out.println("File create error");
            mFile.setWritable(true);
            
            this.out = new BufferedWriter(new FileWriter(mFile));
        }catch(IOException e){
            Log.i("PortReader","Error " + e.getLocalizedMessage());
        }
    }
    
    public synchronized void closePortReaderFile(){
        try {
            this.out.close();
            this.out = null;
        } catch (IOException ex) {
            Log.i("PortReader","PortReader close error: " + ex.getMessage());
        }
    }
    
    @Override
    public synchronized void receivePacket(Packet packet) {
        if(this.out != null)
            try {
                this.out.write(packet.toString() + "\n" );
            } catch (IOException ex) {
                Log.i("PortReader","Packet File write error: " + ex.getMessage());
//                try {
//                    this.out.close();
//                } catch (IOException ex1) {
//                    Log.i("PortReader","Packet File close error");
//                }
            }
        if(this.mQueuePackets != null){
            try {
                Log.i("PortReader","Put in queue: " + packet.toString());
                this.mQueuePackets.put(packet.toString());
                
                Log.i("PortReader","Put in queue: " + packet.datalink.toString());
                this.mQueuePackets.put(packet.datalink.toString());
                
                String message  = "";
                for(Byte character : packet.data)
                    message += (char) character.intValue();
                
                Log.i("PortReader","Put in queue: " + "BODY: " + message);
                this.mQueuePackets.put("BODY: " + message);
                
                message = "";
                for(Byte character : packet.header)
                    message += character;
                
                Log.i("PortReader","Put in queue: " + "HEAD:" + message);
                this.mQueuePackets.put("HEAD:" + message);
                
            } catch (InterruptedException ex) {
                Log.i("PortReader","Queue put error: " + ex.getMessage());
            }
        }
    }
}
