package com.remindme.model;

import java.time.LocalDate;

public class Note {
	private int id;
	private LocalDate endDate;
	private String noteDescription;
	private String noteName;
	private LocalDate remainderDate;
	private LocalDate startDate;
	private int notebook_id;
	private String statusName;
	private String tagName;
	
	public Note(int id, LocalDate endDate, String noteDescription, String noteName, LocalDate remainderDate,
			LocalDate startDate, String statusName, String tagName) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.noteDescription = noteDescription;
		this.noteName = noteName;
		this.remainderDate = remainderDate;
		this.startDate = startDate;
		this.statusName = statusName;
		this.tagName = tagName;
	}
	public Note(int id, LocalDate endDate, String noteDescription, String noteName, LocalDate remainderDate,
			LocalDate startDate, int notebook_id, String statusName, String tagName) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.noteDescription = noteDescription;
		this.noteName = noteName;
		this.remainderDate = remainderDate;
		this.startDate = startDate;
		this.notebook_id = notebook_id;
		this.statusName = statusName;
		this.tagName = tagName;
	}
	public Note(LocalDate endDate, String noteDescription, String noteName, LocalDate remainderDate,
			LocalDate startDate, int notebook_id, String statusName, String tagName) {
		super();
		this.endDate = endDate;
		this.noteDescription = noteDescription;
		this.noteName = noteName;
		this.remainderDate = remainderDate;
		this.startDate = startDate;
		this.notebook_id = notebook_id;
		this.statusName = statusName;
		this.tagName = tagName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getNoteDescription() {
		return noteDescription;
	}
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public LocalDate getRemainderDate() {
		return remainderDate;
	}
	public void setRemainderDate(LocalDate remainderDate) {
		this.remainderDate = remainderDate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getNotebook_id() {
		return notebook_id;
	}
	public void setNotebook_id(int notebook_id) {
		this.notebook_id = notebook_id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", endDate=" + endDate + ", noteDescription=" + noteDescription + ", noteName="
				+ noteName + ", remainderDate=" + remainderDate + ", startDate=" + startDate + ", notebook_id="
				+ notebook_id + ", statusName=" + statusName + ", tagName=" + tagName + "]";
	}
	
}