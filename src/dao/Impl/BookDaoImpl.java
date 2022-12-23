package dao.Impl;

import dao.BookDao;
import enity.Book;
import enity.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BookDaoImpl implements BookDao {
    @Override
    public ArrayList<Book> getAllBooks(int start,int count) {
        ArrayList<Book> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt = connection.prepareStatement("select * from book limit ?,?");
            psmt.setInt(1,start);
            psmt.setInt(2,count);
            resultSet = psmt.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String writer = resultSet.getString("writer");
                int nums = resultSet.getInt("nums");
                Book book = new Book(name,price,writer,nums);
                ans.add(book);
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
        return ans;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt = connection.prepareStatement("select * from book");
            resultSet = psmt.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String writer = resultSet.getString("writer");
                int nums = resultSet.getInt("nums");
                Book book = new Book(name,price,writer,nums);
                ans.add(book);
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
        return ans;
    }

    @Override
    public ArrayList<Orders> getOrdersByCustomer(String customer) {
        ArrayList<Orders> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt = connection.prepareStatement("select * from orders where customer=?");
            psmt.setString(1,customer);
            resultSet = psmt.executeQuery();
            while (resultSet.next())
            {
                String customer2 = resultSet.getString("customer");
                String buyName = resultSet.getString("buyName");
                int buyNum = resultSet.getInt("buyNum");
                Date buyDate = resultSet.getTimestamp("buyDate");
                Orders order = new Orders(customer2,buyName,buyNum,buyDate);
                ans.add(order);
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

        return ans;
    }

    @Override
    public boolean buyBooks(Orders order) {
        boolean successBuy = false;
        Connection connection = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt1 = connection.prepareStatement("select * from book where name=? and nums>=?");
            psmt1.setString(1, order.getBuyName());
            psmt1.setInt(2,order.getBuyNum());
            psmt2 = connection.prepareStatement("update book set nums=? where name=?");
            connection.setAutoCommit(false);

            resultSet = psmt1.executeQuery();
            if (resultSet.next())
            {
                String name = resultSet.getString("name");
                int nums = resultSet.getInt("nums");
                Book buyBook = new Book(name,nums);
                psmt2.setInt(1,buyBook.getNums()-order.getBuyNum());
                psmt2.setString(2, buyBook.getName());
                int rows = psmt2.executeUpdate();
                successBuy = rows > 0 ? true:false;
            }
            String sql = "insert into orders(customer,buyName,buyNum,buyDate) values(?,?,?,?)";
            PreparedStatement psmt3 = connection.prepareStatement(sql);
            psmt3.setString(1, order.getCustomer());
            psmt3.setString(2, order.getBuyName());
            psmt3.setInt(3,order.getBuyNum());
            psmt3.setTimestamp(4, new Timestamp(order.getBuyDate().getTime()));
            int count = psmt3.executeUpdate();
            if (count > 0)
                System.out.println("订单记录更新成功");
            else
            {
                System.out.println("订单记录更新失败...");
                throw new RuntimeException();                 // 订单记录出现异常(正常来说一个购买记录必然对应一个订单)
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null)           // 出现异常，我们的购买订单和数据库的修改应该是同步的
            {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                psmt2.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
        return successBuy;
    }

    @Override
    public Book insertBook(Book book) {
        Connection connection = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        ResultSet resultSet = null;
        Book backbook = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            psmt1 = connection.prepareStatement("select * from book where name=?");
            psmt1.setString(1, book.getName());
            psmt2 = connection.prepareStatement("update book set price=?,nums=? where name=?");
            psmt3 = connection.prepareStatement("insert into book(name,price,writer,nums) values(?,?,?,?)");
            resultSet = psmt1.executeQuery();
            if(resultSet.next())
            {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int nums = resultSet.getInt("nums");
                String writer = resultSet.getString("writer");
                psmt2.setDouble(1,book.getPrice());
                psmt2.setInt(2,nums+book.getNums());
                psmt2.setString(3,name);
                psmt2.executeUpdate();
                backbook = new Book(name,book.getPrice(), writer,book.getNums()+nums);
            }
            else
            {
                psmt3.setString(1,book.getName());
                psmt3.setDouble(2,book.getPrice());
                psmt3.setString(3,book.getWriter());
                psmt3.setInt(4,book.getNums());
                psmt3.executeUpdate();
                backbook = book;
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
                psmt3.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                psmt2.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
        return backbook;
    }

    @Override
    public void insertOrder(Orders o) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            String sql = "insert into orders(customer,buyName,buyNum) values(?,?,?)";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1, o.getCustomer());
            psmt.setString(2, o.getBuyName());
            psmt.setInt(3,o.getBuyNum());
            int count = psmt.executeUpdate();
            if (count > 0)
                System.out.println("订单记录更新成功");
            else
                System.out.println("订单记录更新失败...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
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

    }

    @Override
    public ArrayList<Book> getBooksByWriter(String writer) {
        ArrayList<Book> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            String sql = "select * from book where writer like ?";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1,"%"+writer+"%");
            resultSet = psmt.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String author = resultSet.getString("writer");
                int nums = resultSet.getInt("nums");
                Book book = new Book(name,price,author,nums);
                ans.add(book);
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
        return ans;
    }
}
