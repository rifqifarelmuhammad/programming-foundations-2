package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class DetailAnggotaPanel extends SistakaPanel {
    private String[] iDAnggota = new String[1];
    private ArrayList<Anggota> daftarAnggota = new ArrayList<Anggota>();
    private JComboBox<String> dropdownAnggota = new JComboBox<>();

    public DetailAnggotaPanel(HomeGUI main) {
        //Prepare the DetailAnggotaPanel
        super(main); 
        JFrame frame = main.getFrame();  
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Lihat Detail Anggota");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTitle, gbc);  

        //Label Text Pilih ID
        JLabel textPiihID = new JLabel();
        textPiihID.setText("Pilih id Anggota");
        textPiihID.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textPiihID.setForeground(Color.white);
        textPiihID.setHorizontalAlignment(JLabel.CENTER);
        textPiihID.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textPiihID, gbc);

        //DropdownAnggota
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(dropdownAnggota, gbc); 
        
        //Label Text Detail
        JLabel textDetail = new JLabel();
        textDetail.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textDetail.setForeground(Color.white);
        textDetail.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 3;
        add(textDetail, gbc);
        
        //LihatButton
        JButton lihatButton = new JButton("Lihat");
        lihatButton.setPreferredSize(new Dimension(80,25));
        gbc.insets = new Insets(3,0,3,60);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipadx = 50;
        add(lihatButton, gbc);

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setPreferredSize(new Dimension(80,25));
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(kembaliButton, gbc); 
        
        frame.add(this);
        
        //Memvalidasi input dan memperlihatkan detail suatu anggota
        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String iD = dropdownAnggota.getItemAt(dropdownAnggota.getSelectedIndex());

                if (iD != null){
                    textDetail.setText(""); 
                    Anggota anggota = SistakaNG.findAnggota(iD);
                    textDetail.setText(anggota.detail());
                }else{
                    JOptionPane.showMessageDialog(frame, "Silahkan memilih ID Anggota!", "Warning", 
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textDetail.setText("");   //Reset
                main.setPanel("staf");   //Display  -->  StafHomePanel
            }
        });
    }
    
    @Override
    public void refresh() {
        daftarAnggota = SistakaNG.getDaftarAnggota();

        if (daftarAnggota.size() > 0){
            iDAnggota = new String[daftarAnggota.size()];
            
            for(int i = 0; i < daftarAnggota.size(); i++){
                iDAnggota[i] = daftarAnggota.get(i).getId();
            }
        }

        dropdownAnggota.setModel(new DefaultComboBoxModel<>(iDAnggota));

        //Reset
        dropdownAnggota.setSelectedIndex(0);
    }
}
