package it.uniroma3.siw.progettoSIW.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progettoSIW.model.Album;


public interface AlbumRepository extends CrudRepository<Album, Long>{
	
	public List<Album> findByTitolo(String titolo);
}
