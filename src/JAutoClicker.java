import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * JAutoClicker
 * This is a desktop application for AutoClicker. It records
 * user click actions and repeats them automatically
 *
 * The below class contains the main function for the Application
 *
 * @author akashdeepb
 */
public class JAutoClicker {

    public static void main(String[] args){

        // Main Frame Configurations
        JFrame frame = new JFrame("JAutoClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,350);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Application Name Label
        JLabel appName = new JLabel("JAutoClicker", SwingConstants.CENTER);
        appName.setBounds(50, 20, 200,30);
        appName.setBorder(BorderFactory.createLineBorder(Color.white));
        appName.setForeground(Color.white);
        frame.add(appName);

        // Listen Button
        JButton startListenButton = new JButton("START LISTENER");
        startListenButton.setBounds(50,80,200,30);
        startListenButton.setFocusPainted(false);
        startListenButton.setFocusable(false);
        startListenButton.setBackground(Color.decode("#90a4ae"));       // Background of START LISTENER Button
        startListenButton.setForeground(Color.DARK_GRAY);
        startListenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setState(JFrame.ICONIFIED);
                this.mouseExited(e);
                new JACListener(frame, screenSize, false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                // Change Button background and text color when cursor hovers
                startListenButton.setBackground(Color.decode("#0277bd"));
                startListenButton.setForeground(Color.WHITE);

            }

            @Override
            public void mouseExited(MouseEvent e) {
               startListenButton.setBackground(Color.decode("#90a4ae"));
               startListenButton.setForeground(Color.DARK_GRAY);
            }
        });
        frame.add(startListenButton);

        //Configuration Area
        // CheckBox for Time Counter
        JCheckBox timeCount = new JCheckBox("Enable Time Counter");
        timeCount.setBounds(50,140,180,20);
        timeCount.setFocusPainted(false);
        timeCount.setBackground(Color.DARK_GRAY);
        timeCount.setForeground(Color.white);
        frame.add(timeCount);

        // Default Delay
        JLabel delayText = new JLabel("DELAY : ");
        delayText.setBounds(50,170,60, 20);
        delayText.setForeground(Color.white);
        frame.add(delayText);
        JTextPane delayTime = new JTextPane();
        StyledDocument doc = delayTime.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center,false);
        delayTime.setBounds(120,170,100,20);
        delayTime.setText("1000");
        frame.add(delayTime);
        timeCount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(timeCount.isSelected()) {
                    delayText.setEnabled(false);
                    delayTime.setEnabled(false);
                    delayTime.setBackground(Color.DARK_GRAY);
                }
                else{
                    delayText.setEnabled(true);
                    delayTime.setEnabled(true);
                    delayTime.setBackground(Color.white);
                }
            }
        });

        // Settings Button
        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setBounds(50,240,200,30);
        settingsButton.setFocusPainted(false);
        settingsButton.setFocusable(false);
        settingsButton.setBackground(Color.decode("#90a4ae"));
        settingsButton.setForeground(Color.DARK_GRAY);
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setBackground(Color.decode("#d50000"));
                settingsButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsButton.setBackground(Color.decode("#90a4ae"));
                settingsButton.setForeground(Color.DARK_GRAY);
            }
        });
        frame.add(settingsButton);


        frame.setLayout(null);
        frame.setVisible(true);
    }
}