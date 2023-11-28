package assignments.assignment3.pengguna;

//Mengimport beberapa class yang dibutuhkan oleh class Dosen
import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

//Class Dosen merupakan subclass dari class Anggota
public class Dosen extends Anggota{
    //Membuat attribut class dengan modifier public & private
    private static int jumlahDosen = 0;
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static long BATAS_MAKSIMAL_DENDA = 10000;

    //Membuat constructor dengan 1 parameter & mengset id Dosen
    public Dosen(String nama){
        super(nama);
        super.setId(generateId());
    }
    
    //Mengoverride method "generateId" agar sesuai dengan kebutuhan class Dosen
    protected String generateId(){
        jumlahDosen += 1;
        String id = String.format("DOSEN-%d", jumlahDosen);
        return id;
    }

    //Mengoverride method "pinjam" agar sesuai dengan kebutuhan class Dosen
    //Method "pinjam" berguna untuk memproses peminjaman buku yang dilakukan oleh Dosen
    public String pinjam(Buku buku, String tanggalPeminjaman){
        //Memastikan bahwa peminjaman aktif dosen tidak lebih dari batas maksimal peminjaman buku
        int countStatus = 0;
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if (daftarPeminjaman[i].getStatus()){
                countStatus += 1;
            }
        }

        if (countStatus >= BATAS_JUMLAH_PEMINJAMAN_BUKU){  //Jika peminjaman aktif melebihi batas maksimal
            return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        }else{  //Jika peminjaman aktif belum melebihi batas maksimal
            if (denda >= BATAS_MAKSIMAL_DENDA){  //Jika denda dosen >= 10000
                return "Denda lebih dari Rp10000";
            }else{  //Jika denda dosen < 10000
                if (daftarPeminjaman.length == 0){   //Jika dosen belum meminjam buku sama sekali
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
}
