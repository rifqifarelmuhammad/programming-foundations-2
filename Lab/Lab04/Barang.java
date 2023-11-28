public class Barang {
    //Membuat atribut class dengan modifier private
    private String nama;
    private int harga;
    private int beratBarang;
    private int stock;

    //Membuat Constructor dengan 4 parameter
    public Barang(String nama, int harga, int beratBarang, int stock) {
        this.nama = nama;
        this.harga = harga;
        this.beratBarang = beratBarang;
        this.stock = stock;
    }
      
    //Method "cekStock" adalah method untuk mengecek apakah stock barang yang diinginkan tersedia atau tidak
    boolean cekStock(int stock){
        if (stock <= this.stock){   //Jika stock barang ada, maka jumlah stock dikurangi
            this.stock -= stock;
            return true;    
        } else{
            return false;
        }
    }
    
    //Getter "getNama" akan mereturn nama barang
    String getNama() {
        return nama;
    }
    
    //Getter "getStock" akan mereturn jumlah stock barang
    int getStock(){
        return stock;
    }
  
    //Setter "setStock" akan memperbarui jumlah stock barang
    void setStock(int kuantitas){
        this.stock = kuantitas;
    }
    
    //Getter "getBeratBarang" akan mereturn berat barang
    int getBeratBarang(){
        return beratBarang;
    }

    //Getter "getHarga" akan mereturn harga barang
    int getHarga(){
        return harga;
    }
}
