abstract class Mobil  {
    //Membuat beberapa variable data dengan modifier private
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    //Membuat constructor dengan 4 parameter dan modifier protected
    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        this.nama = nama;
        this.engineBehaviour = engineBehaviour;
        this.bahanBakar = bahanBakar;
        this.jenis = jenis;
        persenFuel = 100;
    }

    //Membuat method start() sesuai dengan ketentuan program
    public String start(){
        isOn = true;  //Mengubah isOn menjadi on
        return engineBehaviour.start(this);  //Mereturn method start dari engineBehaviour
    }

    //Membuat method gas() sesuai dengan ketentuan program
    public String gas(){
        if (isOn){  //Mobil harus nyala agar bisa digas
            if (persenFuel == 0){  //Jika bensin habis, maka mobil tidak bisa di gas
                return "Bensin habis!";
            }else{  //Jika bensin tidak habis
                this.persenFuel = engineBehaviour.gas(persenFuel);  //Mengurangi bensin menggunakan method gas(int persenFuel) dari engineBehaviour
                
                //Mereturn String output berdasarkan jenis mobil
                String output = "";
                if (jenis.equals("Air")){
                    output = String.format("%s digas dengan cepat di Laut! Bahan bakar mobil %s sekarang ", this.nama, this.bahanBakar) + persenFuel + "%.";
                }else if (jenis.equals("Terbang")){
                    output = String.format("%s digas dengan cepat di Langit! Bahan bakar mobil %s sekarang ", this.nama, this.bahanBakar) + persenFuel + "%.";
                }else if (jenis.equals("Truk")){
                    output = String.format("%s digas dengan cepat di Jalan Raya! Bahan bakar mobil %s sekarang ", this.nama, this.bahanBakar) + persenFuel + "%.";
                }
        
                return output;
            }
        }else{  //Jika mobil tidak nyala
            return "Nyalakan mobil dulu!";
        }
    }
  
    //Membuat method stop() sesuai dengan ketentuan program
    public String stop(){
        isOn = false;  //Mengubah isOn menjadi off
        return engineBehaviour.stop(this);  //Mereturn method stop dari engineBehaviour
    }
  
    //Membuat abstract method untuk dioverride oleh subclass Mobil
    public abstract String isiBahanBakar();
    public abstract String[] simulasi();

    //Getter & Setter
    public String getNama() {
        return nama; 
    }

    public int getPersenFuel() {
        return persenFuel; 
    }

    public void setPersenFuel(int persenFuel) {
        this.persenFuel = persenFuel;
    }

    public String getBahanBakar() {
        return bahanBakar; 
    }
  
    public boolean getIsOn() {
        return isOn; 
    }
}