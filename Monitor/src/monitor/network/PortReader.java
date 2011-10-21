/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;

/**
 *
 * @author Właściciel
 */
public class PortReader implements PacketReceiver {
    
    BufferedWriter out;
    
    public PortReader(File mFile) {
        System.out.println("PortReader" );
        try{
            
            if(!mFile.exists())
                if(!mFile.createNewFile())
                    System.out.println("File create error");
            mFile.setWritable(true);
            
            this.out = new BufferedWriter(new FileWriter(mFile));
        }catch(IOException e){
            System.out.println("PortReader error " + e.getLocalizedMessage());
        }
    }
    
    public void closePortReaderFile(){
        synchronized(this.out){
            try {
                this.out.close();
                this.out = null;
            } catch (IOException ex) {
                System.out.println("PortReader close error ");
            }
            
        }
    }
    
    @Override
    public void receivePacket(Packet packet) {
        synchronized(this.out){
            if(this.out != null)
                try {
                    this.out.write(packet.toString() + "\n\n" );
                    System.out.println(packet.toString());
                } catch (IOException ex) {
                    System.out.println("Packet File write error");
                    try {
                        this.out.close();
                    } catch (IOException ex1) {
                        System.out.println("Packet File close error");
                    }
                }
        }
    }
}
