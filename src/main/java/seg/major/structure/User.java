package seg.major.structure;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private int is_admin;

    public User(int id, String username, String email, String password, int is_admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
    }

    public int getID() {
        return id;
    }

    public void setID(int toSet) {
        this.id = toSet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String toSet) {
        this.username = toSet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String toSet) {
        this.email = toSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String toSet) {
        this.password = toSet;
    }

    public int getIsAdmin() {
        return is_admin;
    }

    public void setIsAdmin(int toSet) {
        this.is_admin = toSet;
    }

}
