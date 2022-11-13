public class Filozof implements Runnable {


    private Object lewywidelec;
    private Object prawywidelec;

    public Filozof(Object leftFork, Object rightFork) {
        this.lewywidelec = leftFork;
        this.prawywidelec = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    public void run() {
        try {
            while (true) {

                doAction(System.nanoTime() + ": mysli");
                synchronized (lewywidelec) {
                    doAction(
                            System.nanoTime()
                                    + ": podniosl lewy widelec");
                    synchronized (prawywidelec) {
                        doAction(
                                System.nanoTime()
                                        + ": podniosl prawy widelec i zaczal jesc");

                        doAction(
                                System.nanoTime()
                                        + ": odlozyl prawy widelec");
                    }

                    doAction(
                            System.nanoTime()
                                    + ": odlozyl lewy widelec - wraca do rozmyslania");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

}