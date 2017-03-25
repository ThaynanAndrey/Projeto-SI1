package br.edu.ufcg.computacao.si1.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.si1.model.forms.NotificacaoForm;
import br.edu.ufcg.computacao.si1.model.notificacao.Notificacao;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.NotificacaoRepository;

/**
 * Serviço responsável por gerenciar as operações sobre as entidades notificações que
 * estão ou serão armazenadas no banco de dados.
 * 
 * @author Thaynan Andrey
 * 
 */
@Service
public class NotificacaoServiceImpl implements IService<Notificacao,NotificacaoForm>{

	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
    private UsuarioServiceImpl usuarioService;
	
	@Autowired
    public void setNotificacaoRepository(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }
	
	@Override
	public Notificacao criarNovaEntidade(NotificacaoForm notificacaoForm) {
    	String titulo = notificacaoForm.getDescricao();
    	Usuario dono = notificacaoForm.getDono();
    	Long dataDeNotificacao = notificacaoForm.getDataDeNotificacao();
    	
		Notificacao novaNotificacao = new Notificacao(titulo, dono, dataDeNotificacao);
        /*aqui salvamos a notificacao recem criada no repositorio jpa*/
        return notificacaoRepository.save(novaNotificacao);
	}

	@Override
	public boolean atualizarEntidade(Notificacao notificacao) {
		
		boolean existeNotificao = notificacaoRepository.exists(notificacao.get_id());
		
		if(existeNotificao)
			notificacaoRepository.save(notificacao);
		
		return existeNotificao;
	}

	@Override
	public Optional<Notificacao> obterEntidadePorId(Long id) {
		/*aqui recuperamos a notificacao pelo seu id*/
        return Optional.ofNullable(notificacaoRepository.findOne(id));
	}

	@Override
	public Collection<Notificacao> obterTodasEntidadesCadastradas() {
		/*aqui retornamos todas as notificacoes, sem distincao*/
		return notificacaoRepository.findAll();
	}

	@Override
	public boolean deletarEntidade(Long id) {
		/*aqui se apaga a notificacao se ela existir*/
        boolean existeNotificacao = notificacaoRepository.exists(id);
		
        if (existeNotificacao)
        	notificacaoRepository.delete(id);
        
        return existeNotificacao;
	}
    
	/**
	 * metodo que retorna uma lista das notificacoes do usuario logado
	 * @return List<Notificacao> - Lista de notificacoes do usuario logado
	 */
	 public List<Notificacao> notificacoesUsuarioLogado() {
		Usuario usuario = usuarioService.getUsuarioLogado();
		return usuario.getNotificacao();
    }
}