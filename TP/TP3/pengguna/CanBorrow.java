package assignments.assignment3.pengguna;

//Mengimport class yang dibutuhkan oleh interface CanBorrow
import assignments.assignment3.buku.*;

public interface CanBorrow {
    //Mendeclare method yang harus dioverride oleh class yang mengimplement interface CanBorrow
    public String pinjam(Buku buku, String tanggalPeminjaman);
    public String kembali(Buku buku, String tanggalPengembalian);
}