public class Parking {

    int miejsca;
    int max;
    int samochody;
    Stanparkingu stanparkingu;
    Stansamochodu stansamochodu;

    Parking(int miejsca, int samochody){
        this.miejsca = miejsca;
        this.samochody = samochody;
        this.max = 20;
    }

    public void bramka(){
        try{

            Thread.sleep(400);//sleep for 1000 ms

        }
        catch(Exception ie){
        }
        //jeśli nie ma miejsc to wypisuje brak miejsca
        if(miejsca > max){
            System.out.println("Nie ma miejsc na parkingu");
            //zmiana stanu parkingu na pełny
            stanparkingu = Stanparkingu.PELNY;
        }
        else {
            //zmiana stanu parkingu na pusty (czyli są miejsca)
            stanparkingu = Stanparkingu.PUSTY;
        }


    }
    // metoda zapełniająca miejsce parkingu
    public void parkuj(){
        miejsca++;
        try{

            Thread.sleep(400);//sleep for 1000 ms

        }
        catch(Exception ie){
        }
    }
    //metoda zwalniająca miejsce w parkingu i zmniejszająca liczbę samochodów
    public void wyjazd(){
        miejsca++;
        samochody--;

    }

}