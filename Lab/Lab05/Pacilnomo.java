//Mengimport module yang dibutuhkan
import java.util.Scanner;

public class Pacilnomo {
	//Membuat attribut class dengan modifier private
	private static Aset[] portofolio;
	private static double earnings;

	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	//Method "daftarAset" berguna untuk menampilkan informasi tentang aset yang dimiliknya
	private static void daftarAset() {
		printSeparator();

		//Mencetak informasi tentang aset yang dimiliknya
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	/*
		Method "infoPortofolio" berguna untuk menampilkan informasi tentang jumlah aset
		dan total nilai portofolio yang dimilikinya
	*/
	private static void infoPortofolio() {
		int jumlahSaham = 0, jumlahObligasi = 0;
		double netWorth = 0;

		//Mencari jumlah aset dan nilai portofolio yang dimilikinya
		for (int i = 0; i < portofolio.length; i++){
			if (portofolio[i] instanceof Saham){
				jumlahSaham += 1;
			}else{
				jumlahObligasi += 1;
			}
			netWorth += (portofolio[i].getHarga() * portofolio[i].getJumlah());
		}
		netWorth += earnings;

		//Mencetak jumlah aset dan total nilai portofolio yang dimilikinya
		printSeparator();
		System.out.printf("""
		Info Portofolio
		Jumlah Jenis Saham: %d
		Jumlah Jenis Obligasi: %d
		Total Nilai Portofolio: %.2f
		""", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	//Melakukan perubahan pendapatan per tahun dari aset bergantung jenis asetnya
	private static void nextYear() {
		for (int i = 0; i < portofolio.length; i++){
			if (portofolio[i] instanceof Saham){
				portofolio[i].nextYear();
			}else{
				portofolio[i].nextYear();
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//Meminta user menginput jumlah aset yang dimilikinya
		System.out.print("Silakan masukkan banyak aset yang tersedia: ");
		int banyakAset = Integer.parseInt(in.nextLine());
		
		//Membuat list sepanjang aset yang dimilikinya
		portofolio = new Aset[banyakAset];   
		
		//Meminta user menginput asetnya berdasarkan format yang telah ditentukan
		for(int i = 0; i < banyakAset; i++) {
			System.out.printf("Aset %d: ", i + 1);
			String inp[] = in.nextLine().split("\\s+");
			String namaAset = inp[0], jenisAset = inp[1]; 
			int jumlah = Integer.valueOf(inp[2]); 
			double harga = Double.valueOf(inp[3]);
			
			//Membuat objek baru di list portofolio berdasarkan jenis asetnya
			if(jenisAset.equals("SAHAM")) {
				double pertumbuhan = Double.valueOf(inp[4]);
				double dividen = Double.valueOf(inp[5]);
				portofolio[i] = new Saham(namaAset, jumlah, harga, pertumbuhan, dividen);
			} else if(jenisAset.equals("OBLIGASI")) {
				double bunga = Double.valueOf(inp[4]);
				int maturitas = Integer.valueOf(inp[5]);
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturitas);
			} 
		}

		//The Beginning of Program
		System.out.print("Selamat datang di...");
		System.out.print(""" 


							 /$$$$$$$                     /$$ /$$                                            
							| $$__  $$                   |__/| $$                                            
							| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$ 
							| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$
							| $$____/  /$$$$$$$| $$      | $$| $$| $$  \\ $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$
							| $$      /$$__  $$| $$      | $$| $$| $$  | $$| $$  | $$| $$ | $$ | $$| $$  | $$
							| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$  | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$/
							|__/      \\_______/ \\_______/|__/|__/|__/  |__/ \\______/ |__/ |__/ |__/ \\______/ 
																											
                                                                                 
                                                                                 """);
		
		while(true) {   //Program akan terus berjalan kecuali user menginput pilihan "Keluar"
			System.out.printf("""
				Silakan pilih salah satu opsi berikut:
				[1] Daftar aset
				[2] Info portofolio
				[3] Lanjut ke tahun berikutnya
				[*] Keluar\n""", earnings);
			printSeparator();
			System.out.print("Input: ");
			String pilihan = in.nextLine();
			if(pilihan.equals("1")) {
				daftarAset();
			} else if(pilihan.equals("2")) {
				infoPortofolio();
			} else if(pilihan.equals("3")) {
				nextYear();
				System.out.println("Setahun telah berlalu...");
				printSeparator();
			} else {
				System.out.println("Terima kasih telah menggunakan layanan Pacilnomo ~ !");
				break;
			}
		}
		
		in.close();
	}
	
	//Method "addToEarnings" berguna untuk menambahkan pendapatan per tahunnya
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}
