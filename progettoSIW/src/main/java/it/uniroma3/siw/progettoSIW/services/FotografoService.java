package it.uniroma3.siw.progettoSIW.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.progettoSIW.model.Fotografo;
import it.uniroma3.siw.progettoSIW.repository.FotografoRepository;

@Service
public class FotografoService {

	@Autowired 
	private FotografoRepository fotografoRepository;

	@Transactional 
	public Fotografo inserisci(Fotografo fotografo) {
		return fotografoRepository.save(fotografo);
	}

	@Transactional
	public List<Fotografo> tutti(){
		return (List<Fotografo>) fotografoRepository.findAll();
	}
	
	
	@Transactional 
	public void elimina(Long id) {
		 fotografoRepository.deleteById(id);
	}

	public Fotografo fotografoPerId(Long id) {
		return this.fotografoRepository.findById(id).get();
	}
}