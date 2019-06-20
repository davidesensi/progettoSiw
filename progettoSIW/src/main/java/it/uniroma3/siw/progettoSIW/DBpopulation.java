package it.uniroma3.siw.progettoSIW;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.progettoSIW.model.Admin;
import it.uniroma3.siw.progettoSIW.model.Album;
import it.uniroma3.siw.progettoSIW.model.Foto;
import it.uniroma3.siw.progettoSIW.model.Fotografo;
import it.uniroma3.siw.progettoSIW.model.Richiesta;
import it.uniroma3.siw.progettoSIW.repository.*;



@Component
public class DBpopulation implements ApplicationRunner{
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RichiestaRepository richiestaRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		this.addAll();
	}
	
	private void addAll() {
		
		System.out.print("Popolazione DB con fotografi, album e foto");
		
		
		
		
		String u1 = "https://www.collater.al/wp-content/uploads/2017/11/Gli-incredibili-paesaggi-aerei-del-fotografo-Niaz-Uddin-Collater.al-6.jpg";
		String u2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0vMPqicG-V4pZ5fePQ2HF6Qb9uE4P5YP9jutsvHR0Cc2sw3Gc";
		String u3 = "https://wordpress-network.prod.aws.skyscnr.com/wp-content/uploads/2018/05/GettyImages-487530908.jpg?w=1000&h=312&crop=1";
		String u4 = "https://www.ilgazzettino.it/photos/HIGH/47/36/3334736_1057_tramonto.jpg";
		String u5 = "https://www.skyscanner.it/wp-content/uploads/2018/05/GettyImages-483567736.jpg?fit=1048,638";
		String u6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6M8EoAvP3PKeDc0KoDWoKweAYUD58W8Jn7efpIgIgr7T5KzMVcg";
		String u7 = "https://www.skyscanner.it/wp-content/uploads/2018/05/GettyImages-471016064.jpg?fit=1048,699";
		String u8 = "https://cdn.pixabay.com/photo/2017/06/14/16/10/mountainous-landscape-2402590_960_720.jpg";
		String u9 = "https://static2-viaggi.corriereobjects.it/wp-content/uploads/2017/08/braies-strillo.jpg";
		String u10 = "http://thumb.spreafotografia.it/generate.php?src=http://www.spreafotografia.it/photos/tramonto_dal_rifugio_in_montagna_1252534366.jpg&w=825&q=100";
		
		Fotografo f1 = new Fotografo("Paolo","Raeli");
		this.fotografoRepository.save(f1);
		
		Fotografo f2 = new Fotografo("Steve","McCurry");
		this.fotografoRepository.save(f2);
		
		Album a1 = new Album("Paesaggi", f2);
		Album a2 = new Album("Tramonti", f1);
		
		f1.getAlbum().add(a2);
		f2.getAlbum().add(a1);
		
		Foto foto1 = new Foto("Earth Rotation", a1, u2);
		Foto foto2 = new Foto("Tramonto", a2, u1);
		Foto foto3 = new Foto("Mongolfiera", a1, u3);
		Foto foto4 = new Foto("Tramonto con Barche", a2, u4);
		Foto foto5 = new Foto("Barche e Isole", a1, u5);
		Foto foto6 = new Foto("Tramonto in Puglia", a2, u6);
		Foto foto7 = new Foto("Grand Canyon", a1, u7);
		Foto foto8 = new Foto("Tramonto sulle Dolomiti", a2, u8);
		Foto foto9 = new Foto("Lago di Braies", a1, u9);
		Foto foto10 = new Foto("Tramonto con Neve", a2, u10);
		
		
		a1.getFoto().add(foto1);
		a1.getFoto().add(foto3);
		a1.getFoto().add(foto5);
		a1.getFoto().add(foto7);
		a1.getFoto().add(foto9);
		a2.getFoto().add(foto2);
		a2.getFoto().add(foto4);
		a2.getFoto().add(foto6);
		a2.getFoto().add(foto8);
		a2.getFoto().add(foto10);
		
		this.albumRepository.save(a1);
		this.albumRepository.save(a2);
		
		this.fotoRepository.save(foto1);
		this.fotoRepository.save(foto2);
		this.fotoRepository.save(foto3);
		this.fotoRepository.save(foto4);
		this.fotoRepository.save(foto5);
		this.fotoRepository.save(foto6);
		this.fotoRepository.save(foto7);
		this.fotoRepository.save(foto8);
		this.fotoRepository.save(foto9);
		this.fotoRepository.save(foto10);
		
		
		Admin admin = new Admin("admin", "admin");
		this.adminRepository.save(admin);
		
		
		Richiesta r1 = new Richiesta();
		List<Foto> listaFoto = new ArrayList<Foto>();
		listaFoto.add(foto1);
		r1.setListaFoto(listaFoto);
		r1.setEmail("haydos_pedersen@silph.com");
		
		richiestaRepository.save(r1);
		
	}
	

}
