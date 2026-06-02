package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


record UserForm(String email, String password, int age) {
    public UserForm {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail nie może być pusty!");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Hasło nie może być puste!");
        }
    }
}

class UserValidator {
    private final List<Predicate<UserForm>> rules = new ArrayList<>();

    public void addRule(Predicate<UserForm> rule) {
        rules.add(rule);
    }

    public boolean isValid(UserForm form) {
        for (Predicate<UserForm> rule : rules) {
            if (!rule.test(form)) {
                return false;
            }
        }
        return true;
    }
}

public class zad1 {
    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        UserForm validForm = new UserForm("anna@example.com", "bezpieczne123", 20);
        System.out.println("Czy form 1 poprawny? " + validator.isValid(validForm));

        UserForm invalidForm = new UserForm("jan.nowak.com", "12345", 15);
        System.out.println("Czy form 2 poprawny? " + validator.isValid(invalidForm));
    }
}
