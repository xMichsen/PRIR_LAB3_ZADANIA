public class Pkt2D {
    private double x;
    private double y;

    public Pkt2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // metoda obliczajaca odlwglosc od 2 punktow
    public double distanceFrom(Pkt2D point) {
        return Math.sqrt(Math.pow((point.getX() - this.x), 2) + Math.pow((point.getY() - this.y), 2));
    }
    // poniżej gettery i settery
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}