package com.ss.lms.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;

public class AdminService 
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
	
	@Autowired
	PublisherDAO pdao;
	
	/*
	 * READ METHODS:
	 */
	public List<Author> readAuthors() throws SQLException 
	{
		List<Author> authors = new ArrayList<Author>();
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
		List<Book> books = new ArrayList<Book>();
		try {

			books = bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		}
		return books;
	}
	
	public List<BookCopies> readBookCopies() throws SQLException 
	{
		List<BookCopies> bcs = new ArrayList<BookCopies>();
		try {
			bcs = bcdao.readBookCopies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return bcs;
	}
	
	public List<BookLoans> readBookLoans() throws SQLException 
	{
		List<BookLoans> bls = new ArrayList<BookLoans>();
		try {

			bls = bldao.readBookLoans();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return bls;
	}
	
	public List<Borrower> readBorrowers() throws SQLException
	{
		List<Borrower> borrowers = new ArrayList<Borrower>();
		try {
			borrowers = bordao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		}
		return borrowers;
	}
	
	public List<Branch> readBranches() throws SQLException 
	{
		List<Branch> branches = new ArrayList<Branch>();
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
		
		List<Genre> genres = new ArrayList<Genre>();
		try {
			genres = gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading genres faiiled");
		}
		return genres;
	}
	
	public List<Publisher> readPublishers() throws SQLException
	{
		
		List<Publisher> publishers = new ArrayList<Publisher>();
		try {
			publishers = pdao.readPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading publishers failed");
		}
		return publishers;
	}
	/*
	 * ADD METHODS:
	 */
	@Transactional
	public String addAuthor(Author author) throws SQLException
	{
		try
		{
			Integer authorId = adao.saveAuthorWithId(author);
			if(author.getBooks()!=null)
			{
				for(Book b: author.getBooks())
					adao.insertBookAuthors(b.getBookId(), authorId);
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Author Failed");
		}
		return "Author Added Successfully";
	}
	
	@Transactional
	public String addBook(Book book) throws SQLException
	{
		try
		{
			Integer bookId = bdao.saveBookWithId(book);
			if(book.getAuthors()!=null)
			{
				for(Author a: book.getAuthors())
					bdao.insertBookAuthors(bookId, a.getAuthorId());
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Failed");
		}
		return "Book Added Successfully";
	}
	
	@Transactional
	public String addBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			Integer bookId = bcdao.saveBookCopiesWithId(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Copies Failed");
		}
		return "Book Copy Added Successfully";
	}
	
	@Transactional
	public String addBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			Integer bookId = bldao.saveBookLoansWithId(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Loans Failed");
		}
		return "Book Loan Added Successfully";
	}
	
	@Transactional
	public String addBorrower(Borrower b) throws SQLException
	{
		try
		{
			Integer bookId = bordao.saveBorrowerWithId(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Borrower Failed");
		}
		return "Borrower Added Successfully";
	}
	
	@Transactional
	public String addBranch(Branch b) throws SQLException
	{
		try
		{
			Integer bookId = brdao.saveBranchWithId(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Library Branch Failed");
		} 
		return "Library Branch Added Successfully";
	}
	
	@Transactional
	public String addGenre(Genre g) throws SQLException
	{
		try
		{
			Integer bookId = gdao.saveGenreWithId(g);
			if(g.getBooks()!=null)
			{
				for(Book b: g.getBooks())
					gdao.insertBookGenre(g.getGenreId(),b.getBookId());
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Genre Failed");
		}
		return "Genre Added Successfully";
	}
	
	@Transactional
	public String addPublishers(Publisher p) throws SQLException
	{
		try
		{
			Integer id = pdao.savePublisherWithId(p);
		
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Publisher Failed");
		}
		return "Publisher Added Successfully";
	}
	
	/*
	 * UPDATE METHODS:
	 */
	@Transactional
	public String editAuthor(Author author) throws SQLException
	{
		try
		{
			adao.editAuthor(author);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Author Failed");
		}
		return "Edit Author Successfully";
	}
	@Transactional
	public String editBook(Book book) throws SQLException
	{
		try
		{
			bdao.editBook(book);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Failed");
		} 
		return "Book Edit Success";
	}
	@Transactional
	public String editBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			bcdao.editBookCopies(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Copies Failed");
		}
		return "Book Copies Edit Success";
	}
	@Transactional
	public String editBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			bldao.editBookLoans(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Loans Failed");
		}
		return "Book Loans Edit Success";
	}
	@Transactional
	public String editBorrower(Borrower b) throws SQLException
	{
		try
		{
			bordao.editBorrower(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Borrower Failed");
		}
		return "Borrower Edit Success";
	}
	@Transactional
	public String editBranch(Branch b) throws SQLException
	{
		try
		{
			brdao.editBranch(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Branch Failed");
		}
		return "Branch Edit Success";
	}
	@Transactional
	public String editGenre(Genre g) throws SQLException
	{
		try
		{
			gdao.editGenre(g);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Genre Failed");
		}
		return "Genre Edit Success";
	}
	
	@Transactional
	public String editPublisher(Publisher p) throws SQLException
	{
		try
		{
			pdao.editPublisher(p);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Edit Publisher Failed");
		}
		return "Publisher Edit Successfully";
	}
	
	/*
	 * DELETE METHODS:
	 */
	@Transactional
	public String deleteAuthor(Author author) throws SQLException
	{
		try
		{
			adao.deleteAuthor(author);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Deleting Author Failed");
		}
		return "Deleted Author";
	}

	@Transactional
	public String deleteBook(Book book) throws SQLException
	{
		try
		{
			bdao.deleteBook(book);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Failed");
		}
		return "Book Delete Success";
	}

	@Transactional
	public String deleteBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			bcdao.deleteBookCopies(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Copies Failed");
		}
		return "Book Copies Delete Success";
	}

	@Transactional
	public String deleteBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			bldao.deleteBookLoans(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Loans Failed");
		}
		return "Book Loans Delete Success";
	}

	@Transactional
	public String deleteBorrower(Borrower b) throws SQLException
	{
		try
		{
			bordao.deleteBorrower(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Borrower Failed");
		}
		return "Borrower Delete Success";
	}

	@Transactional
	public String deleteBranch(Branch b) throws SQLException
	{
		try
		{
			brdao.deleteBranch(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Branch Failed");
		}
		return "Branch Delete Success";
	}
	
	@Transactional
	public String deleteGenre(Genre g) throws SQLException
	{
		try
		{
			gdao.deleteGenre(g);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Genre Failed");
		}
		return "Genre Delete Success";
	}
	
	@Transactional
	public String deletePublisher(Publisher p) throws SQLException
	{
		try
		{
			pdao.deletePublisher(p);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Publisher Failed");
		}
		return "Publisher Delete Successfully";
	}
}
