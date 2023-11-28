public class Warga {
	//Membuat attribut class dengan modifier protected
	protected String nama;
	protected Warga[] logInteraksi = new Warga[0];

	//Membuat constructor dengan 1 parameter
	Warga(String nama) {
		this.nama = nama;
	}

	//Method "getNama()" berfungsi untuk mereturn nama warga
	public String getNama() {
		return nama;
	}

	// Method sengaja dikosongkan
	public void berinteraksi(Warga X){
	}

	// Method untuk menambah log interaksi
	public void addLogInteraksi(Warga warga){
		Warga[] newLog = new Warga[this.logInteraksi.length+1];

        for(int i = 0; i < this.logInteraksi.length; i++){
            newLog[i] = this.logInteraksi[i];
        }
        this.logInteraksi = newLog;

        newLog[this.logInteraksi.length -1] = warga;
	}
	
	//Mengoverride method "toString" agar sesuai dengan kebutuhan class Warga
	@Override
	public String toString() {
		return this.nama;
	}

	//Method "getLogInteraksi()" berfungsi untuk mereturn list logInteraksi warga
	public Warga[] getLogInteraksi(){
		return logInteraksi;
	}
}
