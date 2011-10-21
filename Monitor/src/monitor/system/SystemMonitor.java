/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.system;

import java.util.Properties;

/**
 *
 * @author Właściciel
 */
public class SystemMonitor {
    
    public String systemBitValue;
    public String systemName;
    public String systemVersion;
    
    public SystemMonitor(String systemBitValue, String systemName, String systemVersion){
        this.systemBitValue = systemBitValue;
        this.systemName = systemName;
        this.systemVersion = systemVersion;
    }
    
    public static SystemMonitor getSystem(){
        Properties mProperties = System.getProperties();
        return new SystemMonitor(mProperties.getProperty("os.arch"),
                             mProperties.getProperty("os.name"),
                             mProperties.getProperty("os.version"));
    }
            
}
