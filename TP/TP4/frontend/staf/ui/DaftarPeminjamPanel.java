package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class DaftarPeminjamPanel extends SistakaPanel {
    private String[] listBuku = new String[1];
    private ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    private JComboBox<String> dropdownBuku = new JComboBox<>();
    private Buku buku;
    private GridBagConstraints gbc = new GridBagConstraints(); 

    public DaftarPeminjamPanel(HomeGUI main) {
        //Prepare the DaftarPeminjamPanel
        super(main); 
        JFrame frame = main.getFrame(); 
        setLayout(new GridBagLayout());

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Lihat Daftar Peminjam");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipadx = 350;
        add(labelTitle, gbc); 

        gbc.ipadx = 0;  //Reset

        //Label Text Buku
        JLabel textBuku = new JLabel();
        textBuku.setText("Pilih buku");
        textBuku.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textBuku.setForeground(Color.white);
        textBuku.setHorizontalAlignment(JLabel.CENTER);
        textBuku.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textBuku, gbc);

        //Label Text Daftar Peminjam
        JLabel textDaftarPeminjam = new JLabel();
        textDaftarPeminjam.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textDaftarPeminjam.setForeground(Color.white);
        textDaftarPeminjam.setHorizontalAlignment(JLabel.CENTER);
        textDaftarPeminjam.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.ipadx = 210;
        add(textDaftarPeminjam, gbc); 

        //LihatButton
        JButton lihatButton = new JButton("Lihat");
        gbc.insets = new Insets(3,193,3,-130);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipady = 6;
        gbc.ipadx = 45;
        add(lihatButton, gbc); 

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipadx = 30;
        add(kembaliButton, gbc); 

        //Reset
        gbc.ipadx = 0;
        gbc.ipady = 0;

        frame.add(this); 

        //Memvalidasi input dan memperlihatkan daftar peminjam suatu buku
        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String bukuString = dropdownBuku.getItemAt(dropdownBuku.getSelectedIndex());
                
                if (bukuString == null){
                    JOptionPane.showMessageDialog(frame, "Silahkan memilih buku!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }else{
                    textDaftarPeminjam.setText(""); 
                    int index = dropdownBuku.getSelectedIndex();
                    buku = daftarBuku.get(index);
                    textDaftarPeminjam.setText(SistakaNG.daftarPeminjam(buku));
                }
            }
        });

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textDaftarPeminjam.setText("");  //Reset
                main.setPanel("staf");   //Display  -->  StafHomePanel
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
        dropdownBuku.setSelectedIndex(0);
    }
}
