package app.chat.facebooklogin.dataModel;

/**
 * Created by ashrafiqubal on 19/08/17.
 */

public class FacebookLoginResult {
    String id, gender, email, name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacebookLoginResult(String id, String gender, String email, String name) {

        this.id = id;
        this.gender = gender;
        this.email = email;
        this.name = name;
    }
}
