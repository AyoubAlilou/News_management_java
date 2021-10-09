package Reader;

import Category.Category;
import User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private List<Category> categories = new ArrayList<Category>();

    public Reader() {
    }

    public Reader(String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        super(name, last_name, password, email, dateOfBirth);
    }
    public Reader(User user) {
        super(user.getId(), user.getName(), user.getLast_name(), user.getPassword(), user.getEmail(), user.getDateOfBirth());
    }

    public Reader(int id, String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        super(id, name, last_name, password, email, dateOfBirth);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
