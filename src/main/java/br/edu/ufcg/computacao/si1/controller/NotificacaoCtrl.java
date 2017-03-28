package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;
import java.util.List;

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

import br.edu.ufcg.computacao.si1.model.forms.NotificacaoForm;
import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;
import br.edu.ufcg.computacao.si1.service.NotificacaoServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;

/**
 * Controller responsável por intermediar as notificações dos usuários.
 * 
 * @author Thaynan Andrey
 * @author Giuseppe Mongiovi
 *
 */
@CrossOrigin(origins="*")
@RestController
public class NotificacaoCtrl {
	
	@Autowired
	private NotificacaoServiceImpl notificacaoService;
	
	/**
	 * Método Post que cadastra no BD uma notificação
	 * @param NotificacaoForm notificacaoForm - Formulario com os dados da notificação
	 * @return Notificacao - Notificacao cadastrada
	 */
	@RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_NOTIFICACAO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notificacao> cadastrarNotificacaoUsuario(@RequestBody NotificacaoForm notificacaoForm) {

		Notificacao novaNotificacaoCadastrada = notificacaoService.criarNovaEntidade(notificacaoForm);

		return new ResponseEntity<>(novaNotificacaoCadastrada, HttpStatus.CREATED);
	}
	
	/**
	 * Método Delete que deleta a notificacao através do ID
	 * @param Long id - id da notificacao a ser deletada
	 * @return Resposta do servidor em relação ao processo de deleção
	 */
	@RequestMapping(method=RequestMethod.DELETE, value=Paths.PATH_APAGAR_NOTIFICACAO_DE_USUARIO)
	public ResponseEntity<Notificacao> removerNotificacao(@PathVariable Long id) {
		
		boolean deletouNotificacao = notificacaoService.deletarEntidade(id);
		
		if(!deletouNotificacao)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Método Get que obtem e retorna uma colecao de todas as notificacoes cadastradas.
	 * @return Collection<Notificacao> - Colecao de notificacoes
	 */
	@RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_NOTIFICACOES, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Notificacao>> retornarTodasNotificacoes() {
		
    	Collection<Notificacao> listaDeNotifacoes = notificacaoService.obterTodasEntidadesCadastradas();
    	
		return new ResponseEntity<>(listaDeNotifacoes, HttpStatus.OK);
	}
    
	/**
	 * Método Get que obtem e retorna uma colecao das notificacoes do usuario logado.
	 * @return Collection<Notificacao> - Colecao de notificacoes do usuario logado
	 */
	@RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_NOTIFICACOES_DE_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Notificacao>> retornarNotificacoesDoUsuarioLogado() {
		
    	List<Notificacao> listaDeNotifacoes = notificacaoService.notificacoesUsuarioLogado();
    	
		return new ResponseEntity<>(listaDeNotifacoes, HttpStatus.OK);
	}
}
