package it.uniroma3.authtest.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fotografi")
public class Fotografo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(columnDefinition = "serial", nullable = false)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@Column
	@OneToMany(mappedBy = "fotografo")
	private List<Album> album;

	public Fotografo() {}
	
	public Fotografo(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		this.album = new LinkedList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	
}
