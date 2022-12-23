package dao.Impl;

import dao.UserDao;
import enity.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public User checkUser(User user) {
        User backuser = null;
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt = connection.prepareStatement("select * from user where username=? and pwd=?");
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPwd());
            resultSet = psmt.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String pwd = resultSet.getString("pwd");
                int root = resultSet.getInt("root");
                backuser = new User(id,username,pwd,root);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return backuser;
    }

    @Override
    public User registerUser(User user) {
        User backuser = null;
        Connection connection = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt1 = connection.prepareStatement("select * from user where username=?");
            psmt1.setString(1, user.getUsername());
            resultSet = psmt1.executeQuery();
            if (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String pwd = resultSet.getString("pwd");
                int root = resultSet.getInt("root");
                backuser = new User(id,username,pwd,root);
            }
            else
            {
                psmt2 = connection.prepareStatement("insert into user(username,pwd,root) values(?,?,?)");
                psmt2.setString(1, user.getUsername());
                psmt2.setString(2, user.getPwd());
                psmt2.setInt(3, 0);
                psmt2.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (psmt2 != null)
            {
                try {
                    psmt2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                psmt1.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return backuser;
    }
}
