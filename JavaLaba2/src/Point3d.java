
public class Point3d {
    private double x;
    private double y;
    private double z;

    // Точность с которой хранятся числа
    private static double precision = 1000d;

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

    public void setX(double xInput) {
        x = xInput;
    }

    public void setY(double yInput) {
        y = yInput;
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
        if (secondPoint.x != this.x)
            return false;
        if (secondPoint.y != this.y)
            return false;
        if (secondPoint.z != this.z)
            return false;
        return true;
    }

    // Вычисляет расстояние между двумя точками
    public static double distanceTo(Point3d firsPoint, Point3d secondPoint) {
        double x = Math.round(Math.sqrt(Math.pow((secondPoint.x - firsPoint.x), 2) +
                Math.pow((secondPoint.y - firsPoint.y), 2) +
                Math.pow((secondPoint.z - firsPoint.z), 2)) * precision) / precision;
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