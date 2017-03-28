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
 * @author Giuseppe Mongiovi
 *
 */
@CrossOrigin(origins="*")
@RestController
public class AnuncioCtrl {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    /**
     * Método Get que ao ser requisitado retorna uma coleção de todos os anúncios do banco de dados.
     * @return	Coleção de Anuncios
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_TODOS_ANUNCIOS, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnuncios() {
   		
   		Collection<Anuncio> listaDeAnuncios= anuncioService.obterTodasEntidadesCadastradas();
   		
   		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
   	}
    
    /**
     * Método Get que ao ser requisitado retorna uma coleção de anúncios que não são do usuário logado do banco de dados.
     * @return	Coleção de Anuncios
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_PARA_COMPRAR, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getTodosOsAnunciosParaComprar() {
   		
    	Collection<Anuncio> anunciosParaComprar = anuncioService.anunciosDisponiveisParaUsuarioComprar();    		
   		return new ResponseEntity<>(anunciosParaComprar, HttpStatus.OK);
   	}
    
    /**
     * Método Get que ao ser requisitado recebe o id de um anúncio e retorna o seu dono.
     * @param long id - id do anúncio.
     * @return	Usuario - Usuario dono do anúncio.
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_DONO_DO_ANUNCIO_POR_ID, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getDonoDoAnuncio(@PathVariable Long id) {
    	
    	Usuario dono = anuncioService.obterDonoDoAnuncio(id);
		
		if(dono == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(dono, HttpStatus.OK);
	}
    
    /**
     * Método Get que ao ser requisitado busca e retorna todos os anúncios pertencentes ao usuário logado.
     * @return List<Anuncio> -Lista de Anuncios do usuário
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_DE_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Anuncio>> getTodosOsAnunciosDoUsuarioLogado() { 
		
    	List<Anuncio> anunciosDoUsuarioLogado = anuncioService.todosAnunciosUsuarioLogado();
    	
		return new ResponseEntity<>(anunciosDoUsuarioLogado, HttpStatus.OK);
	}
    
    /**
     * Método Get que busca e retorna os tipos de anúncios que o usuário logado pode cadastrar.
     * @return List<String> - Lista de tipos de anúncios que o usuário logado pode cadastrar.
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_TIPOS_ANUNCIOS_USUARIO_LOGADO_PODE_CADASTRAR, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getTodosOsTiposDeAnunciosParaCadastro() {
		
    	List<String> tiposDeAnuncioParaCadastrar = anuncioService.tiposAnunciosParaCadastrarUsuarioLogado();
		
		return new ResponseEntity<>(tiposDeAnuncioParaCadastrar, HttpStatus.OK);
	}
    
    /**
     * Método Get que através busca e retorna um anúncio através do seu ID.
     * @param Long id - id do anúncio a ser buscado. 
     * @return Anuncio - Anuncio encontrado através do ID.
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_BUSCAR_ANUNCIO_POR_ID, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Anuncio> getAnuncioPorID(@PathVariable Long id) {
   		
   		Anuncio anuncio = anuncioService.obterEntidadePorId(id).get();
   		
   		if(anuncio == null)
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		
   		return new ResponseEntity<>(anuncio, HttpStatus.OK);
   	}
    
    /**
     * Método Post que cadastra um anúncio no banco de dados para o usuário.
     * @param AnuncioForm anuncioForm - Formulario com as informações do anuncio.
     * @return Anuncio - Anuncio cadastrado.
     */
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_ANUNCIO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anuncio> cadastrarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {

		Anuncio novoAnuncioCadastrado = anuncioService.criarNovaEntidade(anuncioForm);
		
		return new ResponseEntity<>(novoAnuncioCadastrado, HttpStatus.CREATED);
	}
    
    /**
     * Método Post que realiza compra de um anúncio pelo usuário logado.
     * @param AnuncioForm  anuncioForm - Formulario do anuncio a ser comprado
     */
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_USUARIO_COMPRA_ANUNCIO, consumes=MediaType.APPLICATION_JSON_VALUE)
   	public void comprarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {
    	
       	anuncioService.comprarAnuncio(anuncioForm);
   	}
    
    /**
     * Método Put que edita as informações de um anuncio
     * @param Anuncio anuncio - Anuncio editado.
     * @return Boolean - Boolean referente ao sucesso da operação de atualizar as informações.
     */
    @RequestMapping(method=RequestMethod.PUT, value=Paths.PATH_USUARIO_EDITAR_ANUNCIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> editarTarefa(@RequestBody Anuncio anuncio) {
		
		boolean anuncioEditado = anuncioService.atualizarEntidade(anuncio);
		
		if(!anuncioEditado)
			return new ResponseEntity<>(anuncioEditado, HttpStatus.NOT_MODIFIED);
		
		return new ResponseEntity<>(anuncioEditado, HttpStatus.OK);
	}
	
    /**
     * Méotodo Delete que deleta o anuncio através de um Id recebido.
     * @param Long id - id do anuncio a ser deletado
     * @return Resposta do servidor em relação ao sucesso da operação de deleção.
     */
	@RequestMapping(method=RequestMethod.DELETE, value=Paths.PATH_USUARIO_DELETAR_ANUNCIO_POR_ID)
	public ResponseEntity<Anuncio> removerTarefa(@PathVariable Long id) {
		
		boolean anuncioDeletado = anuncioService.deletarEntidade(id);
		
		if(!anuncioDeletado)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}   
    
 
}