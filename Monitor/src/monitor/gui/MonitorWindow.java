/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Jarek
 */
public class MonitorWindow extends JFrame {
    private int windowDefaultWidth = 500;
    private int windowDefaultHeight = 300;
    
    private JPanel mJPanel;
    
    public MonitorWindow(){
        super("Network Monitor");
        MakeWindow();
    }
    
    public void MakeWindow(){
        
        mJPanel = new JPanel();
        JLabel mJLabel = new JLabel("Witam w programie wspomagającym tworzenie modelu symulacyjnego");
        mJPanel.add(mJLabel);
        
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        int windowDefaultX = (int) (dim.width - windowDefaultWidth)/2;
        int windowDefaultY = (int) (dim.height - windowDefaultHeight)/2;
        
        JMenuBar menuBar;
        JMenu menu, subMenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Menu");
        menuBar.add(menu);

        //Menu items
        
        //Menu of packets
        subMenu = new JMenu("Packets");
        menuItem = new JMenuItem("Display");
        subMenu.add(menuItem);
        menuItem = new JMenuItem("Save");
        subMenu.add(menuItem);
        menu.add(subMenu);

        //Menu of services
        subMenu = new JMenu("Services");
        menu.add(subMenu);

        menuItem = new JMenuItem("Model");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("Options");
        menuBar.add(menu);

        
        //defining frame for window
        this.setBounds(windowDefaultX, windowDefaultY, windowDefaultWidth, windowDefaultHeight);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setJMenuBar(menuBar);
        
        this.add(mJPanel);
    }
}
