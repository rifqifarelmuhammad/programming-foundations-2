package assignments.assignment4.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePanel extends SistakaPanel {
    public WelcomePanel(HomeGUI homeGUI) {
        //Prepare the WelcomePanel
        super(homeGUI);
        JFrame frame = homeGUI.getFrame();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //LabelTitle
        JLabel labelTitle = new JLabel();
        labelTitle.setText("Welcome to SistakaNG");
        labelTitle.setFont(new Font("Serif", Font.PLAIN, 35));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(15,0,210,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitle, gbc);
        
        //LoginButton
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(370,50));
        gbc.insets = new Insets(75,0,15,0);
        gbc.gridy = 1;
        add(loginButton, gbc);

        //ExitButton
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(370,50));
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 2;
        add(exitButton, gbc);
        
        frame.add(this);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                homeGUI.setPanel("login");  //Display  -->  LoginPanel
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                homeGUI.exit();
            }
        });
    }

    @Override
    public void refresh() {}
}
