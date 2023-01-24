package com.formation.mvc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="users")
public class UserEntity implements Serializable{

	
	private static final long serialVersionUID = 6919423130687256863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false,length=120,unique=true)
	private String email;
	
	@Column(nullable=true)
	private Boolean admin= false;
	
	@Column(nullable = false)
	private String EncreptedPassword;
	
	
	@Column(nullable = true)
	private String emailVerficationToken;
	
	@Column(nullable =false)
    private boolean emainVereficationStatus = false;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getEncreptedPassword() {
		return EncreptedPassword;
	}

	public void setEncreptedPassword(String encreptedPassword) {
		EncreptedPassword = encreptedPassword;
	}

	public String getEmailVerficationToken() {
		return emailVerficationToken;
	}

	public void setEmailVerficationToken(String emailVerficationToken) {
		this.emailVerficationToken = emailVerficationToken;
	}

	public boolean isEmainVereficationStatus() {
		return emainVereficationStatus;
	}

	public void setEmainVereficationStatus(boolean emainVereficationStatus) {
		this.emainVereficationStatus = emainVereficationStatus;
	}

	

}
