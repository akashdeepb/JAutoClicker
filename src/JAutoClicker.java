import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

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

        Properties properties = new Properties();

        // Main Frame Configurations
        JFrame frame = new JFrame("JAutoClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,350);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        File configFile = new File("config");
        if(!configFile.exists()) {
            properties.setProperty("save", "X");
            properties.setProperty("delete", "Z");
            properties.setProperty("execute", "E");
            try {
                properties.store(new FileWriter(configFile.getName()), "Configuration File for JAutoClicker\nGit Repo : https://www.github.com/akashdeepb/JAutoClicker");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

        frame.add(startListenButton);

        //Configuration Area
        // Default Delay
        JLabel delayText = new JLabel("DELAY : ");
        delayText.setBounds(50,130,60, 20);
        delayText.setForeground(Color.white);
        frame.add(delayText);
        JTextPane delayTime = new JTextPane();
        StyledDocument doc = delayTime.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center,false);
        delayTime.setBounds(150,130,100,20);
        delayTime.setText("1000");
        frame.add(delayTime);

        // Repeats
        JLabel repeatText = new JLabel("REPS:" );
        repeatText.setBounds(50,170,60,20);
        repeatText.setForeground(Color.white);
        frame.add(repeatText);
        JTextPane repeatNumber = new JTextPane();
        StyledDocument styledRepeat = repeatNumber.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        styledRepeat.setParagraphAttributes(0,styledRepeat.getLength(),center,false);
        repeatNumber.setBounds(150,170,100,20);
        repeatNumber.setText("20");
        frame.add(repeatNumber);

        // Mouse Listener for Start Listen Button
        startListenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(delayTime.getText().isEmpty() || !delayTime.getText().matches("[0-9]+"))
                    delayTime.setBorder(BorderFactory.createLineBorder(Color.RED));
                else if(repeatNumber.getText().isEmpty() || !repeatNumber.getText().matches("[0-9]+")) {
                    delayTime.setBorder(BorderFactory.createLineBorder(Color.white));
                    repeatNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else {
                    repeatNumber.setBorder(BorderFactory.createLineBorder(Color.white));
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setState(JFrame.ICONIFIED);
                    this.mouseExited(e);
                    new JACListener(frame, screenSize,Integer.parseInt(delayTime.getText()), Integer.parseInt(repeatNumber.getText()));
                }
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

        // Settings Button
        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setBounds(50,220,200,30);
        settingsButton.setFocusPainted(false);
        settingsButton.setFocusable(false);
        settingsButton.setBackground(Color.decode("#90a4ae"));
        settingsButton.setForeground(Color.DARK_GRAY);
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Settings(frame);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setBackground(Color.decode("#0277bd"));
                settingsButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsButton.setBackground(Color.decode("#90a4ae"));
                settingsButton.setForeground(Color.DARK_GRAY);
            }
        });
        frame.add(settingsButton);



        // Help Button
        JButton helpBtn = new JButton("HELP");
        helpBtn.setBounds(50,270,200,30);
        helpBtn.setBackground(Color.decode("#757575"));
        helpBtn.setFocusPainted(false);
        frame.add(helpBtn);

        frame.setLayout(null);
        frame.setVisible(true);
    }
}