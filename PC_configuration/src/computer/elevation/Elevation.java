/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.elevation;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Jarek
 */
public class Elevation {
    
    private static final String UAC_EXAMPLE_EXE = "./Elevation/Elevate.exe";
    
    /**
     * method returning string for source of elevation file
     * @return 
     */
    public static void getElevation(){
        File uacExample = new File(UAC_EXAMPLE_EXE);
        File thisApp = new File(".");
        String uacPath = uacExample.getAbsolutePath();
        try {
            Runtime.getRuntime().exec(uacPath + " -k java " + thisApp + " \"elevated\"");//uacExample.getAbsolutePath() + " ls");
        } catch (IOException ex) {
            System.out.println("ERROR in Elevation class: " + ex.getMessage());
        }
          
    }
    
}
