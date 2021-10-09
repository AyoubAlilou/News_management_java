package User;

import java.time.LocalDate;

public class User {
    private int id ;
    private  String name ;
    private  String last_name ;
    private  String password ;
    private  String email ;
    private LocalDate dateOfBirth ;

    public User(String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public User(int id, String name, String last_name, String password, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return  name  +
                "  " + last_name
             ;
    }
}
