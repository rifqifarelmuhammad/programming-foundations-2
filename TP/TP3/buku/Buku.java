package assignments.assignment3.buku;

//Mengimport beberapa class yang dibutuhkan oleh class Buku
import assignments.assignment3.pengguna.Anggota;
import assignments.assignment3.pengguna.CanBorrow;

public class Buku {
    //Membuat attribut class dengan modifier private
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private CanBorrow[] daftarPeminjam = new CanBorrow[0];
    private CanBorrow[] historyPeminjam = new CanBorrow[0];

    //Membuat constructor dengan 5 parameter
    public Buku(String judul, String penulis, String penerbit, int stokAwal, Kategori kategori){
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stokAwal = stokAwal;
        this.kategori = kategori;
        this.stok = stokAwal;
    }
    
    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Buku
    public String toString(){
        return String.format("Judul Buku: %s\nPenulis Buku: %s\nPenerbit Buku: %s\n",
        judul, penulis, penerbit) + kategori.toString();
    }

    //Method "stokBerkurang" akan mengurangi stok buku jika ada yang meminjamnya
    public int stokBerkurang(){
        return this.stok -= 1;
    }

    //Method "stokBertambah" akan menambahkan stok buku jika ada yang mengembalikannya
    public int stokBertambah(){
        return this.stok += 1;
    }
    
    /*
    Method "peminjamBertambah" akan menambahkan angggota yang meminjam buku ke dalam list
    daftarPeminjam dan historyPeminjam
    */
    public void peminjamBertambah(Anggota anggota){
        CanBorrow[] tempDaftarPeminjam = new CanBorrow[daftarPeminjam.length];
        for (int i = 0; i < daftarPeminjam.length; i++){
            tempDaftarPeminjam[i] = daftarPeminjam[i];
        }

        daftarPeminjam = new CanBorrow[tempDaftarPeminjam.length + 1];
        for (int i = 0; i < tempDaftarPeminjam.length; i++){
            daftarPeminjam[i] = tempDaftarPeminjam[i];
        }

        daftarPeminjam[daftarPeminjam.length - 1] = anggota;

        CanBorrow[] tempHistoryPeminjam = new CanBorrow[historyPeminjam.length];
        for (int i = 0; i < historyPeminjam.length; i++){
            tempHistoryPeminjam[i] = historyPeminjam[i];
        }

        historyPeminjam = new CanBorrow[tempHistoryPeminjam.length + 1];
        for (int i = 0; i < tempHistoryPeminjam.length; i ++){
            historyPeminjam[i] = tempHistoryPeminjam[i];
        }

        historyPeminjam[historyPeminjam.length - 1] = anggota;
    }

    //Method "peminjamBerkurang" akan menghapus anggota yang mengembalikan buku dari list daftarPeminjam
    public void peminjamBerkurang(Anggota anggota){
        int indeksAnggota = 0;
        for (int i = 0; i < daftarPeminjam.length; i++){
            if (daftarPeminjam[i] == anggota){
                indeksAnggota = i;
                break;
            }
        }

        CanBorrow[] tempDaftarPeminjam = new CanBorrow[daftarPeminjam.length];
        for (int i = 0; i < daftarPeminjam.length; i++){
            tempDaftarPeminjam[i] = daftarPeminjam[i];
        }

        daftarPeminjam = new CanBorrow[tempDaftarPeminjam.length - 1];
        for (int i = 0; i < tempDaftarPeminjam.length; i++){
            if (i != indeksAnggota){
                daftarPeminjam[i] = tempDaftarPeminjam[i];
            }
        }
    }

    //Method getter
    public String getJudul(){
        return judul;
    }

    public Kategori getKategori(){
        return kategori;
    }

    public String getPenulis(){
        return penulis;
    }

    public CanBorrow[] getDaftarPeminjam(){
        return daftarPeminjam;
    }

    public CanBorrow[] getHistoryPeminjam(){
        return historyPeminjam;
    }

    public int getStok(){
        return stok;
    }

    public int getStokAwal(){
        return stokAwal;
    }
}
