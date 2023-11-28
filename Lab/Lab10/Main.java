//Mengimport beberapa module yang diperlukan oleh program
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    //Membuat attribut class dengan modifier private
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    // Gunakan out sebagai pengganti System.out
    // out ini akan menahan output sampai dia di-(close/flush)
    // Contoh jika ingin print("merah"), maka tulis out.print("merah")

    private static DaftarPesanan<Makanan> daftarMakanan = new DaftarPesanan<>();
    private static DaftarPesanan<Minuman> daftarMinuman = new DaftarPesanan<>();

    public static void main(String[] args) {
        //Meminta input jumlah makanan dan minuman yang ingin diproses
        int jumlahMakanan = in.nextInt();
        int jumlahMinuman = in.nextInt();

        //Menambahkan pesanan makanan ke dalam daftarMakanan
        for (int i = 0; i < jumlahMakanan; i++) {
            //Mengumpulkan beberapa data yang dibutuhkan
            String namaMakanan = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            int tingkatKepedasan = in.nextInt();

            //Membuat objek Makanan baru dan menambahkannya ke daftarMakanan
            daftarMakanan.tambahPesanan(new Makanan(namaMakanan, harga, prioritas, tingkatKepedasan));
        }

        //Menambahkan pesanan minuman ke dalam daftarMinuman
        for (int i = 0; i < jumlahMinuman; i++) {
            //Mengumpulkan beberapa data yang dibutuhkan
            String namaMinuman = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            boolean isPakeEs = in.next().equals("YES");

            //Membuat objek Minuman baru dan menambahkannya ke daftarMinuman
            daftarMinuman.tambahPesanan(new Minuman(namaMinuman, harga, prioritas, isPakeEs));
        }

        while (true) {
            String command = in.next();

            //Jika commandnya adalah "KELUAR", maka program akan berhenti
            if (command.equals("KELUAR")) {
                break;
            }

            //Jika tidak, maka akan diproses pesanannya
            String tipe = in.next();
            if (tipe.equals("MAKANAN")) {
                try{  //Jika masih terdapat pesanan makanan
                    out.println(daftarMakanan.nextPesanan() + " telah disajikan.");
                }catch (IndexOutOfBoundsException e){  //Jika sudah tidak ada pesanan makanan
                    out.println("Semua pesanan makanan telah disajikan!");
                }
            } else {
                try{  //Jika masih terdapat pesanan minuman
                    out.println(daftarMinuman.nextPesanan() + " telah disajikan.");
                }catch (IndexOutOfBoundsException e){  //Jika sudah tidak ada pesanan minuman
                    out.println("Semua pesanan minuman telah disajikan!");
                }
            }
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    private static class InputReader {
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
