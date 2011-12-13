package computer;


import computer.elevation.Elevation;
import computer.services.Net;
import java.io.File;
import java.util.Vector;

public class Computer {

    
    
    public static void main(String[] args) {
        
        if(args.length >0 && args[0].equals("elevated")){
            //do program
            Net netObj = new Net();
            Vector names = netObj.getServicesList();

            for(int i = 0; i < names.size(); i++){
                System.out.println(names.elementAt(i));
            }
        }
        else{//do elevation
            Elevation.getElevation();
        }
    }
    
            
    //        SystemInfo si = new SystemInfo();
    //        OperatingSytem os = si.getOperatingSystem();
    //        System.out.println(os);
    //        HardwareAbstractionLayer hal = si.getHardware();
    //        System.out.println(hal.getProcessors().length + " CPU(s):");
    //        for (Processor cpu : hal.getProcessors()) {
    //            System.out.println(" " + cpu);
    //        }
    //        System.out.println("Memory: "
    //                + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/"
    //                + FormatUtil.formatBytes(hal.getMemory().getTotal()));
            
            
            
            
            
    //    /* Total number of processors or cores available to the JVM */
    //    System.out.println("Available processors (cores): " + 
    //        Runtime.getRuntime().availableProcessors());
    //
    //    /* Total amount of free memory available to the JVM */
    //    System.out.println("Free memory (bytes): " + 
    //        Runtime.getRuntime().freeMemory());
    //
    //    /* This will return Long.MAX_VALUE if there is no preset limit */
    //    long maxMemory = Runtime.getRuntime().maxMemory();
    //    /* Maximum amount of memory the JVM will attempt to use */
    //    System.out.println("Maximum memory (bytes): " + 
    //        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
    //
    //    /* Total memory currently in use by the JVM */
    //    System.out.println("Total memory (bytes): " + 
    //        Runtime.getRuntime().totalMemory());
    //
    //    /* Get a list of all filesystem roots on this system */
    //    File[] roots = File.listRoots();
    //
    //    /* For each filesystem root, print some info */
    //    for (File root : roots) {
    //      System.out.println("File system root: " + root.getAbsolutePath());
    //      System.out.println("Total space (bytes): " + root.getTotalSpace());
    //      System.out.println("Free space (bytes): " + root.getFreeSpace());
    //      System.out.println("Usable space (bytes): " + root.getUsableSpace());
    //    }
    
}