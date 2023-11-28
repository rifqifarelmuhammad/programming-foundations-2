package assignments.assignment3.pengguna;

public class Staf extends Pengguna {
    //Membuat attribut class dengan modifier private
    private static int jumlahStaf = 0;

    //Membuat constructor dengan 1 parameter & mengset id Staf
    public Staf(String nama){
        super(nama);
        super.setId(generateId());
    }
    
    //Mengoverride method "generateId" agar sesuai dengan kebutuhan class Staf
    protected String generateId(){
        jumlahStaf += 1;
        String id = String.format("STAF-%d", jumlahStaf);
        return id;
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Staf
    public String toString(){
        return String.format("ID Staf: %s\nNama Staf: %s", super.getId(), super.getNama());
    }
}
