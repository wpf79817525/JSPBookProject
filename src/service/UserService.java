package service;

import enity.User;

public interface UserService {
    public User checkUser(User user);
    public User registerUser(User user);
}
