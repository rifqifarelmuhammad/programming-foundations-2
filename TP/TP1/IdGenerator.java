package assignments.assignment1;

//Mengimport module yang dibutuhkan program
import java.util.HashMap;
import java.util.Scanner;

public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) {
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);   //Mendeklarasi variable Scanner yang bernama "input"
        System.out.println("Selamat Datang di Perpustakaan!");   //The beginning of program

        boolean shouldTerminateProgram = false;   //temp untuk while loop nanti

        //While loop untuk selalu meminta input dari user sebelum diclose (input pilihan 3)
        while (!shouldTerminateProgram) {
            printMenu();   //Mencetak menu menggunakan method "printMenu"
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();   //Mendeklarasi variable bertipe int yang berisi pilihan menu user
            input.nextLine();

            if (menu == 1) {   //Program akan membuat ID keanggotaan user
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();   //Program studi yang diinput oleh user
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();   //Angkatan yang diinput oleh user
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();   //Tanggal lahir yang diinput oleh user

                //Membuat & mencetak ID keanggotaan dengan bantuan method "generateId"
                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
            } else if (menu == 2) {    //Program akan mengecek validitas ID keanggotaan user
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();   //ID anggota yang diinput oleh user

                System.out.print("Validitas: ");
                //Memvalidasi ID keanggotaan dengan bantuan method "checkValidity"
                System.out.println(checkValidity(idAnggota));
            } else {    //Jika user memilih menu 3, maka akan keluar dari program
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();   //Menutup Scanner input
    }

    //Method "buildMapCharToValue" adalah method untuk membuat mapping reference numbers Code 93
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }
    
    //Method "getCharFromValue" adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    //Method "getValueFromChar" adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    //Method "printMenu" adalah method yang akan mencetak pilihan menu yang dapat dipilih oleh user
    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
    }

    //Method "validasiInput" adalah method yang akan memvalidasi data yang diinput oleh user
    private static boolean validasiInput(String programStudi, String angkatan, String tanggalLahir){
        //Membuat variable bertipe boolean untuk temp sebagai patokan validasi nanti
        boolean checkerProgramStudi = false;
        boolean checkerAngkatan = false;
        boolean checkerTanggalLahir = false;

        //Memvalidasi data program studi yang diinput oleh user
        String[] listProgramStudi = {"SIK", "SSI", "MIK", "MTI", "DIK"};
        for (int i = 0 ; i < listProgramStudi.length ; i++){
            if (programStudi.equals(listProgramStudi[i])){
                checkerProgramStudi = true;
            }
        }
        
        //Memvalidasi data angkatan yang diinput oleh user
        int angkatanInt = Integer.parseInt(angkatan);
        if ((angkatan.length() == 4) && angkatanInt >= 2000 && angkatanInt <= 2021){
            checkerAngkatan = true;
        }

        //Memvalidasi data tanggal lahir yang diinput oleh user
        if (tanggalLahir.length() == 10 && tanggalLahir.charAt(2) == '/' && tanggalLahir.charAt(5) == '/'){
            int dd = Integer.parseInt(tanggalLahir.substring(0, 2));
            int mm = Integer.parseInt(tanggalLahir.substring(3, 5));
            int yyyy = Integer.parseInt(tanggalLahir.substring(6));
            
            if ((dd >= 0 && dd <= 31) && (mm >= 0 && mm <= 12) && (yyyy >= 1000 && yyyy <= 3000)){
                checkerTanggalLahir = true;
            }
        }

        //Mereturn hasil validasi data yang diinput user
        return checkerProgramStudi && checkerAngkatan && checkerTanggalLahir;
    }

    //Method "generateId" adalah method untuk membuat ID keanggotaan perpustakaan
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        //Memvalidasi data yang diinput terlebih dahulu menggunakan bantuan method "validasiInput"
        boolean hasilChecker = validasiInput(programStudi, angkatan, tanggalLahir);

        if (hasilChecker){   //Jika data yang diinput oleh user sudah memenuhi standar
            //Mempersiapkan data-data yang diperlukan untuk disatukan kedalam variable "iDTemp1"
            String twoLastAngkatan = angkatan.substring(2);
            tanggalLahir = tanggalLahir.replace("/", "");
            String tanggalBulan = tanggalLahir.substring(0, 4);
            String tahun = tanggalLahir.substring(6);
            String iDTemp1 = programStudi + twoLastAngkatan + tanggalBulan + tahun;
    
            String cK = checksumCK(iDTemp1);   //Mencari checksum C & K dari data yang diinput oleh user
    
            //Mereturn ID keanggotaan perpustakaan yang telah dibuat
            return "ID Anggota: " + iDTemp1 + cK;
        } else{   //Jika data yang diinput oleh user belum memenuhi standar
            return "Input tidak valid!";
        }
    }

    //Method "checkValidity" adalah method untuk mengecek validitas ID keanggotaan perpustakaan
    public static boolean checkValidity(String idAnggota) {
        if (idAnggota.length() == 13){   //Panjang idAnggota yang diinput wajib 13
            //Mempersiapkan data-data yang dibutuhkan untuk dicek kevalidasian datanya
            String programStudi = idAnggota.substring(0,3);
            String angkatan = "20" + idAnggota.substring(3, 5);
            String tanggalBulanLahir = idAnggota.substring(5, 7) + "/" + idAnggota.substring(7, 9) + "/";
            String tanggalLahir = tanggalBulanLahir + "20" + idAnggota.substring(9,11);
            String cKTemp = idAnggota.substring(11);   //Sebagai temp CK untuk validasi input data nanti
            
            //Memvalidasi data yang diinput terlebih dahulu menggunakan bantuan method "validasiInput"
            boolean hasilChecker = validasiInput(programStudi, angkatan, tanggalLahir);
            hasilChecker = hasilChecker && (cKTemp).equals((cKTemp).toUpperCase());

            if (hasilChecker){   //Jika data yang diinput oleh user sudah memenuhi standar
                //Mencari checksum C & K dari data yang diinput oleh user
                String cK = checksumCK(idAnggota.substring(0, 11));
                
                //Menentukan apakah ID keanggotaan perpustakaan yang diinput sudah tepat atau tidak
                if ((idAnggota.substring(11)).equals(cK)){
                    return true;
                }else{
                    return false;
                }
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    //Method "checksumCK" adalah method untuk mencari checksum C & K berdasarkan data yang diinput user
    public static String checksumCK(String iDTemp1){
        int jumlahChecksumC = 0;   //Sebagai temp hasil penjumlahan checksum C nanti
        int angkaPerkalian1 = iDTemp1.length();   //Sebagai temp bobot karakter nanti

        //Mengalikan dan menjumlahkan setiap value dan bobot setiap karakter dari "iDTemp1"
        for (int i = 0 ; i < iDTemp1.length() ; i++){
            int valueChar = getValueFromChar(iDTemp1.charAt(i));
            jumlahChecksumC += (valueChar * angkaPerkalian1);
            angkaPerkalian1 -= 1;
        }

        char checksumC = getCharFromValue(jumlahChecksumC % 36);   //Menentukan karakter checksum C

        String iDTemp2 = iDTemp1 + checksumC;   //Sebagai data untuk mencari checksum K
        int jumlahChecksumK = 0;   //Sebagai temp hasil penjumlahan checksum K nanti
        int angkaPerkalian2 = iDTemp2.length();   //Sebagai temp bobot karakter nanti

        //Mengalikan dan menjumlahkan setiap value dan bobot setiap karakter dari "iDTemp2"
        for (int i = 0 ; i < iDTemp2.length() ; i++){
            int valueChar = getValueFromChar(iDTemp2.charAt(i));
            jumlahChecksumK += (valueChar * angkaPerkalian2);
            angkaPerkalian2 -= 1;
        }

        char checksumK = getCharFromValue(jumlahChecksumK % 36);   //Menentukan karakter checksum K

        return "" + checksumC + checksumK;   //Mereturn checksum C dan K sebagai tipe data String
    }
}
