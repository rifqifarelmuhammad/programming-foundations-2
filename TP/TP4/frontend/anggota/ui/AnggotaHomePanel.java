package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnggotaHomePanel extends SistakaPanel {
    private JLabel labelTitle = new JLabel();

    public AnggotaHomePanel(HomeGUI main) {
        //Prepare the AnggotaHomePanel
        super(main);
        JFrame frame = main.getFrame();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 25));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,23,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitle, gbc);

        //Button Peminjaman
        JButton peminjaman = new JButton("Peminjaman");
        peminjaman.setPreferredSize(new Dimension(560,60));
        gbc.insets = new Insets(5,0,5,0);
        gbc.gridy = 1;
        add(peminjaman, gbc);
        
        //Button Pengembalian
        JButton pengembalian = new JButton("Pengembalian");
        pengembalian.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 2;
        add(pengembalian, gbc); 

        //Button Pembayaran Denda
        JButton pembayaranDenda = new JButton("Pembayaran Denda");
        pembayaranDenda.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 3;
        add(pembayaranDenda, gbc); 

        //Button Detail Anggota
        JButton detailAnggota = new JButton("Detail Anggota");
        detailAnggota.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 4;
        add(detailAnggota, gbc);

        //Button Logout
        JButton logout = new JButton("Logout");
        logout.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 5;
        add(logout, gbc); 
        
        frame.add(this);  

        peminjaman.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peminjaman");   //Display  -->  PeminjamanPanel
            }
        });

        pengembalian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pengembalian");   //Display  -->  PengembalianPanel
            }
        });

        pembayaranDenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pembayaran");   //Display  -->  PembayaranPanel
            }
        });

        detailAnggota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailUser");   //Display  -->  DetailUserPanel
            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("welcome");   //Display  -->  WelcomePanel
            }
        });
    }

    @Override
    public void refresh() {
        labelTitle.setText("Selamat datang kembali " + main.getUser().getNama());
    }
}
