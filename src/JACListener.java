import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private ArrayList<Integer> x,y;
    private Boolean zflag = false;
    private int listenFlag = 0;

    JACListener(JFrame parent, Dimension screenSize){
        x = new ArrayList<Integer>(10);
        y = new ArrayList<Integer>(10);
        // Listener frame configurations
        JFrame frame = new JFrame("JACListener");
        frame.setSize(60,250);
        frame.setUndecorated(true);
        frame.setLocation( screenSize.width , (screenSize.height/2 - 150));
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Label to show recorded/listened clicks
        JLabel numberOfClicks = new JLabel("0",SwingConstants.CENTER);
        numberOfClicks.setBounds(1,1,55,55);
        numberOfClicks.setToolTipText("Number of Clicks Recorded");
        numberOfClicks.setForeground(Color.white);
        numberOfClicks.setBorder(BorderFactory.createLineBorder(Color.white));
        frame.add(numberOfClicks);

        // Record/Listen Button
        JButton listenBtn = new JButton("S");
        listenBtn.setBounds(1,57,55, 55);
        listenBtn.setFocusPainted(false);

        // Mouse Listener for listenBtn
        listenBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listenFlag+=1;
                if(listenFlag == 1) {
                    listenBtn.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                if (zflag)
                                    numberOfClicks.setForeground(Color.white);
                                if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
                                    x.add(MouseInfo.getPointerInfo().getLocation().x);
                                    y.add(MouseInfo.getPointerInfo().getLocation().y);
                                    btnCount += 1;
                                    numberOfClicks.setText(String.valueOf(btnCount));
                                }
                                if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
                                    if (btnCount == 0) {
                                        zflag = true;
                                        numberOfClicks.setForeground(Color.RED);
                                    } else {
                                        x.remove(btnCount - 1);
                                        y.remove(btnCount - 1);
                                        btnCount -= 1;
                                        numberOfClicks.setText(String.valueOf(btnCount));
                                    }
                                }
                        }
                    });
                }else
                    listenBtn.setEnabled(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        frame.add(listenBtn);

        // Abort
        JButton abortBtn = new JButton("S");
        abortBtn.setBounds(1,57,55, 55);
        abortBtn.setFocusPainted(false);
        abortBtn.setVisible(false);
        frame.add(abortBtn);

        // Stop Listening Button
        JButton executeBtn = new JButton("O");
        executeBtn.setBounds(1,113,55,55);
        executeBtn.setFocusPainted(false);
        // Mouse Listener for executeBtn
        executeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    AutoClickBot autoClickBot = new AutoClickBot(x, y);

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
        });
        frame.add(executeBtn);

        // Exit Button
        JButton exitBtn = new JButton("X");
        exitBtn.setBounds(1,168,55,55);
        exitBtn.setFocusPainted(false);
        // Mouse Listener for exitBtn
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();            // Close Listener Frame
                parent.setState(JFrame.NORMAL);        // Bring JAutoClicker to Normal State
                parent.toFront();       // Bring Parent Frame (JAutoClicker) to Front
            }
        });
        frame.add(exitBtn);

        frame.setLayout(null);
        frame.setVisible(true);
    }

}