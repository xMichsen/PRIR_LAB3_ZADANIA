public class Main {

    static int samochody=50;
    static int miejsca=0;
    static Parking parking;


    public static void main(String[] args) {
        parking = new Parking(miejsca, samochody);
        for(int i=0;i<samochody;i++)
            new Samochod(i,parking).start();
    }
}