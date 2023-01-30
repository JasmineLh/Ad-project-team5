package sg.nus.iss.adprojectTeam5api.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;

    @NotBlank(message = "{error.user.name.empty}")
    private String name;

    @NotBlank(message = "{error.user.password.empty}")
    private String password;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "role", columnDefinition = "ENUM('ADMIN','REGUSER')")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public User(int userId, String name, String password, boolean isActive, RoleEnum role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public User() {
    }

    public User(int id, String name, String password, boolean isActive) {
        this.userId = id;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
    }

//  public User(int userId) {
//    this.userId = userId;
//  }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}

