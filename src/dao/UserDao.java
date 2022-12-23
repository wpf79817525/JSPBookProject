package dao;

import enity.User;

public interface UserDao {
    public User checkUser(User user);
    public User registerUser(User user);
}
