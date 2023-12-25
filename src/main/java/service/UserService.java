package service;

public class UserService {
    //TODO: Delete this for production but can be used for testing
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.login("jdoe", "jdoePassword");
    }

    public UserService() {
    }

    public void login(String username, String password) {
        UserDao userDao = new UserDao();
        try {
            User user = userDao.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful!");
            } else {
                throw new IllegalArgumentException("Password incorrect");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("No user with that username exists");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void register(String username, String password) {
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        user = new User(username, password);
        userDao.saveUser(user);
    }
    public User getUserFromUserName(String username){
        UserDao userDao=new UserDao();
        return userDao.getUserByUsername(username);
    }
}
