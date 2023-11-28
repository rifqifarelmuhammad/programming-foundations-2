public class Pesanan implements Comparable<Pesanan> {
    //Membuat attribut class dengan modifier private
    private String nama;
    private int harga;
    private int prioritas;

    //Membuat constructor dengan 3 parameter dan modifier public
    public Pesanan(String nama, int harga, int prioritas) {
        // TODO: Lengkapi Constructor berikut
        this.nama = nama;
        this.harga = harga;
        this.prioritas = prioritas;
    }

    //Mengoverride method compareTo agar sesuai dengan kebutuhan class Pesanan
    @Override
    public int compareTo(Pesanan o) {
        if (prioritas > o.getPrioritas()){
            return 1;
        }else if (prioritas < o.getPrioritas()){
            return -1;
        }else{
            return 0;
        }
    }

    //Method getter
    public int getPrioritas(){
        return prioritas;
    }

    public String getNama(){
        return nama;
    }
}