package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends SistakaPanel {
    public LoginPanel(HomeGUI main){
        //Prepare the LoginPanel
        super(main); 
        JFrame frame = main.getFrame();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Masukkan ID Anda untuk login ke sistem");
        labelTitle.setFont(new Font("DIALOG", Font.PLAIN, 15));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitle, gbc);

        //InputID
        JTextField inputID = new JTextField(500);
        gbc.gridy = 1;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputID, gbc);

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //LoginButton
        JButton loginButton = new JButton("Login");
        gbc.gridy = 2;
        add(loginButton, gbc);

        frame.add(this);

        //Memvalidasi input dan mengubah display panel berdasarkan jenis pengguna
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String iD = inputID.getText();

                if (iD.length() == 0){ 
                    JOptionPane.showMessageDialog(frame, "Harap masukan id anda pada kotak diatas!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                }else{ 
                    Pengguna penggunaLoggedin = SistakaNG.handleLogin(iD);

                    if (penggunaLoggedin != null){
                        inputID.setText("");
                        main.setUser(penggunaLoggedin);

                        if (main.getUser() instanceof Staf){ 
                            main.setPanel("staf");   //Display  -->  StafHomePanel
                        }else{  
                            main.setPanel("anggota");   //Display  -->  AnggotaHomePanel
                        }
                    }else{  
                        JOptionPane.showMessageDialog(frame, "Pengguna dengan ID " + iD + " tidak ditemukan",
                        "Warning", JOptionPane.WARNING_MESSAGE);

                        inputID.setText(""); 
                    }
                }
            }
        });
    }
    
    @Override
    public void refresh() {}
}
