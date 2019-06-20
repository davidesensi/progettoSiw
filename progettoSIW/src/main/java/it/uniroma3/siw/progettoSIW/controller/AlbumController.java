package it.uniroma3.siw.progettoSIW.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.progettoSIW.model.*;
import it.uniroma3.siw.progettoSIW.services.*;


@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AlbumValidator albumValidator;

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotoService fotoService;


	@RequestMapping(value = "/album", method = RequestMethod.POST)
	public String newAlbum(@Valid @ModelAttribute("albumForm") AlbumForm albumForm, //Da controllare
			Model model, BindingResult bindingResult) {

		this.albumValidator.validate(albumForm, bindingResult);
		if(!bindingResult.hasErrors()) {
			Album album=new Album();
			album.setTitolo(albumForm.getTitolo());
			album.setFotografo(fotografoService.fotografoPerId(Long.parseLong(albumForm.getFotografoId())));
			this.albumService.inserisci(album);
			model.addAttribute("fotografie", this.fotoService.primi());
			return "indexAdmin.html"; 
		}else {
			model.addAttribute("fotografi", this.fotografoService.tutti());
			return "albumForm.html";
		}
	}


	@RequestMapping({"galleryAlbum/galleryAlbum","/galleryAlbum","gallery/galleryAlbum",
		"galleryFotografi/galleryAlbum", "addFotoRichiesta/galleryAlbum", "deleteFotoRichiesta/galleryAlbum"})
	public String galleryAlbum(Model model) {
		model.addAttribute("albums", albumService.tutti());
		return "galleryAlbum.html";
	}

	@RequestMapping(value={"/galleryAlbum/{id}","galleryAlbum/galleryAlbum/{id}", "galleryFotografy/galleryAlbum/{id}","gallery/galleryAlbum/{id}"},
			method = RequestMethod.GET)
	public String galleryAlbumFotografi(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Fotografo fotografo= this.fotografoService.fotografoPerId(id);
			model.addAttribute("albums", fotografo.getAlbum());
		}else {
			model.addAttribute("albums", this.albumService.tutti());
		}

		return "galleryAlbum.html";
	}






}

