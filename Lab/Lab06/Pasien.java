//Class Pasien adalah child class dari class Warga
public class Pasien extends Warga {
	//Membuat attribut class dengan modifier private
	private int happiness;
	private String penyakit;
	private boolean pasienSembuh;

	//Membuat constructor dengan 2 parameter
	Pasien(String nama, String penyakit) {
		super(nama);
		this.penyakit = penyakit;
	}

	//Mengoverride method "berinteraksi" agar sesuai dengan kebutuhan class Pasien
	@Override
	public void berinteraksi(Warga X) {
		super.addLogInteraksi(X);  //Menambahkan X ke logInteraksi warga

		if (X instanceof Dokter){  //Jika X adalah dokter
			Dokter dokter = (Dokter) X;

			/*
			Jika keahlian penyakit dokter sama dengan penyakit warga, maka warga akan
			sembuh dan happinessnya bertambah 20
			*/
			if (pasienSembuh == false && dokter.getPenyakitKeahlian().equalsIgnoreCase(penyakit)){
				pasienSembuh = true;
				happiness += 20;
				limitOfHappiness();
			}

			if (dokter.getDokterRamah()){  //Jika dokter ramah, happiness bertambah 10
				happiness += 10;
				limitOfHappiness();
			}else{  //Jika dokter tidak ramah, happiness berkurang 5
				happiness -= 5;
				limitOfHappiness();
			}
		}
		
		if (X instanceof Pasien){  //Jika X adalah pasien, happiness bertambah 5
			happiness += 5;
			limitOfHappiness();
		}
	}

	//Mengoverride method "toString" dengan memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString();
	}

	//Membuat method getter yang dibutuhkan oleh program
	public int getHappiness() {
		return this.happiness;
	}

	public boolean getStatusSembuh() {
		return this.pasienSembuh;
	}

	public String getPenyakit() {
		return this.penyakit;
	}

	//Method "limitOfHappiness()" untuk menjaga range happiness tetap di 0 <= x <= 100
	public void limitOfHappiness(){
		if (happiness <= 0){
			happiness = 0;
		}else if (happiness >= 100){
			happiness = 100;
		}
	}
}
