/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.computer;

/**
 *
 * @author Jarek
 */
public class WindowsServices {
    public native void getServices();
      static 
      {
        System.loadLibrary("WindowsServices"); 
      }
}
