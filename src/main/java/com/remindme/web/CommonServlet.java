package com.remindme.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.remindme.dao.CommonDao;
import com.remindme.dao.CommonDaoImpl;
import com.remindme.model.Note;
import com.remindme.model.NoteBook;
import com.remindme.model.User;


public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommonDao commonDao;
	public CommonServlet() {
		super();
	}
	
	public void init() {
		commonDao = new CommonDaoImpl();
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
            case "/signup":
            	createUser(request, response);
            	break;
            case "/login":
            	login(request, response);
            	break;
            // Notebook related functionality
            case "/createNoteBook":
            	createNoteBook(request, response);
            	break;
            case "/showAllNoteBooks":
            	showAllNoteBooks(request, response);
            	break;
            case "/createNote":
            	createNote(request,response);
            	break;
            case "/deleteNoteBook":
            	deleteNoteBook(request,response);
            	break;
            case "/editNoteBook":
            	editNoteBook(request, response);
            	break;
            case "/renameNoteBook":
            	renameNoteBook(request,response);
            	break;
            case "/deleteNote":
            	deleteNote(request, response);
            	break;
            case "/showNoteDetails":
            	showNoteDetails(request, response);
            	break;
            case "/editNote":
            	editNote(request, response);
            	break;
            case "/listAllNotes":
            	listAllNotes(request,response);
            	break;
            case "/showNoteDetailsNote":
            	showNoteDetailsNote(request, response);
            	break;
            case "/deleteNotes": // Delete note in notes section
            	deleteNotes(request,response);
            	break;
            case "/editNotes": // Edit note in notes section
            	editNotes(request, response);
            	break;
            case "/editUser":
            	editUser(request, response);
            	break;
            case "/updateUser":
            	updateUser(request, response);
            	break;
            case "/logout":
            	logout(request, response);
            	break;
            default:
            	break;
            }
        }
        catch(Exception e) {
        	
        }
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("userId");
		User user = new User(user_id, userName, mobileNumber, email, password);
		boolean status = commonDao.updateUser(user);
		if(status)
		{
			request.setAttribute("Notification", "Success!! User profile updated successfully.");
			session.setAttribute("user", user);
			session.setAttribute("userName", user.getUserName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
	        dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("Notification", "Sorry, can't update user profile.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
	        dispatcher.forward(request, response);
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Notification", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
        dispatcher.forward(request, response);
	}

	private void editNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String noteName = request.getParameter("noteName");
		String date1 = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(date1);
		String date2 = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(date2);
		String date3 = request.getParameter("remainderDate");
		LocalDate remainderDate = LocalDate.parse(date3);
		String statusName = request.getParameter("statusName");
		String tagName = request.getParameter("tagName");
		String noteDescription = request.getParameter("noteDescription");
		Note note = new Note(note_id,endDate, noteDescription, noteName, remainderDate, startDate, statusName, tagName);
		commonDao.updateNote(note);
		listAllNotes(request, response);
	}

	private void deleteNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int note_id = Integer.parseInt(request.getParameter("itemId"));
		commonDao.deleteNote(note_id);
		listAllNotes(request, response);	
		}

	private void showNoteDetailsNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int note_id = Integer.parseInt(request.getParameter("itemId"));
		Note note = commonDao.showNote(note_id);
		request.setAttribute("note", note);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showNoteDetail.jsp");
        dispatcher.forward(request, response);
	}

	private void listAllNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("userId");
		List<Note> noteObjDate = commonDao.showDailyTasks(user_id);
		
		List<Note> note = commonDao.showAllNotes(user_id);
		request.setAttribute("note", note);
		request.setAttribute("noteObjDate", noteObjDate);
		request.setAttribute("countRemainder", noteObjDate.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("notes.jsp");
        dispatcher.forward(request, response);
	}

	private void editNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String noteName = request.getParameter("noteName");
		String date1 = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(date1);
		String date2 = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(date2);
		String date3 = request.getParameter("remainderDate");
		LocalDate remainderDate = LocalDate.parse(date3);
		String statusName = request.getParameter("statusName");
		String tagName = request.getParameter("tagName");
		String noteDescription = request.getParameter("noteDescription");
		int notebook_id = Integer.parseInt(request.getParameter("notebook_id"));
		Note note = new Note(note_id,endDate, noteDescription, noteName, remainderDate, startDate, statusName, tagName);
		commonDao.updateNote(note);
		NoteBook noteBook = commonDao.getNoteBook(notebook_id);
		request.setAttribute("noteBook", noteBook);
		showParticularNotebook(request, response);
	}

	private void showNoteDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NumberFormatException {
		int note_id = Integer.parseInt(request.getParameter("itemId"));
		Note note = commonDao.showNote(note_id);
		request.setAttribute("note", note);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showNote.jsp");
        dispatcher.forward(request, response);
	}

	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int note_id = Integer.parseInt(request.getParameter("itemId"));
		int notebook_id = Integer.parseInt(request.getParameter("notebook_id"));
		commonDao.deleteNote(note_id);
		NoteBook noteBook = commonDao.getNoteBook(notebook_id);
		request.setAttribute("noteBook", noteBook);
		showParticularNotebook(request, response);
	}

	private void renameNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int notebook_id = Integer.parseInt(request.getParameter("notebook_id"));
		String noteBookName = request.getParameter("noteBookName");
		commonDao.renameNoteBook(notebook_id, noteBookName);
		showAllNoteBooks(request, response);
	}

	private void showParticularNotebook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		NoteBook notebook = (NoteBook) request.getAttribute("noteBook");
		int id = notebook.getId();
		List< Note > note = commonDao.showParticularNotebook(id);
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("userId");
		List<Note> noteObjDate = commonDao.showDailyTasks(user_id);
		request.setAttribute("noteObjDate", noteObjDate);
		request.setAttribute("countRemainder", noteObjDate.size());
		
		request.setAttribute("note", note);
		RequestDispatcher dispatcher = request.getRequestDispatcher("particularNotes.jsp");
        dispatcher.forward(request, response);
	}

	private void createNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int notebook_id = Integer.parseInt(request.getParameter("notebook_id"));
		String noteName = request.getParameter("noteName");
		String date1 = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(date1);
		String date2 = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(date2);
		String date3 = request.getParameter("remainderDate");
		LocalDate remainderDate = LocalDate.parse(date3);
		String statusName = request.getParameter("statusName");
		String tagName = request.getParameter("tagName");
		String noteDescription = request.getParameter("noteDescription");
		
		Note note = new Note(endDate, noteDescription, noteName, remainderDate, startDate, notebook_id, statusName, tagName);
		System.out.println("Notebook id : "+notebook_id+" noteName: "+noteName+" startdate: "+date1+" endStart:"+ date2+ " remainderDate: "+remainderDate+
				" statusName: "+statusName+" tagName: "+tagName+" noteDesp:"+noteDescription);
		commonDao.insertNote(note);
		NoteBook noteBook = commonDao.getNoteBook(notebook_id);
		request.setAttribute("noteBook", noteBook);
		showParticularNotebook(request, response);
	}

	private void editNoteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("itemId"));
		NoteBook noteBook = commonDao.getNoteBook(id);
		request.setAttribute("noteBook", noteBook);
		showParticularNotebook(request, response);
	}

	private void deleteNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("itemId"));
		System.out.println("id:"+id);
		commonDao.deleteNoteBook(id);
		showAllNoteBooks(request, response);
	}

	private void showAllNoteBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List< NoteBook > noteBooks = commonDao.getAllNoteBook(commonDao.getUserId());
		for(NoteBook notebook : noteBooks)
		{
			int id = notebook.getId();
			int countOfNotes = commonDao.countOfNotes(id);
			notebook.setValue(countOfNotes);
		}
		request.setAttribute("noteBooks", noteBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("noteBooks.jsp");
        dispatcher.forward(request, response);
	}

	private void createNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("Inside createNoteBook");
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("userId");
		String noteBookName = request.getParameter("noteBookName");
		NoteBook noteBook = new NoteBook(noteBookName, user_id);
		commonDao.createNoteBook(noteBook);
	    showAllNoteBooks(request, response);
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User(userName, mobileNumber, email, password);
		System.out.println(user);
		commonDao.createUser(user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("Inside login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		boolean status = commonDao.validateLogin(email, password);
		
		if(status)
		{
		String name = commonDao.getUserName();
		session.setAttribute("userId", commonDao.getUserId());
		session.setAttribute("userName", name);
		session.setAttribute("user", commonDao.getUser(commonDao.getUserId()));
		showAllNoteBooks(request, response);
		}
		else {
			System.out.println("Inside else");
		    session.setAttribute("error", "Error");
			session.setAttribute("message", "Wrong username or password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
