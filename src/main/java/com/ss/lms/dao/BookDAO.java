package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>>
{

	public void saveBook(Book book) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPubId()});
	}
	
	public Integer saveBookWithId(Book book) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO tbl_book (title) values(?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "bookId" });
			ps.setString(1, book.getTitle());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey();
	}
	
	public void editBook(Book book) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_book SET title = ? WHERE bookId = ?", new Object[] {book.getTitle(), book.getBookId()});
		mySqlTemplate.update("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] {book.getPubId(), book.getBookId()});
	}
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readBooks() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_book", this);
	}
	
	public List<Book> readBooksByTitle(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return mySqlTemplate.query("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] { searchString }, this);
	}
	
	public void insertBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {bookId, authorId});
	}
	public void insertBookGenres(Integer bookId, Integer genreId) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book_genres (bookId, authorId) values (?,?)", new Object[] {bookId, genreId});
	}
	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next())
		{
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubId(rs.getInt("pubId"));
			books.add(book);
		}
		return books;
	}

	
}
