package hob.psd.todo.bean.form;

public class SignInForm {

    private int userId;
    private String userName;
    private String password;
    private boolean keepMeSignIn;
    private String fromPage;
    private String continuePage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKeepMeSignIn() {
        return keepMeSignIn;
    }

    public void setKeepMeSignIn(boolean keepMeSignIn) {
        this.keepMeSignIn = keepMeSignIn;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContinuePage() {
        return continuePage;
    }

    public void setContinuePage(String continuePage) {
        this.continuePage = continuePage;
    }
}
