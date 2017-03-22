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
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.utils.Utils;

@Service
public class NotificacaoServiceImpl implements IService<Notificacao,NotificacaoForm>{

	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
    public void setNotificacaoRepository(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }
	
	@Autowired
    private UsuarioServiceImpl usuarioService;
	
	@Override
	public Notificacao create(NotificacaoForm notificacaoForm) {
    	String titulo = notificacaoForm.getDescricao();
    	Usuario dono = notificacaoForm.getDono();
    	System.out.println("id dono: " + dono.getNome());
    	Long dataDeNotificacao = notificacaoForm.getDataDeNotificacao();
    	
		Notificacao novaNotificacao = new Notificacao(titulo, dono, dataDeNotificacao);
        /*aqui salvamos a notificacao recem criada no repositorio jpa*/
        return notificacaoRepository.save(novaNotificacao);
	}

	@Override
	public boolean update(Notificacao notificacao) {
		// Não necessário
		return false;
	}

	@Override
	public Optional<Notificacao> getById(Long id) {
		/*aqui recuperamos a notificacao pelo seu id*/
        return Optional.ofNullable(notificacaoRepository.findOne(id));
	}

	@Override
	public Collection<Notificacao> getAll() {
		/*aqui retornamos todas as notificacoes, sem distincao*/
		return notificacaoRepository.findAll();
	}

	@Override
	public boolean delete(Long id) {
		/*aqui se apaga a notificacao se ela existir*/
        if (notificacaoRepository.exists(id)) {
        	notificacaoRepository.delete(id);
            return true;
        }
        return false;
	}
	
	private Usuario getUsuarioLogado() {
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		Usuario usuario = usuarioLogado.get();
		
		return usuario;
    }
    
	 public List<Notificacao> notificacoesUsuarioLogado() {
		Usuario usuario = this.getUsuarioLogado();
		return usuario.getNotificacao();
    }
}
