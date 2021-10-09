package Editor;

import User.User;

import java.time.LocalDate;

public class Editor extends User {
    public Editor(String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        super(name, last_name, password, email, dateOfBirth);
    }

    public Editor() {
    }

    public Editor(User user) {
        super(user.getId(), user.getName(), user.getLast_name(), user.getPassword(), user.getEmail(), user.getDateOfBirth());
    }
    public Editor(int id, String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        super(id, name, last_name, password, email, dateOfBirth);
    }
}
