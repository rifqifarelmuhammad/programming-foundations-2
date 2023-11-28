// Nama lengkap : Rifqi Farel Muhammad
// NPM : 2106650310
// Lab 01 - Kalkulator BMI

import java.util.Scanner;   //Mengimport Scanner untuk meminta input dari user

public class KalkulatorBMI {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);  //Mendeklarasi variable bertipe Scanner yang bernama "inp"

        //Mendeklarasi beberapa variable bertipe int untuk kebutuhan ringkasan data nanti
        int bawahNormal = 0;   //Jumlah mahasiswa dengan berat badan di bawah normal
        int normal = 0;   //Jumlah mahasiswa dengan berat badan normal
        int atasNormal = 0;   //Jumlah mahasiswa dengan berat badan di atas normal
        int obesitas = 0;   //Jumlah mahasiswa obesitas

        //The Beginning of Program
        System.out.println("Selamat datang di program kalkulator BMI!");
        System.out.println("--------------------------------------------------------");

        //Meminta user menginput jumlah mahasiswa yang akan dihitung datanya
        System.out.print("Masukkan jumlah mahasiswa yang akan dihitung datanya: ");
        //Mendeklarasi variable bertipe int yang berisi jumlah mahasiswa yang akan dihitung BMI nya
        int jumlahMahasiswa = inp.nextInt();

        for (int i = 1 ; i <= jumlahMahasiswa ; i++){   //Proses permintaan & pengolahan data mahasiswa
            System.out.println("--------------------DATA MAHASISWA " + i + "--------------------");

            //Meminta user menginput standar pengukuran apa yang ingin digunakan
            System.out.print("Standar pengukuran apakah yang digunakan? ");
            inp.nextLine();   //Mencegah error --> Sebelumnya ada request input numerical (inp.nextInt())
            //Mendeklarasi variable bertipe String yang berisi standar pengukuran yang ingin digunakan user
            String standarPengukuran = inp.nextLine();

            if (standarPengukuran.equals("METRIK")){   //Jika ingin menggunakan pengukuran "METRIK"
                //Meminta user menginput massa tubuh mahasiswa, dalam satuan kilogram
                System.out.print("Masukkan massa tubuh mahasiswa (kilogram): ");
                //Mendeklarasi variable bertipe double yang berisi massa tubuh mahasiswa
                double massaTubuh = inp.nextDouble();

                //Meminta user menginput tinggi tubuh mahasiswa, dalam satuan sentimeter
                System.out.print("Masukkan tinggi tubuh mahasiswa (sentimeter): ");
                //Mendeklarasi variable bertipe double yang berisi tinggi tubuh mahasiswa
                double tinggiTubuh = inp.nextDouble();
                tinggiTubuh /= 100;   //Membuat satuan tinggi tubuh mahasiswa menjadi meter

                //Menghitung BMI melalui pengukuran "METRIK" dan memasukkan nilainya ke variabel "BMI"
                double BMI = massaTubuh / (Math.pow(tinggiTubuh, 2));
                
                //Mencari keterangan massa tubuh mahasiswa dengan cara menyortir nilai BMI nya
                if (BMI < 18.5){
                    bawahNormal += 1;
                } else if (BMI >= 18.5 && BMI < 25.0){
                    normal += 1;
                } else if (BMI >= 25.0 && BMI < 30.0){
                    atasNormal += 1;
                } else if (BMI >= 30.0){
                    obesitas += 1;
                }
                
            } else if (standarPengukuran.equals("IMPERIAL")){   //Jika ingin menggunakan pengukuran "IMPERIAL"
                //Meminta user menginput massa tubuh mahasiswa, dalam satuan pon
                System.out.print("Masukkan massa tubuh mahasiswa (pon): ");
                //Mendeklarasi variable bertipe double yang berisi massa tubuh mahasiswa
                double massaTubuh = inp.nextDouble();

                //Meminta user menginput tinggi tubuh mahasiswa, dalam satuan inci
                System.out.print("Masukkan tinggi tubuh mahasiswa (inci): ");
                //Mendeklarasi variable bertipe double yang berisi tinggi tubuh mahasiswa
                double tinggiTubuh = inp.nextDouble();

                //Menghitung BMI melalui pengukuran "IMPERIAL" dan memasukkan nilainya ke variabel "BMI"
                double BMI = 703  * (massaTubuh / (Math.pow(tinggiTubuh, 2)));
                
                //Mencari keterangan massa tubuh mahasiswa dengan cara menyortir nilai BMI nya
                if (BMI < 18.5){
                    bawahNormal += 1;
                } else if (BMI >= 18.5 && BMI < 25.0){
                    normal += 1;
                } else if (BMI >= 25.0 && BMI < 30.0){
                    atasNormal += 1;
                } else if (BMI >= 30.0){
                    obesitas += 1;
                }
            }
        }

        //Mencetak ringkasan data mahasiswa berdasarkan data yang telah diinput oleh user
        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.println("Berikut merupakan ringkasan hasil pengukuran BMI dari " + jumlahMahasiswa + " mahasiswa.");
        System.out.println("Jumlah mahasiswa dengan berat badan di bawah normal: " + bawahNormal);
        System.out.println("Jumlah mahasiswa dengan berat badan normal: " + normal);
        System.out.println("Jumlah mahasiswa dengan berat badan di atas normal: " + atasNormal);
        System.out.println("Jumlah mahasiswa obesitas: " + obesitas);

        //The Ending Of Program
        System.out.println("--------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program kalkulator BMI!");

        inp.close();   //Menutup Scanner inp
    }
}