package it.uniroma3.siw.progettoSIW.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String titolo;
	
	@OneToMany (mappedBy="album")
	private List<Foto> foto;
	
	@ManyToOne
	private Fotografo fotografo;
	
	//Costruttori
	public Album (String titolo, Fotografo f) {
		this.titolo=titolo;
		this.fotografo=f;
		this.foto= new ArrayList<>();
	}
	
	public Album() {
		
	}
	
	
	
	// Getters & Setters
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	public Fotografo getFotografo() {
		return fotografo;
	}
	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

	

}
