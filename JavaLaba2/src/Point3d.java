
public class Point3d {
    private double x;
    private double y;
    private double z;
    private static double accuracy = 100d;

    public Point3d(double xInput, double yInput, double zInput) {
        x = xInput;
        y = yInput;
        z = zInput;
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void println() {
        System.out.print(x + " ");
        System.out.print(y + " ");
        System.out.println(z);
    }

    public static void setPrecision(double accuracyInput) {
        accuracy = accuracyInput;
    }

    public void setX(double xInput) {
        x = xInput;
    }

    public void setY(double yInput) {
        y = yInput;
    }

    public void setZ(double zInput) {
        z = zInput;
    }

    public boolean equalsPoint3d(Point3d secondPoint) {
        if (secondPoint.x != this.x)
            return false;
        if (secondPoint.y != this.y)
            return false;
        if (secondPoint.z != this.z)
            return false;
        return true;
    }

    public static double distanceTo(Point3d firsPoint, Point3d secondPoint) {
        return Math.round(
                Math.sqrt(Math.pow((secondPoint.x - firsPoint.x), 2) +
                        Math.pow((secondPoint.y - firsPoint.y), 2) +
                        Math.pow((secondPoint.z - firsPoint.z), 2)) * accuracy)
                / accuracy;
    }

    // public static double computeArea() {

    // return -1;
    // }
}