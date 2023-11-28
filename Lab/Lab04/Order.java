public class Order {
    //Membuat atribut class dengan modifier private
    private Barang barang;
    private int banyakBarang;

    //Membuat Constructor dengan 2 parameter
    public Order(Barang barang, int banyakBarang) {
        this.barang = barang;
        this.banyakBarang = banyakBarang;
    }

    //Getter "getBarang" akan mereturn barang yang diorder (class Barang)
    Barang getBarang() {
        return barang;
    }

    //Getter "getBanyakBarang" akan mereturn banyak barang yang diorder
    int getBanyakBarang(){
        return banyakBarang;
    }

    //Setter "setBarang" akan memperbarui barang yang diorder (class Barang)
    void setBarang(Barang barang) {
        this.barang = barang;
    }

    //Setter "setBanyakBarang" akan memperbarui banyak barang yang diorder
    void setBanyakBarang(int banyakBarang){
        this.banyakBarang = banyakBarang;
    }

    //method "newBanyakBarang" akan menambahkan banyak barang yang diorder
    void newBanyakBarang(int banyakBarang){
        this.banyakBarang += banyakBarang;
    }
}