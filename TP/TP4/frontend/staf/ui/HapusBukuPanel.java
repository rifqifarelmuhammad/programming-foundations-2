package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class HapusBukuPanel extends SistakaPanel {
    private ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    private JComboBox<String> dropdownBuku = new JComboBox<>();
    private String[] listBuku = new String[1];
    private Buku buku;
    private GridBagConstraints gbc = new GridBagConstraints(); 

    public HapusBukuPanel(HomeGUI main) {
        //Prepare the HapusBukuPanel
        super(main);  
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Hapus Buku");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);   
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipadx = 450;
        add(labelTitle, gbc); 

        gbc.ipadx = 0;  //Reset

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
        
        //HapusButton
        JButton hapusButton = new JButton("Hapus");
        gbc.insets = new Insets(3,209,3,-160);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(hapusButton, gbc); 

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(kembaliButton, gbc);  

        frame.add(this);

        //Memvalidasi input dan memproses penghapusan buku
        hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String bukuString = dropdownBuku.getItemAt(dropdownBuku.getSelectedIndex());

                if (bukuString == null){
                    JOptionPane.showMessageDialog(frame, "Silahkan memilih buku!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }else{
                    int index = dropdownBuku.getSelectedIndex();
                    buku = daftarBuku.get(index);
                    String output = SistakaNG.deleteBuku(buku);
                    JOptionPane.showMessageDialog(frame, output, "Info", JOptionPane.INFORMATION_MESSAGE);
                    refresh(); 
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
