//Mengimport beberapa module yang diperlukan oleh program
import java.util.ArrayList;
import java.util.Collections;

public class DaftarPesanan<T extends Pesanan> {
    //Membuat attribut class dengan modifier private
    private ArrayList<T> pesanans = new ArrayList<>();
    private ArrayList<T> tempPesanans;

    //Membuat constructor tanpa parameter dan modifier public
    public DaftarPesanan() {

    }

    //Method "tambahPesanan" akan menambahkan pesanan ke ArrayList pesanans
    public void tambahPesanan(T pesanan) {
        pesanans.add(pesanan);
    }

    //Method "nextPesanan" akan mereturn pesanan selanjutnya sesuai dengan urusan prioritas
    public T nextPesanan() {
        Collections.sort(pesanans);  //Mensortir pesanans berdasarkan urutan prioritas

        /*
            Menaruh semua komponen pesanans ke tempPesanans agar indeks ke 0 nya tidak hilang 
            ketika indeks ke 0 pesanans dihapus
        */
        tempPesanans = new ArrayList<>();
        for (int i = 0; i < pesanans.size(); i++){
            tempPesanans.add(pesanans.get(i));
        }

        //Jika pesanans tidak kosong, maka akan dihapus indeks ke 0 nya
        if (pesanans.size() > 0){
            pesanans.remove(0);
        }
        return tempPesanans.get(0);  //Mereturn pesanan selanjutnya
    }
}
