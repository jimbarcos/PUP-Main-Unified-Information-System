package Classes;

public class Logs {

    private String name;
    private String username;
    private String password;
    private String code;

    // return name
    public String getName() {
        return name;
    }

    // set name
    public void setName(String name) {
        this.name = name;
    }

    // return name
    public String getUsername() {
        return username;
    }

    // set username
    public void setUsername(String username) {
        this.username = username;
    }

    // return password
    public String getPassword() {
        return password;
    }

    // set back-up code
    public void setCode(String code) {
        // Generate backup Code

        this.code = code;
    }

    // return code
    public String getCode() {
        return code;
    }

    // set password
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean credVerifier(String name, String username, String password) {
        return (name.isEmpty() && username.isEmpty() && password.isEmpty());
    }

    public int nameVerifier(String name) {

        /*if (!name.matches("^[A-Za-z]+(?: [A-Za-z]\\.)?(?: [A-Za-z]+)?$") || (!name.isEmpty() && name.length() < 5)) {
            return 1;
        } else */
        if (name.isEmpty()) {
            return 1;
        }
        /*else if (!name.isEmpty() && name.length() < 5) {
            return 1;
        }*/
        return 0;
    }

    public boolean usernameVerifier(String username) {
        return (username.isEmpty() || username.length() < 4);
    }

    public int passVerifier(String password, String username) {
        if (password.isEmpty()) {
            return 1;
        }

        if (username.equals(password) || password.trim().length() <= 11) {
            return 2;
        }

        /*if (password.length() <= 12)
            return 2;*/
        return 0;
    }
}
