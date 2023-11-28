//Class yang akan menghandle jika tingkat kesehatan mahasiswa tidak layak
public class HealthinessUnworthyException extends Exception {
    //Constructor kosong
    public HealthinessUnworthyException(){

    }

    //Constructor dengan argumen untuk membuat message (method superClass)
    public HealthinessUnworthyException(String message){
        super(message);
    }
}
