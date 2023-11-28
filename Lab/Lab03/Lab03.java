import java.util.Scanner;

public class Lab03 {
    /*
    Mendeclare beberapa static variable untuk kebutuhan program nanti, agar bisa digunakan dan diubah
    di berbagai method yang ada di program ini.
    */
    static int pointer = 0;
    static String[][] playlist = new String[1][4];
    static Scanner in = new Scanner(System.in);
    static int jumlahMusik = 0;

    public static void main(String[] args) {
        //The beginning of program
        System.out.println("Selamat Datang di Pacilfy!");
        
        boolean lanjutBerhenti = true;   //Sebagai patokan while loop menambahkan lagu
        while(lanjutBerhenti){
            System.out.println("Silahkan masukkan lagu Anda");

            /*
            Meminta user menginput judul, artist, album, dan tahun rilis lagu yang ingin ditambahkan
            ke dalam playlist dan memasukkannya ke dalam array "playlist"
            */
            System.out.print("Judul : ");
            String judul = in.nextLine();
            playlist[jumlahMusik][0] = judul;
            System.out.print("Artist: ");
            String artist = in.nextLine();
            playlist[jumlahMusik][1] = artist;
            System.out.print("Album : ");
            String album = in.nextLine();
            playlist[jumlahMusik][2] = album;
            System.out.print("Tahun : ");
            String tahun = in.nextLine();
            playlist[jumlahMusik][3] = tahun;
            
            jumlahMusik += 1;   //Jumlah musik yang ada di playlist user bertambah 1

            //Bertanya kepada user apakah ingin menambahkan lagu atau tidak
            System.out.println("Lanjut menambahkan lagu?");
            System.out.println("[1] Lanjut");
            System.out.println("[0] Berhenti");
            System.out.print("Perintah: ");
            int perintah = in.nextInt();

            in.nextLine();   //Agar tidak error ketika meminta input bertipe string

            if (perintah == 0){   //Jika user tidak ingin menambahkan lagu, keluar dari while loop
                lanjutBerhenti = false;
            } else if(perintah == 1){   //Jika user ingin menambahkan lagu
                /*
                Memindahkan elemen array "playlist" ke dalam array "tempPlaylist" untuk sementara waktu,
                agar dapat mengexpand array "playlist" (karena user ingin menambahkan lagu lagi
                ke dalam playlistnya)
                */
                int panjangPlaylist = playlist.length;
                String[][] tempPlaylist = new String[jumlahMusik + 1][4];
                for (int i = 0 ; i < panjangPlaylist ; i++){
                    tempPlaylist[i] = playlist[i];
                }

                //Memindahkan elemen array "tempPlaylist" ke dalam array "playlist" yang sudah diexpand
                playlist = new String[jumlahMusik + 1][4];
                for (int i = 0 ; i < panjangPlaylist + 1 ; i++){
                    playlist[i] = tempPlaylist[i];
                }
            }
        }

        //Mencetak kalimat lanjutan program
        System.out.println("Pacilfy siap dimulai");
               
        System.out.println("\nSELAMAT DATANG DI\n");
        System.out.println(" /$$$$$$$                     /$$ /$$  /$$$$$$");
        System.out.println("| $$__  $$                   |__/| $$ /$$__  $$");
        System.out.println("| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$| $$  \\__//$$   /$$");
        System.out.println("| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$$$   | $$  | $$");
        System.out.println("| $$____/  /$$$$$$$| $$      | $$| $$| $$_/   | $$  | $$");
        System.out.println("| $$      /$$__  $$| $$      | $$| $$| $$     | $$  | $$");
        System.out.println("| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$     |  $$$$$$$");
        System.out.println("|__/      \\_______/ \\_______/|__/|__/|__/      \\____  $$");
        System.out.println("                                               /$$  | $$");
        System.out.println("                                              |  $$$$$$/");
        System.out.println("                                               \\______/");

        int command = 1;
        while (true){
            display();  //Menampilkan lagu dan menu yang dapat dipilih oleh user
            System.out.print("Command (0 untuk exit) : ");
            command = Integer.parseInt(in.nextLine());

            //Menjalankan menu yang telah dipilih oleh user
            if (command == 1){
                prevMusic();
            }
            else if (command == 2){
                addMusic();
            }
            else if (command == 3){
                detailsMusic();
            }
            else if (command == 4){
                deleteMusic();
            }
            else if (command == 5){
                nextMusic();
            }
            else if (command == 0){
                break;
            }
            else {
                System.out.println("Maaf, command yang anda masukan salah");
            }
        }

        //The ending of program
        System.out.println("Terima kasih sudah menggunakan Pacilfy!");
    }

    /*
    Method "nextMusic" akan mendengarkan lagu dengan urutan selanjutnya dari lagu yang sedang 
    didengarkan oleh user saat ini
    */
    private static void nextMusic() {
        pointer += 1;   //Mendengarkan lagu urutan selanjutnya
        
        /*
        Jika user sedang mendegarkan lagu terakhir, maka selanjutnya akan mendengarkan lagu
        urutan pertama yang ada di playlist user
        */
        if ((pointer) == (jumlahMusik)){
            pointer = 0;
        } 
    }

    /*
    Method "deleteMusic" akan menghapus lagu yang sedang didengarkan oleh user saat ini dan 
    berpindah ke lagu selanjutnya yang ada di playlist user
    */
    private static void deleteMusic() {
        /*
        Jika jumlah musik yang ada di playlist user hanya 1, maka user tidak dapat menghapus lagu
        tersebut karena harus terdapat minimal 1 lagu di dalam playlist
        */
        if (jumlahMusik == 1){
            System.out.println("Minimal ada satu musik dalam sistem");
        } else{
            //Memasukkan elemen array "playlist" yang tidak dihapus ke dalam array "tempPlaylist"
            int panjangPlaylist = playlist.length;
            int tempIndex = 0;
            String[][] tempPlaylist = new String[panjangPlaylist - 1][4];
            for (int i = 0 ; i < panjangPlaylist ; i++){
                if (i != pointer){
                    tempPlaylist[tempIndex] = playlist[i];
                    tempIndex += 1;
                }
            }
            
            /*
            Memindahkan elemen array "tempPlaylist" ke dalam array "playlist" yang jumlah elemennya
            sudah dikurangi 1
            */
            int panjangTempPlaylist = tempPlaylist.length;
            playlist = new String[panjangTempPlaylist][4];
            for (int i = 0; i < panjangTempPlaylist ; i++){
                playlist[i] = tempPlaylist[i];
            }

            jumlahMusik -= 1;   //Jumlah musik di playlist berkurang 1
        }

        //Menghandle jika lagu yang dihapus adalah lagu terakhir
        if (pointer >= jumlahMusik){
            pointer = 0;
        }
    }

    //Method "detailsMusic" akan menampilkan data lagu sesuai judul yang diinput oleh user
    private static void detailsMusic() {
        System.out.print("Judul yang ingin dicari: ");
        String judulDetails = in.nextLine();  //Meminta user menginput judul lagunya

        /*
        Mendeclare variable lagunyaAda sebagai patokan apakah lagu yang dicari oleh user ada
        atau tidak di dalam playlist user
        */
        boolean lagunyaAda = false;
        
        //Jika lagu yang dicari ada di playlist, maka akan ditampilkan data lagunya
        int panjangPlaylist = playlist.length;
        for (int i = 0; i < panjangPlaylist ; i++){
            if (((playlist[i][0]).toUpperCase()).equals(judulDetails.toUpperCase())){
                System.out.println("Data lagu:");
                System.out.println("Judul : " + playlist[i][0]);
                System.out.println("Artist: " + playlist[i][1]);
                System.out.println("Album : " + playlist[i][2]);
                System.out.println("Tahun : " + playlist[i][3]);
                lagunyaAda = true;
            }
        }

        //Jika lagu yang dicari oleh user tidak ada keberadaannya di dalam playlist
        if (lagunyaAda != true){
            System.out.println("Lagu tidak ditemukan");
        }
    }

    /*
    Method "prevMusic" akan mendengarkan lagu dengan urutan sebelumnya dari lagu yang sedang 
    didengarkan user saat ini
    */
    private static void prevMusic() {
        int panjangPlaylist = playlist.length;
        
        //Menghandle jika lagu yang sedang didengarkan adalah lagu urutan pertama dalam playlist
        if (pointer == 0){
            pointer = panjangPlaylist - 1;
        } else{
            pointer -= 1;
        }
    }

    //Method "addMusic" akan menambahkan lagu ke dalam playlist dengan urutan terakhir
    private static void addMusic() {
        System.out.println("Silahkan masukkan lagu Anda");
        
        /*
        Membuat array baru bernama "tempPlaylist" dengan panjang lebih besar 1 daripada array
        "playlist" dan memindahkan elemen array "playlist" ke dalam array "tempPlaylist"
        */
        int panjangPlaylist = playlist.length;
        String[][] tempPlaylist = new String[panjangPlaylist + 1][4];
        for (int i = 0 ; i < panjangPlaylist ; i++){
            tempPlaylist[i] = playlist[i] ;
        }

        /*
        Meminta user menginput judul, artist, album, dan tahun rilis lagu yang ingin ditambahkan
        ke dalam playlist dan memasukkannya ke dalam array "tempPlaylist"
        */
        System.out.print("Judul : ");
        String judul = in.nextLine();
        tempPlaylist[panjangPlaylist][0] = judul;
        System.out.print("Artist: ");
        String artist = in.nextLine();
        tempPlaylist[panjangPlaylist][1] = artist;
        System.out.print("Album : ");
        String album = in.nextLine();
        tempPlaylist[panjangPlaylist][2] = album;
        System.out.print("Tahun : ");
        String tahun = in.nextLine();
        tempPlaylist[panjangPlaylist][3] = tahun;

        /*
        Memindahkan elemen array "tempPlaylist" ke dalam array "playlist" yang panjangnya sudah
        diexpand
        */
        int panjangTempPlaylist = tempPlaylist.length;
        playlist = new String[panjangTempPlaylist][4];
        for (int i = 0 ; i < panjangTempPlaylist ; i++){
            playlist[i] = tempPlaylist[i];
        }

        jumlahMusik += 1;  //Jumlah musik di playlist bertambah 1
    }

    /*
    Method "display" untuk menampilkan lagu yang sedang didengarkan oleh user saat ini dan menu
    yang dapat dipilih oleh user
    */
    private static void display() {
        System.out.println();
        System.out.println("Currently Playing");

        String displayedMusic = " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";
        String command = "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

        if (displayedMusic.length() < command.length()){
            int width = 62;
            String s = displayedMusic;

            int padSize = width - s.length();
            int padStart = s.length() + padSize / 2;

            s = String.format("%" + padStart + "s", s);
            s = String.format("%-" + width  + "s", s);


            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println("= "+ s +" =");
            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println(command);

            return;
        }

        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println("=" + displayedMusic + "=");
        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println(command);
    }
    
}