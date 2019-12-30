package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> implements ResultSetExtractor<List<Genre>>
{

	public void saveGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
	}
	
	public Integer saveGenreWithId(Genre genre) throws ClassNotFoundException, SQLException
	{
		//return saveWithId("INSERT INTO tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
		String sql = "INSERT INTO tbl_genre (genre_name) values (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "genre_id" });
			ps.setString(1, genre.getGenreName());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}
	
	public void editGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public List<Genre> readGenres() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_genre", this);
	}
	
	public List<Genre> readGenresByGenreName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return mySqlTemplate.query("SELECT * FROM tbl_genre WHERE genre_name LIKE ?", new Object[] { searchString }, this);
	}
	
	public void insertBookGenre(Integer genreId, Integer bookId) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book_genres (genre_id, bookId) values (?,?)", new Object[] {genreId, bookId });
	}
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next())
		{
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
