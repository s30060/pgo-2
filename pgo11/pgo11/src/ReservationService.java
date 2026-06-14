import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ReservationService {
    private final List<Student> students = new ArrayList<>();
    private final List<Equipment> inventory = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final DiscountPolicy discountPolicy;
    private int reservationCounter = 1;

    public ReservationService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addEquipment(Equipment eq) {
        inventory.add(eq);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Equipment> getInventory() {
        return inventory;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation createReservation(String studentId, String equipmentId, int days) throws Exception {
        Student student = students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(studentId))
                .findFirst()
                .orElseThrow(() -> new Exception("Błąd: Student o ID " + studentId + " nie istnieje."));

        Equipment equipment = inventory.stream()
                .filter(e -> e.getId().equalsIgnoreCase(equipmentId))
                .findFirst()
                .orElseThrow(() -> new Exception("Błąd: Sprzęt o ID " + equipmentId + " nie istnieje."));

        if (!equipment.isAvailable()) {
            throw new Exception("Błąd: Sprzęt " + equipmentId + " nie jest obecnie dostępny.");
        }

        if (days < 1 || days > 14) {
            throw new Exception("Błąd: Okres rezerwacji musi wynosić od 1 do 14 dni.");
        }

        String reservationId = String.format("R%03d", reservationCounter++);

        Reservation reservation = new Reservation(reservationId, student, equipment, days, discountPolicy);
        equipment.setAvailable(false);
        reservations.add(reservation);

        return reservation;
    }

    public void returnEquipment(String reservationId) throws Exception {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId().equalsIgnoreCase(reservationId))
                .findFirst()
                .orElseThrow(() -> new Exception("Błąd: Rezerwacja o ID " + reservationId + " nie istnieje."));

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new Exception("Błąd: Ta rezerwacja nie jest aktywna (aktualny status: " + reservation.getStatus() + ").");
        }

        reservation.setStatus(ReservationStatus.RETURNED);
        reservation.getEquipment().setAvailable(true);


    int pointsEarned = (int) (reservation.getTotalCost() / 10);{
        reservation.getStudent().addLoyaltyPoints(pointsEarned);

        System.out.println("Zwrócono sprzęt. Student otrzymał " + pointsEarned + " punkty lojalnościowe.");
    }
}

public void printReport() {
    System.out.println("\n=== RAPORT KOŃCOWY ===");

    long activeCount = reservations.stream()
            .filter(r -> r.getStatus() == ReservationStatus.ACTIVE)
            .count();

    long returnedCount = reservations.stream()
            .filter(r -> r.getStatus() == ReservationStatus.RETURNED)
            .count();

    double totalRevenue = reservations.stream()
            .filter(r -> r.getStatus() == ReservationStatus.RETURNED)
            .mapToDouble(Reservation::getTotalCost)
            .sum();

    System.out.println("Liczba aktywnych rezerwacji: " + activeCount);
    System.out.println("Liczba zakończonych rezerwacji: " + returnedCount);
    System.out.printf("Łączny przychód ze zwróconego sprzętu: %.2f PLN\n", totalRevenue);

    students.stream()
            .max(Comparator.comparingInt(Student::getLoyaltyPoints))
            .ifPresent(topStudent -> System.out.println(
                    "Student z największą liczbą punktów lojalnościowych: "
                            + topStudent.getFullName() + " (" + topStudent.getLoyaltyPoints() + " pkt)"
            ));

    System.out.println("=======================");
}
}