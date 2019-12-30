package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> implements ResultSetExtractor<List<BookLoans>>
{
	public void saveBookLoans(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)", 
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate(), bl.getDateIn()});
	}

	
	public Integer saveBookLoansWithId(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		/*return saveWithId("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)", 
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate(), bl.getDateIn()});
*/		String sql = "INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, bl.getBookId());
			ps.setInt(2, bl.getBranchId());
			ps.setInt(3, bl.getCardNo());
			ps.setDate(4, bl.getDateOut());
			ps.setDate(5, bl.getDueDate());
			ps.setDate(6, bl.getDateIn());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}
	
	public void editBookLoans(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_book_loans SET dateDue = ? WHERE cardNo = ? && bookId = ? && branchId = ? && dateOut = ?",
				new Object[] {bl.getDueDate(), bl.getCardNo(), bl.getBookId(), bl.getBranchId(), bl.getDateOut()});
		mySqlTemplate.update("UPDATE tbl_book_loans SET dateIn = ? WHERE cardNo = ? && bookId = ? && branchId = ? && dateOut = ?",
				new Object[] {bl.getDateIn(), bl.getCardNo(), bl.getBookId(), bl.getBranchId(), bl.getDateOut()});
	}
	public void deleteBookLoans(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_book_loans WHERE bookId = ? && branchId = ? && cardNo = ? &"
				+ "dateOut = ?", new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut()});
	}
	
	public List<BookLoans> readBookLoans() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_book_loans", this);
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		List<BookLoans> bls = new ArrayList<BookLoans>();
		while(rs.next())
		{
			BookLoans bl = new BookLoans();
			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));
			bls.add(bl);
		}
		return bls;
	}
	
}
