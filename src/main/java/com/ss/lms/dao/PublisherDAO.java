package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements ResultSetExtractor<List<Publisher>>
{
	public void savePublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)",
				new Object[] {p.getPublisherName(),p.getPublisherAddress(),p.getPublisherPhone()});
	}
	public Integer savePublisherWithId(Publisher p) throws SQLException, ClassNotFoundException
	{
		//return saveWithId("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)",
		//		new Object[] {p.getPublisherName(),p.getPublisherAddress(),p.getPublisherPhone()});
		String sql = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "publisherId" });
			ps.setString(1, p.getPublisherName());
			ps.setString(2, p.getPublisherAddress());
			ps.setString(3, p.getPublisherPhone());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey();
	}
	public void editPublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?", new Object[] {p.getPublisherName(),p.getPublisherId()});
		mySqlTemplate.update("UPDATE tbl_publisher SET publisherAddress = ? WHERE publisherId = ?", new Object[] {p.getPublisherAddress(),p.getPublisherId()});
		mySqlTemplate.update("UPDATE tbl_publisher SET publisherPhone = ? WHERE publisherId = ?", new Object[] {p.getPublisherPhone(),p.getPublisherId()});
	}
	public void deletePublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update( "DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] {p.getPublisherId()});
	}
	
	public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_publishers", this);
	}
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> ps = new ArrayList<Publisher>();
		while(rs.next())
		{
			Publisher pub = new Publisher();
			pub.setPublisherId(rs.getInt("publisherId"));
			pub.setPublisherName(rs.getString("publisherName"));
			pub.setPublisherAddress(rs.getString("publisherAddress"));
			pub.setPublisherPhone(rs.getString("publisherPhone"));
			ps.add(pub);
		}
		return ps;
	}


}
