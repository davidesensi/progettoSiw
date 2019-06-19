package it.uniroma3.authtest.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.storage.FotografiaRepository;

@Service
public class FotografiaService {

	private String UPLOAD_ROOT = "upload-dir";
	
	FotografiaRepository fotografiaRepository;
	
	ResourceLoader loader;

	@Autowired
	public FotografiaService(FotografiaRepository fotografiaRepository, ResourceLoader loader) {
		this.fotografiaRepository = fotografiaRepository;
		this.loader = loader;
	}
	
	public Page<Fotografia> findPage(Pageable pageable){
		return fotografiaRepository.findAll(pageable);
	}
	
	public Resource findOneImage(String fileName) {
		return loader.getResource("file:" + UPLOAD_ROOT+"/"+ fileName);
	}
	
	public void createImage(MultipartFile file) throws IOException {
		
		if(!file.isEmpty()) {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
			fotografiaRepository.save(new Fotografia(file.getOriginalFilename()));
		}
	}
	
	public void deleteImage(String filename) throws IOException {
		final Fotografia byName = fotografiaRepository.findByNomeFotografia(filename);
		fotografiaRepository.delete(byName);
		Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
	}
}
