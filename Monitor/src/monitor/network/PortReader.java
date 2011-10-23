/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import monitor.Log;

/**
 *
 * @author Właściciel
 */
public class PortReader implements PacketReceiver {
    
    BufferedWriter out;
    
    public PortReader(File mFile) {
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
            Log.i("PortReader","PortReader close error ");
        }
    }
    
    @Override
    public synchronized void receivePacket(Packet packet) {
        if(this.out != null)
            try {
                this.out.write(packet.toString() + "\n" );
            } catch (IOException ex) {
                Log.i("PortReader","Packet File write error");
                try {
                    this.out.close();
                } catch (IOException ex1) {
                    Log.i("PortReader","Packet File close error");
                }
            }
    }
}
