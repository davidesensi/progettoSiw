package it.uniroma3.siw.progettoSIW.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.progettoSIW.services.FotoService;
import it.uniroma3.siw.progettoSIW.services.FotografoService;
import it.uniroma3.siw.progettoSIW.model.Fotografo;
import it.uniroma3.siw.progettoSIW.services.FotografoValidator;

@Controller
public class FotografoController{

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotografoValidator fotografoValidator;

	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo,
			Model model, BindingResult bindingResult) {

		this.fotografoValidator.validate(fotografo, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.fotografoService.inserisci(fotografo);
			model.addAttribute("fotografie", this.fotoService.primi());
			return "indexAdmin.html";
		}else {
			return "fotografoForm.html";
		}
	}

	@RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
			return "fotografo.html";
		}else {
			model.addAttribute("fotografi", this.fotografoService.tutti());
			return "fotografi.html";
		}
	}


	@RequestMapping("/lista")
	public String lista(Model model) {
		List<Fotografo> fotografi=fotografoService.tutti();
		model.addAttribute("fotografi", fotografi);
		return "fotografi.html";
	}

	@RequestMapping(value= {"gallery/galleryFotografi", "/galleryFotografi", "galleryAlbum/galleryFotografi",
			"addFotoRichiesta/galleryFotografi", "deleteFotoRichiesta/galleryFotografi"})
	public String galleryFotografi(Model model) {
		model.addAttribute("fotografi",fotografoService.tutti());
		return "galleryFotografi.html";
	}



}