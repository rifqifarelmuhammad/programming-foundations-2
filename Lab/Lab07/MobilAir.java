//Class MobilAir adalah subclass dari class Mobil
public class MobilAir extends Mobil {
    //Membuat constructor dengan bantuan constructor superclass
    public MobilAir(String nama, EngineBehaviour engineBehaviour, String bahanBakar){
        super(nama, engineBehaviour, bahanBakar, "Air");
    }

    //Mengoverride method isiBahanBakar() agar sesuai dengan kebutuhan class MobilAir
    @Override
    public String isiBahanBakar(){
        if (super.getIsOn()){  //Mobil harus mati untuk diisi bahan bakarnya
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak tenggelam.";
        }else{  //Jika mobil dalam keadaan mati
            super.setPersenFuel(100);  //Bensin terisi penuh (100%)
            return super.getBahanBakar() + " sekarang sudah penuh, mobil dapat digunakan kembali!";
        }
    }

    //Mengoverride method simulasi() agar sesuai dengan kebutuhan class MobilAir
    @Override
    public String[] simulasi(){
        //Akan memanggil method start(), 5 kali gas(), stop(), dan isiBahanBakar()
        String[] output = new String[8];
        
        output[0] = super.start();
        
        for (int i = 1; i < 6; i++){
            output[i] = super.gas();
        }

        output[6] = super.stop();
        output[7] = isiBahanBakar();

        return output;
    }   
}