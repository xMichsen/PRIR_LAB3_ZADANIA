import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Main extends Thread {
    final static int N = 4096;
    //stala okreslająca czy szereg manderglora w aktualnym punkcje będzie nieskończony
    final static int CUTOFF = 100;
    static int[][] set = new int[N][N];
    private static final double ZOOM = 1;
    private static final double CX = -0.75;
    private static final double CY = 0.27015;
    private static final double MOVE_X = 0;
    private static final double MOVE_Y = 0;

    public static void main(String[] args) throws Exception {

//ustawienie stopera liczącego czas obliczeń
        long startTime = System.currentTimeMillis();
//ustawienie 4 wątków generujących fraktal w 4 ćwiartkach
        Main thread0 = new Main(0);
        Main thread1 = new Main(1);
        Main thread2 = new Main(2);
        Main thread3 = new Main(3);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
//czekanie głównego programu na zakończenie 4 wątków roboczych
        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();
        //zakończenie działania stopera i wyświetlenie czasu generowania fraktala
        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");
//ustawienie pliku graficznego, w którym zostanie wygenerowany fraktal
        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
//wstawianie pixeli do pliku graficznego
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = set[i][j];
                float level;
                if (k < CUTOFF) {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = (float) k / CUTOFF;
                } else {
//pixel o wspolrzednych i,j należy do zbioru Manderbrota
                    level = 0;
                }
//zapisywanie pixela (na zielono lub czarno)
                Color c = new Color(level, 0, 0); // czerwony
                img.setRGB(i, j, c.getRGB());
            }
        }
//zapis rysunku do pliku Mandelbrot.png
        ImageIO.write(img, "PNG", new File("Mandelbrot.png"));
    }

    int me;

    //konstruktor, który ustawie numeracje wątków
    public Main(int me) {
        this.me = me;
    }

    //procedura wykonywana przez każdy z 4 wątków sprawdzająca czy dany punkt należy do zbioru Manderbrota
    public void run() {
        int begin = 0, end = 0;
        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        } else if (me == 1) {
            begin = (N / 4) * 1;
            end = (N / 4) * 2;
        } else if (me == 2) {
            begin = (N / 4) * 2;
            end = (N / 4) * 3;
        } else if (me == 3) {
            begin = (N / 4) * 3;
            end = N;
        }
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
                double zx = 1.5 * (i - (N) / 2) / (0.5 * ZOOM * (N)) + MOVE_X;
                double zy = (j - N / 2) / (0.5 * ZOOM * N) + MOVE_Y;
                int k = 0;
                float ip = CUTOFF;
                while (zx * zx + zy * zy < 4 && ip > 0) {
                    double tmp = zx * zx - zy * zy + CX;
                    zy = 2.0 * zx * zy + CY;
                    zx = tmp;
                    ip--;
                    k++;
                }
                set[i][j] = k;
            }
        }
    }
}
