package it.uniroma3.authtest.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Fotografia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long idFoto;
	
	private String nomeFotografia;
	
	@ManyToMany
	@Column
	private List<Album> albumDiRiferimento;

	@SuppressWarnings("unused")
	private Fotografia() {}

	public Fotografia(String nome) {
		this.nomeFotografia = nome;
	}
	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public List<Album> getAlbumDiRiferimento() {
		return albumDiRiferimento;
	}

	public void setAlbumDiRiferimento(List<Album> albumDiRiferimento) {
		this.albumDiRiferimento = albumDiRiferimento;
	}

	public String getNomeFotografia() {
		return nomeFotografia;
	}

	public void setNomeFotografia(String nomeFotografia) {
		this.nomeFotografia = nomeFotografia;
	}
	
	
	
	
}
