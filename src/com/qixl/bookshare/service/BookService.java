package com.qixl.bookshare.service;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PagesPerMinute;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.qixl.bookshare.dao.BookDao;
import com.qixl.bookshare.exception.DBException;
import com.qixl.bookshare.exception.ParameterException;
import com.qixl.bookshare.model.Book;
import com.qixl.bookshare.model.Pagenation;
import com.qixl.bookshare.util.DBUtils;
import com.qixl.bookshare.util.StringUtils;

public class BookService {
        
    public int sava (Book book) throws ParameterException{
        ParameterException parameterException = new ParameterException();
        if(StringUtils.isEmpty(book.getName())){
            parameterException.addErrorField("name", "请填写书名");
        }
        
        if(StringUtils.isEmpty(book.getAuthor())){
            parameterException.addErrorField("name", "请填写作者");
        }
        
        if(parameterException.isErrorField()){
            throw parameterException;
        }
        
        BookDao bookDao  = new BookDao();
        if(book.getId() == 0){
            bookDao.save(book);
        }else {
            bookDao.update(book);
        }
        
        return book.getId();
    }
    
    public ArrayList<Book> query(int userId,Pagenation pagenation,String status) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.query(userId,pagenation,status);
        
        return bookList;
    }
    
    public ArrayList<Book> queryByUserId (int userId) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.queryByUserId(userId);
        
        return bookList;
    }
    
    public ArrayList<Book> queryAll () {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.queryAll();
        
        return bookList;
    }
    
   

    public ArrayList<Book> queryMyOutBook(int userId,Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.queryMyOutBook(userId,pagenation);
        
        return bookList;
    }

    public ArrayList<Book> queryMyInBook(int userId,Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.queryMyInBook(userId,pagenation);
        
        return bookList;
    }

    public ArrayList<Book> queryMyBorrowBook(int userId,Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookDao bookDao  = new BookDao();
        bookList = bookDao.queryMyBorrowBook(userId,pagenation);
        
        return bookList;
    }

    public int getMyBookCount (int userId,String status){
        BookDao bookDao = new BookDao();
        return bookDao.getMyBookCount(userId,status);
    }
    
    public int getMyBookOutCount(int userId) {
        BookDao bookDao = new BookDao();
        return bookDao.getMyBookOutCount(userId);
    }
    
    public int getMyBookInCount(int userId) {
        BookDao bookDao = new BookDao();
        return bookDao.getMyBookInCount(userId);
    }

    public int getMyBookBorrowCount(int userId) {
        BookDao bookDao = new BookDao();
        return bookDao.getMyBookBorrowCount(userId);
    }

    public Book getById(int id) {
        BookDao bookDao = new BookDao();
        
        return bookDao.getById(id);
    }

    public void deleteById(int id) {
        BookDao bookDao = new BookDao();
        bookDao.updateDeleted(id,1);
        
    }
    

}
