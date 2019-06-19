package it.uniroma3.authtest.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.authtest.model.Fotografo;

public interface FotografoRepository extends JpaRepository<Fotografo, Long> {

}
