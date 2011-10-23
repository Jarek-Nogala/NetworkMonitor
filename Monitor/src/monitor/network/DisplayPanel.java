package monitor.network;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class extending JPanel for creating window to display cathcing packets,
 * destination file, and list of interfaces (beta)
 * @author Jarosław Nogała
 */
public class DisplayPanel extends JPanel {
    public DisplayPanel(){
        super();
        
        JLabel mJLabel = new JLabel("DISPLAY");
        this.add(mJLabel);
    }
}
