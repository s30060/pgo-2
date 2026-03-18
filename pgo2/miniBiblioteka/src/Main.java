void main() {
    Ksiazka ksiazka1 = new Ksiazka("Książka 1","Sqwore?",349,true);
    Ksiazka ksiazka2 = new Ksiazka("Książka ale druga","ddd eee",149,true);
    Ksiazka ksiazka3 = new Ksiazka("Książka?","Cocodrillo",846,false);
    ksiazka1.wypiszInfo();
    ksiazka2.wypiszInfo();
    ksiazka3.wypiszInfo();

    ksiazka2.wypozycz();
    ksiazka2.wypozycz();
    ksiazka3.wypozycz();

    ksiazka1.wypiszInfo();
    ksiazka2.wypiszInfo();
    ksiazka3.wypiszInfo();

    ksiazka1.zwroc();
    ksiazka2.zwroc();
    ksiazka3.zwroc();

    ksiazka1.wypiszInfo();
    ksiazka2.wypiszInfo();
    ksiazka3.wypiszInfo();
}
