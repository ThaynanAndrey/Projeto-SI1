package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioFactory;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;
import br.edu.ufcg.computacao.si1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@Controller
@RestController
public class AnuncioCtrl {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_DE_USUARIO, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnuncios() {
   		
   		Collection<Anuncio> listaDeAnuncios= anuncioService.getAll();
   		
   		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/listar/anuncios/comprar", produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnunciosParaComprar() {
   		
   		Collection<Anuncio> listaDeAnuncios= anuncioService.getAll();
   		Collection<Anuncio> anunciosParaComprar = new ArrayList<Anuncio>();
   		
   		String email = Utils.userNameUsuarioLogado();    	
		Usuario usuarioLogado = usuarioService.getByEmail(email).get();
   		 		
   		for (Anuncio anuncio : listaDeAnuncios){
   			if(!anuncio.pegueDono().equals(usuarioLogado)){
   				anunciosParaComprar.add(anuncio);
   			}
   		}	
   		
   		return new ResponseEntity<>(anunciosParaComprar, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/dono/anuncio/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getDonoDoAnuncio(@PathVariable Long id) {
		
		Anuncio anuncio = anuncioService.getById(id).get();
		
		if(anuncio == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(anuncio.pegueDono(), HttpStatus.OK);
	}
    
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/logado/anuncios", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Anuncio>> getTodosOsAnunciosDoUsuarioLogado() {
		
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		Usuario usuario = usuarioLogado.get(); 
		
		return new ResponseEntity<>(usuario.getAnuncios(), HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/anuncios/tipos/cadastrar", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String[]> getTodosOsTiposDeAnunciosParaCadastro() {
		
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		Usuario usuario = usuarioLogado.get(); 
		
		return new ResponseEntity<>(usuario.getTiposDeAnunciosDisponiveis(), HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/anuncio/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Anuncio> getAnuncioPorID(@PathVariable Long id) {
   		
   		Anuncio anuncio = anuncioService.getById(id).get();
   		
   		if(anuncio == null)
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		
   		return new ResponseEntity<>(anuncio, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_ANUNCIO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anuncio> cadastrarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {

    	String email = Utils.userNameUsuarioLogado();
    	Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
    	Usuario usuario = usuarioLogado.get(); 
    	
    	anuncioForm.setDono(usuario);
		Anuncio novoAnuncioCadastrado = anuncioService.create(anuncioForm);
		
		return new ResponseEntity<>(novoAnuncioCadastrado, HttpStatus.CREATED);
	}
    
    @RequestMapping(method=RequestMethod.POST, value="usuario/comprar/anuncio", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
   	public void comprarAnuncioUsuario(@RequestBody Anuncio anuncio) {

       	String email = Utils.userNameUsuarioLogado();
       	Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
       	Usuario comprador = usuarioLogado.get(); 
       	Usuario vendedor = anuncio.pegueDono();
       	
       	vendedor.venderAnuncio(anuncio);
       	comprador.comprarAnuncio(anuncio);
       	
   		anuncioService.delete(anuncio.get_id());
   	}
    
    @RequestMapping(method=RequestMethod.PUT, value="/usuario/editar/anuncio", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean editarTarefa(@RequestBody Anuncio anuncio) {
		
		boolean anuncioEditado = anuncioService.update(anuncio);
		
		return anuncioEditado;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/usuario/deletar/anuncio/{id}")
	public ResponseEntity<Anuncio> removerTarefa(@PathVariable Long id) {
		
		Anuncio anuncioEncontrado = anuncioService.getById(id).get();
		
		if(anuncioEncontrado == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		anuncioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}   
    
 
}