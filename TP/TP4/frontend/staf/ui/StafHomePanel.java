package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StafHomePanel extends SistakaPanel {
    private JLabel labelTitle = new JLabel();

    public StafHomePanel(HomeGUI main) {
        //Prepare the StafHomePanel
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

        //Button Tambah Mahasiswa
        JButton tambahMahasiswa = new JButton("Tambah Mahasiswa");
        tambahMahasiswa.setPreferredSize(new Dimension(560,60));
        gbc.insets = new Insets(5,0,5,0);
        gbc.gridy = 1;
        add(tambahMahasiswa, gbc); 
        
        //Button Tambah Dosen
        JButton tambahDosen = new JButton("Tambah Dosen");
        tambahDosen.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 2;
        add(tambahDosen, gbc);  

        //Button Tambah Kategori
        JButton tambahKategori = new JButton("Tambah Kategori");
        tambahKategori.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 3;
        add(tambahKategori, gbc); 

        //Button Tambah Buku
        JButton tambahBuku = new JButton("Tambah Buku");
        tambahBuku.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 4;
        add(tambahBuku, gbc);  

        //Button Hapus Buku
        JButton hapusBuku = new JButton("Hapus Buku");
        hapusBuku.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 5;
        add(hapusBuku, gbc);

        //Button Tiga Peringkat Pertama
        JButton tigaPeringkatPertama = new JButton("3 Peringkat Pertama");
        tigaPeringkatPertama.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 6;
        add(tigaPeringkatPertama, gbc);  

        //Button Detail Anggota
        JButton detailAnggota = new JButton("Detail Anggota");
        detailAnggota.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 7;
        add(detailAnggota, gbc);  

        //Button Daftar Peminjam Buku
        JButton daftarPeminjamBuku = new JButton("Daftar Peminjam Buku");
        daftarPeminjamBuku.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 8;
        add(daftarPeminjamBuku, gbc); 

        //Button Logout
        JButton logout = new JButton("Logout");
        logout.setPreferredSize(new Dimension(560,60));
        gbc.gridy = 9;
        add(logout, gbc);  
        
        frame.add(this); 

        tambahMahasiswa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahMhs");   //Display  -->  TambahMahasiswaPanel
            }
        });

        tambahDosen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahDosen");   //Display  -->  TambahDosenPanel
            }
        });

        tambahKategori.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahKategori");   //Display  -->  TambahKategoriPanel
            }
        });

        tambahBuku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahBuku");   //Display  -->  TambahBukuPanel
            }
        });

        hapusBuku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("hapusBuku");   //Display  -->  HapusBukuPanel
            }
        });

        tigaPeringkatPertama.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peringkat");   //Display  -->  PeringkatPanel
            }
        });

        detailAnggota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailAnggota");   //Display  -->  DetailAnggotaPanel
            }
        });

        daftarPeminjamBuku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("daftarPeminjam");   //Display  -->  DaftarPeminjamPanel
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
        labelTitle.setText("Selamat datang kembali " + main.getUser().getNama() + "!");
    }
}