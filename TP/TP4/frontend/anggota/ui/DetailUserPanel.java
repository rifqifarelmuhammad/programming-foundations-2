package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailUserPanel extends SistakaPanel {
    private Pengguna pengguna;
    private Anggota anggota;
    private JLabel textDetail = new JLabel();

    public DetailUserPanel(HomeGUI main) {
        //Prepare the DetailUserPanel
        super(main);  
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Lihat Detail Anggota");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitle, gbc); 

        //Label Text Detail
        textDetail.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textDetail.setForeground(Color.white);
        textDetail.setHorizontalAlignment(JLabel.CENTER);
        textDetail.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(textDetail, gbc);

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setPreferredSize(new Dimension(80,25));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(kembaliButton, gbc); 

        frame.add(this);  

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textDetail.setText("");   //Reset
                main.setPanel("anggota");   //Display  -->  AnggotaHomePanel
            }
        });
    }

    @Override
    public void refresh() {
        pengguna = main.getUser();
        anggota = (Anggota) pengguna;
        
        textDetail.setText(anggota.detail());
    }
}
