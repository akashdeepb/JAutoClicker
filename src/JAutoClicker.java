import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * JAutoClicker
 * This is a desktop application for AutoClicker. It records
 * user click actions and repeats them automatically
 *
 * The below class contains the main funciton for the Application
 *
 * @author akashdeepb
 */
public class JAutoClicker {

    public static void main(String[] args){

        // Main Frame Configurations
        JFrame frame = new JFrame("JAutoClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        // Application Name Label
        JLabel appName = new JLabel("JAutoClicker", SwingConstants.CENTER);
        appName.setBounds(100, 20, 200,30);
        appName.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        frame.add(appName);

        // Listen Button
        JButton startListenButton = new JButton("START LISTENER");
        startListenButton.setBounds(100,80,200,30);
        startListenButton.setFocusPainted(false);
        frame.add(startListenButton);

        // Stop Button
        JButton stopListenButton = new JButton("STOP LISTENER");
        stopListenButton.setBounds(100,120,200,30);
        stopListenButton.setFocusPainted(false);
        frame.add(stopListenButton);


        frame.setLayout(null);
        frame.setVisible(true);
    }
}