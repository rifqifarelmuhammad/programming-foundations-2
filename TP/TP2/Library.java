package assignments.assignment2;

//Mengimport module yang dibutuhkan oleh program
import java.util.Scanner;
import java.util.HashMap;

public class Library {
    //Membuat attribut class dengan modifier private
    private static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    private static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private Scanner input = new Scanner(System.in);
    private Member[] members = new Member[0];
    private Book[] books = new Book[0];
    private Category[] categories = new Category[0];

    public static void main(String[] args) {
        Library program = new Library();
        program.menu();   //Menjalankan method "menu"
    }

    //Method "menu" akan menampilkan pilihan menu dan menjalankan menu yang dipilih oleh user
    public void menu() {
        buildMapCharToValue();
        int command = 0;
        boolean hasChosenExit = false;   //Sebagai flag program menjalankan perintah
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            //Program akan menampilkan menu utama yang ada di program
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");

            //Meminta user menginput pilihan menu apa yang ingin dilakukan
            command = Integer.parseInt(input.nextLine());
            System.out.println();

            if (command == 1) {  //Jika user menginput pilihan menu 1
                System.out.println("---------- Tambah Anggota ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
                String tanggalLahir = input.nextLine();

                if (validasiInput(programStudi, angkatan, tanggalLahir)){   //Melakukan validasi input
                    String iDAnggota = generateId(programStudi, angkatan, tanggalLahir);  //Membuat ID anggota

                    if(members == null){   //Jika belum ada member sama sekali
                        members = new Member[1];  //Memperbarui panjang array members menjadi 1

                        //Membuat objek Member di array members indeks 0
                        members[0] = new Member(iDAnggota, nama, tanggalLahir, programStudi, angkatan);

                        System.out.println("Member " + members[0].getName() + " berhasil ditambahkan dengan data:");
                        System.out.println(members[0]);
                    }else{  //Jika sudah ada member di array members
                        //Program akan menambahkan panjang array members
                        Member[] tempMembers = new Member[members.length];
                        for (int i = 0; i < members.length; i++){
                            tempMembers[i] = members[i];
                        }

                        members = new Member[tempMembers.length + 1];
                        for (int i = 0; i < tempMembers.length; i++){
                            members[i] = tempMembers[i];
                        }

                        //Membuat objek Member di array members indeks terakhir
                        members[members.length-1] = new Member(iDAnggota, nama, tanggalLahir, programStudi, angkatan);

                        System.out.println("Member " + members[members.length-1].getName() + " berhasil ditambahkan dengan data:");
                        System.out.println(members[members.length-1]);
                    }
                }else{   //Jika input yang diterima program tidak sesuai ketentuan
                    System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
                }
                

            } else if (command == 2) {   //Jika user menginput pilihan menu 2
                System.out.println("---------- Tambah Kategori ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("Nama Kategori: ");
                String name = input.nextLine();
                System.out.print("Point: ");
                int point = input.nextInt();

                if (categories == null){   //Jika belum ada category sama sekali
                    categories = new Category[1];  //Memperbarui panjang array categories menjadi 1

                    //Membuat objek Category di array categories indeks 0
                    categories[0] = new Category(name, point);
                    
                    System.out.println("Kategori " + categories[0].getName() + " dengan " + categories[0].getPoint() + 
                    " point berhasil ditambahkan");
                }else{  //Jika sudah ada category di array categories
                    boolean sudahAda = false;   //Sebagai flag apakah category yang diinput user sudah ada atau belum
                    int indeksSama = 0;   //Sebagai patokan indeks category

                    //Mencari tau apakah category yang diinput oleh user sudah ada atau belum dan mencari indeksnya
                    for (int i = 0 ; i < categories.length; i++){
                        if((categories[i].getName().toUpperCase()).equals(name.toUpperCase())){
                            sudahAda = true;
                            indeksSama = i;
                            break;
                        }
                    }

                    if (!sudahAda){  //Jika category yang diinput user belum ada di array categories
                        //Program akan menambahkan panjang array categories
                        Category[] tempCategories = new Category[categories.length];
                        for (int i = 0; i < categories.length; i++){
                            tempCategories[i] = categories[i];
                        }
    
                        categories = new Category[tempCategories.length + 1];
                        for (int i = 0; i < tempCategories.length; i++){
                            categories[i] = tempCategories[i];
                        }
    
                        //Membuat objek Category di array categories indeks terakhir
                        categories[categories.length - 1] = new Category(name, point);
                        System.out.println(categories[categories.length - 1]);
                    }else{   //Jika category yang diinput user sudah ada di array categories
                        System.out.println("Kategori " + categories[indeksSama].getName() + " sudah pernah ditambahkan");
                    }
                }
                input.nextLine();

            } else if (command == 3) {  //Jika user menginput pilihan menu 3
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
                int indeksCategories = 0;   //Sebagai temp indeks categorynya
                
                if (books == null){   //Jika array books masih kosong
                    //Mencari tau indeks category dan apakah categorynya ada atau tidak di array categories
                    if (categories != null){
                        for (int i = 0 ; i < categories.length; i++){
                            if ((categories[i].getName().toUpperCase()).equals(kategori.toUpperCase())){
                                kategoriAda = true;
                                indeksCategories = i;
                                break;
                            }
                        }
                    }
                    
                    if (kategoriAda & stok > 0){   //Jika category ada & stok > 0
                        books = new Book[1];   //Memperbarui panjang array books menjadi 1

                        //Membuat objek Book di array books indeks 0
                        books[0] = new Book(judul, penulis, penerbit, stok, categories[indeksCategories]);

                        System.out.println("Buku " + books[0].getTitle() + " oleh " + books[0].getAuthor() + 
                        " berhasil ditambahkan");
                    }else if (!kategoriAda){   //Jika category tidak ada
                        System.out.println("Kategori " + kategori + " tidak ditemukan");
                    }else if (kategoriAda && stok <= 0){   //Jika category ada tetapi stock <= 0
                        System.out.println("Stok harus lebih dari 0");
                    }
                }else{   //Jika array books tidak kosong
                    boolean sudahAda = false;  //Sebagai flag buku yang diinput sudah ada di array books atau belum
                    
                    //Mencari tau apakah buku yang diinput sudah ada di array books atau belum
                    for (int i = 0 ; i < books.length; i++){
                        if((books[i].getTitle().toUpperCase()).equals(judul.toUpperCase()) && 
                        ((books[i].getAuthor().toUpperCase()).equals(penulis.toUpperCase()))){
                            sudahAda = true;
                            break;
                        }
                    }

                    if (!sudahAda){   //Jika buku belum ada di array books
                        //Mencari tau indeks category dan apakah categorynya ada atau tidak di array categories
                        if (categories != null){
                            for (int i = 0 ; i < categories.length; i++){
                                if ((categories[i].getName().toUpperCase()).equals(kategori.toUpperCase())){
                                    kategoriAda = true;
                                    indeksCategories = i;
                                    break;
                                }
                            }
                        }

                        if (kategoriAda & stok > 0){   //Jika category ada & stok > 0
                            //Program akan menambahkan panjang array books
                            Book[] tempBooks = new Book[books.length];
                            for (int i = 0; i < books.length; i++){
                                tempBooks[i] = books[i];
                            }
                            
                            books = new Book[books.length + 1];
                            for (int i = 0; i < tempBooks.length; i++){
                                books[i] = tempBooks[i];
                            }
        
                            //Membuat objek Book di array books indeks terakhir
                            books[books.length - 1] = new Book(judul, penulis, penerbit, stok, categories[indeksCategories]);
        
                            System.out.println("Buku " + books[books.length - 1].getTitle() + " oleh " + 
                            books[books.length - 1].getAuthor() + " berhasil ditambahkan");
                        }else if (!kategoriAda){   //Jika category tidak ada
                            System.out.println("Kategori " + kategori + " tidak ditemukan");
                        }else if (kategoriAda && stok <= 0){   //Jika category ada tetapi stock <= 0
                            System.out.println("Stok harus lebih dari 0");
                        }
                    }else{   //Jika buku sudah ada di array books
                        System.out.println("Buku " + judul + " oleh " + penulis + " sudah pernah ditambahkan");
                    }
                }
                input.nextLine();

            } else if (command == 4) {   //Jika user menginput pilihan menu 4
                System.out.println("---------- Peminjaman Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("ID Anggota: ");
                String iDAnggota = input.nextLine();
                System.out.print("Judul Buku: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulisBuku = input.nextLine();
                System.out.print("Tanggal Peminjaman: ");
                String tanggalPeminjaman = input.nextLine();

                boolean iDAda = false;   //Sebagai flag apakah ID ada di array members atau tidak

                //Mencari tau apakah ID ada di array members atau tidak
                if (members != null){
                    for (int i = 0; i < members.length; i++){
                        if ((members[i].getId()).equals(iDAnggota)){
                            iDAda = true;
                            break;
                        }
                    }
                }

                if (iDAda){   //Jika ID ada di array members
                    boolean bukuAda = false;   //Sebagai flag apakah bukunya ada atau tidak
                    int indeksBuku = 0;   //Sebagai temp indeks bukunya

                    //Mencari tau apakah bukunya ada di array books atau tidak dan mencari indeks bukunya
                    if (books != null){
                        for (int i = 0; i < books.length ; i++){
                            if ((books[i].getTitle().toUpperCase()).equals(judulBuku.toUpperCase()) &&
                            (books[i].getAuthor().toUpperCase()).equals(penulisBuku.toUpperCase())){
                                bukuAda = true;
                                indeksBuku = i;
                                break;
                            }
                        }
                    }

                    if (bukuAda){   //Jika bukunya ada di array books
                        if ((books[indeksBuku].getStok()) >= 1){  //Jika stok bukunya >= 1
                            int indeksMember = 0;   //Sebagai temp indeks membernya

                            //Mencari tau indeks membernya
                            for (int i = 0; i < members.length; i++){
                                if ((members[i].getId()).equals(iDAnggota)){
                                    indeksMember = i;
                                    break;
                                }
                            }

                            //Mencari tau apakah member sudah melebihi batas maksimal dari peminjaman buku
                            if (members[indeksMember].getBookLoans() == null){
                                if (members[indeksMember].getFine() < 5000){   //Jika denda member < 5000
                                    //Program akan memproses peminjaman buku oleh member
                                    members[indeksMember].pinjam(books[indeksBuku], tanggalPeminjaman);
                                }else{   //Jika denda member >=  5000
                                    System.out.println("Denda lebih dari Rp 5000");
                                }
                            }else if(members[indeksMember].getBookLoans() != null && members[indeksMember].getBookLoans().length < 3){
                                if (members[indeksMember].getFine() < 5000){  //Jika denda member < 5000 
                                    //Program akan memproses peminjaman buku oleh member
                                    members[indeksMember].pinjam(books[indeksBuku], tanggalPeminjaman);
                                }else{  //Jika denda member >=  5000
                                    System.out.println("Denda lebih dari Rp 5000");
                                }
                            }else {
                                System.out.println("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
                            }
                        }else{  //Jika stok bukunya < 1
                            System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak tersedia");
                        }
                    }else{   //Jika bukunya tidak ada di array books
                        System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak ditemukan");
                    }
                }else{   //Jika ID tidak ada di array members
                    System.out.println("Anggota dengan ID " + iDAnggota + " tidak ditemukan");
                }

            } else if (command == 5) {   //Jika user menginput pilihan menu 5
                System.out.println("---------- Pengembalian Buku ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("ID Anggota: ");
                String iDAnggota = input.nextLine();
                System.out.print("Judul Buku: ");
                String judulBuku = input.nextLine();
                System.out.print("Penulis Buku: ");
                String penulisBuku = input.nextLine();
                System.out.print("Tanggal Pengembalian: ");
                String tanggalPengembalian = input.nextLine();

                boolean iDAda = false;  //Sebagai flag apakah ID ada di array members atau tidak
                int indeksMember = 0;   //Sebagai temp indeks member

                //Mencari tau apakah ID ada di array members dan mencari tau indeksnya
                if (members != null){
                    for (int i = 0; i < members.length; i++){
                        if ((members[i].getId()).equals(iDAnggota)){
                            iDAda = true;
                            indeksMember = i;
                            break;
                        }
                    }
                }

                if (iDAda){   //Jika ID ada di array members
                    boolean bukuAda = false;   //Sebagai flag apakah bukunya ada atau tidak
                    int indeksBukuDipinjam = 0;   //Sebagai temp indeks bukunya

                    //Mencari tau apakah bukunya ada di array books atau tidak dan mencari indeks bukunya
                    for(int i = 0; i < books.length; i++){
                        if ((books[i].getTitle().toUpperCase()).equals(judulBuku.toUpperCase()) &&
                        (books[i].getAuthor().toUpperCase()).equals(penulisBuku.toUpperCase())){
                            bukuAda = true;
                            indeksBukuDipinjam = i;
                            break;
                        }
                    }

                    if (bukuAda){   //Jika bukunya ada di array books
                        boolean bukuDipinjam = false;   //Sebagai flag apakah bukunya dipinjam member atau tidak

                        //Mencari tau apakah bukunya sedang dipinjam atau tidak
                        if (members[indeksMember].getBookLoans() != null){
                            for (int i = 0; i < members[indeksMember].getBookLoans().length; i++){
                                if ((members[indeksMember].getBookLoans()[i].getBook().getTitle()).equalsIgnoreCase(judulBuku)
                                && members[indeksMember].getBookLoans()[i].getBook().getAuthor().equalsIgnoreCase(penulisBuku)){
                                    bukuDipinjam = true;
                                    break;
                                }
                            }
                        }

                        if (bukuDipinjam){   //Jika buku sedang dipinjam, program akan memproses pengembalian bukunya
                            members[indeksMember].kembali(books[indeksBukuDipinjam], tanggalPengembalian);
                        }else{   //Jika buku tidak sedang dipinjam oleh member
                            System.out.println("Buku " + judulBuku + " tidak sedang dipinjam");
                        }
                    }else{  //Jika bukunya tidak ada di array books
                        System.out.println("Buku " + judulBuku + " oleh " + penulisBuku + " tidak ditemukan");
                    }
                }else{   //Jika ID tidak ada di array members
                    System.out.println("Anggota dengan ID " + iDAnggota + " tidak ditemukan");
                }

            } else if (command == 6) {  //Jika user menginput pilihan menu 6
                System.out.println("---------- Pembayaran Denda ----------");

                //Program akan meminta beberapa data yang dibutuhkan
                System.out.print("ID Anggota: ");
                String iDAnggota = input.nextLine();
                System.out.print("Jumlah: ");
                long jumlah = input.nextLong();

                boolean iDAda = false;  //Flag apakah ID ada di array members atau tidak
                int indeksMember = 0;   //Sebagai temp indeks membernya

                //Mencari tau apakah ID nya ada di array members atau tidak dan mencari indeks membernya
                if (members != null){
                    for (int i = 0; i < members.length; i++){
                        if ((members[i].getId()).equals(iDAnggota)){
                            iDAda = true;
                            indeksMember = i;
                            break;
                        }
                    }
                }

                if (iDAda){   //Jika ID ada di array members, program akan memproses pembayaran dendanya
                    members[indeksMember].bayarDenda(jumlah);
                }else{   //Jika ID tidak ada di array members
                    System.out.println("Anggota dengan ID " + iDAnggota + " tidak ditemukan");
                }
                input.nextLine();

            } else if (command == 7) {   //Jika user menginput pilihan menu 7
                System.out.println("---------- Detail Anggota ----------");

                //Program akan meminta user menginput ID anggota yang ingin ditampilkan detailnya
                System.out.print("ID Anggota: ");
                String iDAnggota = input.nextLine();

                boolean iDAda = false;   //Flag apakah ID ada di array members atau tidak
                int indeksMember = 0;   //Sebagai temp indeks membernya

                //Mencari tau apakah ID nya ada di array members atau tidak dan mencari indeks membernya
                if (members != null){
                    for (int i = 0; i < members.length; i++){
                        if ((members[i].getId()).equals(iDAnggota)){
                            iDAda = true;
                            indeksMember = i;
                            break;
                        }
                    }
                }

                if (iDAda){   //Jika ID ada di array members, maka program akan menampilkan detail member
                    System.out.println(members[indeksMember]);
                    members[indeksMember].detail();
                }else{   //Jika ID tidak ada di array members
                    System.out.println("Anggota dengan ID " + iDAnggota + " tidak ditemukan");
                }


            } else if (command == 8) {   //Jika user menginput pilihan menu 8
                if (members.length != 0){   //Jika ada member di array members
                    System.out.println("---------- Peringkat Anggota ----------");

                    /*
                        Jika panjang members >= 3, maka program akan mencari 3 peringkat tertinggi dengan bantuan 2 array
                        tambahan. Proses yang dilakukan adalah mensortir tiap point yang dimiliki oleh member untuk
                        mencari point tertinggi. Jika program sudah menemukan point tertinggi, maka program akan
                        memasukkan members tersebut ke dalam array khusus yang bernama "rankMembers". Setelah itu,
                        program akan menghilangkan members dari sortiran selanjutnya. Begipula untuk pensortiran
                        selanjutnya. Jika terdapat anggota dengan point yang sama, maka pensortiran akan dilakukan
                        berdasarkan abjad nama anggota
                    */
                    if (members.length >= 3){
                        Member[] rankMembers = new Member[3];
                        Member[] tempmMembers1 = new Member[members.length];

                        int highestindex1 = 0;
                        for (int i = 1; i < members.length; i++){
                            if (members[highestindex1].getPoint() < members[i].getPoint()){
                                highestindex1 = i;
                            }else if (members[highestindex1].getPoint() == members[i].getPoint()){
                                boolean characterSama = false;
                                if (members[highestindex1].getName().length() > members[i].getName().length()){
                                    for (int j = 0; j < members[i].getName().length(); j++){
                                        if (members[highestindex1].getName().charAt(j) > members[i].getName().charAt(j)){
                                            highestindex1 = i;
                                            characterSama = false;
                                            break;
                                        }else if (members[highestindex1].getName().charAt(j) == members[i].getName().charAt(j)){
                                            characterSama = true;
                                        }else{
                                            characterSama = false;
                                            break;
                                        }
                                    }

                                    if (characterSama){
                                        highestindex1 = i;
                                    }
                                }else{
                                    for (int j = 0; j < members[highestindex1].getName().length(); j++){
                                        if (members[highestindex1].getName().charAt(j) > members[i].getName().charAt(j)){
                                            highestindex1 = i;
                                            break;
                                        }else if(members[highestindex1].getName().charAt(j) < members[i].getName().charAt(j)){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        rankMembers[0] = members[highestindex1];

                        for (int i = 0; i < members.length; i++){
                            tempmMembers1[i] = members[i];
                        }

                        Member[] tempmMembers2 = new Member[tempmMembers1.length - 1];
                        int indeksList = 0;
                        for (int i = 0; i < tempmMembers1.length; i++){
                            if (i != highestindex1){
                                tempmMembers2[indeksList] = tempmMembers1[i];
                                indeksList += 1;
                            }
                        }

                        int highestindex2 = 0;
                        for (int i = 1; i < tempmMembers2.length; i++){
                            if (tempmMembers2[highestindex2].getPoint() < tempmMembers2[i].getPoint()){
                                highestindex2 = i;
                            }else if (tempmMembers2[highestindex2].getPoint() == tempmMembers2[i].getPoint()){
                                boolean characterSama = false;
                                if (tempmMembers2[highestindex2].getName().length() > tempmMembers2[i].getName().length()){
                                    for (int j = 0; j < tempmMembers2[i].getName().length(); j++){
                                        if (tempmMembers2[highestindex2].getName().charAt(j) > tempmMembers2[i].getName().charAt(j)){
                                            highestindex2 = i;
                                            characterSama = false;
                                            break;
                                        }else if (tempmMembers2[highestindex2].getName().charAt(j) == tempmMembers2[i].getName().charAt(j)){
                                            characterSama = true;
                                        }else{
                                            characterSama = false;
                                            break;
                                        }
                                    }

                                    if (characterSama){
                                        highestindex2 = i;
                                    }
                                }else{
                                    for (int j = 0; j < tempmMembers2[highestindex2].getName().length(); j++){
                                        if (tempmMembers2[highestindex2].getName().charAt(j) > tempmMembers2[i].getName().charAt(j)){
                                            highestindex2 = i;
                                            break;
                                        }else if (tempmMembers2[highestindex2].getName().charAt(j) < tempmMembers2[i].getName().charAt(j)){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        rankMembers[1] = tempmMembers2[highestindex2];

                        tempmMembers1 = new Member[tempmMembers2.length - 1];
                        indeksList = 0;
                        for (int i = 0; i < tempmMembers2.length; i++){
                            if (i != highestindex2){
                                tempmMembers1[indeksList] = tempmMembers2[i];
                                indeksList += 1;
                            }
                        }

                        if (members.length > 3){   //Jika panjang array members > 3
                            int highestindex3 = 0;
                            for (int i = 1; i < tempmMembers1.length; i++){
                                if (tempmMembers1[highestindex3].getPoint() < tempmMembers1[i].getPoint()){
                                    highestindex3 = i;
                                }else if (tempmMembers1[highestindex3].getPoint() == tempmMembers1[i].getPoint()){
                                    boolean characterSama = false;
                                    if (tempmMembers1[highestindex3].getName().length() > tempmMembers1[i].getName().length()){
                                        for (int j = 0; j < tempmMembers1[i].getName().length(); j++){
                                            if (tempmMembers1[highestindex3].getName().charAt(j) > tempmMembers1[i].getName().charAt(j)){
                                                highestindex3 = i;
                                                characterSama = false;
                                                break;
                                            }else if (tempmMembers1[highestindex3].getName().charAt(j) == tempmMembers1[i].getName().charAt(j)){
                                                characterSama = true;
                                            }else{
                                                characterSama = false;
                                                break;
                                            }
                                        }
    
                                        if (characterSama){
                                            highestindex3 = i;
                                        }
                                    }else{
                                        for (int j = 0; j < tempmMembers1[highestindex3].getName().length(); j++){
                                            if (tempmMembers1[highestindex3].getName().charAt(j) > tempmMembers1[i].getName().charAt(j)){
                                                highestindex3 = i;
                                                break;
                                            }else if (tempmMembers1[highestindex3].getName().charAt(j) < tempmMembers1[i].getName().charAt(j)){
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            rankMembers[2] = tempmMembers1[highestindex3];

                            for (int i = 0; i < 3; i++){
                                System.out.println("----------------- " + (i+1) + " -----------------");
                                System.out.println(rankMembers[i]);
                            }
                        }else {   //Jika panjang array members = 3
                            for (int i = 0; i < 2; i++){
                                System.out.println("----------------- " + (i+1) + " -----------------");
                                System.out.println(rankMembers[i]);
                            }
                            System.out.println("----------------- " + 3 + " -----------------");
                            System.out.println(tempmMembers1[0]);
                        }

                    }else {   //Jika panjang array members < 3
                        if (members.length == 1){  //Jika panjang array members = 1
                            System.out.println("----------------- " + 1 + " -----------------");
                            System.out.println(members[0]);
                        }else{
                            /*
                                Jika panjang array members adalah 2, maka akan melakukan hal yang sama dengan kalau
                                panjang members >= 3
                            */
                            if (members[0].getPoint() > members[1].getPoint()){
                                System.out.println("----------------- " + 1 + " -----------------");
                                System.out.println(members[0]);
                                System.out.println("----------------- " + 2 + " -----------------");
                                System.out.println(members[1]);
                            }else if (members[0].getPoint() < members[1].getPoint()){
                                System.out.println("----------------- " + 1 + " -----------------");
                                System.out.println(members[1]);
                                System.out.println("----------------- " + 2 + " -----------------");
                                System.out.println(members[0]);
                            }else{
                                boolean characterSama = false;
                                boolean nolLebihBesar = false;
                                if (members[0].getName().length() > members[1].getName().length()){
                                    for (int i = 0; i < members[1].getName().length(); i++){
                                        if (members[0].getName().charAt(i) > members[1].getName().charAt(i)){
                                            nolLebihBesar = false;                                            
                                            characterSama = false;
                                        }else if (members[0].getName().charAt(i) == members[1].getName().charAt(i)){
                                            characterSama = true;
                                        }else{
                                            nolLebihBesar = true; 
                                            characterSama = false;
                                        }
                                    }

                                    if (characterSama){
                                        nolLebihBesar = false;
                                    }
                                }else{
                                    for (int i = 0; i < members[0].getName().length(); i++){
                                        if (members[0].getName().charAt(i) > members[1].getName().charAt(i)){
                                            nolLebihBesar = false;                                            
                                            characterSama = false;
                                        }else if (members[0].getName().charAt(i) == members[1].getName().charAt(i)){
                                            characterSama = true;
                                        }else{
                                            nolLebihBesar = true;  
                                            characterSama = false;
                                        }
                                    }

                                    if (characterSama){
                                        nolLebihBesar = true;
                                    }
                                }

                                if (nolLebihBesar){
                                    System.out.println("----------------- " + 1 + " -----------------");
                                    System.out.println(members[0]);
                                    System.out.println("----------------- " + 2 + " -----------------");
                                    System.out.println(members[1]); 
                                }else{
                                    System.out.println("----------------- " + 1 + " -----------------");
                                    System.out.println(members[1]);
                                    System.out.println("----------------- " + 2 + " -----------------");
                                    System.out.println(members[0]); 
                                }
                            }
                        }
                    }
                }else{   //Jika belum ada member di array members
                    System.out.println("---------- Peringkat Anggota ----------");
                    System.out.println("Belum ada anggota yang terdaftar pada sistem");
                }


            } else if (command == 99) {    //Jika user menginput pilihan menu 99, maka program akan berhenti
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {   //Jika user tidak menginput pilihan menu yang sesuai
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }

        input.close();
    }
    
    //Method "buildMapCharToValue" adalah method untuk membuat mapping reference numbers Code 93
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    private static int getValueFromChar(char character) {
        return charToValue.get(character);
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
            return "" + iDTemp1 + cK;
        } else{   //Jika data yang diinput oleh user belum memenuhi standar
            return "Input tidak valid!";
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
