package assignments.assignment3.buku;

//Mengimport beberapa class yang dibutuhkan oleh class Peminjaman
import assignments.assignment3.pengguna.Anggota;

//Mengimport beberapa module yang dibutuhkan oleh class Peminjaman
import java.util.*;
import java.util.concurrent.TimeUnit ;
import java.text.*;

public class Peminjaman {
    //Membuat attribut class dengan modifier private
    private static long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda = 0;
    private boolean status;

    //Membuat constructor dengan 3 parameter
    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman){
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.status = true;
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Peminjaman
    public String toString(){
        return buku + String.format("\nTanggal Peminjaman: %s\nTanggal Pengembalian: %s\nDenda: Rp%d",
        tanggalPeminjaman, tanggalPengembalian, denda);
    }

    //Method "kembalikanBuku" akan menetapkan tanggal pengembalian serta mengubah status peminjaman menjadi false
    public void kembalikanBuku(String tanggalPengembalian){
        this.tanggalPengembalian = tanggalPengembalian;
        this.status = false;
    }

    //Method "hitungDenda" akan menghitung denda peminjaman anggota yang telah mengembalikan buku
    public long hitungDenda(){
        //Mempersiapkan format perhitungan durasi peminjaman
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String date1 = tanggalPeminjaman.substring(3,5) + "/" + tanggalPeminjaman.substring(0,2) + "/" + tanggalPeminjaman.substring(6);
        String date2 = tanggalPengembalian.substring(3,5) + "/" + tanggalPengembalian.substring(0,2) + "/" + tanggalPengembalian.substring(6);
        
        try{
            //Menghitung durasi peminjaman
            Date loanDate = sdf.parse(date1);
            Date returnDate = sdf.parse(date2);
            long diffInMillies = Math.abs(returnDate.getTime() - loanDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff > 7){  //Denda baru tersedia jika peminjaman dilakukan > 7 hari
                this.denda = (diff - 7) * DENDA_PER_HARI;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }

        return this.denda;
        //Referensi : https://www.baeldung.com/java-date-difference  &  https://stackoverflow.com/questions/47717633/how-to-use-timeunit-in-java
    }

    //Method getter
    public Buku getBuku(){
        return buku;
    }

    public String getTanggalPeminjaman(){
        return tanggalPeminjaman;
    }

    public String getTanggalPengembalian(){
        return tanggalPengembalian;
    }

    public long getDenda(){
        return denda;
    }

    public boolean getStatus(){
        return status;
    }

    public Anggota getAnggota(){
        return anggota;
    }
}
