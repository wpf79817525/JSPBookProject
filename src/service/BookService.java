package service;

import enity.Book;
import enity.Orders;

import java.util.ArrayList;

public interface BookService {
    public ArrayList<Book> getAllBooks(int start,int count);
    public ArrayList<Book> getAllBooks();
    public ArrayList<Orders> getOrdersByCustomer(String customer);
    public boolean buyBooks(Orders order);
    public Book insertBook(Book book);
    public void insertOrder(Orders order);

    public ArrayList<Book> getBooksByWriter(String writer);
}
