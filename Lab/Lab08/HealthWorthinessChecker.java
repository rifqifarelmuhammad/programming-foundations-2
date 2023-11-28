//Meingimport module yang dibutuhkan oleh program
import java.io.*;
import java.util.*;

public class HealthWorthinessChecker {
    //Membuat attribut class dengan modifier private
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;

    public static void main(String[] args) {
        //Membuat beberapa objek dan variable yang dibutuhkan oleh program
        Scanner in = new Scanner(System.in);
        Scanner inputFile = null;
        PrintWriter outputFile = null;
        int jumlahMahasiswa = 0;

        //The beginning of program
        System.out.println("Selamat datang di program Health Worthiness Checker.");
        System.out.println("-------------------------------------------------------");

        //Meminta user menginput nama inputFile dan outputFile
        System.out.print("Silakan masukkan nama file masukan: ");
        String inputFileName = in.next();
        System.out.print("Silakan masukkan nama file keluaran: ");
        String outputFileName = in.next();

        System.out.println("-------------------------------------------------------");

        in.close();  //Menutup Scanner dengan nama variable "in"

        try { //Menulis instruksi yang memiliki kemungkinan menyebabkan error
            inputFile = new Scanner(new File(inputFileName));
            System.out.println("Data sedang diproses, harap menunggu...");

            //Menentukan jumlah mahasiswa yang akan dicek datanya
            String line = inputFile.nextLine(); 
            jumlahMahasiswa = Integer.valueOf(line);

            //Membuat array of Mahasiswa dengan panjang jumlahMahasiswa
            Mahasiswa[] daftarMahasiswa = new Mahasiswa[jumlahMahasiswa];

            int indeksList = 0;  //Sebagai temp indeks daftarMahasiswa nanti
            
            //Membuat objek mahasiswa dengan informasi berdasarkan inputFile
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();  //Menentukan nama mahasiswa

                //Menentukan tingkat kesehatan tiap mahasiswa
                int nilaiIndikatorKesehatan = 0;            
                for (char ch: inputFile.nextLine().toCharArray()){
                    if (ch != ' '){
                        nilaiIndikatorKesehatan += Character.getNumericValue(ch);
                    }
                }

                int tingkatKesehatan = 2 * nilaiIndikatorKesehatan; 

                //Membuat objek mahasiswa berdasarkan datanya ke dalam array daftarMahasiswa
                daftarMahasiswa[indeksList] = new Mahasiswa(line, tingkatKesehatan);
                indeksList += 1;  //Menambahkan indeks dari daftarMahasiswa
            }

            // Proses data mahasiswa
            System.out.println("\nLOG:");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                try {  //Mencari tau apakah tingkat kesehatan mahasiswa tergolong layak atau tidak
                    //Jika tingkat kesehatan mahasiswa layak
                    if (daftarMahasiswa[i].getTingkatKesehatan() >= MINIMUM_TINGKAT_KESEHATAN){
                        System.out.println(daftarMahasiswa[i].getNama() + ": LAYAK");
                    }else{  //Jika tidak, akan dihandle dengan cara melempar ke class HealthinessUnworthyException
                        throw new HealthinessUnworthyException(daftarMahasiswa[i].getNama() + ": TIDAK LAYAK");
                    }
                } catch (HealthinessUnworthyException e) {
                    //Jika tingkat kesehatan mahasiswa tidak layak, mencetak message dari HealthinessUnworthyException
                    System.out.println(e.getMessage());
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            
            //Mencetak output data mahasiswa ke outputFile
            for (int i = 0; i < jumlahMahasiswa; i++){
                outputFile.write(daftarMahasiswa[i].toString());
            }

            System.out.println("\nData mahasiswa berhasil diproses!");

        } catch (FileNotFoundException e) {
            //Jika inputFile tidak ditemukan
            System.out.println("ERROR: File masukan tidak ditemukan.");
        } finally {  //Menutup inputFile & outputFile
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }

        //Akhir dari program
        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program Health Worthiness Checker.");
    }
}
