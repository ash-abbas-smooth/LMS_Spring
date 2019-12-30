package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Author;

public class AuthorDAO extends BaseDAO<Author> implements ResultSetExtractor<List<Author>>
{

	public void saveAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	public Integer saveAuthorWithId(Author author) throws ClassNotFoundException, SQLException
	{
		//("INSERT INTO tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
		String sql = "INSERT INTO tbl_author (authorName) values (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "authorId" });
			ps.setString(1, author.getAuthorName());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey();
	}
	
	public void editAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAuthors() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_author", this);
	}
	
	public List<Author> readAuthorsByAuthorName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return mySqlTemplate.query("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] { searchString }, this);
	}
	
	public void insertBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?,?)", new Object[] {bookId, authorId});
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		
		List<Author> authors = new ArrayList<Author>();
		while(rs.next())
		{
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}


	
}
