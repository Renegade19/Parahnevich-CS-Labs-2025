import java.util.Scanner;
public class QuadraticSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = readCoefficient("A", args, 0, scanner);
        while (a == 0) {
            System.out.println("Коэффициент A не может быть равен нулю для квадратного уравнения.");
            a = readCoefficient("A", args, 0, scanner);
        }

        double b = readCoefficient("B", args, 1, scanner);
        double c = readCoefficient("C", args, 2, scanner);

        double D = b * b - 4 * a * c;
        System.out.println("Дискриминант: " + D);

        if (D < 0) {
            System.out.println("Действительных корней нет.");
        } else if (D == 0) {
            double x = -b / (2 * a);
            System.out.println("Один действительный корень: " + x);
        } else {
            double sqrtD = Math.sqrt(D);
            double x1 = (-b + sqrtD) / (2 * a);
            double x2 = (-b - sqrtD) / (2 * a);

            if (x1 > x2) {
                double temp = x1;
                x1 = x2;
                x2 = temp;
            }

            System.out.println("Действительные корни: " + x1 + ", " + x2);
        }

        scanner.close();
    }

    private static double readCoefficient(String name, String[] args, int index, Scanner scanner) {
        if (args.length > index) {
            String token = args[index];
            try {
                return Double.parseDouble(token);
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка! Коэффициент %s в командной строке ('%s') не является числом.%n", name, token);
                return promptForCoefficient(name, scanner);
            }
        } else {
            return promptForCoefficient(name, scanner);
        }
    }

    private static double promptForCoefficient(String name, Scanner scanner) {
        while (true) {
            System.out.printf("Введите коэффициент %s: ", name);
            String s = scanner.nextLine();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка! Коэффициент %s должен быть числом.%n", name);
            }
        }
    }
}
