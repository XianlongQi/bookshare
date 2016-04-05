package com.qixl.bookshare.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.qixl.bookshare.exception.DBException;
import com.qixl.bookshare.model.Book;
import com.qixl.bookshare.model.Pagenation;
import com.qixl.bookshare.util.DBUtils;
import com.qixl.common.JDBCCallBack;
import com.qixl.common.JDBCTemplate;

public class BookDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void save(Book book) {
        String sql = "insert into book(name,picture,owner_id,current_owner_id,author,description,created_time,updated_time,owner_name,current_owner_name) values (?,?,?,?,?,?,NOW(),NOW(),?,?)";
        conn = DBUtils.getConnection();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getPicture());
            pstmt.setInt(3, book.getOwnerId());
            pstmt.setInt(4, book.getCurrentId());
            pstmt.setString(5, book.getAuthor());
            pstmt.setString(6, book.getDesc());
            pstmt.setString(7, book.getOwnerName());
            pstmt.setString(8, book.getCurrentOwnerName());

            pstmt.executeUpdate();

            rs = (ResultSet) pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
                book.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
    }

    public void update(Book book) {
        String sql = "UPDATE book SET name = ?,author = ?,description = ?,updated_time = NOW() WHERE id = "
                + book.getId();

        try {
            conn = DBUtils.getConnection();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getDesc());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }
    }

    // 查询书籍
    public ArrayList<Book> query(int userId, Pagenation pagenation,String status) {
        
        pagenation.setTotalCount(this.getMyBookCount(userId,status));     
        if (pagenation.getCurretnPage() > pagenation.getPageCount()) {    
            pagenation.setCurretnPage(pagenation.getCurretnPage());       
        }                       
        String sql ="SELECT * FROM book WHERE "+this.getWhereSQL(userId, status)+" ORDER BY updated_time DESC LIMIT "+ pagenation.getOffset() + "," + pagenation.getPageSize() + ";";
        JDBCTemplate<Book> jdbcTemplate = new JDBCTemplate<Book>();
        ArrayList<Book> bookList  = jdbcTemplate.query(sql,new JDBCCallBack<Book>() {
            
            @Override
            public Book rsToObject(ResultSet rs) throws SQLException {
                Book book = new Book();                                       
                book.setId(rs.getInt("id"));                                  
                book.setName(rs.getString("name"));                           
                book.setPicture(rs.getString("picture"));                     
                book.setOwnerId(rs.getInt("owner_id"));                       
                book.setCurrentId(rs.getInt("current_owner_id"));             
                book.setAuthor(rs.getString("author"));                       
                book.setDesc(rs.getString("description"));                    
                book.setOwnerName(rs.getString("owner_name"));                
                book.setCurrentOwnerName(rs.getString("current_owner_name")); 
                return book;
            }

            @Override
            public void setParams(PreparedStatement pstmt) throws SQLException {
                
            }
        });
        return bookList;
//        ArrayList<Book> bookList = new ArrayList<Book>();
//        try {
//            conn = DBUtils.getConnection();
//            pagenation.setTotalCount(this.getMyBookCount(userId,status));
//            if (pagenation.getCurretnPage() > pagenation.getPageCount()) {
//                pagenation.setCurretnPage(pagenation.getCurretnPage());
//            }
////            pstmt = (PreparedStatement) conn.prepareStatement(
////                    "select b.*,u.user_name as current_owner_name from book as b left join user as u on b.current_owner_id = u.id where owner_id = "
////                            + userId +  " AND deleted = 0 limit " + pagenation.getOffset() + "," + pagenation.getPageSize() + ";");
//            String sql ="SELECT * FROM book WHERE "+this.getWhereSQL(userId, status)+" ORDER BY updated_time DESC LIMIT "+ pagenation.getOffset() + "," + pagenation.getPageSize() + ";";
////            System.out.println("sql:"+sql);
//            pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            // pstmt.setInt(1, userId);
//            rs = (ResultSet) pstmt.executeQuery();
//            while (rs.next()) {
//                Book book = new Book();
//                book.setId(rs.getInt("id"));
//                book.setName(rs.getString("name"));
//                book.setPicture(rs.getString("picture"));
//                book.setOwnerId(rs.getInt("owner_id"));
//                book.setCurrentId(rs.getInt("current_owner_id"));
//                book.setAuthor(rs.getString("author"));
//                book.setDesc(rs.getString("description"));
//                book.setOwnerName(rs.getString("owner_name"));
//                book.setCurrentOwnerName(rs.getString("current_owner_name"));
//                bookList.add(book);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DBException();
//        } finally {
//            DBUtils.close(rs, pstmt, conn);
//        }
//        return bookList;
    }

    // 根据userId查询书籍
    public ArrayList<Book> queryByUserId(int userId) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        String sql = "select * from book where owner_id=?";
        conn = DBUtils.getConnection();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = (ResultSet) pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }

        return bookList;

    }

    // 查询所有书籍
    public ArrayList<Book> queryAll() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        String sql = "select * from book";
        conn = DBUtils.getConnection();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = (ResultSet) pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }

        return bookList;

    }

    public ArrayList<Book> queryMyOutBook(int userId, Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try {
            conn = DBUtils.getConnection();
            pagenation.setTotalCount(this.getMyBookOutCount(userId));
            if (pagenation.getCurretnPage() > pagenation.getPageCount()) {
                pagenation.setCurretnPage(pagenation.getCurretnPage());
            }
            pstmt = (PreparedStatement) conn.prepareStatement(
                    "select b.*,u.user_name as current_owner_name from book as b left join user as u on b.current_owner_id = u.id where owner_id = "
                            + userId + " and current_owner_id != " + userId + " AND deleted = 0  limit " + pagenation.getOffset() + ","
                            + pagenation.getPageSize() + ";");
            rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }

        return bookList;

    }

    public ArrayList<Book> queryMyInBook(int userId, Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try {
            conn = DBUtils.getConnection();
            pagenation.setTotalCount(this.getMyBookInCount(userId));
            if (pagenation.getCurretnPage() > pagenation.getPageCount()) {
                pagenation.setCurretnPage(pagenation.getCurretnPage());
            }
            pstmt = (PreparedStatement) conn.prepareStatement(
                    "select b.*,u.user_name as current_owner_name from book as b left join user as u on b.current_owner_id = u.id where  current_owner_id = "
                            + userId + " and owner_id !=" + userId + " AND deleted = 0  limit " + pagenation.getOffset() + ","
                            + pagenation.getPageSize() + ";");
            rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }

        return bookList;

    }

    public ArrayList<Book> queryMyBorrowBook(int userId, Pagenation pagenation) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try {
            conn = DBUtils.getConnection();
            pagenation.setTotalCount(this.getMyBookBorrowCount(userId));
            if (pagenation.getCurretnPage() > pagenation.getPageCount()) {
                pagenation.setCurretnPage(pagenation.getCurretnPage());
            }
            pstmt = (PreparedStatement) conn.prepareStatement(
                    "select b.*,u.user_name as current_owner_name from book as b left join user as u on b.current_owner_id = u.id where owner_id = "
                            + userId + " and current_owner_id =" + userId + " AND deleted = 0  limit " + pagenation.getOffset() + ","
                            + pagenation.getPageSize() + ";");
            rs = (ResultSet) pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, pstmt, conn);
        }

        return bookList;

    }

    public int getMyBookCount(int userId,String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mybookCount = 0;
        try {
            conn = DBUtils.getConnection();
            String countSQL = "SELECT count(*) AS mybookCount FROM book WHERE "+this.getWhereSQL(userId, status);
            stmt = (PreparedStatement) conn.prepareStatement(countSQL);
            rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                mybookCount = rs.getInt("mybookCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return mybookCount;
    }

    public int getMyBookOutCount(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mybookCount = 0;
        try {
            conn = DBUtils.getConnection();
            String countSQL = "SELECT count(*) AS mybookCount FROM book WHERE  owner_id = " + userId
                    + " and current_owner_id != " + userId+ " AND deleted = 0 ;";
            stmt = (PreparedStatement) conn.prepareStatement(countSQL);
            rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                mybookCount = rs.getInt("mybookCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return mybookCount;
    }

    public int getMyBookBorrowCount(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mybookCount = 0;
        try {
            conn = DBUtils.getConnection();
            String countSQL = "SELECT count(*) AS mybookCount FROM book WHERE   owner_id = " + userId
                    + " and current_owner_id = " + userId+ " AND deleted = 0 ;";
            stmt = (PreparedStatement) conn.prepareStatement(countSQL);
            rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                mybookCount = rs.getInt("mybookCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return mybookCount;
    }

    public int getMyBookInCount(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mybookCount = 0;
        try {
            conn = DBUtils.getConnection();
            String countSQL = "SELECT count(*) AS mybookCount FROM book WHERE   owner_id != " + userId
                    + " and current_owner_id = " + userId+ " AND deleted = 0 ;";
            stmt = (PreparedStatement) conn.prepareStatement(countSQL);
            rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                mybookCount = rs.getInt("mybookCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return mybookCount;
    }

    /*
    // 查询书籍
    private ArrayList<Book> query(int userId, Pagenation pagenation, String status) {
        String sql = "";
        if (status.equals("all")) {
            sql = "";
        } else if (status.equals("out")) {
            sql = "";
        } else if (status.equals("in")) {
            sql = "";
        } else if (status.equals("borrow")) {
            sql = "";
        }

        return null;
    }
*/
    public Book getById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book book = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from book as b where id = " + id;
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = (ResultSet) stmt.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPicture(rs.getString("picture"));
                book.setOwnerId(rs.getInt("owner_id"));
                book.setCurrentId(rs.getInt("current_owner_id"));
                book.setAuthor(rs.getString("author"));
                book.setDesc(rs.getString("description"));
                book.setOwnerName(rs.getString("owner_name"));
                book.setCurrentOwnerName(rs.getString("current_owner_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return book;
    }

    public void updateDeleted(int id, int deleted) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String sql = "";
            conn = DBUtils.getConnection();
            stmt = (PreparedStatement) conn.prepareStatement("UPDATE book SET deleted = "+deleted+",updated_time = NOW() WHERE id = "+id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        }finally{
            DBUtils.close(null, stmt, conn);
        }

    }
    
    private String getWhereSQL (int userId,String status){
        String sql ="";
        if("all".equals(status)){
            sql="owner_id = "+ userId + " AND deleted = 0 ";
        }else if("out".equals(status)){
            sql="owner_id = "+ userId + " and current_owner_id != " + userId + " AND deleted = 0 ";
        }else if("in".equals(status)){
            sql="current_owner_id = "+ userId + " and owner_id !=" + userId + " AND deleted = 0 ";
        }else if("borrow".equals(status)){
            sql="owner_id = "+ userId + " and current_owner_id =" + userId + " AND deleted = 0 ";
        }
        return sql;
    }

}
