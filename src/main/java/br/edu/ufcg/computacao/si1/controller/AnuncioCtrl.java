package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.forms.AnuncioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Controller responsável por intermediar operações sobre os anúncios cadastrados
 * do usuário.
 * 
 * @author Thaynan Andrey
 *
 */
@CrossOrigin(origins="*")
@RestController
public class AnuncioCtrl {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_TODOS_ANUNCIOS, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnuncios() {
   		
   		Collection<Anuncio> listaDeAnuncios= anuncioService.obterTodasEntidadesCadastradas();
   		
   		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_PARA_COMPRAR, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnunciosParaComprar() {
   		
    	Collection<Anuncio> anunciosParaComprar = anuncioService.anunciosDisponiveisParaUsuarioComprar();    		
   		return new ResponseEntity<>(anunciosParaComprar, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_DONO_DO_ANUNCIO_POR_ID, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getDonoDoAnuncio(@PathVariable Long id) {
    	
    	Usuario dono = anuncioService.obterDonoDoAnuncio(id);
		
		if(dono == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(dono, HttpStatus.OK);
	}
    
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_DE_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Anuncio>> getTodosOsAnunciosDoUsuarioLogado() { 
		
    	List<Anuncio> anunciosDoUsuarioLogado = anuncioService.todosAnunciosUsuarioLogado();
    	
		return new ResponseEntity<>(anunciosDoUsuarioLogado, HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_TIPOS_ANUNCIOS_USUARIO_LOGADO_PODE_CADASTRAR, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getTodosOsTiposDeAnunciosParaCadastro() {
		
    	List<String> tiposDeAnuncioParaCadastrar = anuncioService.tiposAnunciosParaCadastrarUsuarioLogado();
		
		return new ResponseEntity<>(tiposDeAnuncioParaCadastrar, HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_BUSCAR_ANUNCIO_POR_ID, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Anuncio> getAnuncioPorID(@PathVariable Long id) {
   		
   		Anuncio anuncio = anuncioService.obterEntidadePorId(id).get();
   		
   		if(anuncio == null)
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		
   		return new ResponseEntity<>(anuncio, HttpStatus.OK);
   	}
    
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_ANUNCIO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anuncio> cadastrarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {

		Anuncio novoAnuncioCadastrado = anuncioService.criarNovaEntidade(anuncioForm);
		
		return new ResponseEntity<>(novoAnuncioCadastrado, HttpStatus.CREATED);
	}
    
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_USUARIO_COMPRA_ANUNCIO, consumes=MediaType.APPLICATION_JSON_VALUE)
   	public void comprarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {
    	
       	anuncioService.comprarAnuncio(anuncioForm);
   	}
    
    @RequestMapping(method=RequestMethod.PUT, value=Paths.PATH_USUARIO_EDITAR_ANUNCIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> editarTarefa(@RequestBody Anuncio anuncio) {
		
		boolean anuncioEditado = anuncioService.atualizarEntidade(anuncio);
		
		if(!anuncioEditado)
			return new ResponseEntity<>(anuncioEditado, HttpStatus.NOT_MODIFIED);
		
		return new ResponseEntity<>(anuncioEditado, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=Paths.PATH_USUARIO_DELETAR_ANUNCIO_POR_ID)
	public ResponseEntity<Anuncio> removerTarefa(@PathVariable Long id) {
		
		boolean anuncioDeletado = anuncioService.deletarEntidade(id);
		
		if(!anuncioDeletado)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}   
    
 
}