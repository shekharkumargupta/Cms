package com.opgea.cms.domain.modal;

import java.util.Arrays;

public class MailModel {

	private String from;
	private String to;
	private String subject;
	private String message;
	private String[] filePath;
	
	public MailModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailModel(String from, String to, String subject, String message,
			String[] filePath) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.filePath = filePath;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getFilePath() {
		return filePath;
	}
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "MailModel [from=" + from + ", to=" + to + ", subject="
				+ subject + ", bodyText=" + message + ", filePath="
				+ Arrays.toString(filePath) + "]";
	}
}
