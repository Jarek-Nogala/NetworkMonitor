package computer;


import computer.elevation.Elevation;
import computer.services.Net;
import computer.services.Sc;
import computer.services.ServiceObj;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class Computer {

    public static JTextArea mJTextArea = new JTextArea();
    public static JList mJList;
    
    public static void main(String[] args) {
        makeDialog();
        
        Sc mSc = new Sc();
        String str = "";
        for(ServiceObj i : mSc.getServicesList()){
            str += i.DISPLAY_NAME + "  :   " + i.SERVICE_NAME + "\n";
        }
        addText(str);
//        Vector names = mSc.getListOfServices();
//
//        String Effect = "";
//        Computer.addText("Run matafaka");
//        for(int i = 0; i < names.size(); i++){
//            Effect += Net.getInfoAboutService(9,(String) names.elementAt(i)) + '\n';
//        }
//        addText(Effect);
//        if(args.length >0 && args[0].equals("elevated")){
//            //do program
//            Net netObj = new Net();
//            Vector names = netObj.getNetStartList();
//
//            String Effect = "";
//            
//            Computer.addText("Run matafaka");
//            for(int i = 0; i < names.size(); i++){
//                Effect += Net.getInfoAboutService(9,(String) names.elementAt(i)) + '\n';
//            }
//            addText(Effect);
//        }
//        else{//do elevation
//            Elevation.getElevation();
//        }
        
        
    }
    
    
   public static void makeDialog(){
      JFrame frame = new JFrame("Show Message Dialog");
      
      SpringLayout mSpringLayout = new SpringLayout();
        frame.setLayout(mSpringLayout);
        frame.setSize(600, 600);
        
        mJList = new JList(new DefaultListModel());
        
        mJList.setEnabled(false);
        mJList.setForeground(new Color(200, 200, 200));
        mJList.setVisibleRowCount(10);
        
        JScrollPane mJScrollPane = new JScrollPane(mJList);
        mJScrollPane.setPreferredSize(  new Dimension(550, 500));
        frame.add(mJScrollPane);
        //mSpringLayout.putConstraint(SpringLayout.EAST, mTextField, 20,SpringLayout.EAST, mJCheckBox);
//        mSpringLayout.putConstraint(SpringLayout.NORTH, mJScrollPane, 30,SpringLayout.NORTH, mJButton);
        
      
//      frame.setadd(mJTextArea);
      
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void addText(String newText){
       String text = mJTextArea.getText() + "\r\n" + newText;
       mJTextArea.setText(text);
      DefaultListModel mListModel = (DefaultListModel) mJList.getModel();
      if(!newText.equals(""))
        mListModel.addElement(newText);
   }

    
            
    //        SystemInfo si = new SystemInfo();
    //        OperatingSytem os = si.getOperatingSystem();
    //        System.out.println(os);
    //        HardwareAbstractionLayer hal = si.getHardware();
    //        System.out.println(hal.getProcessors().length + " CPU(s):");
    //        for (Processor cpu : hal.getProcessors()) {
    //            System.out.println(" " + cpu);
    //        }
    //        System.out.println("Memory: "
    //                + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/"
    //                + FormatUtil.formatBytes(hal.getMemory().getTotal()));
            
            
            
            
            
    //    /* Total number of processors or cores available to the JVM */
    //    System.out.println("Available processors (cores): " + 
    //        Runtime.getRuntime().availableProcessors());
    //
    //    /* Total amount of free memory available to the JVM */
    //    System.out.println("Free memory (bytes): " + 
    //        Runtime.getRuntime().freeMemory());
    //
    //    /* This will return Long.MAX_VALUE if there is no preset limit */
    //    long maxMemory = Runtime.getRuntime().maxMemory();
    //    /* Maximum amount of memory the JVM will attempt to use */
    //    System.out.println("Maximum memory (bytes): " + 
    //        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
    //
    //    /* Total memory currently in use by the JVM */
    //    System.out.println("Total memory (bytes): " + 
    //        Runtime.getRuntime().totalMemory());
    //
    //    /* Get a list of all filesystem roots on this system */
    //    File[] roots = File.listRoots();
    //
    //    /* For each filesystem root, print some info */
    //    for (File root : roots) {
    //      System.out.println("File system root: " + root.getAbsolutePath());
    //      System.out.println("Total space (bytes): " + root.getTotalSpace());
    //      System.out.println("Free space (bytes): " + root.getFreeSpace());
    //      System.out.println("Usable space (bytes): " + root.getUsableSpace());
    //    }
    
}