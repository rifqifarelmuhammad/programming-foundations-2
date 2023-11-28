//Mengimport module yang dibutuhkan oleh program
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import java.awt.event.*;

public class CurrencyConversionFrame extends JFrame {
    //Mendeclare beberapa atribut class
    JLabel labelTitle, labelFrom, labelTo;
    JButton convertButton, exitButton;
    String[] currency = {"Rupiah", "Euro", "US Dollar"};
    HashMap<String, Integer> kurs = new HashMap<String, Integer>();

    public CurrencyConversionFrame() {
        kurs.put("Rupiah", 1);
        kurs.put("Euro", 15000);
        kurs.put("US Dollar", 14000);

        //Mengatur layout dan size frame sesuai kebutuhan program
        setLayout(new GridLayout(4, 1, 5, 2));
        setSize(400, 200);

        setLocationRelativeTo(null);   //Menempatkan frame di tengah layar windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Program berhenti jika tombol close diklick
        setTitle("Currency Converter");  //Judul dari frame

        //Membuat panel sesuai kebutuhan program
        JPanel panelFrom = new JPanel();
        JPanel panelTo = new JPanel();
        JPanel panelButton = new JPanel();
        
        //Membuat label title beserta tulisannya
        labelTitle = new JLabel();
        labelTitle.setText("Welcome to Currency Converter");      

        //Membuat dropdown currency asal convert dan tujuan convert
        JComboBox<String> dropdownFrom = new JComboBox<>(currency);
        JComboBox<String> dropdownTo = new JComboBox<>(currency);
        
        //Membuat label currency asal convert dan tujuan convert
        labelFrom = new JLabel("From");
        labelTo = new JLabel("To");
            
        //Membuat textfield input dan output convert dengan panjang columns 20
        JTextField inputConvert = new JTextField(20);
        JTextField outputConvert = new JTextField(20);
        outputConvert.setEditable(false);  //Menjadikan outputConvert tidak bisa diedit

        //Membuat button convert dan exit beserta tulisannya
        convertButton = new JButton("Convert");
        exitButton = new JButton("Exit");

        //Memasukkan widget ke panel yang tepat
        panelFrom.add(labelFrom);
        panelFrom.add(dropdownFrom);
        panelFrom.add(inputConvert);
        panelTo.add(labelTo);
        panelTo.add(dropdownTo);
        panelTo.add(outputConvert);
        panelButton.add(convertButton);
        panelButton.add(exitButton);

        //Menaruh labelTitle tepat di atas & di tengah frame
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        add(labelTitle);

        //Memasukkan seluruh panel sesuai urutannya ke dalam frame
        add(panelFrom);
        add(panelTo);
        add(panelButton);

        setVisible(true);  //Menjadikan frame dapat dilihat

        //Membuat anonymous listener untuk convertButton
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //Mengumpulkan beberapa data yang dibutuhkan
                String originalValue = inputConvert.getText();
                String tipeCurrencyFrom = dropdownFrom.getItemAt(dropdownFrom.getSelectedIndex());
                String tipeCurrencyTo = dropdownTo.getItemAt(dropdownTo.getSelectedIndex());
                
                //Melakukan convert currency menggunakan method "ConvertCurrency"
                String output = ConvertCurrency(originalValue, tipeCurrencyFrom, tipeCurrencyTo);

                //Mencetak hasil convert currency ke textfield outputConvert
                outputConvert.setText(output); 
            }
        });

        //Membuat anonymous listener untuk exitButton
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();  //Menghentikan eksekusi program
            }
        });
    }

    // Method converter
    public String ConvertCurrency(String originalValue, String tipeCurrencyFrom, String tipeCurrencyTo) {
        double value = Double.valueOf(originalValue);
        double fromValue = value * kurs.get(tipeCurrencyFrom);
        double convertedValue = fromValue / kurs.get(tipeCurrencyTo);
        return String.format("%.2f", convertedValue);
    }
}