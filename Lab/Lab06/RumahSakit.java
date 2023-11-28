//Mengimport module yang dibutuhkan
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

class RumahSakit {
    //Membuat attribut class
    private static InputReader in;
    private static PrintWriter out;
    Warga[] daftarWarga = new Warga[0];

    //Method "handleLog" berfungsi untuk menampilkan Log Warga tersebut
    static private void handleLog(Warga X) {
		//Jika X adalah pasien, akan mencetak happiness dan status kesembuhannya
        if (X instanceof Pasien) { 
            Pasien pasien = (Pasien) X;
			out.println(pasien.getHappiness()); 
			out.println(pasien.getStatusSembuh());
		} else { // Jika X adalah dokter, akan mencetak jumlah pasien yang ditemuinya
            Dokter dokter = (Dokter) X;
			out.println(dokter.getJumlahPasienDitemui());
		}

		//Mencetak Warga lain yang berinteraksi dengannya
        for (int i = 0; i < X.getLogInteraksi().length; i++){ 
			out.println(X.getLogInteraksi()[i]); 
		}
		out.println("------------");;
        
    }

    /*
    Method "handleInteraksi" berfungsi untuk melakukan hal-hal yang sudah ditentukan
    berdasarkan X berinteraksi dengan dokter atau pasien, begitu juga dengan Y.
    */
    static private void handleInteraksi(Warga X, Warga Y) {
		X.berinteraksi(Y);
		Y.berinteraksi(X);
    }

    //Method untuk menambahkan daftar warga
    private void masukkanKeDaftarWarga(Warga objWarga){
        Warga[] newDaftarWarga = new Warga[this.daftarWarga.length+1];

        for(int i = 0; i < this.daftarWarga.length; i++){
            newDaftarWarga[i] = this.daftarWarga[i];
        }
        this.daftarWarga = newDaftarWarga;

        newDaftarWarga[this.daftarWarga.length -1] = objWarga;
    }

    //Method untuk mendapat objek warga berdasarkan nama
    private Warga getWarga(String nama){
        for(Warga warga: this.daftarWarga){
            if(warga.getNama().equalsIgnoreCase(nama)){
                return warga;
            }
        }
        return null;
    }

    //Method "mainProgram" merupakan susunan program utama
    private void mainProgram(){
        //Membuat objek InputReader & PrintWriter untuk I/O program nanti
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        //Membuat variable bertipe int yang berisi banyaknya perintah yang akan dilakukan
        int N; 
        
        N = in.nextInt();  //Meminta input banyaknya perintah yang akan dilakukan
        for(int tmp=0;tmp<N;tmp++) {
            String event = in.next();  //Meminta jenis instruki yang akan dilakukan

            if(event.equals("ADD")) {  //Jika instruksinya "ADD"
                String roleWarga = in.next();  //Meminta input role warga
                String nama = in.next();  //Meminta input nama warga
				if (roleWarga.equals("DOKTER")){  //Jika role warga adalah DOKTER
					String penyakitKeahlian = in.next();  //Meminta input penyakitKeahlihan

                    //Meminta input dokter ramah atau tidak
					boolean dokterRamah = in.next().equals("Yes") ? true : false;

                    //Membuat objek Dokter dan memasukkannya ke array "daftarWarga"
					Dokter dokter = new Dokter(nama, penyakitKeahlian, dokterRamah);
                    masukkanKeDaftarWarga(dokter);
				} else {  //Jika role warga adalah PASIEN
					String penyakit = in.next();  //Meminta input penyakit pasien

                    //Membuat objek Pasien dan memasukkannya ke array "daftarWarga"
					Pasien pasien = new Pasien(nama, penyakit);
                    masukkanKeDaftarWarga(pasien);
				}
            } else if(event.equals("INTERAKSI")) {  //Jika instruksinya "INTERAKSI"
                String X = in.next();  //Meminta input warga 1
				String Y = in.next();  //Meminta input warga 2

                //Menjalankan method "handleInteraksi" kepada warga 1 dan 2
				handleInteraksi(getWarga(X), getWarga(Y));
            } else {  //Jika instruksinya "LOG"
                String X = in.next();  //Meminta input warganya

                handleLog(getWarga(X));  //Menjalankan method "handleLog" kepada warga
            }
        }

        out.flush();
    }

    public static void main(String[] args) throws IOException{
        RumahSakit rs = new RumahSakit();  //Membuat objek RumahSakit
        rs.mainProgram();  //Menjalankan method "mainProgram"
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
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