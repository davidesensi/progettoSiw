package it.uniroma3.siw.progettoSIW.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String titolo;
	@ManyToOne
	private Album album;
	@Column(nullable=false)
	private LocalDateTime dataCreazione;
	@Column(nullable=false)
	private String url;
	

//Costruttori	
public Foto (String titolo, Album album, String url) {
	this.dataCreazione= LocalDateTime.now();
	this.titolo=titolo;
	this.album=album;
	this.url=url;
}	
	
public Foto () {
	this.dataCreazione= LocalDateTime.now();
}


// Getters & Setters
public String getTitolo() {
	return titolo;
}


public void setTitolo(String titolo) {
	this.titolo = titolo;
}


public Album getAlbum() {
	return album;
}


public void setAlbum(Album album) {
	this.album = album;
}


public LocalDateTime getDataCreazione() {
	return dataCreazione;
}


public void setDataCreazione(LocalDateTime dataCreazione) {
	this.dataCreazione = dataCreazione;
}


public String getUrl() {
	return url;
}


public void setUrl(String url) {
	this.url = url;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}




}
