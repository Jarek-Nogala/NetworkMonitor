package monitor.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import monitor.Log;
import monitor.gui.Dimens;

/**
 * Class extending JPanel for creating window to display cathcing packets,
 * destination file, and list of interfaces (beta)
 * @author Jarosław Nogała
 */
public class DisplayPanel extends JPanel {
    
    private JCheckBox mJCheckBox;
    private JTextField mTextField;
    public DisplayPanel(String what){
        super();
        
        SpringLayout mSpringLayout = new SpringLayout();
        this.setLayout(mSpringLayout);
        this.setBorder(new EmptyBorder(20, 20, 20, 20) );
        
        mJCheckBox = new JCheckBox("Start listening interface: " + what);
        this.add(mJCheckBox);
        mTextField = new JTextField("START?");
        mTextField.setHorizontalAlignment(JTextField.CENTER);
        mTextField.setPreferredSize(
                  new Dimension(Dimens.mainWindowWidth - 40, 
                                Dimens.displayPanelTextFieldHeight));
        mTextField.setEnabled(false);
        mTextField.setForeground(new Color(200, 200, 200));

        this.add(mTextField);
        
        //mSpringLayout.putConstraint(SpringLayout.EAST, mTextField, 20,SpringLayout.EAST, mJCheckBox);
        mSpringLayout.putConstraint(SpringLayout.NORTH, mTextField, 30,SpringLayout.NORTH, mJCheckBox);
    }
}
