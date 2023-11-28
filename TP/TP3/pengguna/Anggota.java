package assignments.assignment3.pengguna;

//Mengimport beberapa class yang dibutuhkan oleh class Anggota
import assignments.assignment3.buku.Peminjaman;
import assignments.assignment3.buku.*;

//Class Anggota adalah subclass dari class Pengguna dan mengimplement interface CanBorrow & Comparable
public abstract class Anggota extends Pengguna implements CanBorrow, Comparable<Anggota>{
    //Membuat attribut class dengan modifier protected
    protected long denda = 0;
    protected int poin = 0;
    protected Peminjaman[] daftarPeminjaman = new Peminjaman[0];

    //Membuat constructor dengan 1 parameter & dapat digunakan oleh subclass
    protected Anggota(String nama){
        super(nama);
    }
    
    //Mengoverride method "compareTo" untuk pengurutan peringkat anggota
    public int compareTo(Anggota other){
        //Melakukan pengurutan poin anggota terlebih dahulu
        if (this.poin < other.getPoin()){
            return 1;
        }else if (this.poin > other.getPoin()){
            return -1;
        }else {
            //Jika poin anggota sama, akan dilakukan pengurutan berdasarkan nama anggota
            if (this.getNama().compareTo(other.getNama()) < 0){
                return -1;
            }else if (this.getNama().compareTo(other.getNama()) > 0){
                return 1;
            }else {
                return 0;
            }
        }
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Anggota
    public String toString(){
        return String.format("ID Anggota: %s\nNama Anggota: %s\nTotal Poin: %d\nDenda: Rp%d",
        super.getId(), super.getNama(), this.poin, this.denda);
    }

    //Method "detail" akan mencetak informasi anggota & riwayat peminjaman anggota
    public void detail(){
        if (daftarPeminjaman.length == 0){  //Jika belum pernah meminjam buku
            System.out.println(this);  //Mencetak informasi anggota
            System.out.println("Riwayat Peminjaman Buku:");
            System.out.println("Belum pernah meminjam buku");
        }else{  //Jika sudah pernah meminjam buku
            System.out.println(this);  //Mencetak informasi anggota
            System.out.println("Riwayat Peminjaman Buku:");

            //Program akan mencetak informasi buku yang ada di array daftarPeminjaman
            for (int i = 0; i < daftarPeminjaman.length ; i++){
                System.out.println("----------------- " + (i+1) + " -----------------");
                System.out.println(daftarPeminjaman[i]);
            }
        }
    }

    //Method "bayarDenda" berguna untuk memproses pembayaran denda anggota
    public String bayarDenda(long jumlahBayar){
        if (denda != 0){  //Jika anggota punya denda
            if (jumlahBayar < denda){  //Jika denda lebih besar dari jumlah pembayaran denda anggota
                denda -= jumlahBayar;
                return String.format("%s berhasil membayar denda sebesar Rp%d\nSisa denda saat ini: Rp%d",
                super.getNama(), jumlahBayar, denda);
            }else{  //Jika jumlah pembayaran denda anggota lebih besar atau sama dengan denda
                long tempDenda = denda;
                denda = 0;
                return String.format("%s berhasil membayar lunas denda\nJumlah kembalian: Rp%d",
                super.getNama(), (jumlahBayar - tempDenda));
            }
        }else{  //Jika anggota tidak punya denda
            return String.format("%s tidak memiliki denda", super.getNama());
        }
    }

    //Mengoverride method "kembali" agar sesuai dengan kebutuhan class Anggota
    //Method "kembali" berguna untuk memproses pengembalian buku yang dilakukan oleh anggota
    public String kembali(Buku buku, String tanggalPengembalian){
        //Memastikan terlebih dahulu bahwa buku sedang dipinjam oleh anggota
        boolean bukuDipinjam = false;
        int indeksBuku = 0;
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if (daftarPeminjaman[i].getBuku() == buku && daftarPeminjaman[i].getStatus() == true){
                bukuDipinjam = true;
                indeksBuku = i;
                break;
            }
        }

        if (bukuDipinjam){  //Jika buku sedang dipinjam
            //Melakukan pemrosesan pengembalian buku
            daftarPeminjaman[indeksBuku].kembalikanBuku(tanggalPengembalian);
            buku.stokBertambah();
            buku.peminjamBerkurang(this);

            //Menambahkan poin dan denda anggota berdasarkan buku yang telah dikembalikan
            this.poin += buku.getKategori().getPoin();
            this.denda += daftarPeminjaman[indeksBuku].hitungDenda();

            return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%s!",
            buku.getJudul(), super.getNama(), daftarPeminjaman[indeksBuku].getDenda());
        }else{  //Jika buku tidak sedang dipinjam
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), super.getNama());
        }
    }

    //Method getter
    public int getPoin(){
        return this.poin;
    }

    public Peminjaman[] getDaftarPeminjaman(){
        return daftarPeminjaman;
    }

    public long getDenda(){
        return denda;
    }
}
