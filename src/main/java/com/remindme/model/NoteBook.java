package com.remindme.model;

public class NoteBook {
	private int id;
	private String noteBookName;
	private int user_id;
	private int value;
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NoteBook(String noteBookName, int user_id) {
		super();
		this.noteBookName = noteBookName;
		this.user_id = user_id;
	}
	
	public NoteBook(int id, String noteBookName, int user_id) {
		super();
		this.id = id;
		this.noteBookName = noteBookName;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNoteBookName() {
		return noteBookName;
	}
	public void setNoteBookName(String noteBookName) {
		this.noteBookName = noteBookName;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "NoteBook [id=" + id + ", noteBookName=" + noteBookName + ", user_id=" + user_id + "]";
	}
}
