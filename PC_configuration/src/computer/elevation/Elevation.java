/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.elevation;

import computer.Computer;
import java.io.File;
import java.io.IOException;

/**
 *
 * it need to run: C:\Windows\system32\.\Elevation\Elevate.exe -k `javaC:\Windows\system32\.\PC_configuration.jar "elevated"`
 * E:\REPO\NetworkMonitor\PC_configuration\.\Elevation\Elevate.exe -k `javaE:\REPO\NetworkMonitor\PC_configuration\.\PC_configuration.jar "elevated"`
 * @author Jarek
 */
public class Elevation {
    
    private static final String UAC_EXAMPLE_EXE = "./Elevation/Elevate.exe";
    private static final String thisProgramName = "PC_configuration.jar";
    /**
     * method returning string for source of elevation file
     * @return 
     */
    public static void getElevation(){
        File uacExample = new File(UAC_EXAMPLE_EXE);
        File thisApp = new File(".");
        String thisAppPath = thisApp.getAbsolutePath();
        String uacPath = uacExample.getAbsolutePath();
        try {
            String command = uacPath + " -k `java" + thisAppPath + "\\" + thisProgramName + " \"elevated\"`";
            Computer.makeDialog(command);
            Runtime.getRuntime().exec(command);//uacExample.getAbsolutePath() + " ls");
        } catch (IOException ex) {
            System.out.println("ERROR in Elevation class: " + ex.getMessage());
        }
          
    }
    
}
