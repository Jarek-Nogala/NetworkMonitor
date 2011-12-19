/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.services;

import computer.Computer;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Class for windows command for getting windows services
 * list of commands:
 * 0 - NET CONFIG
 * 1 - NET ACCOUNTS
 * 2 - NET FILE 
 * 3 - NET LOCALGROUP
 * 4 - NET SESSION 
 * 5 - NET SHARE
 * 6 - NET STATISTICS
 * 7 - NET USE
 * 8 - NET USER
 * 9 - NET START 
 * @author Jarek
 */
public class Net {
    
    private final static String commandNetStart       = "NET START %s";
    private final static String commandNetConfig      = "NET CONFIG %s";
    private final static String commandNetAccounts    = "NET ACCOUNTS %s";
    private final static String commandNetFile        = "NET FILE %s";
    private final static String commandNetLocalGroup  = "NET LOCALGROUP %s";
    private final static String commandNetSession     = "NET SESSION %s";
    private final static String commandNetShare       = "NET SHARE %s";
    private final static String commandNetStatistics  = "NET STATISTICS %s";
    private final static String commandNetUse         = "NET USE %s";
    private final static String commandNetUser        = "NET USER %s";
    
    
    
    private Vector<String> listNetStart;
    private Vector<String> listNetConfig;
    private Vector<String> listNetAccounts;
    private Vector<String> listNetFile;
    private Vector<String> listNetLocalGroup;
    private Vector<String> listNetSession;
    private Vector<String> listNetShare;
    private Vector<String> listNetStatistics;
    private Vector<String> listNetUse;
    private Vector<String> listNetUser;
    
    
    public Net(){
        listNetStart = createListOfServices(9);
    }
    
    /**
     * method return Vector<String> with names of Windows services returned by NET START
     * @return 
     */
    public Vector<String> getNetStartList(){
        return listNetStart;
    }
    public Vector<String> getNetConfigList(){
        return listNetConfig;
    }
    public Vector<String> getNetAccountsList(){
        return listNetAccounts;
    }
    public Vector<String> getNetFileList(){
        return listNetFile;
    }
    public Vector<String> getNetLocalGroupList(){
        return listNetLocalGroup;
    }
    public Vector<String> getNetSessionList(){
        return listNetSession;
    }
    public Vector<String> getNetShareList(){
        return listNetShare;
    }
    public Vector<String> getNetStatisticsList(){
        return listNetStatistics;
    }
    public Vector<String> getNetUseList(){
        return listNetUse;
    }
    public Vector<String> getNetUserList(){
        return listNetUser;
    }
    
    private Vector<String> createListOfServices(int commnad){
        
        String output = "";
        int nextValue;
        try {
            //get String returned by console command
            String commandToRun = String.format(getCommand(commnad),"");
            Process mProcess = Runtime.getRuntime().exec(commandToRun);//empty type command
            InputStream mInput = mProcess.getInputStream();
            
            while((nextValue = mInput.read()) != -1 && mInput.available() != 0){
                output += (char)nextValue;
            }
        } catch (IOException ex) {
            Computer.addText("ERROR in Net class: " + ex.getMessage());
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
    public static String getInfoAboutService(int command, String argument){
        String output = "";
        int nextValue;
        try {
            //get String returned by console command
            String commandToRun = String.format(getCommand(command), argument);
            Computer.addText(commandToRun);
            Process mProcess = Runtime.getRuntime().exec(commandToRun);
            InputStream mInput = mProcess.getInputStream();
            
            while((nextValue = mInput.read()) != -1 && mInput.available() != 0){
                output += (char)nextValue;
            }
        } catch (IOException ex) {
            Computer.addText("ERROR in Net class: " + ex.getMessage());
            System.out.println("ERROR in Net class: " + ex.getMessage());
        }
        
        return output;
    }
    
    /**
     * method for getting string formatter commands
     * @param i
     * @return 
     */
    public static String getCommand(int i){
        switch(i){
            case 0 : return commandNetConfig;
            case 1 : return commandNetAccounts;
            case 2 : return commandNetFile;
            case 3 : return commandNetLocalGroup;
            case 4 : return commandNetSession;
            case 5 : return commandNetShare;
            case 6 : return commandNetStatistics;
            case 7 : return commandNetUse;
            case 8 : return commandNetUser;
            case 9 : return commandNetStart;
        }
        return "";
    }
}
