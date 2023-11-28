//Class Dokter adalah child class dari class Warga
public class Dokter extends Warga {
	//Membuat attribut class dengan modifier private
	private int jumlahPasienDitemui;
	private String penyakitKeahlian;
	private boolean dokterRamah;
	
	//Membuat constructor dengan 3 parameter
	Dokter(String nama, String penyakitKeahlian, boolean dokterRamah) {
		super(nama);
		this.penyakitKeahlian = penyakitKeahlian;
		this.dokterRamah = dokterRamah;
	}

	//Mengoverride method "berinteraksi" agar sesuai dengan kebutuhan class Dokter
	@Override
	public void berinteraksi(Warga X){
		super.addLogInteraksi(X);  //Menambahkan X ke logInteraksi warga
		
		//Jika X adalah pasien, maka jumlahPasienDitemui bertambah 1
		if (X instanceof Pasien){
			jumlahPasienDitemui += 1;
		}
	}

	//Mengoverride method "toString" dengan memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString();
	}

	//Membuat method getter yang dibutuhkan oleh program
	public int getJumlahPasienDitemui(){
		return this.jumlahPasienDitemui;
	}

	public String getPenyakitKeahlian(){
		return this.penyakitKeahlian;
	}

	public boolean getDokterRamah(){
		return this.dokterRamah;
	}
}
