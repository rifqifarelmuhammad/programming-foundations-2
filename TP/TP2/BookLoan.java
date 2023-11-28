package assignments.assignment2;

//Mengimport module yang dibutuhkan oleh program
import java.util.*;
import java.util.concurrent.TimeUnit ;
import java.text.*;

public class BookLoan {
    //Membuat attribut class dengan modifier private
    private static long DENDA_PER_HARI = 3000;
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    //Membuat constructor dengan 2 parameter
    public BookLoan(Member member, Book book){
        this.member = member;
        this.book = book;
    }

    //Membuat getter yang diperlukan untuk program
    public Book getBook() {
        return book;
    }

    public long getFine() {
        return fine;
    }

    public boolean getStatus() {
        return status;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    //Method "pinjam" akan menetapkan loanDate dan mengubah status dari buku yang dipinjam
    public void pinjam(String loanDate){
        this.loanDate = loanDate;
        this.status = true;
    }

    //Method "kembali" akan menetapkan returnDate dan mengubah status dari buku yang dikembalikan
    public void kembali(String returnDate){
        this.returnDate = returnDate;
        this.status = false;
    }

    //Method denda() akan menentukan denda berdasarkan durasi peminjaman
    public long denda(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String date1 = loanDate.substring(3,5) + "/" + loanDate.substring(0,2) + "/" + loanDate.substring(6);
        String date2 = returnDate.substring(3,5) + "/" + returnDate.substring(0,2) + "/" + returnDate.substring(6);
        
        try{
            Date loanDate = sdf.parse(date1);
            Date returnDate = sdf.parse(date2);
            long diffInMillies = Math.abs(returnDate.getTime() - loanDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff > 7){
                this.fine = (diff - 7) * DENDA_PER_HARI;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }

        return this.fine;
        //Referensi : https://www.baeldung.com/java-date-difference  &  https://stackoverflow.com/questions/47717633/how-to-use-timeunit-in-java
    }
}