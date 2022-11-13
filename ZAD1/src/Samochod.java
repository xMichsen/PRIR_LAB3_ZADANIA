import java.util.Random;

public class Samochod extends Thread {

    Random random = new Random();
    int id;
    int czas_postoju;
    boolean loop = true;
    int x;
    Parking p;
    Stansamochodu stansamochodu;
    Stanparkingu stanparkingu;

    Samochod(int id, Parking p) {
        this.id = id;

        this.p = p;
        stansamochodu = Stansamochodu.START;
        stanparkingu = Stanparkingu.PUSTY;

    }

    public void run() {
        while (loop) {
            // jeśli samochó wjeżdża to stan samochodu zmieniamy na wjazd
            if (stansamochodu == Stansamochodu.WJAZD) {
                p.bramka();
                //jeśli parking jest pełny to czekaj na zwolnienie się miejsca
                if (stanparkingu == Stanparkingu.PELNY) {
                    stansamochodu = Stansamochodu.CZEKAJ;
                }
                try {
                    sleep(10000);
                } catch (Exception ie) {
                }
                System.out.println("Samochod o nr " + id + " wjezdza na parking");

                //losujemy 1 lub 0 jeśli 1 to samochód parkuje jeśli 0 to dalej czeka
                x = random.nextInt(2);
                if (x == 1) {
                    p.parkuj();
                    // zmiana statusu samochodu na postój (na parkingu zaparkowany)
                    stansamochodu = Stansamochodu.POSTOJ;
                } else if (x == 0) {
                    stansamochodu = Stansamochodu.CZEKAJ;
                }
            } else if (stansamochodu == Stansamochodu.POSTOJ) {
                try {
                    sleep(czas_postoju);
                } catch (Exception ie) {
                }
                x = random.nextInt(2);
                if (x == 1) {
                    stansamochodu = Stansamochodu.WYJAZD;
                } else if (x == 0) {
                    stansamochodu = Stansamochodu.POSTOJ;
                }

            } else if (stansamochodu == Stansamochodu.WYJAZD) {
                try {
                    sleep(random.nextInt(2000));
                } catch (Exception ie) {
                }
                p.wyjazd();
                System.out.println("Samochod o nr " + id + " wyjechal z parkingu");
                stansamochodu = Stansamochodu.KONIEC;

            } else if (stansamochodu == Stansamochodu.START) {
                System.out.println("Samochod o nr " + id + " czeka na wjazd");
                try {
                    sleep(random.nextInt(2000));
                } catch (Exception ie) {

                }
                stansamochodu = Stansamochodu.WJAZD;
            } else if (stansamochodu == Stansamochodu.CZEKAJ) {
                try {
                    sleep(random.nextInt(2000));
                } catch (Exception ie) {

                }
                System.out.println("Samochod " + id + " postanawia poczekac");
                stansamochodu = Stansamochodu.WJAZD;

                if (stansamochodu == Stansamochodu.KONIEC) {
                    loop = false;
                }
            }
        }

    }
}