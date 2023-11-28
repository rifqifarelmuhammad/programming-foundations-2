package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;

import javax.swing.*;

public class SistakaNGUI {
    public static void main(String[] args) {
        //Setup the Window & Frame
        JFrame frame = new JFrame();
        HomeGUI homeGUI = new HomeGUI(frame);
        frame.setTitle("SistakaNG");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //Registrasi Staf
        SistakaNG.registerStaf();

        //Display  -->  WelcomePanel
        homeGUI.setPanel("welcome");
    }
}
