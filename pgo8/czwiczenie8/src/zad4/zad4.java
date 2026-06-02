package zad4;

class LibraryAccount {
    private final String readerName;
    private final int borrowedBooks;
    private final int lateDays;

    public LibraryAccount(String readerName, int borrowedBooks, int lateDays) {
        this.readerName = readerName;
        this.borrowedBooks = borrowedBooks;
        this.lateDays = lateDays;
    }

    public String getReaderName() { return readerName; }

    public class FineCalculator {
        public double calculate() {
            return borrowedBooks * lateDays * 1.50;
        }
    }
}

@FunctionalInterface
interface MessagePrinter {
    void print(String message);
}

public class zad4 {
    public static void main(String[] args) {
        LibraryAccount account = new LibraryAccount("Jan Nowak", 3, 5);

        LibraryAccount.FineCalculator calculator = account.new FineCalculator();

        double fine = calculator.calculate();

        MessagePrinter printer = message -> System.out.println("[BIBLIOTEKA] " + message);

        printer.print("Czytelnik: " + account.getReaderName() + ", opłata za przetrzymanie: " + fine + " zł");
    }
}