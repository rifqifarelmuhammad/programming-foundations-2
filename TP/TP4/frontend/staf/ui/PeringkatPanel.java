package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PeringkatPanel extends SistakaPanel {
    private JLabel textPeringtkat = new JLabel();

    public PeringkatPanel(HomeGUI main) {
        //Prepare the PeringkatPanel
        super(main); 
        JFrame frame = main.getFrame(); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Peringkat");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(0,0,3,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitle, gbc); 

        //Label Text Peringkat
        textPeringtkat.setFont(new Font("DIALOG", Font.PLAIN, 13));
        textPeringtkat.setForeground(Color.white);
        textPeringtkat.setHorizontalAlignment(JLabel.CENTER);
        textPeringtkat.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(3,0,3,0);
        gbc.gridy = 1;
        add(textPeringtkat, gbc);

        //KembaliButton
        JButton kembaliButton = new JButton("Kembali");
        gbc.gridy = 2;
        add(kembaliButton, gbc); 

        frame.add(this); 
        
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textPeringtkat.setText("");   //Reset
                main.setPanel("staf");  //Display  -->  StafHomePanel
            }
        });
    }

    @Override
    public void refresh() {
        textPeringtkat.setText(SistakaNG.handleRankingAnggota());
    }
}
