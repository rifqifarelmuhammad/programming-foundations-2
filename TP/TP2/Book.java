package assignments.assignment2;

public class Book {
    //Membuat attribut class dengan modifier private
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    //Membuat constructor dengan 5 parameter
    public Book(String title, String author, String publisher, int stok, Category category){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stok = stok;
        this.category = category;
    }

    //Membuat getter yang diperlukan untuk program
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getStok() {
        return stok;
    }

    public Category getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    //Method stockBerkurang() berguna untuk mengurangi stock buku karena sedang dipinjam
    public int stockBerkurang(){
        return this.stok -= 1;
    }

    //Method stockBertambah() berguna untuk menambahkan stock buku karena sudah dikembalikan
    public int stockBertambah(){
        return this.stok += 1;
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Book
    @Override
    public String toString() {
        return "Judul Buku: " + getTitle() + "\nPenulis Buku: " + getAuthor() +
        "\nPenerbit Buku: " + getPublisher() + "\nKategori: " + getCategory().getName() +
        "\nPoint: " + getCategory().getPoint();
    }
}
