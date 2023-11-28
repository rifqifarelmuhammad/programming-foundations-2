package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class TambahBukuPanel extends SistakaPanel {
    private JTextField inputJudul = new JTextField(500);
    private JTextField inputPenulis = new JTextField(500);
    private JTextField inputPenerbit = new JTextField(500);
    private JTextField inputStok = new JTextField(500);
    private ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    private ArrayList<Kategori> daftarKategori = new ArrayList<Kategori>();
    private String[] namaKategori = new String[1];
    private JComboBox<String> dropdownKategori = new JComboBox<>();
    private GridBagConstraints gbc = new GridBagConstraints();  

    public TambahBukuPanel(HomeGUI main) {
        //Prepare the TambahBukuPanel
        super(main);  
        JFrame frame = main.getFrame(); 
        setLayout(new GridBagLayout());

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Tambah Buku");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitle, gbc);  
        
        //Label Text Judul
        JLabel textJudul = new JLabel();
        textJudul.setText("Judul");
        textJudul.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textJudul.setForeground(Color.white);
        textJudul.setHorizontalAlignment(JLabel.CENTER);
        textJudul.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textJudul, gbc); 
        
        //Input Judul
        gbc.gridy = 2;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputJudul, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;
        
        //Label Text Penulis
        JLabel textPenulis = new JLabel();
        textPenulis.setText("Penulis");
        textPenulis.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textPenulis.setForeground(Color.white);
        textPenulis.setHorizontalAlignment(JLabel.CENTER);
        textPenulis.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 3;
        add(textPenulis, gbc);
        
        //Input Penulis
        gbc.gridy = 4;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputPenulis, gbc);

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;
        
        //Label Text Penerbit
        JLabel textPenerbit = new JLabel();
        textPenerbit.setText("Penerbit");
        textPenerbit.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textPenerbit.setForeground(Color.white);
        textPenerbit.setHorizontalAlignment(JLabel.CENTER);
        textPenerbit.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 5;
        add(textPenerbit, gbc);

        //Input Penerbit
        gbc.gridy = 6;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputPenerbit, gbc);

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Label Text Kategori
        JLabel textKategori = new JLabel();
        textKategori.setText("Kategori");
        textKategori.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textKategori.setForeground(Color.white);
        textKategori.setHorizontalAlignment(JLabel.CENTER);
        textKategori.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 7;
        add(textKategori, gbc); 
        
        //Label Text Stok
        JLabel textStok = new JLabel();
        textStok.setText("Stok");
        textStok.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textStok.setForeground(Color.white);
        textStok.setHorizontalAlignment(JLabel.CENTER);
        textStok.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(textStok, gbc);  

        //Input Stok
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputStok, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Tambah Button
        JButton tambahButton = new JButton("Tambah");
        gbc.insets = new Insets(3,154,3,-115);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        add(tambahButton, gbc); 

        //Kembali Button
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 11;
        add(kembaliButton, gbc);

        frame.add(this); 

        //Memvalidasi input dan memproses penambahan buku
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (inputStok.getText().length() != 0 && isNumeric(inputStok.getText())){
                    String judul = inputJudul.getText();
                    String penulis = inputPenulis.getText();
                    String penerbit = inputPenerbit.getText();
                    int stok = Integer.parseInt(inputStok.getText());
                    String kategori = dropdownKategori.getItemAt(dropdownKategori.getSelectedIndex());

                    if (judul.length() != 0 && penulis.length() != 0 && penerbit.length() != 0 && kategori != null){  
                        if (stok > 0){
                            boolean bukuAda = false;
                            int indeksBuku = 0;

                            for (int i = 0; i < daftarBuku.size(); i++){
                                if (judul.equalsIgnoreCase(daftarBuku.get(i).getJudul()) && 
                                penulis.equalsIgnoreCase(daftarBuku.get(i).getPenulis())){
                                    //Jika buku sudah pernah ditambahkan
                                    bukuAda = true;
                                    indeksBuku = i;
                                }
                            }

                            if (bukuAda){  
                                JOptionPane.showMessageDialog(frame, "Buku " + daftarBuku.get(indeksBuku).getJudul() + " oleh " +
                                daftarBuku.get(indeksBuku).getPenulis() + " sudah pernah ditambahkan", "Warning", 
                                JOptionPane.WARNING_MESSAGE);

                                refresh();
                            }else{  
                                SistakaNG.addBuku(judul, penulis, penerbit, kategori, stok);
                                
                                JOptionPane.showMessageDialog(frame, "Buku " + judul + " oleh " + penulis + " berhasil ditambahkan!",
                                "Success!", JOptionPane.INFORMATION_MESSAGE); 

                                refresh(); 
                            }
                        }else{  
                            JOptionPane.showMessageDialog(frame, "Stok harus lebih dari 0!", "Warning", 
                            JOptionPane.WARNING_MESSAGE);
                        }
                    }else{  
                        JOptionPane.showMessageDialog(frame, "Tidak dapat menambahkan buku silahkan periksa kembali input anda!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{  
                    JOptionPane.showMessageDialog(frame, "Tidak dapat menambahkan buku silahkan periksa kembali input anda!",
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
        daftarKategori = SistakaNG.getDaftarKategori();
        daftarBuku = SistakaNG.getDaftarBuku();

        //DropdownKategori
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridwidth = 2;
        gbc.ipady = 6;
        gbc.gridx = 0;
        gbc.gridy = 8;
        if (daftarKategori.size() > 0){
            namaKategori = new String[daftarKategori.size()];

            for(int i = 0; i < daftarKategori.size(); i++){
                namaKategori[i] = daftarKategori.get(i).getNama();
            }

            add(dropdownKategori, gbc);  
        }else{
            gbc.ipadx = 50;  
            add(dropdownKategori, gbc);  
            gbc.ipadx = 0;
        }
        gbc.ipady = 0; 
        
        dropdownKategori.setModel(new DefaultComboBoxModel<>(namaKategori));

        //Reset
        inputJudul.setText("");
        inputPenulis.setText("");
        inputPenerbit.setText("");
        inputStok.setText("");
        dropdownKategori.setSelectedIndex(0);
    }
}
