
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TourManager manager = new TourManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Створити тур");
            System.out.println("2. Показати тури");
            System.out.println("3. Посортувати тури за днями");
            System.out.println("4. Вихід");
            System.out.print("Введіть цифру щоб вибрати дію ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Виберіть тип туру:");
                    System.out.println("1. Відпочинок");
                    System.out.println("2. Екскурсія");
                    System.out.println("3. Здоров'я");
                    System.out.println("4. Круїз");
                    System.out.println("5. Шопінг");
                    System.out.print("Виберіть номер відпочинку: ");
                    int tourTypeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String type, transport, food;
                    int days;
                    switch (tourTypeChoice) {
                        case 1:
                            type = "Відпочинок";
                            break;
                        case 2:
                            type = "Екскурсія";
                            break;
                        case 3:
                            type = "Здоров'я";
                            break;
                        case 4:
                            type = "Круїз";
                            break;
                        case 5:
                            type = "Шопінг";
                            break;
                        default:
                            type = "Інше";
                            break;
                    }
                    System.out.println("Виберіть транспорт:");
                    System.out.println("1. Автобус");
                    System.out.println("2. Літак");
                    System.out.println("3. Поїзд");
                    System.out.print("Виберіть номер транспорту: ");
                    tourTypeChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (tourTypeChoice) {
                        case 1:
                            transport = "Автобус";
                            break;
                        case 2:
                            transport = "Літак";
                            break;
                        case 3:
                            transport = "Поїзд";
                            break;
                        default:
                            transport = "Інше";
                            break;
                    }
                    System.out.println("Виберіть харчування:");
                    System.out.println("1. Все включено");
                    System.out.println("2. Половинне харчування");
                    System.out.println("3. Самообслуговування");
                    System.out.print("Виберіть номер харчування: ");
                    tourTypeChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (tourTypeChoice) {
                        case 1:
                            food = "Все включено";
                            break;
                        case 2:
                            food = "Половинне харчування";
                            break;
                        case 3:
                            food = "Самообслуговування";
                            break;
                        default:
                            food = "Інше";
                            break;
                    }
                    System.out.print("Введіть кількість днів: ");
                    days = scanner.nextInt();
                    scanner.nextLine();
                    manager.addTour(new TourPackage(type, transport, food, days));
                }
                case 2 -> manager.displayTours();
                case 3 -> {
                    manager.sortToursByDays();
                    System.out.println("Тури посортовані за днями:");
                }
                case 4 -> System.out.println("Вийти з програми");
                default -> System.out.println("Некоректний вибір, спробуйте ще раз");
            }
        } while (choice != 4);

        scanner.close();
    }
}
