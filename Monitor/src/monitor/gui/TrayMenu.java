/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuComponent;
import java.awt.MenuContainer;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * Class created for handling system tray as element of gui of this app
 * @author Jarosla Nogala
 */
public class TrayMenu {
    
    private SystemTray tray;
    
    private TrayIcon trayIcon;
    private PopupMenu mainMenu;
    private PopupMenu subMenuMonitor;
    
    
    private String imageUri = "Images/tray_icon_3.gif";
    private String trayIconName = "Monitor";
    private String trayExit = "Exit";
    private String trayElement2 = "Monitor";
    
    private JFrame frame;
    /**
     * Constructor for customized tray class
     */
    public TrayMenu(JFrame frame){
        this.frame = frame;
        if (SystemTray.isSupported()) {
            
            
            //declaring popupmenues
            mainMenu = trayMakeMenu("Menu");
            subMenuMonitor = trayMakeMenu(trayElement2);
            
            //Tray elements - main menu
            trayElement(exitListener,trayExit,mainMenu); 
            
            //Sub menu elements
            //Monitor
            trayElementSubMenu(mainMenu, subMenuMonitor, 0);
            trayElement(openMonitorListener,trayExit,subMenuMonitor); 
            //ANOTHER
            
            //Tray initialization
            tray = SystemTray.getSystemTray();
            try {
                trayIconConfigure();
            } catch (AWTException ex) {
                System.out.println("Error with creating TrayIcon");
            }
            
        } else {
            Throwable exeption = new Throwable("SystemTray is no supported");
            exeption.initCause(null);
        }
    }
    
    /**
     * method configuring tray icon, mainly setting icon image and listeners for it
     */
    private void trayIconConfigure() throws AWTException{
        Image image = Toolkit.getDefaultToolkit().getImage(imageUri);
        trayIcon = new TrayIcon(image, trayIconName, mainMenu);
        trayIcon.setImageAutoSize(true);
        
        tray.add(trayIcon);
        
        trayIcon.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    frame.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        trayIcon.setPopupMenu(mainMenu);
    }
    
    private PopupMenu trayMakeMenu(String name){
        PopupMenu menu = new PopupMenu(name);
        //TODO add more options if needed
        return menu;
    }
    
    private MenuItem trayElement(ActionListener action, String name,PopupMenu toElement){
        
        MenuItem defaultItem = new MenuItem(name);
        defaultItem.addActionListener(action);
        toElement.add(defaultItem);
        return defaultItem;
    }
    
    private void trayElementSubMenu(PopupMenu main, PopupMenu sub, int indeks){
        main.insert(sub, indeks);
    }
    
    //LISTENERS
    private ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    System.exit(0);
                }
            };
    private ActionListener openMonitorListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
//                    TestNetwork x = new TestNetwork();
                }
            };
}
