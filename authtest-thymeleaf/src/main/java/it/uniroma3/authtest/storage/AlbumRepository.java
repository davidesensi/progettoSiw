package it.uniroma3.authtest.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.authtest.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
