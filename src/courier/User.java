
package courier;


public class User {
    //use static final to make cannot change.
    private static final String Admin_UserName = "admin";
    private static final String Admin_Password = "123";
    
    //static method allows calling user.without creating user object
    public static boolean authenticate(String username, String password){
        return Admin_UserName.equals(username) && Admin_Password.equals(password);
    }
}
