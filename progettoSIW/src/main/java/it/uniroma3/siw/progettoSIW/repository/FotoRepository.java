package it.uniroma3.siw.progettoSIW.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progettoSIW.model.Foto;



public interface FotoRepository extends CrudRepository<Foto, Long>{

	public List<Foto> findByTitolo(String titolo);
}
