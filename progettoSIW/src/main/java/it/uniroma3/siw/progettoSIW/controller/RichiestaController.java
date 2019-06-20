package it.uniroma3.siw.progettoSIW.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.progettoSIW.model.Foto;
import it.uniroma3.siw.progettoSIW.model.Richiesta;
import it.uniroma3.siw.progettoSIW.services.FotoService;
import it.uniroma3.siw.progettoSIW.services.RichiestaService;
import it.uniroma3.siw.progettoSIW.services.RichiestaValidator;

@Controller
public class RichiestaController {

	@Autowired
	private RichiestaService richiestaService;

	@Autowired
	private RichiestaValidator richiestaValidator;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private HttpSession session;



	@RequestMapping(value= {"/mostraCarrello", "gallery/mostraCarrello", "galleryAlbum/mostraCarrello","addFotoRichiesta/mostraCarrello", "deleteFotoRichiesta/mostraCarrello"}, method = RequestMethod.GET)
	public String mostraCarrello(Model model) {
		Richiesta richiesta= (Richiesta) session.getAttribute("richiesta");
		if (richiesta==null || richiesta.getListaFoto().size()==0)
			return "carrelloVuoto.html";
		else {
			model.addAttribute("fotografie",richiesta.getListaFoto());
			return "carrello.html";
		}	 
	}



	@RequestMapping(value= {"/svuotaCarrello","deleteFotoRichiesta/svuotaCarrello","addFotoRichiesta/svuotaCarrello"})
	public String svuotaCarrello() {
		session.invalidate();
		return "carrelloVuoto.html";
	}	



	// AGGIUNGO FOTO AL CARRELLO
	@RequestMapping(value = "/addFotoRichiesta/{id}", method = RequestMethod.GET)
	public String addFotoRichiesta(@PathVariable("id") Long id, Model model) {
		if(id==null) {
			return "error404.html";
		}
		else {
			Richiesta r= null;
			Foto foto= fotoService.fotoPerId(id);
			if (session.getAttribute("richiesta")==null){
				r= new Richiesta();
				session.setAttribute("richiesta", r);
				r.setListaFoto(new ArrayList<Foto>());
			}
			else {
				r=(Richiesta) session.getAttribute("richiesta");
			}

			r.getListaFoto().add(foto);
			model.addAttribute("fotografie", fotoService.tutti());
			return "gallery.html";
		}
	}

	// ELIMINO FOTO DAL CARRELLO
	@RequestMapping(value = "/deleteFotoRichiesta/{id}", method = RequestMethod.GET)
	public String deleteFotoRichiesta(@PathVariable("id") Long id, Model model) {
		if(id==null) {
			return "error404.html";
		}
		else {
			Richiesta richiesta= (Richiesta) session.getAttribute("richiesta");
			List<Foto> lista= richiesta.getListaFoto();
			Foto fotoDaEliminare=null;
			for (Foto f:lista)
				if (f.getId().equals(id))
					fotoDaEliminare=f;
			lista.remove(fotoDaEliminare);
			model.addAttribute("fotografie", richiesta.getListaFoto());
			if (richiesta.getListaFoto().size()==0)
				return "carrelloVuoto.html";
			else
				return "carrello.html";
		}
	}


	@RequestMapping(value = "/selezionaFoto/{id}", method = RequestMethod.GET)
	public String selezionaFoto(@PathVariable("id") Long id, Model model) {
		if(id==null) {
			return "error404.html";
		}
		else {
			boolean prenotato=false;
			Richiesta richiesta= (Richiesta) session.getAttribute("richiesta");
			if (richiesta!=null) {
				List<Foto> lista= richiesta.getListaFoto();
				for (Foto f:lista)
					if (f.getId().equals(id))
						prenotato=true;
			}

			model.addAttribute("fotografia", fotoService.fotoPerId(id));
			model.addAttribute("prenotato",prenotato);
			return "fotoSelezionata.html";
		}
	}



	@RequestMapping(value = "/richiesta/{id}", method = RequestMethod.GET)
	public String getRichiesta(@PathVariable("id") Long id, Model model) {
		if(id==null) {
			return "error404.html";
		}
		else {
			model.addAttribute("fotografie", this.richiestaService.richiestaPerId(id).getListaFoto());
			return "fotografie.html";
		}
	}



	@RequestMapping(value = "/richiesta", method = RequestMethod.POST)
	public String confermaRichiesta(@Valid @ModelAttribute("richiesta") Richiesta richiesta,
			Model model, BindingResult bindingResult) {

		this.richiestaValidator.validate(richiesta, bindingResult);
		if(!bindingResult.hasErrors()) {
			Richiesta r= (Richiesta) session.getAttribute("richiesta");
			richiesta.setListaFoto(r.getListaFoto());
			this.richiestaService.inserisci(richiesta);
			session.invalidate();
			model.addAttribute("fotografie",fotoService.primi());
			return "index.html";
		}else {
			return "richiestaForm.html";
		}
	}


	@RequestMapping(value= {"/inoltraRichiesta","addFotoRichiesta/inoltraRichiesta", "deleteFotoRichiesta/inoltraRichiesta" })
	public String inoltraRichiesta(Model model) {
		model.addAttribute("richiesta", session.getAttribute("richiesta"));
		return "richiestaForm.html";
	}

}
