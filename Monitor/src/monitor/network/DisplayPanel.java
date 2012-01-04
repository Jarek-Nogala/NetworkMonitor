package monitor.network;

import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import monitor.Log;
import monitor.gui.Dimens;

/**
 * Class extending JPanel for creating window to display catching packets,
 * destination file, and list of interfaces (beta)
 * @author Jarosław Nogała
 */
public class DisplayPanel extends JPanel {
    
    private JButton mJButton;
    private JList mJList;
    
    public DisplayPanel(String what,BlockingQueue<String> mQueuePackets){
        super();
        
        SpringLayout mSpringLayout = new SpringLayout();
        this.setLayout(mSpringLayout);
        this.setBorder(new EmptyBorder(20, 20, 20, 20) );
        
        mJButton = new JButton("Start listening interface: " + what);
        this.add(mJButton);
        mJList = new JList(new DefaultListModel());
//        mJTextArea.setHorizontalAlignment(JTextArea.CENTER_ALIGNMENT);
//        mJTextArea.setPreferredSize(
//                  new Dimension(Dimens.mainWindowWidth - 40, 
//                                Dimens.displayPanelTextFieldHeight));
        mJList.setEnabled(false);
        mJList.setForeground(new Color(200, 200, 200));
        mJList.setVisibleRowCount(10);
        
        JScrollPane mJScrollPane = new JScrollPane(mJList);
        mJScrollPane.setPreferredSize(  new Dimension(Dimens.mainWindowWidth - 40, 
                                        Dimens.displayPanelTextFieldHeight));
        this.add(mJScrollPane);
        //mSpringLayout.putConstraint(SpringLayout.EAST, mTextField, 20,SpringLayout.EAST, mJCheckBox);
        mSpringLayout.putConstraint(SpringLayout.NORTH, mJScrollPane, 30,SpringLayout.NORTH, mJButton);
        
        if(mQueuePackets != null){
            QueueRead threadQueue = new QueueRead(mQueuePackets);
            threadQueue.start();
        }
    }
    
    public class QueueRead extends Thread{
        
        public BlockingQueue<String> mQueuePackets;
        
        public QueueRead(BlockingQueue<String> mQueuePackets){
            this.mQueuePackets = mQueuePackets;
        }
        
        @Override
        public void run(){
            while(true){
                String str = "";
                Log.i("Take from queue");
                try {
                    str = mQueuePackets.take();
                    if(!str.isEmpty()){
                        DefaultListModel mListModel = (DefaultListModel) mJList.getModel();//append(str + '\n');
                        mListModel.addElement(str);
                    }
                        
                } catch (InterruptedException ex) {
                    Log.i(this.getClass().getName(), "Problem with getting nest message from queue"); 
                }
            }
        }
    }
    
}
