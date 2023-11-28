package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class TambahKategoriPanel extends SistakaPanel {
    private JTextField inputNama = new JTextField(500);
    private JTextField inputPoin = new JTextField(500);
    private ArrayList<Kategori> daftarKategori = new ArrayList<Kategori>();

    public TambahKategoriPanel(HomeGUI main) {
        //Prepare the TambahKategoriPanel
        super(main); 
        JFrame frame = main.getFrame(); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Tambah Kategori");
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

        //Input Nama
        gbc.gridy = 2;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputNama, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;
        
        //Label Text Poin
        JLabel textPoin = new JLabel();
        textPoin.setText("Poin");
        textPoin.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textPoin.setForeground(Color.white);
        textPoin.setHorizontalAlignment(JLabel.CENTER);
        textPoin.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 3;
        add(textPoin, gbc);  

        //Input Poin
        gbc.gridy = 4;
        gbc.ipadx = 500;
        gbc.ipady = 8;
        add(inputPoin, gbc);  

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        //Tambah Button
        JButton tambahButton = new JButton("Tambah");
        gbc.insets = new Insets(3,154,3,-115);
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(tambahButton, gbc);

        //Kembali Button
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(kembaliButton, gbc); 

        frame.add(this); 

        //Memvalidasi input dan memproses penambahan kategori
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nama = inputNama.getText();
                String poinString = inputPoin.getText();

                if (nama.length() != 0 && poinString.length() != 0 && isNumeric(poinString)){ 
                    boolean kategoriAda = false;
                    int indeksKategori = 0;

                    for (int i = 0; i < daftarKategori.size(); i++){
                        if (nama.equalsIgnoreCase(daftarKategori.get(i).getNama())){
                            kategoriAda = true;
                            indeksKategori = i;
                        }
                    }

                    if (kategoriAda){  
                        JOptionPane.showMessageDialog(frame, "Kategori " + daftarKategori.get(indeksKategori).getNama() +
                        " sudah pernah ditambahkan!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{   
                        int poin = Integer.parseInt(poinString);
                        SistakaNG.addKategori(nama, poin);
                        
                        JOptionPane.showMessageDialog(frame, "Kategori " + nama + " dengan poin " + poin + " berhasil ditambahkan",
                        "Success!", JOptionPane.INFORMATION_MESSAGE); 

                        refresh();
                    }
                }else{  
                    JOptionPane.showMessageDialog(frame, "Tidak dapat menambahkan kategori silahkan periksa kembali input anda!",
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

        //Reset
        inputNama.setText("");
        inputPoin.setText("");
    }
}
