//Class MobilTruk adalah subclass dari class Mobil
public class MobilTruk extends Mobil{
    //Membuat constructor dengan bantuan constructor superclass
    public MobilTruk(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Truk");
    }

    //Mengoverride method isiBahanBakar() agar sesuai dengan kebutuhan class MobilTruk
    @Override
    public String isiBahanBakar(){ 
        if (super.getIsOn()){  //Mobil harus mati untuk diisi bahan bakarnya
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak meledak.";
        }else{  //Jika mobil dalam keadaan mati
            super.setPersenFuel(100);  //Bensin terisi penuh (100%)
            return super.getBahanBakar() + " sekarang sudah penuh, mobil dapat digaskeun kembali!";
        }
    }

    //Mengoverride method simulasi() agar sesuai dengan kebutuhan class MobilTruk
    @Override
    public String[] simulasi(){
        //Akan memanggil method start(), 4 kali gas(), stop(), dan isiBahanBakar()
        String[] output = new String[7];
        
        output[0] = super.start();
        
        for (int i = 1; i < 5; i++){
            output[i] = super.gas();
        }

        output[5] = super.stop();
        output[6] = isiBahanBakar();

        return output;
    }
}