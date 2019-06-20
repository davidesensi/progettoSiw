package it.uniroma3.siw.progettoSIW.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Fotografo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String cognome;
	@OneToMany (mappedBy = "fotografo") 
	private List<Album> album;

	//Costruttori
	public Fotografo(String nome,String cognome) {
		this.nome=nome;
		this.cognome=cognome;
		this.album= new ArrayList<>();
	}
	
	public Fotografo() {
		
	}

	//Getters & Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public List<Album> getAlbum() {
		return album;
	}
	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	


	}
