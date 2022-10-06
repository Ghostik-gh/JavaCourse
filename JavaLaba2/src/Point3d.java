
public class Point3d extends Point2d {
    private double z;

    // Точность с которой хранятся числа
    private static double precision = 1000d;

    public Point3d(double xInput, double yInput, double zInput) {
        super(xInput, yInput);
        z = zInput;
    }

    public Point3d() {
        super();
        z = 0;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double zInput) {
        z = zInput;
    }

    // Количество знаков после запятой
    public static void setPrecision(double precisionInput) {
        precision = precisionInput;
    }

    // Проверяет равенство точек возвращает true если равны иначе false
    public boolean equalsPoint3d(Point3d secondPoint) {
        if (secondPoint.getX() != super.getX())
            return false;
        if (secondPoint.getY() != super.getY())
            return false;
        if (secondPoint.getZ() != this.getZ())
            return false;
        return true;
    }

    // Вычисляет расстояние между двумя точками
    public static double distanceTo(Point3d firsPoint, Point3d secondPoint) {
        double x = Math.round(Math.sqrt(
                Math.pow((secondPoint.getX() - firsPoint.getX()), 2) +
                        Math.pow((secondPoint.getY() - firsPoint.getY()), 2) +
                        Math.pow((secondPoint.getZ() - firsPoint.getZ()), 2))
                * precision) / precision;
        return x;

    }

    // Проверка являются ли три точки различными
    // ture если все точки различны
    public static boolean isTriangle(Point3d firsPoint, Point3d secondPoint, Point3d thirdPoint) {
        return (firsPoint.equalsPoint3d(secondPoint) || firsPoint.equalsPoint3d(thirdPoint)
                || secondPoint.equalsPoint3d(thirdPoint)) ? false : true;
    }

    // Вычисляет площадь для трех заданных точек
    public static double computeArea(Point3d firsPoint, Point3d secondPoint, Point3d thirdPoint) {
        double lineAB = distanceTo(firsPoint, secondPoint);
        double lineBC = distanceTo(thirdPoint, secondPoint);
        double lineAC = distanceTo(firsPoint, thirdPoint);
        double perimeter = (lineAB + lineAC + lineBC) / 2;
        double area = Math.round(
                Math.sqrt(perimeter * (perimeter - lineAB) *
                        (perimeter - lineAC) * (perimeter - lineBC))
                        * precision)
                / precision;

        return isTriangle(firsPoint, secondPoint, thirdPoint) ? area : -1;
    }
}