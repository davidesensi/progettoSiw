package it.uniroma3.siw.progettoSIW.controller;

import java.time.LocalDateTime;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.progettoSIW.services.AlbumService;
import it.uniroma3.siw.progettoSIW.services.FotoForm;
import it.uniroma3.siw.progettoSIW.services.FotoService;
import it.uniroma3.siw.progettoSIW.model.Album;
import it.uniroma3.siw.progettoSIW.model.Foto;
import it.uniroma3.siw.progettoSIW.services.FotoValidator;

@Controller
public class FotoController{

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotoValidator fotoValidator;

	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/foto", method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("fotoForm") FotoForm fotoForm,
			Model model, BindingResult bindingResult) {

		this.fotoValidator.validate(fotoForm, bindingResult);
		if(!bindingResult.hasErrors()) {
			Foto foto= new Foto();
			foto.setTitolo(fotoForm.getTitolo());
			foto.setUrl(fotoForm.getUrl());
			foto.setDataCreazione(LocalDateTime.now());
			foto.setAlbum(albumService.albumPerId(Long.parseLong(fotoForm.getAlbumId())));

			this.fotoService.inserisci(foto);
			model.addAttribute("fotografie", this.fotoService.primi());
			return "indexAdmin.html";
		}else {
			model.addAttribute("albums", this.albumService.tutti());
			return "fotoForm.html";
		}
	}


	@RequestMapping("/")
	public String inzio(Model model) {
		model.addAttribute("fotografie", fotoService.primi());
		return "index.html";
	}

	@RequestMapping(value = {"/gallery","gallery/gallery","galleryAlbum/gallery","galleryFotografo/gallery","addFotoRichiesta/gallery", "deleteFotoRichiesta/gallery"})
	public String gallery(Model model) {
		model.addAttribute("fotografie", fotoService.tutti());
		return "gallery.html";

	}


	@RequestMapping(value={"/gallery/{id}","gallery/gallery/{id}", "galleryFotografy/gallery/{id}","galleryAlbum/gallery/{id}"},
			method = RequestMethod.GET)
	public String galleryFotoAlbum(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Album album= this.albumService.albumPerId(id);
			model.addAttribute("fotografie", album.getFoto());
		}else {
			model.addAttribute("fotografie", this.albumService.tutti());
		}

		return "gallery.html";
	}

	
	
	
	


}