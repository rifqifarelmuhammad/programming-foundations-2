package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TambahMahasiswaPanel extends SistakaPanel {
    private JTextField inputNama = new JTextField(500);
    private JTextField inputTTL = new JTextField(500);
    private JTextField inputAngkatan = new JTextField(500);
    private String[] listProgramStudi = {"SIK", "SSI", "MIK", "MTI", "DIK"};
    private JComboBox<String> dropdownProgramStudi = new JComboBox<>(listProgramStudi);

    public TambahMahasiswaPanel(HomeGUI main) {
        //Prepare the TambahMahasiswaPanel
        super(main);  
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Tambah Mahasiswa");
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

        //InputNama
        gbc.gridy = 2;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputNama, gbc);

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Label Text TTL
        JLabel textTTL = new JLabel();
        textTTL.setText("Tanggal Lahir (DD/MM/YYYY)");
        textTTL.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textTTL.setForeground(Color.white);
        textTTL.setHorizontalAlignment(JLabel.CENTER);
        textTTL.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 3;
        add(textTTL, gbc);  
        
        //inputTTL
        gbc.gridy = 4;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputTTL, gbc);  

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Label Text Program Studi
        JLabel textProgramStudi = new JLabel();
        textProgramStudi.setText("Program Studi");
        textProgramStudi.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textProgramStudi.setForeground(Color.white);
        textProgramStudi.setHorizontalAlignment(JLabel.CENTER);
        textProgramStudi.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 5;
        add(textProgramStudi, gbc); 
        
        //Dropdown Program Studi
        gbc.gridy = 6;
        add(dropdownProgramStudi, gbc);  

        //Label Text Angkatan
        JLabel textAngkatan = new JLabel();
        textAngkatan.setText("Angkatan");
        textAngkatan.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textAngkatan.setForeground(Color.white);
        textAngkatan.setHorizontalAlignment(JLabel.CENTER);
        textAngkatan.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 7;
        add(textAngkatan, gbc); 

        //InputAngkatan
        gbc.gridy = 8;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputAngkatan, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //TambahButton
        JButton tambahButton = new JButton("Tambah");
        gbc.insets = new Insets(3,154,3,-115);
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        add(tambahButton, gbc); 

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(kembaliButton, gbc); 

        frame.add(this); 

        //Memvalidasi input dan memproses penambahan anggota mahasiswa
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nama = inputNama.getText();
                String tanggalLahir = inputTTL.getText();
                String programStudi = dropdownProgramStudi.getItemAt(dropdownProgramStudi.getSelectedIndex());
                String angkatan = inputAngkatan.getText();

                boolean inputValid = false;

                if (nama.length() != 0 && tanggalLahir.length() != 0 && angkatan.length() != 0){
                    if (isNumeric(angkatan) && isDateValid(tanggalLahir)){
                        int angkatanInt = Integer.parseInt(angkatan);  

                        if (angkatan.length() == 4 && angkatanInt >= 2000 && angkatanInt <= 2021){
                            inputValid = true;
                        }
                    }
                }
                
                if (inputValid){ 
                    Anggota mahasiswa = SistakaNG.addMahasiswa(nama, tanggalLahir, programStudi, angkatan);

                    JOptionPane.showMessageDialog(frame, "Berhasil menambahkan mahasiswa dengan id " + mahasiswa.getId() + "!",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);

                    refresh();
                }else{ 
                    JOptionPane.showMessageDialog(frame, "Tidak dapat menambahkan mahasiswa silahkan periksa kembali input anda!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");   //Display  -->  StafHomePanel
            }
        });
    }

    @Override
    public void refresh() {
        //Reset
        inputNama.setText("");
        inputTTL.setText("");
        inputAngkatan.setText("");
        dropdownProgramStudi.setSelectedIndex(0);
    }
}
