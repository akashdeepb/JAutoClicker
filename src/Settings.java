import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

class GetKeyDialog {
    GetKeyDialog(JDialog parent, JLabel label, Properties properties){
        parent.setEnabled(false);
        JDialog getDialog = new JDialog(parent,"Press any Key");
        getDialog.setSize(250,50);
        JLabel info = new JLabel("Press New Key ");
        info.setBounds(50,10,150,30);
        getDialog.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                changeKey(String.valueOf(e.getKeyChar()),label);
                properties.setProperty("save",String.valueOf(e.getKeyChar()));
                try {
                    properties.store(new FileWriter("config"), "Configuration File for JAutoClicker\nGit Repo : https://www.github.com/akashdeepb/JAutoClicker");
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                parent.setEnabled(true);
                getDialog.dispose();
            }
        });
        getDialog.add(info);

        getDialog.setLayout(null);
        getDialog.setUndecorated(true);
        getDialog.setVisible(true);
    }

    private void changeKey (String keyChar, JLabel label){
        label.setText(keyChar);
    }
}

class Settings {

    Settings(JFrame parent){
        //Configuring Setting Dialog
        JDialog settingDialog = new JDialog(parent, "Settings");
        settingDialog.setSize(300,200);
        settingDialog.getContentPane().setBackground(Color.DARK_GRAY);

        Properties properties = new Properties();

        try {
            FileReader reader = new FileReader("config");
            properties.load(reader);
        }catch(IOException  e){
            System.out.print("Error");
        }
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
        JLabel COKey = new JLabel(properties.get("save").toString());
        COKey.setBounds(208, 70, 70,30);
        COKey.setBackground(Color.DARK_GRAY);
        COKey.setForeground(Color.white);
        COKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GetKeyDialog(settingDialog, COKey, properties);
            }
        });
        settingDialog.add(COKey);

        // Label for Deleting Previous coordinate
        JLabel delCO = new JLabel("Delete Prev Position : ");
        delCO.setBounds(8,100,200,30);
        delCO.setForeground(Color.white);
        settingDialog.add(delCO);

        //Label for Displaying Delete Key
        JLabel DELKey = new JLabel(properties.get("delete").toString());
        DELKey.setBounds(208,100,70,30);
        DELKey.setBackground(Color.DARK_GRAY);
        DELKey.setForeground(Color.white);
        DELKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GetKeyDialog(settingDialog, DELKey, properties);
            }
        });
        settingDialog.add(DELKey);


        // Label for Execution
        JLabel exText = new JLabel("Execute : ");
        exText.setBounds(8, 130, 200,30);
        exText.setForeground(Color.white);
        settingDialog.add(exText);

        // Label for Displaying Execute Key
        JLabel EXKey = new JLabel(properties.get("execute").toString());
        EXKey.setBounds(208,130,70,30);
        EXKey.setBackground(Color.DARK_GRAY);
        EXKey.setForeground(Color.white);
        EXKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GetKeyDialog(settingDialog, EXKey, properties);
            }
        });
        settingDialog.add(EXKey);

        // Save Button

        settingDialog.setLayout(null);
        settingDialog.setUndecorated(true);
        settingDialog.setVisible(true);
    }

}
