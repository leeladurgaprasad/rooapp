package hob.psd.todo.bean;

import hob.psd.todo.bean.TodoFile;

public class User {

	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String level;
	private String aboutUser;
    private Integer profilePicId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    public Integer getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(Integer profilePicId) {
        this.profilePicId = profilePicId;
    }
}
