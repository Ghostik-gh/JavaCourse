import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) throws Exception {
        // Сохраним 9 чисел введеных пользователем в массив
        double input[] = new double[9];
        try (Scanner in = new Scanner(System.in)) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("Введите точку номер #" + i);
                System.out.println();
                for (int j = 0; j < 3; j++) {
                    String xyz = "xyz";
                    System.out.print(" " + xyz.charAt(j) + ": ");
                    input[j + 3 * (i - 1)] = in.nextDouble();
                }
            }

        } catch (Exception e) {
            System.out.println("!!!: Ошибка ввода: " + e);
        }
        Point3d x = new Point3d(input[0], input[1], input[2]);
        Point3d y = new Point3d(input[3], input[4], input[5]);
        Point3d z = new Point3d(input[6], input[7], input[8]);

        // Устанавливаем точность по умолчанию 1000d
        Point3d.setPrecision(100d);
        System.out.println("#1 equals #3     :  " + x.equalsPoint3d(z));
        System.out.println("Distance #1, #2  :  " + Point3d.distanceTo(x, y));
        System.out.println("isTriangle       :  " + Point3d.isTriangle(x, y, z));
        System.out.println("Area             :  " + Point3d.computeArea(x, y, z));

    }
}
