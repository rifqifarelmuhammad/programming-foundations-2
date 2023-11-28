package assignments.assignment4.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class SistakaPanel extends JPanel {
    protected final HomeGUI main;

    public SistakaPanel(HomeGUI main) {
        this.main = main;
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(new Color(0x123456));
    }

    public abstract void refresh();

    //Memvalidasi input angka
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //Memvalidasi input tanggal (dd/mm/yyyy)
    public static boolean isDateValid(String tanggal){
        String[] arrOfTanggalLahir = tanggal.split("/");

        if (arrOfTanggalLahir.length != 3) {
            return false;
        }

        for (String s : arrOfTanggalLahir) {
            if (!isNumeric(s)) {
                return false;
            }
        }

        if (arrOfTanggalLahir[0].length() != 2 || arrOfTanggalLahir[1].length() != 2 || arrOfTanggalLahir[2].length() != 4) {
            return false;
        }

        int hari = Integer.parseInt(arrOfTanggalLahir[0]);
        if (hari < 1 || hari > 31) {
            return false;
        }

        int bulan = Integer.parseInt(arrOfTanggalLahir[1]);
        return bulan >= 1 && bulan <= 12;
    }
}
