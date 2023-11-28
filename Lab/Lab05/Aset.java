public class Aset {
	//Membuat attribut class dengan modifier private
	private String nama;
	private int jumlah;
	private double harga;
	private int tahun = 0;
	
	//Nembuat constructor dengan 3 parameter
	Aset(String nama, int jumlah, double harga){
		this.nama = nama;
		this.jumlah = jumlah;
		this.harga = harga;
	}
	
	// Increment tahun
	void nextYear() {
		this.tahun += 1;
	}

	//Membuat getter dan setter yang diperlukan untuk program
	public double getHarga() {
		return harga;
	}

	public int getJumlah() {
		return jumlah;
	}

	public int getTahun() {
		return tahun;
	}

	public String getNama() {
		return nama;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}
}
