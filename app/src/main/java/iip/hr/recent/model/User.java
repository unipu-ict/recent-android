package iip.hr.recent.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Mihovil
 */
public class User implements Serializable {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";

    public User() {}

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName(ID)
    private Long id;

    @SerializedName(USERNAME)
    private String username;

    @SerializedName(PASSWORD)
    private String password;

    @SerializedName(NAME)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
