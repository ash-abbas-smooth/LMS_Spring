package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> implements ResultSetExtractor<List<Borrower>>
{
	public void saveBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	
	public Integer saveBorrowerWithId(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		//return saveWithId("INSERT INTO tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
		String sql = "INSERT INTO tbl_borrower (name, address, phone) values (?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "cardNo"});
			ps.setString(1, borrower.getName());
			ps.setString(2, borrower.getAddress());
			ps.setString(3, borrower.getPhone());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}
	public void editBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_borrower SET name = ? WHERE cardNo = ?", new Object[] {borrower.getName(), borrower.getCardNo()});
		mySqlTemplate.update("UPDATE tbl_borrower SET address = ? WHERE cardNo = ?", new Object[] {borrower.getAddress(), borrower.getCardNo()});
		mySqlTemplate.update("UPDATE tbl_borrower SET phone = ? WHERE cardNo = ?", new Object[] {borrower.getPhone(), borrower.getCardNo()});
	}
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readBorrowers() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_borrower", this);
	}
	
	public List<Borrower> readBorrowersByName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return mySqlTemplate.query("SELECT * FROM tbl_borrower WHERE name LIKE ?", new Object[] { searchString }, this);
	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException{
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next())
		{
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrowers.add(borrower);
		}
		return borrowers;
	}


	
}
