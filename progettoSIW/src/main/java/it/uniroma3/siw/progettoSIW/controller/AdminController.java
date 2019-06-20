package it.uniroma3.siw.progettoSIW.controller;

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

import it.uniroma3.siw.progettoSIW.model.Admin;
import it.uniroma3.siw.progettoSIW.model.Album;
import it.uniroma3.siw.progettoSIW.model.Fotografo;
import it.uniroma3.siw.progettoSIW.services.AdminService;
import it.uniroma3.siw.progettoSIW.services.AdminValidator;
import it.uniroma3.siw.progettoSIW.services.AlbumForm;
import it.uniroma3.siw.progettoSIW.services.AlbumService;
import it.uniroma3.siw.progettoSIW.services.FotoForm;
import it.uniroma3.siw.progettoSIW.services.FotoService;
import it.uniroma3.siw.progettoSIW.services.FotografoService;
import it.uniroma3.siw.progettoSIW.services.RichiestaService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminValidator adminValidator;

	@Autowired
	private HttpSession session;

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private RichiestaService richiestaService;


	@RequestMapping(value="/indexAdmin")
	public String indexAdmin (Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("fotografie", fotoService.primi());
			return "indexAdmin.html";
		}

	} 



	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String newAlbum(@Valid @ModelAttribute("admin") Admin admin, 
			Model model, BindingResult bindingResult) {
		this.adminValidator.validate(admin, bindingResult);
		if(!bindingResult.hasErrors()) {
			List<Admin> admins= adminService.tutti();
			for (Admin a:admins) {
				if (a.getUsername().equals(admin.getUsername()))  
					if (a.getPsw().equals(admin.getPsw()))
						session.setAttribute("admin", a);
			}				

			if (session.getAttribute("admin")!=null) {
				model.addAttribute("fotografie", fotoService.primi());
				return "indexAdmin.html";
			}

			else {
				model.addAttribute("frase", "Attenzione! Username o password errata.");
				model.addAttribute("admin", new Admin());
				return "adminForm.html";

			}
		}

		return "adminForm.html";
	}


	
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminForm.html";
	}

	

	@RequestMapping("/utente")
	public String utente(Model model) {
		session.invalidate();
		model.addAttribute("fotografie", fotoService.primi());
		return "index.html";
	}

	
	@RequestMapping("/ricerca")
	public String ricerca() {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else
			return "ricerca.html";
	}

	
	@RequestMapping("/ricercaFotografi")
	public String ricercaFotografi(Model model) { 
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("fotografi", fotografoService.tutti());
			return "fotografi.html";
		}
	}

	
	@RequestMapping("/ricercaAlbum")
	public String ricercaAlbum(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("albums", albumService.tutti());
			return "albums.html";
		}  
	}

	
	@RequestMapping("/ricercaFotografie")
	public String ricercaFotografie(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("fotografie", fotoService.tutti());
			return "fotografie.html";
		}
	} 


	//Id dell'album
	@RequestMapping(value= "/ricercaFotografie/{id}", method = RequestMethod.GET)
	public String ricercaFotos(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Album album= this.albumService.albumPerId(id);
			model.addAttribute("fotografie", album.getFoto());
		}else {
			model.addAttribute("fotografie", this.fotoService.tutti());
		}

		return "fotografie.html";
	}

	
	//Id del fotografo
	@RequestMapping(value=  "/ricercaAlbum/{id}", method = RequestMethod.GET)
	public String ricercaAlbums(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Fotografo fotografo= this.fotografoService.fotografoPerId(id);
			model.addAttribute("albums", fotografo.getAlbum());
		}else {
			model.addAttribute("albums", this.albumService.tutti());
		}

		return "albums.html";
	}

	
	@RequestMapping("/mostraRichieste")
	public String mostraRichieste(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("richieste",richiestaService.tutti()) ;
			return "richieste.html";
		}
	}

	
	@RequestMapping("/addFotografo")
	public String addFotografo(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("fotografo", new Fotografo());
			return "fotografoForm.html";
		}
	}

	
	@RequestMapping("/addAlbum")
	public String addAlbum(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("albumForm", new AlbumForm());
			model.addAttribute("fotografi", this.fotografoService.tutti());
			return "albumForm.html";
		}
	}


	@RequestMapping("/addFoto")
	public String addFoto(Model model) {
		if (session.getAttribute("admin")==null)
			return "error403.html";
		else {
			model.addAttribute("fotoForm", new FotoForm());
			model.addAttribute("albums", this.albumService.tutti());
			return "fotoForm.html";
		}
	}



}




