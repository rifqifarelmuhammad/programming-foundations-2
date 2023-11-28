public class Minuman extends Pesanan {  //Class Minuman merupakan subclass Pesanan
    //Membuat attribut class dengan modifier private
    private boolean isPakeEs;

    //Membuat constructor dengan 4 parameter dan modifier public
    public Minuman(String nama, int harga, int prioritas, boolean isPakeEs) {
        super(nama, harga, prioritas);
        this.isPakeEs = isPakeEs;
    }

    //Mengoverride method toString agar sesuai dengan kebutuhan class Minuman
    @Override
    public String toString() {
        String output = "";
        if (isPakeEs){
            output = String.format("%s dingin", super.getNama());
        }else{
            output = String.format("%s hangat", super.getNama());
        }
        return output;
    }
}
