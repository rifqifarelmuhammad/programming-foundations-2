public class Mahasiswa {
    //Membuat attribut class dengan modifier private
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;
    private String nama;
    private int tingkatKesehatan;

    //Membuat constructor dengan 2 parameter
    public Mahasiswa(String nama, int tingkatKesehatan) {
        this.nama = nama;
        this.tingkatKesehatan = tingkatKesehatan;
    }

    //Method "getStatus" berfungsi untuk menentukan kelayakan kesehatan mahasiswa
    public String getStatus() {
        if (tingkatKesehatan >= MINIMUM_TINGKAT_KESEHATAN){  //Jika tingkat kesehatan >= 70
            return "Layak mengikuti program";
        }else{  //Jika tingkat kesehatan < 70
            return "Tidak layak mengikuti program";
        }
    }

    // Untuk mencetak sebagai output di file
    @Override
    public String toString() {
        return String.format("%-24s| %s\n", this.nama, this.getStatus());
    }

    //Membuat beberapa method getter
    public int getTingkatKesehatan(){
        return tingkatKesehatan;
    }

    public String getNama(){
        return nama;
    }
}
