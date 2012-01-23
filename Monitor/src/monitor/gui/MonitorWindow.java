/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import monitor.network.DisplayPanel;

/**
 *
 * @author Jarek
 */
public class MonitorWindow extends JFrame {
    
    private JPanel mJPanel;
    
    //frame elements
    //menu bar
    private JMenuBar menuBar;
    //menu
    private JMenu mMenu;
    private JMenu mMenuPackets;
    private JMenu mMenuPacketsInterfaces;
//    private JMenu;
//    private JMenu;
//    private JMenu;
    
    public BlockingQueue<String> mQueuePackets;//queue for communication between threads
    
    
    
    public MonitorWindow(BlockingQueue<String> mQueuePackets){
        super("Network Monitor");
        
        this.mQueuePackets = mQueuePackets;
        
        mJPanel = new JPanel();
        JLabel mJLabel = new JLabel("Witam w programie wspomagającym tworzenie modelu symulacyjnego");
        mJPanel.add(mJLabel);
        
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        int windowDefaultX = (int) (dim.width - Dimens.mainWindowWidth)/2;
        int windowDefaultY = (int) (dim.height - Dimens.mainWindowHeight)/2;
        
        
        JMenu menu, subMenu, subSubMenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        mMenu = new JMenu("Menu");
        menuBar.add(mMenu);

        //Menu items
        //Menu of packets
        mMenuPackets = new JMenu("Packets");
        mMenuPacketsInterfaces = new JMenu("Display");
        updateInterfacesList(null);
        
        
        mMenuPackets.add(mMenuPacketsInterfaces);
        menuItem = new JMenuItem("Model");
        menuItem.addActionListener(new MenuActionListener(new DisplayPanel("model",mQueuePackets)));
        mMenuPackets.add(menuItem);
        menuItem = new JMenuItem("Save");
        mMenuPackets.add(menuItem);
        mMenu.add(mMenuPackets);

        //Menu of services
        subMenu = new JMenu("Services");
        mMenu.add(subMenu);

        menuItem = new JMenuItem("Model");
        mMenu.add(menuItem);

        mMenu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mMenu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("Options");
        menuBar.add(menu);
        
        //defining frame for window
        this.setBounds(windowDefaultX, windowDefaultY, Dimens.mainWindowWidth, Dimens.mainWindowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//HIDE_ON_CLOSE
        this.setJMenuBar(menuBar);
        
        this.add(mJPanel);
    }
    
    public void updateInterfacesList(String[] list){
        if(list != null){
            for(final String name : list){
                JMenuItem menuItem = new JMenuItem(name);
                menuItem.addActionListener(new MenuActionListener(new DisplayPanel(name,mQueuePackets)));//set class for this special panel
                mMenuPacketsInterfaces.add(menuItem);
            }
            
        }
    }
    
    /**
     * Private class for changing panels in application frame window
     */
    private class MenuActionListener implements ActionListener{

        private JPanel mJPanel;
        
        public MenuActionListener(JPanel mJPanel){
//            if(mJPanel != null)
//                this.mJPanel.removeAll();
            this.mJPanel = mJPanel;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(this.mJPanel);
        }
        
    }
    
    /**
     * Method for changing panels in frame
     * @param mJPanel
     */
    private void changePanel(JPanel mJPanel){
        this.remove(this.mJPanel);
        this.add(mJPanel);
        this.mJPanel = mJPanel;
        
        this.invalidate();        
        this.validate();
    }
    
    public JPanel getJPanel(){
        return this.mJPanel;
    }
}
