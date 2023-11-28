//Class Saham adalah child class dari class Aset
public class Saham extends Aset {
	//Membuat attribut class dengan modifier private
	private double dividen;
	private double pertumbuhan;
	
	//Nembuat constructor dengan 5 parameter
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.pertumbuhan = pertumbuhan;
		this.dividen = dividen;
	}

	//Mengoverride method "nextYear" class Aset agar sesuai dengan kebutuhan class Saham
	@Override
	void nextYear() {
		super.nextYear();   //Mengambil perintah dari method "nextYear" class Aset
		grow();   //Mengubah pertumbuhan saham

		setHarga(getHarga() * (pertumbuhan + 1));   //Mengubah harga Saham

		//Mencari pendapatan per tahun dari aset saham
		Pacilnomo.addToEarnings(getHarga() * getDividen() * getJumlah());
	}

	// Linear congruential generator for subsequent growth
	void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	//Mengoverride method "toString" agar sesuai dengan kebutuhan class Saham
	@Override
	public String toString() {
		return getNama() + "\n" + "Tipe: Saham\n" + "Harga: " + String.format("%.2f", getHarga())
		+ "\nJumlah: " + getJumlah() + "\nDividen: " + this.dividen + "\nPertumbuhan: " + this.pertumbuhan;
	}

	//Membuat getter yang diperlukan untuk program
	public double getPertumbuhan() {
		return pertumbuhan;
	}

	public double getDividen() {
		return dividen;
	}
}
