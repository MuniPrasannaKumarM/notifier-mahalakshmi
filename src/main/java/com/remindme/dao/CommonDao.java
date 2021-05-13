package com.remindme.dao;

import java.sql.SQLException;
import java.util.List;

import com.remindme.model.Note;
import com.remindme.model.NoteBook;
import com.remindme.model.User;

public interface CommonDao {
	public void createUser(User user) throws SQLException;
	public boolean validateLogin(String email, String password) throws SQLException; 
	public int getUserId();
	public String getUserName();
	public void createNoteBook(NoteBook noteBook) throws SQLException;
	public List<NoteBook> getAllNoteBook(int user_id);
	public boolean deleteNoteBook(int id) throws SQLException;
	public NoteBook getNoteBook(int id) throws SQLException;
	public void insertNote(Note note) throws SQLException;
	public List< Note > showParticularNotebook(int id) throws SQLException;
	public int countOfNotes(int notebook_id) throws SQLException;
	public void renameNoteBook(int notebook_id, String noteBookName) throws SQLException;
	public void deleteNote(int note_id) throws SQLException;
	public Note showNote(int note_id) throws SQLException;
	public void updateNote(Note note) throws SQLException;
	public List<Note> showAllNotes(int user_id) throws SQLException;
	public List<Note> showDailyTasks(int user_id) throws SQLException;
	public User getUser(int user_id) throws SQLException;
	public boolean updateUser(User user) throws SQLException;
}
