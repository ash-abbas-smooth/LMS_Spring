package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;

public class BorrowerService 
{
	@Autowired
	AuthorDAO adao;
	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	BookCopiesDAO bcdao;
	
	@Autowired
	BookLoansDAO bldao;
	
	@Autowired
	BorrowerDAO bordao;
	
	@Autowired
	BranchDAO brdao;
	
	@Autowired
	GenreDAO gdao;

	/*
	 * READ METHODS
	 */
	
	public List<Branch> readBranches() throws SQLException 
	{
		List<Branch> branches = new ArrayList<>();
		try {
			branches = brdao.readBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return branches;
	}
	
	public List<Genre> readGenres() throws SQLException
	{
		
		List<Genre> genres = new ArrayList<>();
		try {
			genres = gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading genres faiiled");
		}
		return genres;
	}

	public List<BookCopies> readBookCopies() throws SQLException 
	{
		List<BookCopies> bcs = new ArrayList<>();
		try {
			bcs = bcdao.readBookCopies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return bcs;
	}

	
	public List<Author> readAuthors() throws SQLException 
	{
		List<Author> authors = new ArrayList<>();
		try {
			authors = adao.readAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		}
		return authors;
	}
	
	public List<Book> readBooks() throws SQLException 
	{
		List<Book> books = new ArrayList<>();
		try {

			books = bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		}
		return books;
	}
	
}
