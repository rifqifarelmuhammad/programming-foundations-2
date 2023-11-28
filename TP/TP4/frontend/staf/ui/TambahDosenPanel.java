package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TambahDosenPanel extends SistakaPanel {
    private JTextField inputDosen = new JTextField(500);

    public TambahDosenPanel(HomeGUI main) {
        //Prepare the TambahDosenPanel
        super(main); 
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Tambah Dosen");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitle, gbc); 

        //Label Text Nama
        JLabel textNama = new JLabel();
        textNama.setText("Nama");
        textNama.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textNama.setForeground(Color.white);
        textNama.setHorizontalAlignment(JLabel.CENTER);
        textNama.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textNama, gbc); 

        //Input Dosen
        gbc.gridy = 2;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputDosen, gbc);  

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Tambah Button
        JButton tambahButton = new JButton("Tambah");
        gbc.insets = new Insets(3,154,3,-115);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(tambahButton, gbc);  

        //Kembali Button
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(kembaliButton, gbc);  

        frame.add(this); 

        //Memvalidasi input dan memproses penambahan dosen
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nama = inputDosen.getText();

                if (nama.length() != 0){  
                    Anggota dosen = SistakaNG.addDosen(nama);
                    
                    JOptionPane.showMessageDialog(frame, "Berhasil menambahkan dosen dengan id " + dosen.getId() + "!",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);

                    refresh();  
                }else{  
                    JOptionPane.showMessageDialog(frame, "Tidak dapat menambahkan dosen silahkan periksa kembali input anda!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");  //Display  -->  StafHomePanel
            }
        });
    }

    @Override
    public void refresh() {
        //Reset
        inputDosen.setText("");
    }
}
