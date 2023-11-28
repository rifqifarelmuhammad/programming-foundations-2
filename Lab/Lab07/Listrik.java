public class Listrik implements EngineBehaviour {
    //Mengoverride method start, gas, dan stop agar sesuai dengan kebutuhan class Listrik
    public String start(Mobil mobil){
        return String.format("%s menyalakan listrik, SIAP DIGAS!", mobil.getNama());
    }

    public int gas(int persenFuel){
        return (persenFuel - 20);
    }

    public String stop(Mobil mobil){
        return String.format("%s listrik dimatikan, mobil telah berhenti.", mobil.getNama());
    }
}