//Mengimport module yang dibutuhkan oleh program
import java.io.*;
import java.util.*;

public class Main {
    //Membuat beberapa variable data
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    static Mobil[] daftarMobil;
  
    public static void main(String[] args) {      
        int P = in.nextInt();  //Meminta input jumlah mobil yang terdaftar
        daftarMobil = new Mobil[P];  //Membuat array mobil dengan panjang P
        for(int i = 0; i < P; i++){
            String nama = in.next();  //Meminta input nama mobil
            String jenis = in.next();    //Meminta input jenis mobil
            String behaviour = in.next();  //Meminta input behaviour mobil
            String bahanBakar = in.next();  //Meminta input bahanBakar mobil

            //Membuat objek engineBehaviour berdasarkan behaviour mobil
            EngineBehaviour engineBehaviour = null;
            if(behaviour.equals("BBM")) engineBehaviour = new BBM();
            else if(behaviour.equals("Listrik")) engineBehaviour = new Listrik();
            
            //Membuat objek mobil berdasarkan jenis mobil
            if(jenis.equals("Truk")){
                daftarMobil[i] = new MobilTruk(nama, engineBehaviour, bahanBakar);
            } else if(jenis.equals("Terbang")){
                daftarMobil[i] = new MobilTerbang(nama, engineBehaviour, bahanBakar);
            } else {
                daftarMobil[i] = new MobilAir(nama, engineBehaviour, bahanBakar);
            }
        }
        
        int Q = in.nextInt();  //Meminta input jumlah perintah yang ingin dilakukan
        for(int i = 0; i < Q; i++) {
            String nama = in.next();  //Meminta input nama mobil
            String perintah = in.next();  //Meminta input perintah

            Mobil mobilNow = null;
            // Mobil dijamin ada, tidak perlu handle kasus null
            for(Mobil m : daftarMobil) {
                if(m.getNama().equals(nama)){
                    mobilNow = m;
                    break;
                }
            }
            
            //Melakukan perintah berdasarkan input
            if (perintah.equals("START")){
                out.println(mobilNow.start());
            }
            else if (perintah.equals("GAS")){
                out.println(mobilNow.gas());
            }
            else if (perintah.equals("STOP")) {
                out.println(mobilNow.stop());
            }
            else if(perintah.equals("ISI")){
                out.println(mobilNow.isiBahanBakar());
            }
            else if (perintah.equals("SIMULASI")){
                String[] res = mobilNow.simulasi();
                for(String s : res){
                    out.println(s);
                }
            }
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
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