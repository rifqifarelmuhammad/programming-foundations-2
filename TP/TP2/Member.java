package assignments.assignment2;

public class Member {
    //Membuat attribut class dengan modifier private
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans = new BookLoan[0];
    private BookLoan[] bookLoansReturn = new BookLoan[0];

    //Membuat constructor dengan 5 parameter
    public Member(String id, String name, String dateOfBirth, String studyProgram, String angkatan){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studyProgram = studyProgram;
        this.angkatan = angkatan;
    }

    //Membuat getter yang diperlukan untuk program
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getFine() {
        return fine;
    }

    public int getPoint() {
        return point;
    }

    public BookLoan[] getBookLoans() {
        return bookLoans;
    }

    public BookLoan[] getBookLoansReturn() {
        return bookLoansReturn;
    }

    /*
        Method "pinjam" adalah method yang akan menambahkan ukuran array dari bookloans, dan memasukkan
        buku yag baru dipinjam ke dalam array bookloans di index terakhir
    */
    public void pinjam(Book book, String loanDate) {
        if (bookLoans == null){   //Jika member belum meminjam buku sama sekali
            bookLoans = new BookLoan[1];
            bookLoans[0] = new BookLoan(this, book);
            bookLoans[0].pinjam(loanDate);   //Menetapkan loanDate dan mengubah status buku
            book.stockBerkurang();   //Mengurangi stock buku yang dapat dipinjam

            System.out.println(this.name + " berhasil meminjam Buku " + book.getTitle() + "!");
        }else{   //Jika member sedang meminjam beberapa buku
            boolean bukuDipinjam = false;   //Flag apakah buku sedang dipinjam atau tidak
            if (bookLoans != null){   //Mencari tau apakah buku sedang dipinjam atau tidak
                for (int i = 0; i < bookLoans.length; i++){
                    if (bookLoans[i].getBook() == book){
                        bukuDipinjam = true;
                    }
                }
            }

            if (!bukuDipinjam){   //Jika buku tidak sedang dipinjam
                BookLoan[] tempBookLoans = new BookLoan[bookLoans.length];
                for (int i = 0; i < bookLoans.length; i++){
                    tempBookLoans[i] = bookLoans[i];
                }
    
                bookLoans = new BookLoan[bookLoans.length + 1];
                for (int i = 0; i < tempBookLoans.length; i++){
                    bookLoans[i] = tempBookLoans[i];
                }
    
                bookLoans[bookLoans.length - 1] = new BookLoan(this, book);

                //Menetapkan loanDate dan mengubah status buku
                bookLoans[bookLoans.length - 1].pinjam(loanDate);  

                book.stockBerkurang();   //Mengurangi stock buku yang dapat dipinjam

                System.out.println(this.name + " berhasil meminjam Buku " + book.getTitle() + "!");
            }else {   //Jika buku sedang dipinjam
                System.out.println("Buku " + book.getTitle() + " oleh " + book.getAuthor() +
                " sedang dipinjam");
            }
        }
    }

    /*
        Method "kembali" adalah method yang akan mengurangi ukuran array dari bookloans, dan mengeluarkan
        buku yag baru dikembalikan dari array bookloans, dan buku tersebut akan dimasukkan ke dalam
        array bookLoansReturn sebagai tempat riwayat buku yang telah dipinjam
    */
    public void kembali(Book book, String returnDate) {
        //Mencari indeks buku yang ingin dikembalikan
        int indeksBuku = 0;
        for (int i = 0; i < bookLoans.length; i++){
            if (bookLoans[i].getBook() == book){
                indeksBuku = i;
                break;
            }
        }

        //Menetapkan returnDate dan mengubah status buku
        bookLoans[indeksBuku].kembali(returnDate);
        
        //Menghitung denda buku berdasarkan durasi peminjaman buku
        bookLoans[indeksBuku].denda();

        this.point += book.getCategory().getPoint();  //Menambahkan point member
        book.stockBertambah();  //Menambahkan stock buku yang dapat dipinjam

        System.out.println("Buku " + book.getTitle() + " berhasil dikembalikan oleh " +
        name + " dengan denda Rp " + bookLoans[indeksBuku].getFine() + "!");

        //Menambahkan denda member dari denda peminjaman buku yang baru dikembalikan
        this.fine += bookLoans[indeksBuku].getFine();

        /*
            Melakukan perubahan terhadap array bookLoans dan array bookLoansReturn. Jika
            buku dikembalikan, maka panjang dari array bookLoans akan berkurang dan akan
            mengeluarkan buku yang telah dikembalikan. Di saat yang bersamaan, array
            bookLoansReturn akan bertambah ukurannya dan memasukkan buku yang baru
            saja dikembalikan ke dalam array bookLoansReturn di indeks terakhir
        */
        
        BookLoan[] tempBookLoans = new BookLoan[bookLoans.length];
        for (int i = 0; i < bookLoans.length; i++){
            tempBookLoans[i] = bookLoans[i];
        }
        
        int indeksList = 0;
        bookLoans = new BookLoan[tempBookLoans.length - 1];
        for (int i = 0; i < tempBookLoans.length; i++){
            if (i != indeksBuku){
                bookLoans[indeksList] = tempBookLoans[i];
                indeksList += 1;
            }else{
                if (bookLoansReturn.length == 0){
                    bookLoansReturn = new BookLoan[1];
                    bookLoansReturn[0] = tempBookLoans[i];
                }else{
                    BookLoan[] tempBookLoansReturn = new BookLoan[bookLoansReturn.length];
                    for (int j = 0; j < bookLoansReturn.length; j++){
                        tempBookLoansReturn[j] = bookLoansReturn[j];
                    }
        
                    bookLoansReturn = new BookLoan[tempBookLoansReturn.length + 1];
                    for (int j = 0; j < tempBookLoansReturn.length; j++){
                        bookLoansReturn[j] = tempBookLoansReturn[j];
                    }

                    bookLoansReturn[bookLoansReturn.length - 1] = tempBookLoans[i];
                }
            }
        }
    }

    //Method "detail" akan menampilkan informasi tentang riwayat peminjaman buku dari member
    public void detail() {
        System.out.println("Riwayat Peminjaman Buku :");

        int nomor = 1;

        //Program akan mendahulukan buku yang telah dikembalikan (anggota di array bookLoansReturn)
        if (bookLoansReturn != null){
            for (int i = 0; i < bookLoansReturn.length ; i++){
                if (bookLoansReturn[i] != null){
                    System.out.println("----------------- " + nomor + " -----------------");
                    System.out.println(bookLoansReturn[i].getBook());
                    System.out.println("Tanggal Peminjaman: " + bookLoansReturn[i].getLoanDate());
                    System.out.println("Tanggal Pengembalian: " + bookLoansReturn[i].getReturnDate());
                    System.out.println("Denda: Rp " + bookLoansReturn[i].getFine());
                    nomor += 1;
                }
            }
        }

        /*
            Jika program telah mencetak buku yang telah dikembalikan atau sudah tidak ada lagi anggota
            di array bookLoansReturn, maka program akan mencetak informasi buku yang ada di array
            bookloans
        */
        if (bookLoans != null){
            for (int i = 0; i < bookLoans.length ; i++){
                System.out.println("----------------- " + nomor + " -----------------");
                System.out.println(bookLoans[i].getBook());
                System.out.println("Tanggal Peminjaman: " + bookLoans[i].getLoanDate());
                System.out.println("Tanggal Pengembalian: -");
                System.out.println("Denda: Rp " + bookLoans[i].getFine());
                nomor += 1;
            }
        }
    }

    //Method "bayarDenda" akan memperbarui denda member
    public void bayarDenda(long amount) {
        if (this.fine != 0){  //Jika member memiliki denda
            if (amount >= this.fine){  //Jika jumlah pembayaran lebih besar dari dendanya
                System.out.println(this.name + " berhasil membayar lunas denda");
                System.out.println("Jumlah kembalian: Rp " + (amount - this.fine));
                this.fine = 0;  //Memperbarui denda member
            }else{  //Jika dendanya lebih besar dari jumlah yang ingin dibayarkan
                System.out.println(this.name + " berhasil membayar denda sebesar Rp " + amount);
                System.out.println("Sisa denda saat ini: Rp " + (this.fine - amount));
                this.fine -= amount;  //Memperbarui denda member
            }
        }else{  //Jika member tidak memiliki denda
            System.out.println(this.name + " tidak memiliki denda");
        }
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Member
    @Override
    public String toString() {
        return "ID Anggota: " + getId() + "\nNama Anggota: " + getName() + "\nTotal Point: " +
        getPoint() + "\nDenda: " + getFine();
    }
}
