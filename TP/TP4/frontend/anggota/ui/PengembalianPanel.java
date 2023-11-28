package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class PengembalianPanel extends SistakaPanel {
    private JTextField inputTanggalPengembalian = new JTextField(500);
    private String[] listBuku = new String[1];
    private ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    private JComboBox<String> dropdownBuku = new JComboBox<>();
    private Buku buku;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PengembalianPanel(HomeGUI main) {
        //Prepare the PengembalianPanel
        super(main);
        JFrame frame = main.getFrame();
        setLayout(new GridBagLayout());

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Pengembalian Buku");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitle, gbc);

        //Label Text Buku
        JLabel textBuku = new JLabel();
        textBuku.setText("Buku");
        textBuku.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textBuku.setForeground(Color.white);
        textBuku.setHorizontalAlignment(JLabel.CENTER);
        textBuku.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textBuku, gbc);

        //Label Text Tanggal Pengembalian
        JLabel textTanggalPengembalian = new JLabel();
        textTanggalPengembalian.setText("Tanggal Pengembalian (DD/MM/YYYY)");
        textTanggalPengembalian.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textTanggalPengembalian.setForeground(Color.white);
        textTanggalPengembalian.setHorizontalAlignment(JLabel.CENTER);
        textTanggalPengembalian.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 3;
        add(textTanggalPengembalian, gbc);

        //Input Tanggal Pengembalian
        gbc.gridy = 4;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputTanggalPengembalian, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //KembalikanButton
        JButton kembalikanButton = new JButton("Kembalikan");
        gbc.insets = new Insets(3,124,3,-82);
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 8;
        gbc.ipady = 4;
        add(kembalikanButton, gbc); 

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.ipadx = 30;
        gbc.ipady = 6;
        add(kembaliButton, gbc);

        frame.add(this); 

        //Memvalidasi input dan memproses pengembalian buku oleh anggota
        kembalikanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String bukuString = dropdownBuku.getItemAt(dropdownBuku.getSelectedIndex());
                
                if (bukuString == null){
                    JOptionPane.showMessageDialog(frame, "Silahkan memilih buku!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }else{
                    String tanggalPengembalian = inputTanggalPengembalian.getText();
                    
                    if (isDateValid(tanggalPengembalian)){ 
                        int index = dropdownBuku.getSelectedIndex();
                        buku = daftarBuku.get(index);
                        String output = SistakaNG.kembalikanBuku(buku, tanggalPengembalian);
                        JOptionPane.showMessageDialog(frame, output, "Info", JOptionPane.INFORMATION_MESSAGE);
                        refresh(); 
                    }else{ 
                        JOptionPane.showMessageDialog(frame, "Tanggal yang dimasukan harus dalam format DD/MM/YYYY!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    }
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
        daftarBuku = SistakaNG.getDaftarBuku();
        
        //DropdownBuku
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridwidth = 2;
        gbc.ipady = 6;
        gbc.gridx = 0;
        gbc.gridy = 2;
        if (daftarBuku.size() > 0){
            listBuku = new String[daftarBuku.size()];
            
            for (int i = 0; i < daftarBuku.size(); i++){
                listBuku[i] = daftarBuku.get(i).getJudul() + " oleh " + daftarBuku.get(i).getPenulis();
            }

            add(dropdownBuku, gbc); 
        }else{
            listBuku = new String[1];
            gbc.ipadx = 50;  
            add(dropdownBuku, gbc); 
            gbc.ipadx = 0; 
        }
        gbc.ipady = 0; 

        dropdownBuku.setModel(new DefaultComboBoxModel<>(listBuku));

        //Reset
        inputTanggalPengembalian.setText("");
        dropdownBuku.setSelectedIndex(0);
    }
}
