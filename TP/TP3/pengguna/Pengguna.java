package assignments.assignment3.pengguna;

public abstract class Pengguna {
    //Membuat attribut class dengan modifier private
    private String id;
    private String nama;

    //Membuat constructor dengan 1 parameter & dapat digunakan oleh subclass
    protected Pengguna(String nama){
        this.nama = nama;
    }

    //Mendeclare abstract method yang harus dioverride oleh subclass
    protected abstract String generateId();
    public abstract String toString();

    //Method setter & getter
    public void setId(String id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }
}
