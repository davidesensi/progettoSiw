package it.uniroma3.siw.progettoSIW.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.progettoSIW.model.Richiesta;
import it.uniroma3.siw.progettoSIW.repository.RichiestaRepository;



@Service
public class RichiestaService {

	@Autowired
	private RichiestaRepository richiestaRepository;

	@Transactional 
	public Richiesta inserisci(Richiesta richiesta) {
		return richiestaRepository.save(richiesta);
	}


	public Richiesta richiestaPerId (Long id) {
		return this.richiestaRepository.findById(id).get();
	}

	@Transactional
	public List<Richiesta> tutti(){
		return (List<Richiesta>) richiestaRepository.findAll();
	}

}
