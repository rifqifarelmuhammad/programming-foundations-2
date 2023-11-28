package assignments.assignment3.pengguna;

//Mengimport module yang dibutuhkan oleh class Mahasiswa
import java.util.HashMap;

//Mengimport beberapa class yang dibutuhkan oleh class Mahasiswa
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

//Class Mahasiswa merupakan subclass dari class Anggota
public class Mahasiswa extends Anggota{
    //Membuat attribut class dengan modifier public & private
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;
    private static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    private static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    //Membuat constructor dengan 4 parameter & mengset id Mahasiswa
    public Mahasiswa(String nama, String programStudi, String angkatan, String tanggalLahir){
        super(nama);
        this.programStudi = programStudi;
        this.angkatan = angkatan;
        this.tanggalLahir = tanggalLahir;
        super.setId(generateId());
    }
    
    //Mengoverride method "generateId" agar sesuai dengan kebutuhan class Mahasiswa
    protected String generateId(){
        String iDAnggota = generateId(programStudi, angkatan, tanggalLahir);  //Membuat ID anggota
        return iDAnggota;
    }

    //Mengoverride method "pinjam" agar sesuai dengan kebutuhan class Mahasiswa
    //Method "pinjam" berguna untuk memproses peminjaman buku yang dilakukan oleh Mahasiswa
    public String pinjam(Buku buku, String tanggalPeminjaman){
        //Memastikan bahwa peminjaman aktif mahasiswa tidak lebih dari batas maksimal peminjaman buku
        int countStatus = 0;
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if (daftarPeminjaman[i].getStatus()){
                countStatus += 1;
            }
        }

        if (countStatus >= BATAS_JUMLAH_PEMINJAMAN_BUKU){  //Jika peminjaman aktif melebihi batas maksimal
            return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        }else{  //Jika peminjaman aktif belum melebihi batas maksimal
            if (denda >= BATAS_MAKSIMAL_DENDA){  //Jika denda mahasiswa >= 5000
                return "Denda lebih dari Rp5000";
            }else{  //Jika denda mahasiswa < 5000
                if (daftarPeminjaman.length == 0){   //Jika anggota belum meminjam buku sama sekali
                    //Melakukan pemrosesan peminjaman buku
                    daftarPeminjaman = new Peminjaman[1];
                    daftarPeminjaman[0] = new Peminjaman(this, buku, tanggalPeminjaman);
                    buku.stokBerkurang();   
                    buku.peminjamBertambah(this);  
        
                    return String.format("%s berhasil meminjam Buku %s!", super.getNama(), buku.getJudul());
                }else{   //Jika member sedang meminjam beberapa buku
                    //Mencari tahu terlebih dahulu apakah buku sedang dipinjam atau tidak
                    boolean bukuDipinjam = false; 
                    for (int i = 0; i < daftarPeminjaman.length; i++){
                        if (daftarPeminjaman[i].getBuku() == buku && daftarPeminjaman[i].getStatus() == true){
                            bukuDipinjam = true;
                            break;
                        }
                    }
                    
                    if (bukuDipinjam){  //Jika buku sedang dipinjam
                       return String.format("Buku %s oleh %s sedang dipinjam", buku.getJudul(), buku.getPenulis());
                    }else{  //Jika buku tidak sedang dipinjam
                        //Menambahkan objek baru Peminjaman ke dalam list daftarPeminjaman
                        Peminjaman[] tempDaftarPeminjaman = new Peminjaman[daftarPeminjaman.length];
                        for (int i = 0; i < daftarPeminjaman.length; i++){
                            tempDaftarPeminjaman[i] = daftarPeminjaman[i];
                        }
            
                        daftarPeminjaman = new Peminjaman[daftarPeminjaman.length + 1];
                        for (int i = 0; i < tempDaftarPeminjaman.length; i++){
                            daftarPeminjaman[i] = tempDaftarPeminjaman[i];
                        }
            
                        //Melakukan pemrosesan peminjaman buku
                        daftarPeminjaman[daftarPeminjaman.length - 1] = new Peminjaman(this, buku, tanggalPeminjaman);
                        buku.stokBerkurang();   
                        buku.peminjamBertambah(this);  

                        return String.format("%s berhasil meminjam Buku %s!", super.getNama(), buku.getJudul());
                    }
                }
            }
        }
    }

    //Method tambahan untuk membuat id Mahasiswa
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

    //Method "generateId" adalah method untuk membuat ID Mahasiswa
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        buildMapCharToValue();

        //Mempersiapkan data-data yang diperlukan untuk disatukan kedalam variable "iDTemp1"
        String twoLastAngkatan = angkatan.substring(2);
        tanggalLahir = tanggalLahir.replace("/", "");
        String tanggalBulan = tanggalLahir.substring(0, 4);
        String tahun = tanggalLahir.substring(6);
        String iDTemp1 = programStudi + twoLastAngkatan + tanggalBulan + tahun;

        String cK = checksumCK(iDTemp1);   //Mencari checksum C & K dari data yang diinput oleh user

        //Mereturn ID keanggotaan perpustakaan yang telah dibuat
        return "" + iDTemp1 + cK;
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
