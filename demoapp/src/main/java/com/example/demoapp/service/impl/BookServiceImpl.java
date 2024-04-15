package com.example.demoapp.service.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> sortBookByYear() {
        BookDB.books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getYear()-o2.getYear();
            }
        });
        return BookDB.books;
    }

    @Override
    public List<Book> searchBook(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : BookDB.books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> searchBookByYear(int startYear, int endYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : BookDB.books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                result.add(book);
            }
        }
        return result;
    }
}
