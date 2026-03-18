public class Ksiazka {
    private String tytul;
    private String autor;
    private int liczbaStron;
    private boolean dostepna;

    public Ksiazka(String tytul, String autor, int liczbaStron, boolean dostepna) {
        this.tytul = tytul;
        this.autor = autor;
        this.liczbaStron = liczbaStron;
        this.dostepna =  dostepna;
    }

    public void wypiszInfo(){
        System.out.println("Tytuł: " + tytul);
        System.out.println("Autor: " + autor);
        System.out.println("Liczba Stron: " + liczbaStron);
        System.out.println("Dostępna: " + (dostepna ? "Tak" : "Nie"));
        System.out.println(" ");
    }

    public void wypozycz(){
        if (dostepna){
            dostepna = false;
            System.out.println("Książka '" + tytul + "' została wypożyczona.");
            System.out.println(" ");
        }else{
            System.out.println("Książka '" + tytul + "'  nie jest dostępna.");
            System.out.println(" ");
        }
    }

    public void zwroc(){
        if (!dostepna){
            dostepna = true;
            System.out.println("Książka '" + tytul + "' została zwrócona.");
            System.out.println(" ");
        } else {
            System.out.println("Książka '" + tytul + "' już zwrócona.");
            System.out.println(" ");
        }
    }
}

