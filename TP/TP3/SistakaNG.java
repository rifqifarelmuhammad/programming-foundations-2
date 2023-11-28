package assignments.assignment3;

//Mengimport class yang dibutuhkan oleh class SistakaNG
import assignments.assignment3.buku.*;
import assignments.assignment3.pengguna.*;

//Mengimport beberapa module yang dibutuhkan oleh class SistakaNG
import java.util.Arrays;
import java.util.Scanner;

public class SistakaNG {
    //Membuat attribut class dengan modifier public & private
    private static Scanner input = new Scanner(System.in);
    public static Pengguna penggunaLoggedIn;
    public static Staf[] daftarStaf = new Staf[0];
    public static Anggota[] daftarAnggota = new Anggota[0];
    public static Buku[] daftarBuku = new Buku[0];
    public static Kategori[] daftarKategori = new Kategori[0];

    public static void main(String[] args) {  //Method utama program
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        daftarStaf = new Staf[listNama.length];  //Menambahkan panjang list daftarStaf sesuai dengan jumlah staf
        for (int i = 0; i < listNama.length; i++) {
            //Membuat objek baru staf dan memasukkannya ke dalam list daftarStaf
            daftarStaf[i] = new Staf(listNama[i]);
            Staf staf = daftarStaf[i];

            //Mencetak informasi staf yang berhasil ditambahkan
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.println(staf);
        }
    }

    // Method ini digunakan untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        boolean hasChosenExit = false;  //Sebagai flag program terus berjalan sebelum di close user
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;

            //Menampilkan pilihan menu utama SistakaNG dan meminta user menginput command yang ingin dipilih
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {  //Jika user menginput command 1
                menuLogin();
            } else if (command == 2) {  //Jika user menginput command 2
                System.out.println("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {  //Jika user menginput command selain 1 & 2
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;  //Sebagai flag program akan terus berjalan sampai id yang diinput valid
        while (!isLoginSuccess) {
            //Meminta user menginput id
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();

            /*
            Mengecek keberadaan & kevalidan id yang diinput user. Jika id ada dan valid, maka akan mengubah nilai isLoginSuccess
            menjadi true dan keluar dari menu login
            */
            if (daftarStaf.length != 0 && daftarAnggota.length != 0){
                for (int i = 0; i < daftarStaf.length; i++){
                    if (id.equals(daftarStaf[i].getId())){
                        String output = String.format("Halo, %s! Selamat datang di SistakaNG", daftarStaf[i].getNama());
                        System.out.println(output);
                        isLoginSuccess = true;
                        penggunaLoggedIn = daftarStaf[i];
                        break;
                    }
                }

                for (int i = 0; i < daftarAnggota.length; i++){
                    if (id.equals(daftarAnggota[i].getId())){
                        String output = String.format("Halo, %s! Selamat datang di SistakaNG", daftarAnggota[i].getNama());
                        System.out.println(output);
                        isLoginSuccess = true;
                        penggunaLoggedIn = daftarAnggota[i];
                        break;
                    }
                }

                if (!isLoginSuccess){
                    String output = String.format("Pengguna dengan ID %s tidak ditemukan", id);
                    System.out.println(output);
                }
            }else if (daftarStaf.length == 0 && daftarAnggota.length != 0){
                for (int i = 0; i < daftarAnggota.length; i++){
                    if (id.equals(daftarAnggota[i].getId())){
                        String output = String.format("Halo, %s! Selamat datang di SistakaNG", daftarAnggota[i].getNama());
                        System.out.println(output);
                        isLoginSuccess = true;
                        penggunaLoggedIn = daftarAnggota[i];
                        break;
                    }
                }

                if (!isLoginSuccess){
                    String output = String.format("Pengguna dengan ID %s tidak ditemukan", id);
                    System.out.println(output);
                }
            }else if (daftarAnggota.length == 0 && daftarStaf.length != 0){
                for (int i = 0; i < daftarStaf.length; i++){
                    if (id.equals(daftarStaf[i].getId())){
                        String output = String.format("Halo, %s! Selamat datang di SistakaNG", daftarStaf[i].getNama());
                        System.out.println(output);
                        isLoginSuccess = true;
                        penggunaLoggedIn = daftarStaf[i];
                        break;
                    }
                }

                if (!isLoginSuccess){
                    String output = String.format("Pengguna dengan ID %s tidak ditemukan", id);
                    System.out.println(output);
                }
            }else{
                String output = String.format("Pengguna dengan ID %s tidak ditemukan", id);
                System.out.println(output);
            }
        }

        showMenu();  //Lanjut ke method showMenu
    }

    // Method ini digunakan untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        if (penggunaLoggedIn instanceof Staf) {  //Jika yang loggedIn adalah Staf, akan mengakses menu Staf
            showMenuStaf();
        } else {  //Jika yang loggedIn adalah Anggota, maka akan mengakses menu Anggota
            showMenuAnggota();
        }
    }

    // Method ini digunakan untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;  //Sebagai flag program akan terus berjalan sampai staf logout
        while (!hasChosenExit) {
            //Menampilkan menu staf dan meminta staf menginput command yang ingin dijalankan
            System.out.println("================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {  //Jika command yang diinput adalah 1
                System.out.println("---------- Tambah Anggota ----------");
                
                //Program meminta staf menginput tipe anggota yang ingin ditambahkan
                System.out.print("Tipe Anggota: ");
                String tipeAnggota = input.nextLine();

                if (tipeAnggota.equals("Mahasiswa")){  //Jika tipe anggotanya Mahasiswa
                    //Program akan meminta beberapa data yang dibutuhkan untuk membuat id anggota
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
                    String programStudi = input.nextLine();
                    System.out.print("Angkatan: ");
                    String angkatan = input.nextLine();
                    System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
                    String tanggalLahir = input.nextLine();

                    /*
                    Program akan memvalidasi input staf. Jika input valid, maka program akan membuat id anggota serta
                    memasukkannya ke dalam daftar anggota
                    */ 
                    if (validasiInput(programStudi, angkatan, tanggalLahir)){
                        Anggota[] tempDaftarAnggota = new Anggota[daftarAnggota.length];
                        for (int i = 0; i < daftarAnggota.length; i++){
                            tempDaftarAnggota[i] = daftarAnggota[i];
                        }
    
                        daftarAnggota = new Anggota[tempDaftarAnggota.length + 1];
                        for (int i = 0; i < tempDaftarAnggota.length; i++){
                            daftarAnggota[i] = tempDaftarAnggota[i];
                        }
    
                        daftarAnggota[daftarAnggota.length - 1] = new Mahasiswa(nama, programStudi, angkatan, tanggalLahir);

                        System.out.println("Berhasil menambahkan mahasiswa dengan data:");
                        System.out.println(daftarAnggota[daftarAnggota.length - 1]);
                    }else{
                        System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
                    }
                }else if (tipeAnggota.equals("Dosen")){  //Jika tipe anggotanya Dosen
                    //Program akan meminta nama Dosen untuk membuat id Anggota
                    System.out.print("Nama: ");
                    String nama = input.nextLine();

                    //Program akan membuat id Anggota dosen dan memasukkannya ke dalam daftarAnggota
                    Anggota[] tempDaftarAnggota = new Anggota[daftarAnggota.length];
                    for (int i = 0; i < daftarAnggota.length; i++){
                        tempDaftarAnggota[i] = daftarAnggota[i];
                    }

                    daftarAnggota = new Anggota[tempDaftarAnggota.length + 1];
                    for (int i = 0; i < tempDaftarAnggota.length; i++){
                        daftarAnggota[i] = tempDaftarAnggota[i];
                    }

                    daftarAnggota[daftarAnggota.length - 1] = new Dosen(nama);
                    System.out.println("Berhasil menambahkan dosen dengan data:");
                    System.out.println(daftarAnggota[daftarAnggota.length - 1]);
                }else{  //Jika tipe anggota bukanlah Mahasiswa atau Dosen
                    String output = String.format("Tipe Anggota %s tidak valid!", tipeAnggota);
                    System.out.println(output);
                }
            } else if (command == 2) {  //Jika command yang diinput adalah 2
                System.out.println("---------- Tambah Kategori ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Nama Kategori: ");
                String name = input.nextLine();
                System.out.print("Poin: ");
                int point = input.nextInt();
                
                if (daftarKategori.length == 0){   //Jika belum ada Kategori sama sekali
                    daftarKategori = new Kategori[1];  //Memperbarui panjang array daftarKategori menjadi 1

                    //Membuat objek Kategori di array daftarKategori indeks 0
                    daftarKategori[0] = new Kategori(name, point);
                    
                    System.out.println("Kategori " + name + " dengan poin " + point + " berhasil ditambahkan");
                }else{  //Jika sudah ada Kategori di array daftarKategori
                    boolean sudahAda = false;   //Sebagai flag apakah Kategori yang diinput user sudah ada atau belum
                    int indeksSama = 0;   //Sebagai patokan indeks Kategori

                    //Mencari tau apakah Kategori yang diinput oleh user sudah ada atau belum dan mencari indeksnya
                    for (int i = 0 ; i < daftarKategori.length; i++){
                        if (daftarKategori[i].getNama().equalsIgnoreCase(name)){
                            sudahAda = true;
                            indeksSama = i;
                            break;
                        }
                    }

                    if (!sudahAda){  //Jika Kategori yang diinput user belum ada di array daftarKategori
                        //Program akan menambahkan panjang array daftarKategori
                        Kategori[] tempDaftarKategori = new Kategori[daftarKategori.length];
                        for (int i = 0; i < daftarKategori.length; i++){
                            tempDaftarKategori[i] = daftarKategori[i];
                        }
    
                        daftarKategori = new Kategori[tempDaftarKategori.length + 1];
                        for (int i = 0; i < tempDaftarKategori.length; i++){
                            daftarKategori[i] = tempDaftarKategori[i];
                        }
    
                        //Membuat objek Kategori di array daftarKategori indeks terakhir
                        daftarKategori[daftarKategori.length - 1] = new Kategori(name, point);
                        System.out.println("Kategori " + name + " dengan poin " + point + " berhasil ditambahkan");
                    }else{   //Jika Kategori yang diinput user sudah ada di array daftarKategori
                        System.out.println("Kategori " + daftarKategori[indeksSama].getNama() + " sudah pernah ditambahkan");
                    }
                }
                input.nextLine();
            } else if (command == 3) {  //Jika command yang diinput adalah 3
                System.out.println("---------- Tambah Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Judul: ");
                String judul = input.nextLine();
                System.out.print("Penulis: ");
                String penulis = input.nextLine();
                System.out.print("Penerbit: ");
                String penerbit = input.nextLine();
                System.out.print("Kategori: ");
                String kategori = input.nextLine();
                System.out.print("Stok: ");
                int stok = input.nextInt();
                
                boolean kategoriAda = false;   //Sebagai flag apakah kategorinya ada atau tidak
                int indeksdaftarKategori = 0;   //Sebagai temp indeks categorynya

                //Mencari tahu apakah kategori yang diinput terdapat di dalam daftarKategori dan mencari indeksnya
                if (daftarKategori.length != 0){
                    for (int i = 0 ; i < daftarKategori.length; i++){
                        if (daftarKategori[i].getNama().equalsIgnoreCase(kategori)){
                            kategoriAda = true;
                            indeksdaftarKategori = i;
                            break;
                        }
                    }
                }

                if (kategoriAda){  //Jika kategorinya ada
                    if (stok > 0){  //Jika stoknya > 0
                        if (daftarBuku.length == 0){  //Jika belum ada buku sama sekali
                            daftarBuku = new Buku[1];   //Memperbarui panjang array daftarBuku menjadi 1
            
                            //Membuat objek Buku di array daftarBuku indeks 0
                            daftarBuku[0] = new Buku(judul, penulis, penerbit, stok, daftarKategori[indeksdaftarKategori]);
            
                            System.out.println("Buku " + daftarBuku[0].getJudul() + " oleh " + daftarBuku[0].getPenulis() + 
                            " berhasil ditambahkan");
                        }else{  //Jika sudah ada buku
                            //Mencari tau apakah buku yang diinput sudah ada di array daftarBuku atau belum
                            boolean sudahAda = false;  
                            int indeksBuku = 0;
                            for (int i = 0 ; i < daftarBuku.length; i++){
                                if (daftarBuku[i].getJudul().equalsIgnoreCase(judul) && 
                                daftarBuku[i].getPenulis().equalsIgnoreCase(penulis)){
                                    sudahAda = true;
                                    indeksBuku = i;
                                    break;
                                }
                            }

                            if (sudahAda){  //Jika bukunya sudah ada di daftarBuku
                                String output = String.format("Buku %s oleh %s sudah pernah ditambahkan", 
                                daftarBuku[indeksBuku].getJudul(), daftarBuku[indeksBuku].getPenulis());
                                
                                System.out.println(output);
                            }else{  //Jika bukunya belum ada di daftarBuku
                                //Program akan menambahkan panjang array daftarBuku
                                Buku[] tempBuku = new Buku[daftarBuku.length];
                                for (int i = 0; i < daftarBuku.length; i++){
                                    tempBuku[i] = daftarBuku[i];
                                }
                                
                                daftarBuku = new Buku[daftarBuku.length + 1];
                                for (int i = 0; i < tempBuku.length; i++){
                                    daftarBuku[i] = tempBuku[i];
                                }
                
                                //Membuat objek Buku di array daftarBuku indeks terakhir
                                daftarBuku[daftarBuku.length - 1] = new Buku(judul, penulis, penerbit, stok, daftarKategori[indeksdaftarKategori]);
                
                                System.out.println("Buku " + daftarBuku[daftarBuku.length - 1].getJudul() + " oleh " + 
                                daftarBuku[daftarBuku.length - 1].getPenulis() + " berhasil ditambahkan");
                            }
                        }
                    }else{  //Jika stoknya <= 0
                        System.out.println("Stok harus lebih dari 0");
                    }
                }else{  //Jika kategorinya tidak ada
                    String output = String.format("Kategori %s tidak ditemukan", kategori);
                    System.out.println(output);
                }
                input.nextLine();
            } else if (command == 4) {  //Jika command yang diinput adalah 4
                System.out.println("---------- Hapus Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Judul: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis: ");
                String penulisBuku = input.nextLine();
                
                if (daftarBuku.length == 0){  //Jika belum ada buku sama sekali
                    String output = String.format("Buku %s oleh %s tidak ditemukan", judulBuku, penulisBuku);
                    System.out.println(output);
                }else{  //Jika sudah ada buku
                    //Mencari tahu terlebih dahulu apakah buku yang ingin dihapus ada di daftarBuku dan mencari indeksnya
                    boolean bukuAda = false;   
                    int indeksBuku = 0;   
                    for (int i = 0; i < daftarBuku.length; i ++){
                        if (daftarBuku[i].getJudul().equalsIgnoreCase(judulBuku) && daftarBuku[i].getPenulis().equalsIgnoreCase(penulisBuku)){
                            bukuAda = true;
                            indeksBuku = i;
                            break;
                        }
                    }

                    if (bukuAda){  //Jika buku yang ingin dihapus terdapat di daftarBuku
                        //Buku dapat dihapus jika tidak ada anggota yang sedang meminjam buku tersebut
                        if (daftarBuku[indeksBuku].getDaftarPeminjam().length == 0){
                            //Menghapus buku yang ingin dihapus dari daftarBuku
                            Buku[] tempDaftarBuku = new Buku[daftarBuku.length];
                            for (int i = 0; i < daftarBuku.length; i++){
                                tempDaftarBuku[i] = daftarBuku[i];
                            }
    
                            String output = String.format("Buku %s oleh %s berhasil dihapus", daftarBuku[indeksBuku].getJudul(),
                            daftarBuku[indeksBuku].getPenulis());
                            
                            int indeksList = 0;
                            daftarBuku = new Buku[tempDaftarBuku.length - 1];
                            for (int i = 0; i < tempDaftarBuku.length; i++){
                                if (i != indeksBuku){
                                    daftarBuku[indeksList] = tempDaftarBuku[i];
                                    indeksList += 1;
                                }
                            }
    
                            System.out.println(output);
                        }else{  
                            String output = String.format("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam",
                            daftarBuku[indeksBuku].getJudul(), daftarBuku[indeksBuku].getPenulis());

                            System.out.println(output);
                        }
                    }else{  //Jika buku yang ingin dihapus tidak terdapat di daftarBuku
                        String output = String.format("Buku %s oleh %s tidak ditemukan", judulBuku, penulisBuku);
                        System.out.println(output);
                    }
                }
            } else if (command == 5) {  //Jika command yang diinput adalah 5
                System.out.println("---------- Peringkat Anggota ----------");

                //Mensorting daftarAnggota agar mendapatkan 3 peringkat teratas anggota
                Anggota[] tempDaftarAnggota = daftarAnggota;
                Arrays.sort(tempDaftarAnggota);

                if (daftarAnggota.length == 0){  //Jika belum ada anggota
                    System.out.println("Belum ada anggota yang terdaftar pada sistem");
                }else{  //Jika sudah ada anggota
                    if (daftarAnggota.length == 1){  //Jika hanya ada 1 anggota, akan mencetak ia saja
                        System.out.println("----------------- 1 -----------------");
                        System.out.println(tempDaftarAnggota[0]);
                    }else if (daftarAnggota.length == 2){  //Jika ada 2 anggota, akan mencetak mereka saja
                        for (int i = 0; i < 2; i++){
                            System.out.println("----------------- " + (i+1) + " -----------------");
                            System.out.println(tempDaftarAnggota[i]);
                        }
                    }else{  //Jika terdapat >= 3 anggota, akan mencetak 3 anggota saja
                        for (int i = 0; i < 3; i++){
                            System.out.println("----------------- " + (i+1) + " -----------------");
                            System.out.println(tempDaftarAnggota[i]);
                        }
                    }
                }
            } else if (command == 6) {  //Jika command yang diinput adalah 6
                System.out.println("---------- Detail Anggota ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("ID Anggota: ");
                String iDAnggota = input.nextLine();

                //Mencari tau apakah ID nya ada di array daftarAnggota atau tidak dan mencari indeks membernya
                boolean iDAda = false;   
                int indeksMember = 0;   
                if (daftarAnggota.length != 0){
                    for (int i = 0; i < daftarAnggota.length; i++){
                        if ((daftarAnggota[i].getId()).equals(iDAnggota)){
                            iDAda = true;
                            indeksMember = i;
                            break;
                        }
                    }
                }

                if (iDAda){   //Jika ID ada di array daftarAnggota, maka program akan menampilkan detail member
                    daftarAnggota[indeksMember].detail();
                }else{   //Jika ID tidak ada di array daftarAnggota
                    System.out.println("Anggota dengan ID " + iDAnggota + " tidak ditemukan");
                }
            } else if (command == 7) {  //Jika command yang diinput adalah 7
                System.out.println("---------- Daftar Peminjam Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Judul: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis: ");
                String penulisBuku = input.nextLine();

                if (daftarBuku.length == 0){  //Jika belum ada buku
                    String output = String.format("Buku %s oleh %s tidak ditemukan", judulBuku, penulisBuku);
                    System.out.println(output);
                }else{  //Jika sudah ada buku
                    //Mencari tahu apakah buku yang diinput ada di dalam daftarBuku dan mencari indeksnya
                    boolean bukuAda = false;
                    int indeksBuku = 0;
                    for (int i = 0; i < daftarBuku.length; i++){
                        if (daftarBuku[i].getJudul().equalsIgnoreCase(judulBuku) && 
                        daftarBuku[i].getPenulis().equalsIgnoreCase(penulisBuku)){
                            bukuAda = true;
                            indeksBuku = i;
                            break;
                        }
                    }

                    if (bukuAda){  //Jika buku yang diinput ada di dalam daftarBuku
                        System.out.println(daftarBuku[indeksBuku]);
                        System.out.println("---------- Daftar Peminjam ----------");
                        
                        if (daftarBuku[indeksBuku].getHistoryPeminjam().length == 0){  //Jika belum ada yang meminjam buku
                            System.out.println("Belum ada anggota yang meminjam buku " + daftarBuku[indeksBuku].getJudul());
                        }else{  //Jika sudah ada yang meminjam buku
                            //Program akan mencetak informasi buku dan informasi anggota yang pernah meminjam buku tersebut
                            for (int i = 0; i < daftarBuku[indeksBuku].getHistoryPeminjam().length; i++){
                                System.out.println("----------------- " + (i+1) + " -----------------");
                                System.out.println(daftarBuku[indeksBuku].getHistoryPeminjam()[i]);
                            }
                        }
                    }else{  //Jika buku yang diinput tidak ada di dalam daftarBuku
                        String output = String.format("Buku %s oleh %s tidak ditemukan", judulBuku, penulisBuku);
                        System.out.println(output);
                    }
                }
            } else if (command == 99) {  //Jika command yang diinput adalah 99 = Logout
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {  //Jika command yang dipilih tidak tersedia
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;  //Sebagai flag program akan terus berjalan sampai anggota logout
        while (!hasChosenExit) {
            //Menampilkan menu anggota dan meminta anggota menginput command yang ingin dijalankan
            System.out.println("================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {  //Jika command yang diinput adalah 1
                System.out.println("---------- Peminjaman Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Judul Buku: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulisBuku = input.nextLine();
                System.out.print("Tanggal Peminjaman: ");
                String tanggalPeminjaman = input.nextLine();

                if (daftarBuku.length == 0){  //Jika buku belum ada, tidak dapat meminjam buku
                    System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak ditemukan");
                }else{  //Jika sudah terdapat buku di perpustakaan
                    //Mencari tahu apakah buku terdapat di daftarBuku atau tidak dan mencari indeksnya
                    boolean bukuAda = false;
                    int indeksBuku = 0;
                    for (int i = 0; i < daftarBuku.length; i++){
                        if (daftarBuku[i].getJudul().equalsIgnoreCase(judulBuku) &&
                        daftarBuku[i].getPenulis().equalsIgnoreCase(penulisBuku)){
                            bukuAda = true;
                            indeksBuku = i;
                            break;
                        }
                    }
    
                    if (bukuAda){  //Jika buku yang ingin dipinjam terdapat di daftarBuku
                        if (daftarBuku[indeksBuku].getStok() < 1){  //Jika stok buku < 1, tidak bisa meminjamnya
                            System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak tersedia");
                        }else{  //Jika stok buku >= 1, program akan memproses peminjaman buku oleh anggota
                            Anggota anggota = (Anggota) penggunaLoggedIn;
                            System.out.println(anggota.pinjam(daftarBuku[indeksBuku], tanggalPeminjaman));
                        }
                    }else{  //Jika buku yang ingin dipinjam tidak terdapat di daftarBuku
                        System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak ditemukan");
                    }
                }
            } else if (command == 2) {  //Jika command yang diinput adalah 2
                System.out.println("---------- Pengembalian Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Judul Buku: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulisBuku = input.nextLine();
                System.out.print("Tanggal Pengembalian: ");
                String tanggalPengembalian = input.nextLine();

                //Mencari tahu apakah buku yang ingin dikembalikan terdapat di dalam daftarBuku dan mencari indeksnya
                boolean bukuAda = false;
                int indeksBuku = 0;
                for (int i = 0; i < daftarBuku.length; i++){
                    if (daftarBuku[i].getJudul().equalsIgnoreCase(judulBuku) &&
                    daftarBuku[i].getPenulis().equalsIgnoreCase(penulisBuku)){
                        bukuAda = true;
                        indeksBuku = i;
                        break;
                    }
                }

                if (bukuAda){  //Jika buku ada di daftarBuku, program akan memproses pengembalian buku oleh anggota
                    Anggota anggota = (Anggota) penggunaLoggedIn;
                    System.out.println(anggota.kembali(daftarBuku[indeksBuku], tanggalPengembalian));
                }else{  //Jika buku tidak ada di daftarBuku
                    System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak ditemukan");
                }
            } else if (command == 3) {  //Jika command yang diinput adalah 3
                System.out.println("---------- Pembayaran Denda ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Jumlah: ");
                long jumlahBayar = input.nextLong();

                //Program akan memproses pembayaran denda yang dilakukan oleh anggota
                Anggota anggota = (Anggota) penggunaLoggedIn;
                System.out.println(anggota.bayarDenda(jumlahBayar));
                input.nextLine();
            } else if (command == 4) {  //Jika command yang diinput adalah 4
                //Program akan menampilkan detail anggota
                Anggota anggota = (Anggota) penggunaLoggedIn;
                anggota.detail();
            } else if (command == 99) {  //Jika command yang diinput adalah 99 = Logout
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {  //Jika command yang dipilih tidak tersedia
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    //Method "validasiInput" akan melakukan validasi terhadap input yang diberikan oleh user
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
}
