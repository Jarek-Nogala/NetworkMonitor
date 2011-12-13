/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Class for windows command for getting windows services
 * @author Jarek
 */
public class Net {
    
    private Vector<String> listOfService;
    
    public Net(){
        listOfService = createListOfServices();
    }
    
    /**
     * method return Vector<String> with names of Windows services
     * @return 
     */
    public Vector<String> getServicesList(){
        return listOfService;
    }
    
    private Vector<String> createListOfServices(){
        
        String output = "";
        int nextValue;
        try {
            //get String returned by console command
            Process mProcess = Runtime.getRuntime().exec("net config");
            InputStream mInput = mProcess.getInputStream();
            
            while((nextValue = mInput.read()) != -1 && mInput.available() != 0){
                output += (char)nextValue;
            }
        } catch (IOException ex) {
            System.out.println("ERROR in Net class: " + ex.getMessage());
        }
        
        return parseNetConfig(output);
    }
    
    private Vector<String> parseNetConfig(String output){
        Vector<String> result = new Vector();
        Vector<String> temp = new Vector();
        String patternGetLine = "[\r\n]+";//get each line
        String patternSpaceLessString = "\\s+";
        
        for(String element : output.split(patternGetLine)){
            temp.addElement(element);
        }
        
        for(String element : temp){
            String[] oneLine = element.split(patternSpaceLessString);
            if(oneLine.length < 4)
                result.addElement(oneLine[1]);
        }
        
        return result;
    }
    
    /**
     * method returns string with informations about given service
     * @param service
     *      name of service
     * @return 
     */
    public static String getInfoAboutService(String service){
        String output = "";
        int nextValue;
        try {
            //get String returned by console command
            Process mProcess = Runtime.getRuntime().exec("net config " + service);
            InputStream mInput = mProcess.getInputStream();
            
            while((nextValue = mInput.read()) != -1 && mInput.available() != 0){
                output += (char)nextValue;
            }
        } catch (IOException ex) {
            System.out.println("ERROR in Net class: " + ex.getMessage());
        }
        
        return output;
    }
}
