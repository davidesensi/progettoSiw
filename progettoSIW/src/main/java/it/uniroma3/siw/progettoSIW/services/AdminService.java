package it.uniroma3.siw.progettoSIW.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progettoSIW.model.Admin;
import it.uniroma3.siw.progettoSIW.repository.AdminRepository;



@Service
public class AdminService {
	
	@Autowired
	 private AdminRepository adminRepository;
	
	@Transactional 
	public Admin inserisci(Admin admin) {
		return adminRepository.save(admin);
	}
	

	public Admin adminPerId (Long id) {
		return this.adminRepository.findById(id).get();
	}
	
	@Transactional
	public List<Admin> tutti(){
		return (List<Admin>) adminRepository.findAll();
	}
	
}
