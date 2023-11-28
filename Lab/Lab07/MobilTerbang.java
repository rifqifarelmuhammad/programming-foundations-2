//Class MobilTerbang adalah subclass dari class Mobil
public class MobilTerbang extends Mobil{
    //Membuat constructor dengan bantuan constructor superclass
    public MobilTerbang(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Terbang");
    }

    //Mengoverride method isiBahanBakar() agar sesuai dengan kebutuhan class MobilTerbang
    @Override
    public String isiBahanBakar(){
        if (super.getIsOn()){  //Mobil harus mati untuk diisi bahan bakarnya
            return "Mobil masih terbang, matikan terlebih dahulu agar tidak jatuh.";
        }else{  //Jika mobil dalam keadaan mati
            super.setPersenFuel(100);  //Bensin terisi penuh (100%)
            return super.getBahanBakar() + " sekarang sudah penuh, mobil dapat terbang kembali!";
        }
    }

    //Mengoverride method simulasi() agar sesuai dengan kebutuhan class MobilTerbang
    @Override
    public String[] simulasi(){
        //Akan memanggil method start(), 2 kali gas(), stop(), dan isiBahanBakar()
        String[] output = new String[5];
        
        output[0] = super.start();
        
        for (int i = 1; i < 3; i++){
            output[i] = super.gas();
        }

        output[3] = super.stop();
        output[4] = isiBahanBakar();

        return output;
    }
}