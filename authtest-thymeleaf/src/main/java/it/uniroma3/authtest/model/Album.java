package it.uniroma3.authtest.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Albums")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long idAlbum;
	
	@Column
	private String nomeAlbum;
	
	@ManyToMany
	@Column
	private List<Fotografia> fotografie;
	
	@ManyToOne
	@JoinColumn(name="id_fotografo")
	private Fotografo fotografo;
	
	public Album(String nome) {
		this.nomeAlbum = nome;
		this.fotografie = new LinkedList<Fotografia>();
	}

	public List<Fotografia> getFotografie() {
		return fotografie;
	}

	public void setFotografie(List<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getNomeAlbum() {
		return nomeAlbum;
	}

	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}
	
	
	
	
}
