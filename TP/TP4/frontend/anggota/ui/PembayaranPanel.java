package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.SistakaNG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PembayaranPanel extends SistakaPanel {
    private JTextField inputJumlah = new JTextField(500);

    public PembayaranPanel(HomeGUI main) {
        //Prepare the PembayaranPanel
        super(main); 
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Bayar Denda");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitle, gbc); 

        //Label Text Jumlah Denda
        JLabel textJumlahDenda = new JLabel();
        textJumlahDenda.setText("Jumlah Denda");
        textJumlahDenda.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textJumlahDenda.setForeground(Color.white);
        textJumlahDenda.setHorizontalAlignment(JLabel.CENTER);
        textJumlahDenda.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(textJumlahDenda, gbc);  

        //Input Jumlah
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputJumlah, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //BayarButton
        JButton bayarButton = new JButton("Bayar");
        bayarButton.setPreferredSize(new Dimension(80,25));
        gbc.insets = new Insets(3,154,3,-115);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.ipadx = 10;
        add(bayarButton, gbc);

        gbc.ipadx = 0;  //Reset

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setPreferredSize(new Dimension(80,25));
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.ipady = 1;
        add(kembaliButton, gbc);

        frame.add(this); 

        //Memvalidasi input dan memproses pembayaran denda oleh anggota
        bayarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String jumlahDenda = inputJumlah.getText();

                if (jumlahDenda.length() != 0 && isNumeric(jumlahDenda)){
                    long jumlahBayar = Long.parseLong(jumlahDenda);
                    String output = SistakaNG.bayarDenda(jumlahBayar);
                    JOptionPane.showMessageDialog(frame, output, "Info", JOptionPane.INFORMATION_MESSAGE);
                    refresh();  
                }else{
                    JOptionPane.showMessageDialog(frame, "Jumlah Bayar harus berupa angka!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");   //Display  -->  AnggotaHomePanel
            }
        });
    }

    @Override
    public void refresh() {
        //Reset
        inputJumlah.setText("");
    }
}
