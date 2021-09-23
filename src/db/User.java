package db;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullname;
    private boolean isAdmin;

    public User() {
    }

    public User(Long id, String email, String password, String fullname, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
