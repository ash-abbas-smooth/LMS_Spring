package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ss.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch> implements ResultSetExtractor<List<Branch>>
{
	public void saveBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("INSERT INTO tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {branch.getBranchName(), branch.getAddress()});
	}
	
	public Integer saveBranchWithId(Branch branch) throws ClassNotFoundException, SQLException
	{
		//return saveWithId("INSERT INTO tbl_library_branch (branchName, branchAddress) values (?)", new Object[] {branch.getBranchName(),  branch.getAddress()});
		String sql = "INSERT INTO tbl_library_branch (branchName, branchAddress) values (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		mySqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "branchName", "branchAddress" });
			ps.setString(1, branch.getBranchName());
			ps.setString(2, branch.getAddress());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}
	public void editBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("UPDATE tbl_branch SET branchName = ? WHERE branchId = ?", new Object[] {branch.getBranchName(), branch.getBranchId()});
		mySqlTemplate.update("UPDATE tbl_branch SET branchAddress = ? WHERE branchId = ?", new Object[] {branch.getAddress(), branch.getBranchId()});
		
	}
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		mySqlTemplate.update("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] {branch.getBranchId()});
	}
	
	public List<Branch> readBranches() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("SELECT * FROM tbl_library_branch", this);
	}
	
	public List<Branch> readBranchesByBranchName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return mySqlTemplate.query("SELECT * FROM tbl_library_branch WHERE branchName LIKE ?", new Object[] { searchString }, this);
	}
	
	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		while(rs.next())
		{
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));		
			branches.add(branch);
		}
		return branches;
	}

	
}
