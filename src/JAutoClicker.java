import javax.swing.*;
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
                JACListener jacListener = new JACListener(frame, screenSize);
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

        // Stop Button
        JButton stopListenButton = new JButton("STOP LISTENER");
        stopListenButton.setBounds(50,140,200,30);
        stopListenButton.setFocusPainted(false);
        stopListenButton.setFocusable(false);
        stopListenButton.setBackground(Color.decode("#90a4ae"));
        stopListenButton.setForeground(Color.DARK_GRAY);
        stopListenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                stopListenButton.setBackground(Color.decode("#d50000"));
                stopListenButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                stopListenButton.setBackground(Color.decode("#90a4ae"));
                stopListenButton.setForeground(Color.DARK_GRAY);
            }
        });
        frame.add(stopListenButton);


        frame.setLayout(null);
        frame.setVisible(true);
    }
}