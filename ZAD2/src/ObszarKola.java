import java.util.concurrent.ThreadLocalRandom;

public class ObszarKola extends Thread{
    private int TRIALS;
    private double result;
    private double squareSide;
    private Pkt2D squareCenter;
    private double circleRadius;
    private double squareArea;

    public ObszarKola(int t, double squareSide, Pkt2D squareCenter, double circRadius, double squareArea) {
        this.squareSide = squareSide;
        this.squareCenter = squareCenter;
        this.circleRadius = circRadius;
        this.squareArea = squareArea;
        this.TRIALS = t;
    }

    public void run() {
        int insideCircle = 0;
        for (int i = 0; i < TRIALS; i++) {
            double x = ThreadLocalRandom.current().nextDouble(0, squareSide);
            double y = ThreadLocalRandom.current().nextDouble(0, squareSide);
            Pkt2D randomPoint = new Pkt2D(x, y);
            double distanceToCircleCenter = randomPoint.distanceFrom(squareCenter);
            if (distanceToCircleCenter <= circleRadius) {
                insideCircle += 1;
            }
        }

        double probabilityOfPointInCircle = (double)insideCircle / (double)TRIALS;
        double simulatedCircleArea = probabilityOfPointInCircle * squareArea;
        this.result = simulatedCircleArea;
    }

    public double getResult() {
        return this.result;
    }
}