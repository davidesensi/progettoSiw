package it.uniroma3.authtest.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.authtest.model.Fotografia;

public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {

	public Fotografia findByNomeFotografia(String nomeFotografia);
}
