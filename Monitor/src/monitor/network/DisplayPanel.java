package monitor.network;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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
    private JTextPane mJTextPane;
    private DefaultListModel mListModel;
    
    private boolean isListening;
    private PortListener mPortListener;
    
    private QueueRead threadQueue;
    private BlockingQueue<String> mQueuePackets;
    
    public DisplayPanel(final String what,BlockingQueue<String> QueuePackets){
        super();
        
        this.mQueuePackets = QueuePackets;
        mPortListener = new PortListener(mQueuePackets);
        
        
        SpringLayout mSpringLayout = new SpringLayout();
        this.setLayout(mSpringLayout);
        this.setBorder(new EmptyBorder(20, 20, 20, 20) );
        
        mJButton = new JButton("Start listening interface: " + what);
        isListening = false;
        mJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isListening){
                    Log.i("Click to start listening packets from interface");
                    mPortListener.listenInterface(what);
                    mJButton.setText("Stop listening interface: " + what);
                    
                    if(mQueuePackets != null){
                        threadQueue = new QueueRead(mQueuePackets);
                        threadQueue.start();
                    }
                } else {
                    Log.i("Click to stop listening packets from interface");
                    mPortListener.killCaptorThreads();
                    mJButton.setText("Start listening interface: " + what);
                }
                isListening = !isListening; 
            }
        });
        
        this.add(mJButton);
        mJList = new JList(new DefaultListModel());
        mJList.setLayoutOrientation((int)CENTER_ALIGNMENT);
        mJList.setEnabled(false);
        mJList.setForeground(new Color(200, 200, 200));
        mJList.setVisibleRowCount(10);
        
        mListModel = (DefaultListModel) mJList.getModel();
        
        JScrollPane mJScrollPane = new JScrollPane(mJList);
        mJScrollPane.setPreferredSize(  new Dimension(Dimens.mainWindowWidth - 40, 
                                        Dimens.displayPanelTextFieldHeight));
        this.add(mJScrollPane);
        
        //mSpringLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this, 30,SpringLayout.HORIZONTAL_CENTER, mJList);
        mSpringLayout.putConstraint(SpringLayout.NORTH, mJScrollPane, 30,SpringLayout.NORTH, mJButton);
        mSpringLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mJButton, 30,SpringLayout.HORIZONTAL_CENTER, mJScrollPane);
    }

    @Override
    protected void finalize() throws Throwable {
        threadQueue.interrupt();
        while(!mQueuePackets.isEmpty()){
            mQueuePackets.remove();
        }
        super.finalize();
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
                        mListModel.addElement(str);
                    }
                        
                } catch (InterruptedException ex) {
                    Log.i(this.getClass().getName(), "Problem with getting nest message from queue"); 
                }
            }
        }
    }
    
    
}
