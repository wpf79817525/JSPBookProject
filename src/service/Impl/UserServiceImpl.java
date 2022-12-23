package service.Impl;

import dao.Impl.UserDaoImpl;
import dao.UserDao;
import enity.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public User checkUser(User user) {
        return userDao.checkUser(user);
    }

    @Override
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }
}
