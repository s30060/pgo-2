import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReservationService service = new ReservationService(new LoyaltyDiscountPolicy());
        seedData(service);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MediaLab System Rezerwacji ---");
            System.out.println("1. Wyświetl listę studentów");
            System.out.println("2. Wyświetl dostępny sprzęt");
            System.out.println("3. Utwórz rezerwację");
            System.out.println("4. Zwróć sprzęt");
            System.out.println("5. Wyświetl aktywne rezerwacje");
            System.out.println("6. Wyświetl raport końcowy");
            System.out.println("0. Zakończ");
            System.out.print("Wybór: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- LISTA STUDENTÓW ---");
                    for (Student s : service.getStudents()) {
                        System.out.printf("ID: %s | %s | Grupa: %s | Punkty: %d\n",
                                s.getId(), s.getFullName(), s.getGroupName(), s.getLoyaltyPoints());
                    }
                    break;
                case "2":
                    System.out.println("\n--- STAN SPRZĘTU ---");
                    for (Equipment e : service.getInventory()) {
                        System.out.println(e.getDisplayText());
                    }
                    break;
                case "3":
                    System.out.print("Podaj ID studenta: ");
                    String sId = scanner.nextLine();
                    System.out.print("Podaj ID sprzętu: ");
                    String eId = scanner.nextLine();
                    System.out.print("Podaj liczbę dni: ");
                    try {
                        int days = Integer.parseInt(scanner.nextLine());
                        Reservation r = service.createReservation(sId, eId, days);
                        System.out.println("\nSukces!");
                        System.out.println(r.getDisplayText());
                    } catch (NumberFormatException e) {
                        System.out.println("Błąd: Liczba dni musi być liczbą całkowitą.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.print("Podaj ID rezerwacji: ");
                    String rId = scanner.nextLine();
                    try {
                        service.returnEquipment(rId);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("\n--- AKTYWNE REZERWACJE ---");
                    boolean anyActive = false;
                    for (Reservation r : service.getReservations()) {
                        if (r.getStatus() == ReservationStatus.ACTIVE) {
                            System.out.println(r.getDisplayText());
                            anyActive = true;
                        }
                    }
                    if (!anyActive) System.out.println("Brak aktywnych rezerwacji.");
                    break;
                case "6":
                    service.printReport();
                    break;
                case "0":
                    running = false;
                    System.out.println("Dziękujemy za skorzystanie z systemu MediaLab!");
                    break;
                default:
                    System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
            }
        }
        scanner.close();
    }

    private static void seedData(ReservationService service) {
        service.addStudent(new Student("S001", "Anna Kowalska", "12c", 120));
        service.addStudent(new Student("S002", "Marek Nowak", "12c", 40));
        service.addStudent(new Student("S003", "Julia Zielińska", "13a", 0));

        service.addEquipment(new LaptopSet("E001", "Lenovo ThinkPad Lab", 80.0, 32, true));
        service.addEquipment(new LaptopSet("E002", "Dell XPS Demo", 100.0, 16, false));
        service.addEquipment(new CameraKit("E003", "Sony Content Kit", 90.0, 3, true));
        service.addEquipment(new CameraKit("E004", "Canon Interview Kit", 70.0, 1, true));
    }
}