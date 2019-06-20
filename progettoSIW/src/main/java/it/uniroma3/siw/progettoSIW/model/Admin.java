package it.uniroma3.siw.progettoSIW.model;

import javax.persistence.*;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String username;
	@Column(nullable=false)
	private String psw;

public Admin (String nome, String psw) {
	this.username=nome;
	this.psw=psw;
}

public Admin() {
	
}
	
	// Getters & Setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	



}
