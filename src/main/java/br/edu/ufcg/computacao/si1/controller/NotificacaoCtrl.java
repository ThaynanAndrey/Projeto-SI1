package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;
import java.util.List;

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

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.forms.NotificacaoForm;
import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.NotificacaoServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;

@CrossOrigin(origins="*")
@RestController
public class NotificacaoCtrl {
	
	@Autowired
	private NotificacaoServiceImpl notificacaoService;
	
	@RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_NOTIFICACAO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notificacao> cadastrarNotificacaoUsuario(@RequestBody NotificacaoForm notificacaoForm) {

		Notificacao novaNotificacaoCadastrada = notificacaoService.create(notificacaoForm);
		
		return new ResponseEntity<>(novaNotificacaoCadastrada, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=Paths.PATH_APAGAR_NOTIFICACAO_DE_USUARIO)
	public ResponseEntity<Notificacao> removerNotificacao(@PathVariable Long id) {
		
		Notificacao notificacaoEncontrada = notificacaoService.getById(id).get();
		
		if(notificacaoEncontrada == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		notificacaoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_NOTIFICACOES, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Notificacao>> retornarTodasNotificacoes() {
		
    	Collection<Notificacao> listaDeNotifacoes = notificacaoService.getAll();
    	
		return new ResponseEntity<>(listaDeNotifacoes, HttpStatus.OK);
	}
    
	@RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_NOTIFICACOES_DE_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Notificacao>> retornarNotificacoesDoUsuarioLogado() {
		
    	List<Notificacao> listaDeNotifacoes = notificacaoService.notificacoesUsuarioLogado();
    	
		return new ResponseEntity<>(listaDeNotifacoes, HttpStatus.OK);
	}
}
