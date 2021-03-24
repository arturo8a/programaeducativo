package com.progeduc.model;

public class EmailBody {
		
	private String email;
	private String content;
	private String subject;
	
	public EmailBody() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "EmailBody [email1=" + email + ", content=" + content + ", subject=" + subject + "]";
	}
}
