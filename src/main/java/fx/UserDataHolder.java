package fx;

//Singleton for storing user data
public final class UserDataHolder {
    private UserData userData;
    private static final UserDataHolder INSTANCE = new UserDataHolder();

    private UserDataHolder(){}

    public static UserDataHolder getInstance() {
        return INSTANCE;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
