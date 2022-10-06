public class Point2d {
    private double x;
    private double y;

    public Point2d(double xInput, double yInput) {
        x = xInput;
        y = yInput;
    }

    public Point2d() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double xInput) {
        x = xInput;
    }

    public void setY(double yInput) {
        y = yInput;
    }

}
