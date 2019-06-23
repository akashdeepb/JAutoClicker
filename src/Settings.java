import javax.swing.*;
import java.awt.*;

public class Settings {
    Settings(JFrame parent){
        //Configuring Setting Dialog
        JDialog settingDialog = new JDialog(parent, "Settings");
        settingDialog.setSize(300,200);
        settingDialog.getContentPane().setBackground(Color.DARK_GRAY);

        // Title Label
        JLabel title = new JLabel("Settings");
        title.setBounds(120,10,100,30);
        title.setForeground(Color.white);
        settingDialog.add(title);

        // Label for Listening Session
        JLabel recordText = new JLabel("Recording Session");
        recordText.setBounds(8,40,200,30);
        recordText.setForeground(Color.decode("#212121"));
        settingDialog.add(recordText);

        // Label for Saving current coordinate
        JLabel saveCO = new JLabel("Save Current Position : ");
        saveCO.setBounds(8,70,200,30);
        saveCO.setForeground(Color.white);
        settingDialog.add(saveCO);

        // Label for Displaying Coordinate Saving Key


        // Save Button

        settingDialog.setLayout(null);
        settingDialog.setUndecorated(true);
        settingDialog.setVisible(true);
    }
}
