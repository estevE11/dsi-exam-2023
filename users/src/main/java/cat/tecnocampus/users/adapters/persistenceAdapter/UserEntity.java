package cat.tecnocampus.users.adapters.persistenceAdapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_lab")
public class UserEntity {

    @Id
    private String username;
    @Column
    private String name;
    @Column
    private String secondName;
    @Column
    private String email;

    public UserEntity() { }

    public UserEntity(String username, String name, String secondName, String email) {
        this.username = username;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        String value = "Usuari: " + this.username + ", " + this.name + " " + this.secondName;
        return value;
    }

}