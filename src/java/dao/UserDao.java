package dao;

import entity.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    void updateUserIsSubscribed(int userId, boolean isSubscribed);
    User getUserById(int userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
}
