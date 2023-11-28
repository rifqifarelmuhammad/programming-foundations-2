//Mengimport module yang dibutuhkan oleh program
import java.io.*;
import java.util.*;

public class Kasir {
    //Membuat atribut class
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    static Barang[] barang;
    static Pelanggan[] pelanggan;
    static int N, M;

    //Method "cariPelanggan" untuk mencari data pelanggan berdasarkan namanya
    static Pelanggan cariPelanggan(String nama) {
        for (Pelanggan p: pelanggan) {
            if (nama.equals(p.getNama())) {
                return p;
            }
        }
        return null;
    }
    
    //Method "cariBarang" untuk mencari data barang berdasarkan namanya
    static Barang cariBarang(String namaBarang) {
        for (Barang b: barang) {
            if (namaBarang.equals(b.getNama())) {
                return b;
            }
        }
        return null;
    }

    //Method "kasir" untuk menentukan apa yang akan diprint oleh program
    static void kasir(Pelanggan K){
        Order[] keranjang = K.getKeranjang();
        boolean punyaBarang = false;   //Sebagai flag apakah keranjang kosong atau tidak
        for (int i = 0 ; i < keranjang.length; i++){
            if (keranjang[i] != null){   //flag dirubah karena keranjang tidak kosong
                punyaBarang = true;
            }
        }

        if (!punyaBarang){   //Jika keranjang pelanggan K kosong
            out.println("Maaf tidak ada barang di keranjang " + K.getNama());
        }

        //Jika uang pelanggan K kurang dari total harga barang yang ada di keranjangnya
        if ((K.getUang() - K.totalHargaBarang()) < 0){
            out.println("Maaf " + K.getNama() + " tidak memiliki cukup uang");
            K.cekUang();
        }

        //Jika keranjang pelanggan K tidak kosong dan uangnya mencukupi
        if (punyaBarang && ((K.getUang() - K.totalHargaBarang()) >= 0)){
            out.println("Pembelian " + K.getNama() + " berhasil:");
            for (int i = 0 ; i < keranjang.length; i++){
                if (keranjang[i] == null){
                    continue;
                }else{
                    out.println("* " + keranjang[i].getBarang().getNama() + " " + keranjang[i].getBanyakBarang() + 
                    " = " + (keranjang[i].getBanyakBarang() * keranjang[i].getBarang().getHarga()));
                }
            }

            out.println("* Total Belanjaan = " + K.totalHargaBarang());
            out.println("* Sisa Uang = " + (K.getUang() - K.totalHargaBarang()));
            K.setUang(K.getUang() - K.totalHargaBarang());
            K.newKeranjang(N);
        }
    }
    
    public static void main(String[] args) {
        N = in.nextInt();   //Meminta user menginput jumlah jenis barang
        barang = new Barang[N];
        for (int i = 0; i < N; i++) {
            String namaBarang = in.next();
            int hargaBarang = in.nextInt();
            int beratBarang = in.nextInt();
            int stock = in.nextInt();
            
            //Membuat new object Barang di array barang[i]
            barang[i] = new Barang(namaBarang, hargaBarang, beratBarang, stock);
        }
        
        M = in.nextInt();   //Meminta user menginput jumlah pelanggan
        pelanggan = new Pelanggan[M];
        for (int j = 0; j < M; j++) {
            String namaPelanggan = in.next();
            int uang = in.nextInt();

            //Membuat new object Pelanggan di array pelanggan[j]
            pelanggan[j] = new Pelanggan(namaPelanggan, uang, N);
        }
        
        int P = in.nextInt();   //Meminta user menginput berapa fungsi yang ingin dilakukan
        for (int k = 0; k < P; k++) {
            String command = in.next();  //Meminta user menginput fungsinya
            
            //Menjalankan fungsi sesuai yang diinput oleh user
            if (command.equals("ADD")) {
                String namaPelanggan = in.next();
                String namaBarang = in.next();
                int banyakBarangDiambil = in.nextInt();
                
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.println(plg.addBarang(cariBarang(namaBarang), banyakBarangDiambil));
            }
            
            if (command.equals("TOTAL_HARGA")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.printf("Total harga belanjaan %s adalah %d%n", plg.getNama(), plg.totalHargaBarang());
            }
            
            if (command.equals("KASIR")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                kasir(plg);
            }
            
            if (command.equals("CEK_UANG")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.println(plg.cekUang());
            }
        }
        System.out.println();
        out.close(); 
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}