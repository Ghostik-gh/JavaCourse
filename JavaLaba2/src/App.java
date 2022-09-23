public class App {
    public static void main(String[] args) throws Exception {
        Point3d x = new Point3d(0, 0, 0);
        Point3d y = new Point3d(1, 2, 3);
        Point3d z = new Point3d();

        Point3d.setPrecision(100d);
        System.out.println(x.equalsPoint3d(z));
        System.out.println(Point3d.distanceTo(x, y));

    }
}
