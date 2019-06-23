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
import java.net.URI;
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
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

                // Check if Valid Delay Time and Repeat Number is provided
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

        // Mouse Listener for Help Button
        helpBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setEnabled(false);        // Disable Parent

                // Configuring Help Dialog
                JDialog helpDialog = new JDialog(frame, "Help");
                helpDialog.setSize(300,350);
                helpDialog.getContentPane().setBackground(Color.DARK_GRAY);
                helpDialog.setLocation(dim.width/2 - helpDialog.getSize().width/2, dim.height/2 - helpDialog.getSize().height/2);

                // Help Label
                JLabel helpText = new JLabel("HELP");
                helpText.setForeground(Color.white);
                helpText.setBounds(130,5,100,40);
                helpDialog.add(helpText);

                //How to Use Label
                JLabel howToUse = new JLabel("How to Use : ");
                howToUse.setBounds(10,30,140,30);
                howToUse.setForeground(Color.decode("#212121"));
                helpDialog.add(howToUse);

                // Instruction Label
                JLabel instructions = new JLabel("<html>1. Click on START LISTENING after desired configs<br>" +
                        "2. Click on * button on Listener. * will be transformed to [*], this means Listener is initialized<br>" +
                        "3. Press Saving key (By Default 'X') on the Position(s) you want to click. To undo previous point Press on Delete Key (By Default 'Z')<br>" +
                        "4. Once All the Coordinates are Recorded, Click on Ex button (or Execute Key [By Default 'E']<br></html>");
                instructions.setBounds(10,50,280,200);
                instructions.setForeground(Color.white);
                helpDialog.add(instructions);

                // Repo Link Text
                JLabel linkText = new JLabel("Git Repository : ");
                linkText.setBounds(10,250, 180,20);
                linkText.setForeground(Color.decode("#e0e0e0"));
                helpDialog.add(linkText);

                // Git Repository : https://www.github.com/akashdeepb/JAutoClicker.git
                String repoLink = "https://www.github.com/akashdeepb/JAutoClicker.git";
                JButton LINK = new JButton("Click here");
                LINK.setBounds(50,270,100,30);
                LINK.setBackground(Color.DARK_GRAY);
                LINK.setForeground(Color.BLUE);
                LINK.setFocusable(false);
                LINK.setFocusPainted(false);
                LINK.setBorder(null);

                // Mouse Listener for LINK, User can click on this to visit original Repo
                LINK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().browse(URI.create(repoLink));
                            }catch (IOException ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                helpDialog.add(LINK);

                // Close Button
                JButton closeBtn = new JButton("CLOSE");
                closeBtn.setBounds(100,310, 90,30);
                closeBtn.setBackground(Color.decode("#90a4ae"));
                closeBtn.setForeground(Color.DARK_GRAY);
                closeBtn.setFocusPainted(false);
                closeBtn.setFocusable(false);
                closeBtn.setBorder(null);

                // Mouse Listener for Close Button
                closeBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frame.setEnabled(true);
                        helpDialog.dispose();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        closeBtn.setBackground(Color.decode("#f44336"));
                        closeBtn.setForeground(Color.white);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        closeBtn.setBackground(Color.decode("#90a4ae"));
                        closeBtn.setForeground(Color.DARK_GRAY);
                    }
                });
                helpDialog.add(closeBtn);

                helpDialog.setUndecorated(true);
                helpDialog.setLayout(null);
                helpDialog.setVisible(true);

            }
        });
        frame.add(helpBtn);

        frame.setLayout(null);
        frame.setVisible(true);
    }
}