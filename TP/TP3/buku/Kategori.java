package assignments.assignment3.buku;

public class Kategori {
    //Membuat attribut class dengan modifier private
    private String nama;
    private int poin;

    //Membuat constructor dengan 2 parameter
    public Kategori(String nama, int poin){
        this.nama = nama;
        this.poin = poin;
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Kategori
    public String toString(){
        return String.format("Kategori: %s\nPoin: %d", nama, poin);
    }

    //Method getter
    public int getPoin(){
        return poin;
    }

    public String getNama(){
        return nama;
    }
}
