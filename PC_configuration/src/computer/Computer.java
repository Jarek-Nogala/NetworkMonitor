package computer;


import computer.elevation.Elevation;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.hyperic.sigar.win32.Service;
import org.hyperic.sigar.win32.Win32Exception;

public class Computer {

    public static JTextArea mJTextArea = new JTextArea();
    public static JList mJList;
    
    private static Sigar sigar = new Sigar();
    private static SigarProxy sigarProxy = null;
    
    public static void main(String[] args){
        
        if(true){//args.length >0 && args[0].equals("elevated")){//
            //do program
            makeDialog();
            addText("1");
            sigarProxy  = SigarProxyCache.newInstance(new Sigar());
            addText("2");
            List ServicesNames = new ArrayList();
            try
            {   
                ServicesNames = Service.getServiceNames(sigar,null);
            }catch (Exception e){ 
                    addText("Exception: " + e.toString());
            }
            addText("3");
            for(Object i : ServicesNames){
//                addText("3.5");
                String data = i.toString() + " : ";
//                addText("3.7");
//                addText("Service name: " + data);
                try
                {  
                    Service mService = new Service(i.toString());
//                    addText("4");
                    if(mService.getStatus() == Service.SERVICE_RUNNING){
                        try {
                            addText(data + sigarProxy.getProcCpu(sigarProxy.getServicePid(i.toString())).toString());
                        } catch (SigarException ex) {
                            addText("Exception: " + ex.toString());
                        }
//                        addText("5");
                        for(String j : mService.getConfig().getDependencies()){
                            addText("- " + j);
                        }
                    }
                }catch (Win32Exception e){ 
                    addText("Exception: " + e.toString());
                }
            }
                //routing table
//                  for(NetRoute i :sigarProxy.getNetRouteList()){
//                      addText(i.toString());
//                  }
                  //Usage of processor
//                long id = 776;
//                
//                ProcCpu cpu = sigarProxy.getProcCpu(id);
//                addText("cpu=" + cpu.toString());s
//                while (true)
//                {
//                    cpu = sigarProxy.getProcCpu(id);
//                    addText("cpu=" + cpu.toString());
//
//                    try {
//                    Thread.sleep(2000);no 
//                    } 
//                    catch (InterruptedException ex) {}
//                }
        }
        else{//do elevation
            Elevation.getElevation();
        }
        
        
        
        
        /*
        
        
//        ProcessorInfo info = new ProcessorInfo();
//        Long first = info.getProcessCPUTime(624);
//        try {
//            Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//        }
//        
//        Long second = info.getProcessCPUTime(624);
        
        
        Sigar sigar = new Sigar();

        
  
        
        
        ProcCpu mProcCpu = new MultiProcCpu();
       
       
       
        mProcCpu.getPercent();
        try {
            ProcState procState = sigar.getProcState("624");
//            ProcCpu procCpu = sigar.getProcCpu("624"); //take too much time
            ProcStat mStat =  sigar.getProcStat();//statystyki procesora
//            ProcTime mProcTime = sigar.getProcTime("623");
            
            //ProcCpu procCpu = sigar.getProcCpu("624");
            
            addText(procState.getName() + ": " +
                           procState.getState());
            
//            addText("ProcCpu: " + procCpu.toString());
            addText("ProcStat: " + mStat.getRunning());
//            addText("ProcTime: " + sigar.getProcFd("623").getTotal());//tez sie zatrzymuje
            addText("ProcTime: " + sigar.getProcCred("623").getName());//tez sie zatrzymuje
            
            for(CpuPerc i : sigar.getCpuPercList())
                addText(i.toString());
                 
     //            JavaSysMon monitor =   new JavaSysMon();
     //            ProcessInfo[] processtab = monitor.processTable();
     //            for(ProcessInfo i : processtab){
     //                if(i.getName().toLowerCase().contains("unknow"))//onlu pid's from unknown processes
     //                    addText(String.valueOf(i.getPid()));
     //                
     //            }
     //            
     //            List list = monitor.processTree().children();
     //            
     //            for(int i =0; i<list.size() ;i++){
     //                addText(list.get(i).toString());
     //            }

     
        } catch (SigarException ex) {
        }
        
        
        */
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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void addText(String newText){
        String text = mJTextArea.getText() + "\r\n" + newText;
        mJTextArea.setText(text);
        DefaultListModel mListModel = (DefaultListModel) mJList.getModel();
        if(!newText.equals(""))
            mListModel.addElement(newText);
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
        }
   }
    
}