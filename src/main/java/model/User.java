package model;

public class User {
    private String login;
    private String email;
    private String roleId;
    private String hashPassword;

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
