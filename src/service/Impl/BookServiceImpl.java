package service.Impl;

import dao.BookDao;
import dao.Impl.BookDaoImpl;
import enity.Book;
import enity.Orders;
import service.BookService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public ArrayList<Book> getAllBooks(int start,int count) {
        return bookDao.getAllBooks(start,count);
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public ArrayList<Orders> getOrdersByCustomer(String customer) {
        return bookDao.getOrdersByCustomer(customer);
    }

    @Override
    public boolean buyBooks(Orders order) {
        return bookDao.buyBooks(order);
    }

    @Override
    public Book insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    @Override
    public ArrayList<Book> getBooksByWriter(String writer) {
        return bookDao.getBooksByWriter(writer);
    }

    @Override
    public void insertOrder(Orders order) {
        bookDao.insertOrder(order);
    }
}
