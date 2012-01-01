/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.services;

import java.util.Vector;

/**
 *
 * @author Jarek
 */
public class ServiceObj {
    
    public String SERVICE_NAME;
    public String DISPLAY_NAME;
    public int TYPE_VALUE;
    public String TYPE_NAME;
    public int STATE_VALUE;
    public String STATE_NAME;
    public Vector<String> STATE_LIST;
    
    public ServiceObj(  String SERVICE_NAME,
                        String DISPLAY_NAME,
                        int TYPE_VALUE,
                        String TYPE_NAME,
                        int STATE_VALUE,
                        String STATE_NAME,
                        Vector<String> STATE_LIST) {
     this.SERVICE_NAME = SERVICE_NAME;
     this.DISPLAY_NAME = DISPLAY_NAME;
     this.TYPE_VALUE = TYPE_VALUE;
     this.TYPE_NAME = TYPE_NAME;
     this.STATE_VALUE = STATE_VALUE;
     this.STATE_NAME = STATE_NAME;
     this.STATE_LIST = STATE_LIST;
    }
    
}
