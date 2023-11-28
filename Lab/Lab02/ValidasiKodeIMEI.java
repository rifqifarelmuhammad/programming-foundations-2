// Nama lengkap : Rifqi Farel Muhammad
// NPM : 2106650310
// Lab 02 - Validasi Kode IMEI

import java.util.Scanner;   //Mengimport Scanner untuk meminta input dari user

public class ValidasiKodeIMEI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   //Mendeklarasi variable bertipe Scanner yang bernama "sc"
        int n = sc.nextInt();   //Mendeklarasi variable bertipe int yang berisi jumlah IMEI yang ingin dicek
        
        while (n-- > 0) {   //Mengecek IMEI sebanyak yang diinginkan oleh user
            long kode = sc.nextLong();   //Meminta user menginput nomor IMEI
            System.out.println(isValid(kode) ? "YES" : "NO");   //Mengecek & menentukan keaslian IMEI
        }
        sc.close();   //Menutup Scanner sc
    }
    
    //Membuat method "isValid" yang akan menentukan keaslian IMEI dengan aturan yang telah ditentukan
    public static boolean isValid(long kode) {
        return cekPrefix(kode)
        && (getPanjangKode(kode) == 11)
        && ((jumlahDoublePosisiGanjil(kode) + jumlahPosisiGenap(kode)) % 10 == 0);
    }
    
    //Membuat method "cekPrefix" yang mengecek apakah nomor IMEI yang diinput prefixnya 2 atau 18
    public static boolean cekPrefix(long kode) {
        String kodeString4 = String.valueOf(kode);   //Mengubah tipe data variable "kode" dari long ke String
        
        if ((kodeString4.substring(0,1)).equals("2") || (kodeString4.substring(0,2)).equals("18")){
            return true;
        } else{
            return false; 
        }
    }

    //Membuat method "getPanjangKode" yang menentukan panjang nomor IMEI yang diinput oleh user
    public static int getPanjangKode(long kode) {
        String kodeString5 = String.valueOf(kode);   //Mengubah tipe data variable "kode" dari long ke String
        
        return kodeString5.length();
    }
    
    //Membuat method "jumlahDoublePosisiGanjil" untuk mencari jumlah pertambahan (digit ganjil * 2) --> sesuai aturan
    public static int jumlahDoublePosisiGanjil(long kode) {
        String kodeString1 = String.valueOf(kode);   //Mengubah tipe data variable "kode" dari long ke String
        
        int indeks = 0;   //Sebagai patokan awal indeks digit ganjil
        int jumlah = 0;   //Sebagai wadah dari penjumlahan (digit ganjil * 2) nanti
        
        int jumlahGanjil = perkalianGanjil(kodeString1, indeks, jumlah);   //Memanggil method helper "perkalianGanjil"
        
        return jumlahGanjil;
    }

    //Membuat method "perkalianGanjil" untuk membantu method "jumlahDoublePosisiGanjil" menggunakan recursive function
    public static int perkalianGanjil(String kode, int indeks, int jumlah){
        if (indeks > 10){   //Base casenya
            return jumlah;
        } else{   //Recursive casenya
            int hasilPerkalian = Integer.parseInt(kode.substring(indeks, indeks + 1)) * 2;   //Digit ganjil * 2

            if (hasilPerkalian > 9){   //Jika hasil perkalian menjadi 2 digit, maka akan dilakukan method "getDigit"
                hasilPerkalian = getDigit(hasilPerkalian);
            }
            jumlah += hasilPerkalian;   //Hasil perkalian akan dijumlahkan ke dalam variable "jumlah"
            indeks += 2;
            return perkalianGanjil(kode, indeks, jumlah);
        }
    }

    //Membuat method "getDigit" yang akan dijalankan ketika hasil dari (digit ganjil * 2) > 9
    public static int getDigit(int number) {
        String kodeString2 = String.valueOf(number);   //Mengubah tipe data variable "kode" dari int ke String
        
        int number1 = Integer.parseInt(kodeString2.substring(0 , 1));   //Digit pertama
        int number2 = Integer.parseInt(kodeString2.substring(1 , 2));   //Digit kedua
        
        return number1 + number2;   // Menambahkan digit pertama dan digit kedua dari hasil (digit ganjil * 2)
    }
    
    //Membuat method "jumlahPosisiGenap" untuk mencari jumlah pertambahan dari digit genap nomor IMEI
    public static int jumlahPosisiGenap(long kode) {
        String kodeString3 = String.valueOf(kode);   //Mengubah tipe data variable "kode" dari long ke String
        
        int indeks = 1;   //Sebagai patokan awal indeks digit genap
        int jumlah = 0;   //Sebagai wadah dari penjumlahan digit genap nanti
        
        int jumlahGenap = penjumlahanGenap(kodeString3, indeks, jumlah);   //Memanggil method helper "penjumlahanGenap"
        
        return jumlahGenap;
    }

    //Membuat method "penjumlahanGenap" untuk membantu method "jumlahPosisiGenap" menggunakan recursive function
    public static int penjumlahanGenap(String kode, int indeks, int jumlah){
        if (indeks > 9){   //Base casenya
            return jumlah;
        } else{   //Recursive casenya
            //Menjumlahkan setiap digit genap dari nomor IMEI yang diinput dan disimpan ke dalam variable "jumlah"
            jumlah += Integer.parseInt(kode.substring(indeks, indeks + 1));
            indeks += 2;
            return penjumlahanGenap(kode, indeks, jumlah);
        }
    }    
}