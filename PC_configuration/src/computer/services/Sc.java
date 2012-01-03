/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.services;

import computer.Computer;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.util.Vector;

/**
 * Class wrapping sc command
 * @author Jarek
 */
public class Sc {
    
    //define commands
    //define regex for output
    //define function for execution and getting output
    private final static String commandNetStart = "NET START %s";
    private Vector<ServiceObj> servicesList;
    
    //nazwy danych
    public String dataName1 = "SERVICE_NAME";
    public String dataName2 = "DISPLAY_NAME";
    public String dataName3 = "TYPE_VALUE";
    public String dataName4 = "TYPE_NAME";
    public String dataName5 = "STATE_VALUE";
    public String dataName6 = "STATE_NAME";
    public String dataName7 = "STATE_LIST";
    
    public Vector<ServiceObj> getServicesList(){
        return servicesList;
    }
    
    public Sc(){
        servicesList = createListOfServices();
    }
    
    private Vector<ServiceObj> createListOfServices(){
        Vector<ServiceObj> result = new Vector<ServiceObj>();
        
        String commandOutput = runScCommand();
        Vector<String> separateServicesOutput = getListOfServices(commandOutput);
        
        //TODO fill result using method getServiceObj
        for(String serviceOutput : separateServicesOutput){
            result.add(getServiceObj(serviceOutput));
        }
        return result;
    }
    
    private String runScCommand(){
        String output = "";
        int nextValue;
        try {
            //get String returned by console command
            String commandToRun = "sc query"; //default are working
            Process mProcess = Runtime.getRuntime().exec(commandToRun);//empty type command
            InputStream mInput = mProcess.getInputStream();
            
            while((nextValue = mInput.read()) != -1 && mInput.available() != 0){
                output += (char)nextValue;
            }
        } catch (IOException ex) {
            Computer.addText("ERROR in Net class: " + ex.getMessage());
        }
        return output;
    }
    
    private ServiceObj getServiceObj(String service) {
        String SERVICE_NAME = "";
        String DISPLAY_NAME = "";
        int TYPE_VALUE = -1;
        String TYPE_NAME = "";
        int STATE_VALUE = -1;
        String STATE_NAME = "";
        Vector<String> STATE_LIST = new Vector<String>();
    
        //TODO parse service variable to get data!
        //1. split lines
        int lineNumber = 0;
        for(String line : service.split("\n")){
            switch(lineNumber){
                case 0 : 
                    SERVICE_NAME = line.replaceAll(".*:", ""); 
                    break;
                case 1 : 
                    DISPLAY_NAME = line.replaceAll(".*:", "");
                    break;
                case 2 : 
//                    String 
//                    TYPE_VALUE = 
//                    TYPE_NAME = line.replaceAll(".*:", "");
                    break;
                case 3 : 
//                    STATE_VALUE; 
//                    STATE_NAME;
//                    STATE_LISTbreak;
                    break;         
            }
            
            lineNumber++;
        }
        
        
        return new ServiceObj(SERVICE_NAME, DISPLAY_NAME, TYPE_VALUE, TYPE_NAME, STATE_VALUE, STATE_NAME, STATE_LIST);
    }
    
    private Vector<String> getListOfServices(String output){
        Vector<String> result = new Vector<String>();
        
        //TODO cut command output into list of separate services
        for(String element : output.split("SERVICE_NAME")){
            result.addElement(element);
        }
        
        return result;
    }
}
