package core;

import lombok.Data;

@Data
public class UserCreate {
    private String email;
    private String password;
    private String name;

    public UserCreate(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

/*    public UserCreate(String email, String password) {
        this.email = email;
        this.password = password;
    }

 */
}