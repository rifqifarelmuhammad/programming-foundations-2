public class Pelanggan {
    //Membuat atribut class dengan modifier private
    private String nama;
    private int uang;
    private Order[] keranjang;
    private int kapasitasKeranjang = 5000;
    private int beratKeranjang = 0;
    private int indeksList = 0;

    //Membuat Constructor dengan 3 parameter
    public Pelanggan(String nama, int uang, int kapasitas) {
        this.nama = nama;
        this.uang = uang;
        keranjang = new Order[kapasitas];
    }

    //Method "newKeranjang" akan mereset keranjang pelanggan
    void newKeranjang(int kapasitas){
        keranjang = new Order[kapasitas];
        beratKeranjang = 0;
        indeksList = 0;
    }
    
    //Method "addBarang" akan menambahkan barang ke keranjang pelanggan
    String addBarang(Barang barang, int banyakBarang){
        boolean barangSama = false;   //Sebagai flag apakah barang yang diadd sudah ada di keranjang atau tidak

        //Mengecek setiap nama barang yang ada di keranjang, jika sama maka akan diubah banyak barangnya saja
        for (int i = 0 ; i < keranjang.length ; i++){
            if((keranjang[i] != null) && (barang.getNama()).equals(keranjang[i].getBarang().getNama())){
                //Mengecek stock barang menggunakan method "cekStock"
                boolean barangTersedia = barang.cekStock(keranjang[i].getBanyakBarang() + banyakBarang);
                if (barangTersedia){   //Jika barang ready stock, akan ditambahkan ke keranjang
                    int temp1BeratKeranjang = beratKeranjang;
                    temp1BeratKeranjang += (banyakBarang * keranjang[i].getBarang().getBeratBarang());

                    /*
                    Jika barang ditambahkan sebanyak banyakBarang dan membuat berat keranjang melebihi
                    kapasitas keranjang, maka akan dicari banyak barang yang tidak melebihi kapasitas
                    keranjang dan memasukkannya ke dalam keranjang
                    */
                    if (temp1BeratKeranjang > kapasitasKeranjang){
                        for (int k = banyakBarang-1 ; k >= 0 ; k--){
                            int temp2BeratKeranjang = beratKeranjang;
                            temp2BeratKeranjang += (k * keranjang[i].getBarang().getBeratBarang());
                    
                            if (temp2BeratKeranjang <= kapasitasKeranjang){
                                beratKeranjang = temp2BeratKeranjang;
                                keranjang[i].newBanyakBarang(k);
                                return "Maaf " + banyakBarang + " " + keranjang[i].getBarang().getNama() +
                                " terlalu berat, tetapi " + k + " " + keranjang[i].getBarang().getNama() +
                                " berhasil ditambahkan";
                            }
                        }
                    }else{  //Jika berat keranjang setelah barang ditambahkan tidak melebihi kapasitas keranjang
                        keranjang[i].newBanyakBarang(banyakBarang);
                        beratKeranjang = temp1BeratKeranjang;
                    }
                }else{   //Jika barang tidak ready stock
                    return "Stock " + barang.getNama() + " kurang";
                }
                
                //Merubah flag sebagai tanda bahwa barang yang ditambahkan sudah ada di keranjang pelanggan
                barangSama = true;   
            }
        }

        if (!barangSama){   //Jika barang yang ditambahkan belum ada di keranjang pelanggan
            //Mengecek stock barang menggunakan method "cekStock"
            boolean barangTersedia = barang.cekStock(banyakBarang);
            if (barangTersedia){   //Jika barang ready stock, akan ditambahkan ke keranjang
                int temp1BeratKeranjang = beratKeranjang;
                temp1BeratKeranjang += (banyakBarang * barang.getBeratBarang());

                /*
                Jika barang ditambahkan sebanyak banyakBarang dan membuat berat keranjang melebihi
                kapasitas keranjang, maka akan dicari banyak barang yang tidak melebihi kapasitas
                keranjang dan memasukkannya ke dalam keranjang
                */
                if (temp1BeratKeranjang > kapasitasKeranjang){
                    for (int k = banyakBarang ; k >= 0 ; k--){
                        int temp2BeratKeranjang = beratKeranjang;
                        temp2BeratKeranjang += (k * barang.getBeratBarang());

                        if (temp2BeratKeranjang <= kapasitasKeranjang){
                            beratKeranjang = temp2BeratKeranjang;
                            keranjang[indeksList] = new Order(barang, k);
                            return "Maaf " + banyakBarang + " " + barang.getNama() + " terlalu berat, tetapi " +
                            k + " " + barang.getNama() + " berhasil ditambahkan";
                        }
                    }
                }else{   //Jika berat keranjang setelah barang ditambahkan tidak melebihi kapasitas keranjang
                    keranjang[indeksList] = new Order(barang, banyakBarang);
                    beratKeranjang = temp1BeratKeranjang;
                }

                //indeksList ditambahkan 1 agar menambahkan barang ke dalam array keranjang di indeks selanjutnya
                indeksList += 1;
            }else{   //Jika barang tidak ready stock
                return "Stock " + barang.getNama() + " kurang";
            }
        }

        //Mereturn kata-kata barang apa yang ditambahkan ke keranjang pelanggan
        return nama + " berhasil menambahkan " + banyakBarang + " " + barang.getNama();
    }
    
    //Method "totalHargaBarang" akan menghitung total harga barang yang ada di keranjang pelanggan
    int totalHargaBarang(){      
        int totalHarga = 0;
        for(int i = 0 ; i < keranjang.length ; i++){
            if (keranjang[i] != null){
                totalHarga += (keranjang[i].getBarang().getHarga() * keranjang[i].getBanyakBarang());
            }
        }
        return totalHarga;
    }
    
    /*
    Method "cekUang" akan memperbarui uang pelanggan jika uang pelanggan sebelum diperbarui lebih banyak dari 
    total harga barang yang ada di keranjang pelanggan
    */
    String cekUang(){
        if (this.uang >= totalHargaBarang()){
            this.uang -= totalHargaBarang();
        }
        return "Uang " + nama + " sekarang " + this.uang;
    }

    //Getter "getNama" akan mereturn nama pelanggan
    public String getNama() {
        return this.nama;
    }

    //Setter "setNama" akan memperbarui nama pelanggan
    public void setNama(String nama) {
        this.nama = nama;
    }

    //Getter "getUang" akan mereturn uang pelanggan
    public int getUang() {
        return this.uang;
    }

    //Setter "setUang" akan memperbarui uang pelanggan
    public void setUang(int uang) {
        this.uang = uang;
    }

    //Getter "getKeranjang" akan mereturn keranjang pelanggan
    public Order[] getKeranjang() {
        return keranjang;
    }
}