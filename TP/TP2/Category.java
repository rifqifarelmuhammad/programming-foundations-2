package assignments.assignment2;

public class Category {
    //Membuat attribut class dengan modifier private
    private String name;
    private int point;

    //Membuat constructor dengan 2 parameter
    public Category(String name, int point){
        this.name = name;
        this.point = point;
    }

    //Membuat getter yang diperlukan untuk program
    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    //Mengoverride method "toString" agar sesuai dengan kebutuhan class Category
    @Override
    public String toString() {
        return "Kategori " + this.name + " dengan " + this.point + " point berhasil ditambahkan";
    }
}
