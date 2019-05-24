package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "login")
    @Id
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "roleId")
    private String roleId;

    @Column(name = "password")
    private String hashPassword;

    public User(){

    }

    public User(String login, String hashPassword, String email, String roleId) {
        this.login = login;
        this.hashPassword = hashPassword;
        this.email = email;
        this.roleId = roleId;
    }


    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
