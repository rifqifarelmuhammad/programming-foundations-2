//Class Obligasi adalah child class dari class Aset
public class Obligasi extends Aset{
	//Membuat attribut class dengan modifier private
	private double bunga;
	private int maturitas;
	private boolean jatuhTempo = false;
	
	//Nembuat constructor dengan 5 parameter
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		super(nama, jumlah, harga);
		this.bunga = bunga;
		this.maturitas = maturitas;
	}
	
	//Mengoverride method "nextYear" class Aset agar sesuai dengan kebutuhan class Obligasi
	@Override
	void nextYear() {		
		super.nextYear();   //Mengambil perintah dari method "nextYear" class Aset

		//Mengecek apakah sudah jatuh tempo atau belum berdasarkan tahun dan maturitasnya
		if (getTahun() > maturitas){
			this.jatuhTempo = true;
		}
		
		//Mencari pendapatan per tahun dari aset Obligasi
		if (!jatuhTempo){
			Pacilnomo.addToEarnings(getHarga() * getJumlah() * this.bunga);
		}else{
			Pacilnomo.addToEarnings(getHarga() * getJumlah() * 0);
		}

	}

	//Mengoverride method "toString" agar sesuai dengan kebutuhan class Obligasi
	@Override
	public String toString() {
		return getNama() + "\n" + "Tipe: Obligasi\n" + "Harga: " + String.format("%.2f", getHarga())
		+ "\nJumlah: " + getJumlah() + "\nBunga: " + this.bunga + "\nJatuh Tempo: " + this.jatuhTempo;
	}
}
