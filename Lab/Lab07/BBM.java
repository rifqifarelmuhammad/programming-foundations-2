public class BBM implements EngineBehaviour{
    //Mengoverride method start, gas, dan stop agar sesuai dengan kebutuhan class BBM
    public String start(Mobil mobil){
        return String.format("%s menyalakan mesin, NGENG!", mobil.getNama());
    }

    public int gas(int persenFuel){
        return (persenFuel - 25);
    }

    public String stop(Mobil mobil){
        return String.format("%s mesin mati, mobil istirahat dulu.", mobil.getNama());
    }
}