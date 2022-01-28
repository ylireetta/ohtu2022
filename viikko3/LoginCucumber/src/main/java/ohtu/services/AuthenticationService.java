package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        // createUser has already checked if username is taken
        
        // Check username
        if (username.length() < 3 || !username.matches("[a-z]+")) {
            return true;
        }
        
        // Check password
        // If password length is ok, check if only letters
        // If only letters, it is invalid --> return true
        // If other than letters, password length and content are ok --> return false
        if (password.length() >= 8) {
            for (Character c : password.toCharArray()) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            }
            return true; // contains only letters
        } else {
            return true; // too short
        }

    }
}
