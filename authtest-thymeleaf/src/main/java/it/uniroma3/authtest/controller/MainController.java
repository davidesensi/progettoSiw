package it.uniroma3.authtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.JstlView;

import it.uniroma3.authtest.service.FotografiaService;

import java.net.URI;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * The MainController is a Spring Boot Controller to handle
 * the generic interactions with the home pages, and that do not refer to specific entities
 */
@Controller
public class MainController {

	
	private static final String BASE_PATH = "/images";
	
	private static final String FILENAME = "{filename:.+}";
	
	private FotografiaService service;
	
	@Autowired
    public MainController(FotografiaService service) {
        this.service = service;
    }

    
    /**
     * This method is called when a GET request is sent by the user to URL "/" or "/index".
     * This method prepares and dispatches the home view.
     *
     * @return the name of the home view
     */
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }
    
    
    /*
     * 
     * 
     * */
    
    @RequestMapping(value = BASE_PATH + "/" + FILENAME+"/raw", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> oneRawImage(@PathVariable String filename){
    	try {
			Resource file = service.findOneImage(filename);
			return ResponseEntity.ok()
					.contentLength(file.contentLength())
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(file.getInputStream()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(filename + " not found ==> " + e.getMessage());
		}
    }
    
    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH)
    public String createFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes){
    	try {
			service.createImage(file);
			attributes.addFlashAttribute("flash.message", "Successfully uploaded "+ file.getOriginalFilename());
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("flash.message", "Failed to upload "+ file.getOriginalFilename() + "==> " + e.getMessage());
		}
    	return "redirect:/admin.html";
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/welcome".
     * This method prepares and dispatches the welcome view.
     *
     * @return the name of the welcome view
     */
    @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "welcome";
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/admin".
     * This method prepares and dispatches the admin view.
     *
     * @return the name of the admin view
     */
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();    // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "admin";
    }
}