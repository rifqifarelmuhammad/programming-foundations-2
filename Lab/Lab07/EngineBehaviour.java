interface EngineBehaviour { //Untuk perilaku dengan cara apa mobilnya bekerja
    //Membuat beberapa method yang nantinya akan dioverride
    public String start(Mobil mobil);
    public int gas(int persenFuel);
    public String stop(Mobil mobil);
}

