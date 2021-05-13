package com.remindme.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.remindme.utils.JDBCUtils;
import com.remindme.model.Note;
import com.remindme.model.NoteBook;
import com.remindme.model.User;

public class CommonDaoImpl implements CommonDao {

	private static final String insert_user_sql = "INSERT INTO user" + " (userName , mobileNumber, email, password) "
			+ "values " + "(?,?,?,?);";
	private static final String insert_notebook_sql = "INSERT INTO notebook" + " (noteBookName , user_id) " + "values "
			+ "(?,?);";
	private static final String show_all_notebook = "select * from notebook where user_id = ?;";
	private static final String delete_notebook_user = "delete from notebook where id = ?;";
	private static final String get_notebook_sql = "select * from notebook where id = ?; ";
	private static final String insert_note_sql = "INSERT INTO note"
			+ " (endDate , noteDescription , noteName, remainderDate, startDate, notebook_id, statusName, tagName) "
			+ "values " + "(?,?,?,?,?,?,?,?);";
	private static final String show_particular_notebook = "select * from note where noteBook_id = ?;";
	private static final String count_of_notes = "select count(*) from note where notebook_id = ?;";
	private static final String renameNotebook = "update notebook set noteBookName = ? where id = ?;";
	private static final String delete_note_sql = "delete from note where id = ?;";
	private static final String show_note = "select * from note where id = ?;";
	private static final String update_note = "update note set endDate = ?, noteDescription = ?, noteName = ?, remainderDate = ?, "
			+ "startDate = ?, statusName = ?, tagName = ? where id = ?;";

	private static final String showAllNotes = "select note.id, note.endDate, note.noteDescription, note.noteName, note.remainderDate, note.startDate, note.noteBook_id, note.statusName, note.tagName from notebook join note on notebook.id = note.noteBook_id where user_id = ?;"; 
	private static final String showDailyTasks = "select note.id, note.endDate, note.noteDescription, note.noteName, note.remainderDate, note.startDate, note.noteBook_id, note.statusName, note.tagName from notebook join note on notebook.id = note.noteBook_id where  date(remainderDate) = date(curdate()) and user_id = ?;";	
	private static final String getUser = "select * from user where id = ?;";
	private static final String updateUser = "update user set userName = ?, mobileNumber = ?, email = ?, password = ? where id = ?;";
	
	public int userId;
	public String userName;

	
	@Override
	public boolean updateUser(User user) throws SQLException {
		boolean status = false;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateUser)) {
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getMobileNumber());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getId());
			status = preparedStatement.executeUpdate() > 0;

			System.out.println("Updation status:" + status);
		}
		return status;
	}
	
	@Override
	public User getUser(int user_id) throws SQLException {
		User user = null;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(getUser);) {
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("userName");
				String mobileNumber = rs.getString("mobileNumber");
				String email = rs.getString("email");
				String password = rs.getString("password");
				user = new User(userName, mobileNumber, email, password);
			}
		}
		return user;
	}
	@Override
	public List<Note> showDailyTasks(int user_id) throws SQLException {
		List<Note> notes = new ArrayList<>();
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(showDailyTasks);) {
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int note_id = rs.getInt("id");
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				String noteDescription = rs.getString("noteDescription");
				String noteName = rs.getString("noteName");
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				int notebook_id = rs.getInt("notebook_id");
				String statusName = rs.getString("statusName");
				String tagName = rs.getString("tagName");
				notes.add(new Note(note_id, endDate, noteDescription, noteName, remainderDate, startDate, notebook_id,
						statusName, tagName));
			}
		}
		return notes;
	}

	
	
	@Override
	public List<Note> showAllNotes(int user_id) throws SQLException {
		List<Note> notes = new ArrayList<>();
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(showAllNotes);) {
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int note_id = rs.getInt("id");
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				String noteDescription = rs.getString("noteDescription");
				String noteName = rs.getString("noteName");
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				int notebook_id = rs.getInt("notebook_id");
				String statusName = rs.getString("statusName");
				String tagName = rs.getString("tagName");
				notes.add(new Note(note_id, endDate, noteDescription, noteName, remainderDate, startDate, notebook_id,
						statusName, tagName));
			}
		}
		return notes;
	}

	@Override
	public void updateNote(Note note) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(update_note)) {
			preparedStatement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
			preparedStatement.setString(2, note.getNoteDescription());
			preparedStatement.setString(3, note.getNoteName());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(note.getRemainderDate()));
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(note.getStartDate()));
			preparedStatement.setString(6, note.getStatusName());
			preparedStatement.setString(7, note.getTagName());
			preparedStatement.setInt(8, note.getId());
			boolean status = preparedStatement.executeUpdate() > 0;

			System.out.println("Updation status:" + status);

		}
	}

	@Override
	public void createUser(User user) throws SQLException {
		System.out.println("Entering createUser method");
		System.out.println(insert_user_sql);

		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_user_sql)) {

			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getMobileNumber());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());

			boolean status = preparedStatement.executeUpdate() > 0;

			System.out.println("Insertion status:" + status);

		}

	}

	@Override
	public boolean validateLogin(String email, String password) throws SQLException {
		boolean status = false;
		System.out.println("Validate login");
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from user where email = ? and password = ?;")) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			if (status) {
				userId = rs.getInt("id");
				userName = rs.getString("userName");
			}

		}
		return status;
	}

	@Override
	public int getUserId() {
		return userId;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void createNoteBook(NoteBook noteBook) throws SQLException {

		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_notebook_sql)) {

			preparedStatement.setString(1, noteBook.getNoteBookName());
			preparedStatement.setInt(2, noteBook.getUser_id());

			boolean status = preparedStatement.executeUpdate() > 0;

			System.out.println("Insertion status:" + status);

		}
	}

	@Override
	public List<NoteBook> getAllNoteBook(int user_id) {

		List<NoteBook> notebooks = new ArrayList<NoteBook>();
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(show_all_notebook);) {
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
				String noteBookName = rs.getString("noteBookName");
				notebooks.add(new NoteBook(id, noteBookName, user_id));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return notebooks;
	}

	@Override
	public boolean deleteNoteBook(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(delete_notebook_user);) {

			statement.setInt(1, id);
			// System.out.println("deleteMeeting in MeetingDao "+statement);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public NoteBook getNoteBook(int id) throws SQLException {
		NoteBook notebook = null;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(get_notebook_sql);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int note_id = rs.getInt("id");
				String noteBookName = rs.getString("noteBookName");
				int user_id = rs.getInt("user_id");
				notebook = new NoteBook(note_id, noteBookName, user_id);
			}
		}
		return notebook;
	}

	@Override
	public void insertNote(Note note) throws SQLException {
		System.out.println("Inside insertNote");
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_note_sql)) {

			preparedStatement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
			preparedStatement.setString(2, note.getNoteDescription());
			preparedStatement.setString(3, note.getNoteName());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(note.getRemainderDate()));
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(note.getStartDate()));
			preparedStatement.setInt(6, note.getNotebook_id());
			preparedStatement.setString(7, note.getStatusName());
			preparedStatement.setString(8, note.getTagName());

			boolean status = preparedStatement.executeUpdate() > 0;

			System.out.println("Insertion status:" + status);

		}
	}

	@Override
	public List<Note> showParticularNotebook(int id) throws SQLException {
		List<Note> notes = new ArrayList<>();
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(show_particular_notebook);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int note_id = rs.getInt("id");
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				String noteDescription = rs.getString("noteDescription");
				String noteName = rs.getString("noteName");
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				int notebook_id = rs.getInt("notebook_id");
				String statusName = rs.getString("statusName");
				String tagName = rs.getString("tagName");
				notes.add(new Note(note_id, endDate, noteDescription, noteName, remainderDate, startDate, notebook_id,
						statusName, tagName));
			}
		}
		return notes;
	}

	@Override
	public int countOfNotes(int notebook_id) throws SQLException {
		int count = 0;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(count_of_notes);) {
			preparedStatement.setInt(1, notebook_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}
		return count;
	}

	@Override
	public void renameNoteBook(int notebook_id, String noteBookName) throws SQLException {
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(renameNotebook)) {
			preparedStatement.setString(1, noteBookName);

			preparedStatement.setInt(2, notebook_id);

			boolean status = preparedStatement.executeUpdate() > 0;

			System.out.println("Updation status:" + status);

		}
	}

	@Override
	public void deleteNote(int note_id) throws SQLException {
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(delete_note_sql);) {

			statement.setInt(1, note_id);
			// System.out.println("deleteMeeting in MeetingDao "+statement);
			boolean rowDeleted = statement.executeUpdate() > 0;

		}

	}

	@Override
	public Note showNote(int note_id) throws SQLException {
		Note note = null;
		try (Connection connection = JDBCUtils.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(show_note);) {
			preparedStatement.setInt(1, note_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				LocalDate endDate = rs.getDate("endDate").toLocalDate();
				String noteDescription = rs.getString("noteDescription");
				String noteName = rs.getString("noteName");
				LocalDate remainderDate = rs.getDate("remainderDate").toLocalDate();
				LocalDate startDate = rs.getDate("startDate").toLocalDate();
				int notebook_id = rs.getInt("notebook_id");
				String statusName = rs.getString("statusName");
				String tagName = rs.getString("tagName");
				note = new Note(id, endDate, noteDescription, noteName, remainderDate, startDate, notebook_id,
						statusName, tagName);
			}
		}
		return note;
	}
	
}
