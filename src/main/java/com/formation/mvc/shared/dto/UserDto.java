package com.formation.mvc.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable{

	
	private static final long serialVersionUID = 5854291369074287770L;
	
	private long   id;
	private String userId;
	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encrypePasseword;
    private String emailVerificationToken;
    private Boolean emainVereficationStatus = false;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncrypePasseword() {
		return encrypePasseword;
	}
	public void setEncrypePasseword(String encrypePasseword) {
		this.encrypePasseword = encrypePasseword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmainVereficationStatus() {
		return emainVereficationStatus;
	}
	public void setEmainVereficationStatus(Boolean emainVereficationStatus) {
		this.emainVereficationStatus = emainVereficationStatus;
	}
    
   
	

}
