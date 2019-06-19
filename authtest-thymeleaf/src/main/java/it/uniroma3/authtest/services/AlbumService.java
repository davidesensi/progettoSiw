package it.uniroma3.authtest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.storage.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album inserisci(Album fotografo) {
		return albumRepository.save(fotografo);
	}
	
	@Transactional
	public List<Album> tutti(){
		return (List<Album>) albumRepository.findAll();
	}
	
	public Album albumPerId(Long id) {
		return this.albumRepository.findById(id).get();
	}
}
