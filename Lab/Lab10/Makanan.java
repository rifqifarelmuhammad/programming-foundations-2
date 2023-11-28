// Ikuti UML Diagram
public class Makanan extends Pesanan {  //Class Makanan merupakan subclass Pesanan
    //Membuat attribut class dengan modifier private
    private int tingkatKepedasan;

    //Membuat constructor dengan 4 parameter dan modifier public
    public Makanan(String nama, int harga, int prioritas, int tingkatKepedasan) {
        super(nama, harga, prioritas);
        this.tingkatKepedasan = tingkatKepedasan;
    }

    //Mengoverride method toString agar sesuai dengan kebutuhan class Makanan
    @Override
    public String toString() {
        String output = String.format("%s level %d", super.getNama(), tingkatKepedasan);
        return output;
    }
}
