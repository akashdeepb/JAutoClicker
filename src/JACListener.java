import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * JACListener
 * This is a toolbar which appears once Listener is started
 * This module contains following:
 *      Start : User can click on this to start recording
 *              movements
 *      Stop  : User can click on this to stop recording
 *      Execute: User can click on this to start AutoClick
 *               process
 *      Exit  : To exit the Listener Mode
 *
 * @author akashdeepb
 */
class JACListener {

    private int btnCount;
    private ArrayList<Integer> x,y,delayArray;
    private Boolean zeroFlag = false;
    private int listenFlag = 0;

    JACListener(JFrame parent, Dimension screenSize, int DEFAULT_DELAY, int REPEATS){

        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader("config");
            properties.load(fileReader);
        }catch (FileNotFoundException ex){
            System.out.print("File not found");
        }catch (IOException ex){
            System.out.print("An Error Occured while Reading File");
        }
        x = new ArrayList<>(10);
        y = new ArrayList<>(    10);
        delayArray = new ArrayList<>(10);
        // Listener frame configurations
        JFrame frame = new JFrame("JACListener");
        frame.setSize(60,225);
        frame.setUndecorated(true);
        frame.setLocation( screenSize.width - frame.getSize().width , (screenSize.height/2 - frame.getSize().height/2));
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Label to show recorded/listened clicks
        JLabel numberOfClicks = new JLabel("0",SwingConstants.CENTER);
        numberOfClicks.setBounds(1,1,55,55);
        numberOfClicks.setToolTipText("Number of Clicks Recorded");
        numberOfClicks.setForeground(Color.white);
        numberOfClicks.setBorder(BorderFactory.createLineBorder(Color.white));
        frame.add(numberOfClicks);

        // Record/Listen Button
        JButton listenBtn = new JButton("*");
        listenBtn.setBackground(Color.DARK_GRAY);
        listenBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#039be5")));
        listenBtn.setForeground(Color.decode("#039be5"));
        listenBtn.setBounds(1,57,55, 55);
        listenBtn.setFocusPainted(false);
        listenBtn.setToolTipText("Click to Start Listening");

        // Mouse Listener for listenBtn
        listenBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listenFlag+=1;
                if(listenFlag == 1) {
                    listenBtn.setText("[*]");
                    listenBtn.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                if (zeroFlag)
                                    numberOfClicks.setForeground(Color.white);

                                if (e.getKeyChar() == properties.get("save").toString().charAt(0)) {
                                    x.add(MouseInfo.getPointerInfo().getLocation().x);
                                    y.add(MouseInfo.getPointerInfo().getLocation().y);
                                    delayArray.add(DEFAULT_DELAY);

                                    btnCount += 1;
                                    numberOfClicks.setText(String.valueOf(btnCount));
                                }
                                if (e.getKeyChar() == properties.get("delete").toString().charAt(0)) {
                                    if (btnCount == 0) {
                                        zeroFlag = true;
                                        numberOfClicks.setForeground(Color.RED);
                                    } else {
                                        x.remove(btnCount - 1);
                                        y.remove(btnCount - 1);
                                        delayArray.remove(btnCount - 1);
                                        btnCount -= 1;
                                        numberOfClicks.setText(String.valueOf(btnCount));
                                    }
                                }
                                if (e.getKeyChar() == properties.get("execute").toString().charAt(0)){
                                    if(btnCount > 0)
                                        try {
                                            new AutoClickBot(x, y, delayArray, REPEATS);
                                        }catch (Exception ex){
                                            ex.printStackTrace();
                                        }
                                }
                        }
                    });
                }else
                    listenBtn.setEnabled(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               listenBtn.setBackground(Color.decode("#039be5"));
               listenBtn.setForeground(Color.BLACK);
               listenBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                listenBtn.setBackground(Color.DARK_GRAY);
                listenBtn.setForeground(Color.decode("#039be5"));
                listenBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#039be5")));
            }
        });

        frame.add(listenBtn);

        // Stop Listening Button
        JButton executeBtn = new JButton("Ex");
        executeBtn.setBounds(1,113,55,55);
        executeBtn.setBackground(Color.DARK_GRAY);
        executeBtn.setForeground(Color.BLACK);
        executeBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        executeBtn.setFocusPainted(false);
        executeBtn.setToolTipText("Start Execution");

        // Mouse Listener for executeBtn
        executeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new AutoClickBot(x, y, delayArray, REPEATS);
                }catch(Exception ex){
                    JDialog errorDialog = new JDialog(frame, "Error");
                    JLabel info = new JLabel(ex.toString());
                    errorDialog.add(info);
                    JButton okBtn = new JButton("OK");
                    errorDialog.add(okBtn);
                    info.setSize(200,200);
                    info.setVisible(true);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                executeBtn.setBackground(Color.BLACK);
                executeBtn.setForeground(Color.white);
                executeBtn.setBorder(BorderFactory.createLineBorder(Color.white));
            }
            @Override
            public void mouseExited(MouseEvent e){
                executeBtn.setBackground(Color.DARK_GRAY);
                executeBtn.setForeground(Color.BLACK);
                executeBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        });
        frame.add(executeBtn);

        // Exit Button
        JButton exitBtn = new JButton("X");
        exitBtn.setBounds(1,168,55,55);
        exitBtn.setBackground(Color.DARK_GRAY);
        exitBtn.setForeground(Color.decode("#f44336"));
        exitBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#f44336")));
        exitBtn.setFocusPainted(false);
        exitBtn.setFocusable(false);
        exitBtn.setToolTipText("Close Listener");

        // Mouse Listener for exitBtn
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();            // Close Listener Frame
                parent.setState(JFrame.NORMAL);        // Bring JAutoClicker to Normal State
                parent.toFront();       // Bring Parent Frame (JAutoClicker) to Front
            }
            @Override
            public void mouseEntered(MouseEvent e){
                exitBtn.setBackground(Color.decode("#f44336"));
                exitBtn.setForeground(Color.DARK_GRAY);
                exitBtn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitBtn.setBackground(Color.DARK_GRAY);
                exitBtn.setForeground(Color.decode("#f44336"));
                exitBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#f44336")));
            }
        });
        frame.add(exitBtn);

        frame.setLayout(null);
        frame.setVisible(true);
    }

}