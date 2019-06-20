package it.uniroma3.siw.progettoSIW.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.progettoSIW.model.Album;
import it.uniroma3.siw.progettoSIW.repository.AlbumRepository;


@Service
public class AlbumService {
	
	@Autowired
	 private AlbumRepository albumRepository;
	
	@Transactional 
	public Album inserisci(Album album) {
		return albumRepository.save(album);
	}
	

	public Album albumPerId (Long id) {
		return this.albumRepository.findById(id).get();
	}
	
	@Transactional
	public List<Album> tutti(){
		return (List<Album>) albumRepository.findAll();
	}
	
	@Transactional 
	public void elimina(Long id) {
		 albumRepository.deleteById(id);
	}
}


